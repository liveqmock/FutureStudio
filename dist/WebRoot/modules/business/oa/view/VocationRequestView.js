define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    "fputil",
    'modules/base/collection/PriorityCollection',
    './VocationHistoryListView',
    'text!../tpl/VocationRequest.html',
    'jquery.cookie',
    'bootstrap'
],function($, _, Backbone, core, fputil, PriorityCollection, VocationHistoryListView, tpl){
	"use strict";
	/**
	 * 申请请假
	 */
	return Backbone.View.extend({
		tagName:"div",
		className:"business-vocation-request",
		template:_.template(tpl),
		events:{
			"click .submit-button":"submit",
            "click .cancel-button":"unRender"
		},
		initialize:function(){
            var that=this;
			_.bindAll(this,"submit","unRender");
            this.eb= _.extend({},Backbone.Events);
            this.history=new VocationHistoryListView({
                eb:this.eb
            }).render();
            this.typeCollection=new PriorityCollection();//
            this.typeCollection.url="oaVocation_findVocationType.action";
            this.typeCollection.parse=function(response){
                return response.vocationTypes;
            };
            this.typeCollection.on("reset",function(){
                that.loadVocationType();
            });
		},
        loadVocationType:function(){
            var that=this;
            this.typeCollection.each(function(m){
                that.$(".vocation-type").append('<option value="'+ m.get("id")+'">'+ m.get("name")+'</option>');
            });
        },
        //TODO
		submit:function(){
            var that=this;
            var data={
                    vocation:{
                        vocationTypeId:this.$(".vocation-type").val(),
                        vocationStart:fputil.stringToDate(this.$(".vocationStart").val()+":00"),
                        vocationEnd:fputil.stringToDate(this.$(".vocationEnd").val()+":00"),
                        vocationTypeName:this.$(".vocation-type").find("option:selected").text(),
                        hours:parseInt(this.$(".vocation-hour").val(),10),
                        title:this.$(".title").val(),
                        content:this.$(".content").val()
                    },
                    titleInput:this.$(".title").val()
                };
			$.ajax({
				url:"oaVocation_addVocation.action",
				type:"POST",
                contentType:"application/json; charset=utf-8",
				data:JSON.stringify(data),
				success:function(response){
                    core.trigger("Message:Info","成功",response.message);
                    that.unRender();
				},
				error:function(response){
                    var res={};
                    try{
                        res=jQuery.parseJSON(response.responseText);
                    }catch(e){
                        res.message="未知错误";
                    }
                    core.trigger("Message:Error","出错",res.message);
				}
			});
		},
		render:function(){
            this.$el.html(this.template());
            this.$(".history-vocation-info").html(this.history.el);
            this.$(".form_datetime").datetimepicker({
                format: "yyyy-mm-dd hh:ii",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom-left",
                language:"zh-CN"
            });
            this.history.fetch({
                id: $.cookie("ci"),//userid
                page:1,
                pageSize:20
            });
            this.typeCollection.fetch();
            return this;
		},
        unRender:function(){
            core.trigger("Tab:Close",this);
            this.eb.off();
            this.remove();
        }
	});
});

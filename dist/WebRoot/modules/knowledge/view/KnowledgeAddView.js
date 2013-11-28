define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'fputil',
    'text!../tpl/KnowledgeAdd.html',
    'bootstrap'
], function ($, _, Backbone, core, fputil, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        template: _.template(tpl),
        events:{
            "click .button-save":"save"
        },
        initialize: function (options) {
            this.knowledgeType = options.knowledgeType;
            this.eb = options.eb;
        },
        save:function(){
            var data={
                knowledge:{
                    title:this.$(".title-input").val(),
                    date:fputil.stringToDate(this.$(".date-input").val()+" 12:00:00"),
                    content:"",
                    comments:this.$(".comments-input").val(),
                    keyWords:this.$(".keyWords-input").val(),//采用逗号分割
                    knowledgeTypeId:this.knowledgeType.get("id"),
                    knowledgeTypeName:this.knowledgeType.get("name"),
                    userId:"",
                    userName:"",
                    version:1,
                    readTime:0,//初始是0
                    relatedId:"0",
                    relatedName:"0",
                    statusCode:parseInt(this.$(".state-select").val(),10),
                    statusName:this.$(".state-select :selected").text()
                }
            },
                that=this;
            $.ajax({
                url:"knowledgeManage_addKnowledge.action",
                dataType:"json",
                type:"post",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success:function(response){
                    core.trigger("Message:Info","成功",response.message);
                    that.eb.trigger("Knowledge:Add:Over");
                },
                error:function(response){
                    core.trigger("Message:Error","失败",jQuery.parseJSON(response.responseText).message);
                }
            });
        },
        render: function () {
            var current=new Date(),
                sdate=fputil.dateToString(current).substr(0,10);
            this.$el.html(this.template({
                datepickerValue:sdate
            }));
            this.$(".datepicker-add").datepicker({
                format: "yyyy-mm-dd hh:ii",
                autoclose: true,
                todayBtn: true,
                pickerPosition: "bottom-left",
                language:"zh-CN"
            });
            return this;
        },
        unRender: function () {
            this.undelegateEvents();
            this.remove();
        }
    });
});
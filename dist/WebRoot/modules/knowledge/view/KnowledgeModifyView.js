define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'fputil',
    'text!../tpl/KnowledgeModify.html'
], function ($, _, Backbone, core, fputil, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        template: _.template(tpl),
        events:{
            "click .button-save":"save",
            "click .button-cancel":"cancel"
        },
        initialize: function (options) {
            this.eb = options.eb;
        },
        cancel:function(){
            this.eb.trigger("Knowledge:Modify:Cancel");
        },
        save:function(){
            var data={
                knowledge:{
                    id:this.model.get("id"),
                    title:this.$(".title-input").val(),
                    date:fputil.stringToDate(this.$(".date-input").val()+":00"),
                    content:"",
                    comments:this.$(".comments-input").val(),
                    keyWords:this.$(".keyWords-input").val(),//采用逗号分割
                    knowledgeTypeId:this.model.get("knowledgeTypeId"),
                    knowledgeTypeName:this.model.get("knowledgeTypeName"),
                    userId:"",
                    userName:"",
                    version:this.model.get("version"),
                    readTime:0,//初始是0
                    relatedId:"0",
                    relatedName:"0",
                    statusCode:parseInt(this.$(".state-select").val(),10),
                    statusName:this.$(".state-select :selected").text()
                }
            },
                that=this;
            $.ajax({
                url:"knowledgeManage_modifyKnowledge.action",
                dataType:"json",
                type:"post",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success:function(response){
                    core.trigger("Message:Info","成功",response.message);
                    that.eb.trigger("Knowledge:Modify:Over");
                    that.model.set("title",data.knowledge.title);
                    that.model.set("date",fputil.dateToString(data.knowledge.date).substring(0,10));

                    that.model.trigger("refresh");
                },
                error:function(response){
                    core.trigger("Message:Error","失败",jQuery.parseJSON(response.responseText).message);
                }
            });
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            this.$(".form_datetime").datetimepicker({
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
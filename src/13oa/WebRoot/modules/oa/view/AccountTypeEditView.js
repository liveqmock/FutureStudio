/**
 * User: Tony  Date: 13-4-15 Time: 下午10:45
 * Future Studio
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/base/model/BaseModel',
    'text!../tpl/account-type-edit.html'
], function ($, _, Backbone, core, Model, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        template:_.template(tpl),
        events:{
            "click .botton-save":"save",
            "click .botton-cancel":"cancel"
        },
        initialize: function (options) {
            this.isNew = options.isNew;
            this.priority = options.priority;
            this.eb = options.eb;

            if(this.isNew){
                 this.model=new Model({
                    name:"",
                    comments:""
                 });
            }
        },
        render: function () {
            var data=this.model.toJSON(),
                that = this;
            if(this.isNew){
                data.title="新增用户类型";
            }else{
                data.title="修改用户类型";
            }
            this.$el.html(this.template(data));
            $("body").append(this.el);
            this.$(".modal").modal({
                backdrop:"static",
                show:true
            });
            this.$('.modal').on('hidden', function () {
                that.unRender();
            });
            return this;
        },
        save:function(){
            var that=this,
                data={
                    name:this.$(".class-name").val(),
                    comments:this.$(".class-comments").val()
                };
            if(this.isNew){
                data.priority=this.priority;
                $.ajax({
                    "url":"oaAccount_addAccountType.action",
                    "type":"post",
                    data:data,
                    succuss:function(response){
                        core.trigger("Message:Info","成功",response.message);
                        that.unRender();
                        that.eb.trigger("AccountType:Refresh");
                    },
                    error:function(response){
                        core.trigger("Message:Error","出错",response.responseText);
                    }
                });
            }else{
                data.id=this.model.get("id");
                data.priority=this.model.get("priority");
                $.ajax({
                    url:"oaAccount_updateAccountType.action",
                    type:"post",
                    data:data,
                    succuss:function(response){
                        core.trigger("Message:Info","成功",response.message);
                        that.unRender();
                    },
                    error:function(response){
                        core.trigger("Message:Error","出错",response.responseText);
                    }
                });
            }
        },
        cancel:function(){
            this.$(".modal").modal("hide");
        },
        unRender: function () {
            this.undelegateEvents();
            this.remove();
        }
    });
});
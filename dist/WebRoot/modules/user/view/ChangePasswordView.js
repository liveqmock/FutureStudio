define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/ChangePassword.html"
], function ($, _, Backbone,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"change-password",
        template: _.template(tpl),
        events:{
            "input":"validateInput",
            "click .button-save":"savePassword",
            "click .button-cancel":"cancelChange"
        },
        initialize: function () {
            _.bindAll(this,"validateInput","savePassword","cancelChange");
        },
        render: function () {
            this.$el.html(this.template());
            return this;
        },
        validateInput:function(){
            var that=this;
            _.each(this.$(".input"),function(ip){
                if($(ip).val()===""){
                    that.$(".button-save").addClass("disabled");
                    that.$(".info").removeClass("hide");
                    that.$(".info").html("密码输入框不能为空");
                    return false;
                }
            });
            if(this.$(".new-password-a").val()!==this.$(".new-password-b").val()){
                that.$(".button-save").addClass("disabled");
                that.$(".info").removeClass("hide");
                that.$(".info").html("两次输入密码不匹配");
                return false;
            }
            that.$(".info").addClass("hide");
            that.$(".button-save").remove("disabled");
        },
        savePassword:function(){
            var that=this;
            if(this.$(".button-save").hasClass("disabled")){
                return false;
            }
            $.ajax({
                url:"userPassword_changePassword.action",
                data:{
                    password:this.$(".old-password").val(),
                    newPassword:this.$(".new-password-a").val()
                },
                success:function(res){
                    that.$(".info").html(res.message);
                },
                error:function(res){
                    that.$(".info").html(res.responseText);
                }
            });
        },
        cancelChange:function(){
            this.unRender();
        },
        unRender:function(){
            this.remove();
            this.trigger("removed");
        }
    });
});
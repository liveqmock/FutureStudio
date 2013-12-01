/** User:Tony   Date: 3/19/13 - 8:11 PM */
define([
    'jquery',
    'underscore',
    'backbone',
    'modules/message/index'
],function($, _, Backbone, MessageView){
    "use strict";
    return Backbone.View.extend({
        tagName:"button",
        className:"btn btn-default",
        events:{
            "click":"click"
        },
        initialize:function(options){
            this.eb = options.eb;
            this.hasRole = options.hasRole;
            this.userId = options.userId;

            this.listenTo(this.eb,"ModifyRoleItem:Destroy",function(){
                this.unRender();
            });
        },
        render:function(){
            this.$el.html(this.model.get("name"));
            if(this.hasRole){
                this.$el.addClass("btn-primary");
            }
            return this;
        },
        click:function(){
            var that=this;
            if(this.hasRole){
                //delete role
                $.ajax({
                    url:"/fs/oa/roleManage_removeUserRole.action",
                    dataType:"json",
                    type:"post",
                    data:{
                        roleId:this.model.get("id"),
                        userId:this.userId
                    },
                    success:function(response){
                        that.$el.removeClass("btn-primary");
                        MessageView.success({
                            body:response.message
                        });
                    },
                    error:function(res){
                        var t = JSON.parse(res.responseText);
                        MessageView.error({
                            body: t.message
                        });
                    }
                });
            }else{
                //add role
                $.ajax({
                    url:"/fs/oa/roleManage_addUserRole.action",
                    dataType:"json",
                    type:"post",
                    data:{
                        roleId:this.model.get("id"),
                        userId:this.userId
                    },
                    success:function(response){
                        that.$el.addClass("btn-primary");
                        MessageView.success({
                            body:response.message
                        });
                    },
                    error:function(res){
                        var t = JSON.parse(res.responseText);
                        MessageView.error({
                                body: t.message
                            });
                    }
                });
            }
        },
        unRender:function(){
            this.remove();
        }
    });
});
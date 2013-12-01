/** User:Tony   Date: 3/19/13 - 7:19 PM */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/message/index',
    './Role/RoleEditView',
    './Role/RoleListView',
    './Role/RoleRightView',
    'text!modules/base/tpl/fluid48.html'
],function($, _, Backbone, core, MessageView,
           RoleEditView, RoleListView, RoleRightView,
           fluid48){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"oa-role-manage",
        template:_.template(fluid48),
        initialize:function(){
            var that = this;
            this.$el.append(this.template());
            this.eb = _.extend({},Backbone.Events);
            this.eb.on("Role:Add",function(){
                that.roleAdd();
            },this);
            this.eb.on("Role:Edit",function(role){
                that.roleEdit(role);
            },this);
            this.eb.on("Role:Click",function(model){
                that.showRight(model);
            },this);
            this.right=null;
            this.eb.on("Role:Operation:Remove",that.removeOperation,this);
            this.eb.on("Role:Operation:Add",that.addOperation,this);
            this.eb.on("Role:Function:Remove",that.removeFunction,this);
            this.eb.on("Role:Function:add",that.addFunction,this);
        },
        roleEdit:function(model){
            new RoleEditView({
                eb:this.eb,
                isNew:false,
                model:model
            }).render();
        },
        roleAdd:function(){
            new RoleEditView({
                eb:this.eb,
                isNew:true
            }).render();
        },
        /**
         * 进行ajax请求的参数：
         * @param options
         * {
         *     url,
         *     data,
         * }
         */
        updatePrivilege:function(options){
            $.ajax({
                url:options.url,
                type:"post",
                data:options.data,
                beforeSend:function(){

                },
                success:function(){
                    MessageView.success({
                        body:"操作成功"
                    });
                },
                error:function(){
                    MessageView.error({
                        body:"操作失败"
                    });
                },
                complete:function(){

                }
            });
        },
        removeFunction:function(func){
            if(this.currentRole){
                this.updatePrivilege({
                    url:'oaRole_updateFunction.action',
                    data:{
                        id1:this.currentRole.get("id"),
                        id2:func.get("id"),
                        data1:"0"
                    }
                });
            }
        },
        addFunction:function(func){
            if(this.currentRole){
                this.updatePrivilege({
                    url:'oaRole_updateFunction.action',
                    data:{
                        id1:this.currentRole.get("id"),
                        id2:func.get("id"),
                        data1:"1"
                    }
                });
            }
        },
        removeOperation:function(oper){
            if(this.currentRole){
                this.updatePrivilege({
                    url:'oaRole_updateOperation.action',
                    data:{
                        id1:this.currentRole.get("id"),
                        id2:oper.get("id"),
                        data1:"0"
                    }
                });
            }
        },
        addOperation:function(oper){
            if(this.currentRole){
                this.updatePrivilege({
                    url:'oaRole_updateOperation.action',
                    data:{
                        id1:this.currentRole.get("id"),
                        id2:oper.get("id"),
                        data1:"1"
                    }
                });
            }
        },
        showRight:function(model){
            this.currentRole=model;
            this.right.loadRole(model);
        },
        render:function(){
            var roles = new RoleListView({eb:this.eb}).render();
            this.$(".span4").append(roles.el);
            this.right=new RoleRightView({eb:this.eb});
            this.right.render();
            this.$(".span8").append(this.right.el);
            return this;
        }
    });
});
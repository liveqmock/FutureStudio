//组织管理视图
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/userdetail.html',
    'privilege',
    'modules/message/index',
    'modules/base/collection/PriorityCollection',
    '../Role/RoleListCheckItemView'
],function($, _, Backbone, core, tdTpl, privilege, MessageView,PriorityCollection, RoleListCheckItemView ){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"user-detail",
        template:_.template(tdTpl,null,{variable:"args"}),
        events:{
            "click .edit-user":"editUser",
            "click .account-image":"changeImage",
            "click .modify-user-role":"changeRole"
        },
        initialize:function(options){
            this.eb = options.eb;
            var that=this;
            this.eb.on("User:Refresh:Middle",function(){
                that.undelegateEvents();
                that.eb.off(null,null,that);
                that.remove();
            },this);
            this.roles=new PriorityCollection();
        },
        loadRoles:function(){
            var that=this;
            that.$(".info-role").html("");
            this.roles.each(function(m){
                that.$(".info-role").append(new RoleListCheckItemView({
                    model:m
                }).render().el);
            });
            this.model.set("roles",this.roles);
        },
        editUser:function(){
            this.eb.trigger("User:Edit",this.model);
        },
        changeImage:function(){
            core.trigger("File:Select",{
                eb:this.eb,
                selectMode:"Single"
            });
        },
        changeRole:function(){
            this.eb.trigger("User:RoleModify",this.model);
        },
        render:function(){
            var that = this;
            this.$el.html(this.template(this.model.toJSON()));
            if(!privilege.hasRight("300030")){
                this.$(".editUser").remove();
            }
            $.ajax({
                url:"oaRole_findAccountRoles.action",
                type:"get",
                data:{
                    id:this.model.get("id")
                },
                success:function(response){
                    that.roles.add(response.roles);
                    that.loadRoles();
                },
                error:function(response){
                    MessageView.error({
                        body:response.responseText
                    });
                }
            });
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
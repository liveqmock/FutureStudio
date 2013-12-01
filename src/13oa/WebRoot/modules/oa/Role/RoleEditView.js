define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/message/index',
    'text!./assets/roleeditinfo.html'
],function($, _, Backbone, core, MessageView, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"role-edit",
        template: _.template(tpl),
        events:{
            "click .button-save":"save",
            "click .button-cancel":"cancel"
        },
        initialize:function(options){
            this.isNew = options.isNew;
            this.eb = options.eb;

            var title=this.isNew?"新增角色":"修改角色信息";
            //isNew :true :新增，false 修改
            if(this.isNew){
                this.$el.html(this.template({
                    name:"",
                    comments:'',
                    title:title
                }));
            }else{
                this.$el.html(this.template({
                    name:this.model.get("name"),
                    comments:this.model.get("comments"),
                    title:title
                }));
            }
            $("body").append(this.el);
        },
        render:function(){
            this.$(".modal").modal({
                backdrop:"static",
                show:true
            });
            return this;
        },
        save:function(){
            var name=this.$(".role-name").val(),
                comments=this.$(".role-comments").val(),
                that=this,
                option={
                type:"get",
                beforeSend:function(){

                },
                success:function(){
                    that.eb.trigger("Role:List:Update");
                    that.$(".modal").modal("hide");
                    that.undelegateEvents();
                    that.remove();
                },
                error:function(){
                    MessageView.error({
                        body:"操作失败"
                    });
                }
            };
            if(this.isNew){
                option.url="oaRole_addRole.action";
                option.data={
                    data1:name,
                    data2:comments
                };
            }else{
                option.url="oaRole_updateRole.action";
                option.data={
                    data1:name,
                    data2:comments,
                    id:this.model.get("id")
                };
            }
            $.ajax(option);
        },
        cancel:function(){
            this.undelegateEvents();
            this.$(".modal").modal("hide");
            this.remove();
        }
    });
});
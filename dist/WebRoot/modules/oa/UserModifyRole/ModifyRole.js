/** User:Tony   Date: 3/19/13 - 8:11 PM */
define([
    'jquery',
    'underscore',
    'backbone',
    '../Role/RoleCollection',
    './ModifyRoleItem',
    'text!./assets/ModifyRole.html'
],function($, _, Backbone, RoleCollection, ModifyRoleItem, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"oa-user-modify-role",
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .refresh-button":"fetch"
        },
        initialize:function(){
            this.fetched=0;
            this.eb = _.extend({},Backbone.Events);
            this.allRoleCollection = new RoleCollection();
            this.userRoleCollection = new RoleCollection();
            this.listenTo(this.allRoleCollection,"reset",function(){
                this.fetched++;
                if(this.fetched===2){
                    this.roleLoaded();
                }
            });
            this.listenTo(this.userRoleCollection,"reset",function(){
                this.fetched++;
                if(this.fetched===2){
                    this.roleLoaded();
                }
            });
        },
        fetch:function(){
            this.fetched=0;
            this.allRoleCollection.fetch();
            this.userRoleCollection.fetch({
                data:{
                    "userId":this.model.get("id")
                }
            });
        },
        render:function(){
            this.$el.html(this.template(this.model.toJSON()));
            this.fetch();
            return this;
        },
        roleLoaded:function(){
            var hasRole;
            this.eb.trigger("ModifyRoleItem:Destroy");
            this.allRoleCollection.each(function(m){
                if(this.userRoleCollection.get(m.get("id"))){
                    hasRole=true;
                }else{
                    hasRole=false;
                }
                this.$(".role-area").append(new ModifyRoleItem({
                    model:m,
                    hasRole:hasRole,
                    eb:this.eb,
                    userId:this.model.get('id')
                }).render().el);
            },this);
        },
        unRender:function(){
            this.eb.trigger("ModifyRoleItem:Destroy");
            this.remove();
        }
    });
});
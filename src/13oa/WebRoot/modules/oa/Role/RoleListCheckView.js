define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/collection/PriorityCollection',
    'RoleListCheckItemView',
    'bootstrap'
],function($, _, Backbone, PriorityCollection, RoleListCheckItemView){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"role-list-check-view btn-group",
        initialize:function(){
            this.$el.attr("data-toggle","buttons-checkbox");
            var that=this;
            this.eb= _.extend({},Backbone.Events);
            this.roles=new PriorityCollection();
            this.roles.url="oaRole_findRoles.action";
            this.roles.on("reset",function(){
                that.reload();
            },this);
        },
        reload:function(){
            this.$el.html("");
            var that=this;
            that.items=[];
            this.roles.each(function(m){
                var i = new RoleListCheckItemView({
                    eb:that.eb,
                    model:m
                }).render();
                that.items.push(i);
                that.$el.append(i.el);
            });
            this.$el.button();
        },
        checked:function(roles){
            var that=this;
            if(!roles){
                return false;
            }
            for(var i=0;i<roles.length;i++){
                that.eb.trigger("Role:Check:"+roles[i]);
            }
        },
        findChecked:function(){
            var roles=[];
            for(var i=0;i<this.items.length;i++){
                if(this.items[i].isCheck()===1){
                    roles.push(this.items[i].model);
                }
            }
            return roles;
        },
        render:function(){
            this.roles.fetch();
            return this;
        }
    });
});
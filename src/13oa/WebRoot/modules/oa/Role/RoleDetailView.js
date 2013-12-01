/** User:Tony   Date: 3/19/13 - 8:11 PM */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!./assets/roledetailinfo.html'
],function($, _, Backbone, core, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"role-detail",
        template: _.template(tpl),
        events:{
            "click .role-add-button":"add",
            "click .role-edit-button":"edit",
            "click .role-move-up-button":"moveUp",
            "click .role-move-down-button":"moveDown"
        },
        initialize:function(options){
            this.eb = options.eb;

            this.$el.html(this.template(this.model.toJSON()));
        },
        render:function(){
            return this;
        },
        add:function(){
            this.eb.trigger("Role:Add");
        },
        edit:function(){
            this.eb.trigger("Role:Edit",this.model);
        },
        moveUp:function(){
            this.eb.trigger("Role:Move:Up",this.model);
        },
        moveDown:function(){
            this.eb.trigger("Role:Move:Down",this.model);
            console.log("moveDown");
        }

    });
});
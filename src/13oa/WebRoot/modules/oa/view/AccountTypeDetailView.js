define([
    'jquery',
    'underscore',
    'backbone',
    './AccountTypeEditView',
    'text!../tpl/account-type-detail.html'
], function ($, _, Backbone, AccountTypeEditView, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        template: _.template(tpl),
        events:{
            "click .moveUp":"moveUp",
            "click .moveDown":"moveDown",
            "click .modify":"modify"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.eb.on("AccountType:Refresh",function(){
                that.unRender();
            });
        },
        moveUp:function(){
            this.eb.trigger("AccountTYpe:MoveUp",this.model);
        },
        moveDown:function(){
            this.eb.trigger("AccountTYpe:MoveDown",this.model);
        },
        modify:function(){
            new AccountTypeEditView({
                eb:this.eb,
                model:this.model
            }).render();
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender:function(){
            this.undelegateEvents();
            this.remove();
        }
    });
});
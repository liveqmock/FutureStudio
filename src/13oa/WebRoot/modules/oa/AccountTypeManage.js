//账户管理view，分为左右两边
define([
    'jquery',
    'underscore',
    'backbone',
    './view/AccountTypeListView',
    './view/AccountTypeDetailView',
    'text!modules/base/tpl/fluid39.html'
], function ($, _, Backbone,
             AccountTypeListView,
             AccountTypeDetailView,
             tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        template: _.template(tpl),
        initialize: function () {
            var that=this;
            this.eb= _.extend({},Backbone.Events);
            this.eb.on("AccountType:Click",function(model){
                 that.showRight(model);
            });
        },
        render: function () {
            this.$el.html(this.template());
            this.left=new AccountTypeListView({
                eb:this.eb
            });
            this.left.render();
            this.$(".span3").html(this.left.el);
            return this;
        },
        showRight:function(model){
            if(this.right){
                this.right.unRender();
            }
            this.right=new AccountTypeDetailView({
                model:model,
                eb:this.eb
            });
            this.right.render();
            this.$(".span9").html(this.right.el);
        }
    });
});
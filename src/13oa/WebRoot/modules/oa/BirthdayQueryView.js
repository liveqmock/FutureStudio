define([
    "jquery",
    "underscore",
    "backbone",
    "./view/BirthdayQueryConditionView",
    "./view/BirthdayQueryResultView",
    "text!modules/base/tpl/fluid39.html"
], function ($, _, Backbone,BirthdayQueryConditionView,BirthdayQueryResultView,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"birthday-query",
        template: _.template(tpl),
        initialize: function () {
            var that=this;
            this.eb= _.extend({},Backbone.Events);
            this.left=new BirthdayQueryConditionView({
                eb:this.eb
            }).render();
            this.right=new BirthdayQueryResultView({
                eb:this.eb
            }).render();
            this.eb.on("Birthday:Query",function(condition){
                that.right.query(condition);
            });
            this.eb.on("Account:Detail",function(m){

            });
        },
        render: function () {
            this.$el.html(this.template());
            this.$(".span3").html(this.left.el);
            this.$(".span9").html(this.right.el);
            return this;
        }
    });
});
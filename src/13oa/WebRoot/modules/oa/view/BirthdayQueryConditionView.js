define([
    "jquery",
    "underscore",
    "backbone",
    "./BirthdayQueryConditionMonthView",
    "./BirthdayQueryConditionRangeView",
    "text!../tpl/BirthdayQueryCondition.html",
    "bootstrap"
], function ($, _, Backbone,BirthdayQueryConditionMonthView,BirthdayQueryConditionRangeView,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"condition",
        template: _.template(tpl),
        events:{
            "click .month-button":"month",
            "click .range-button":"range"
        },
        initialize: function (options) {
            this.eb = options.eb;
            this.month=new BirthdayQueryConditionMonthView({
                eb:this.eb
            }).render();
            this.range=new BirthdayQueryConditionRangeView({
                eb:this.eb
            }).render();
        },
        month:function(){
            this.$(".condition-item").html(this.month.el);
        },
        range:function(){
            this.$(".condition-item").html(this.range.el);
        },
        render: function () {
            this.$el.html(this.template());
            this.$(".btn-group").button();
            this.$(".condition-item").html(this.month.el);
            return this;
        }
    });
});
define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/BirthdayQueryConditionRange.html"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"range",
        events:{
            "click .search-button":"search"
        },
        template: _.template(tpl),
        initialize: function (options) {
            this.eb = options.eb;
        },
        search:function(){
            var data={
                type:"range",
                startMonth:this.$(".start-month").val(),
                startDay:this.$(".start-day").val(),
                endMonth:this.$(".end-month").val(),
                endDay:this.$(".end-day").val()
            };
            this.eb.trigger("Birthday:Query",data);
        },
        render: function () {
            this.$el.html(this.template());
            return this;
        }
    });
});
define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/BirthdayQueryConditionMonth.html",
    "bootstrap"
], function ($, _, Backbone,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"month",
        template: _.template(tpl),
        events:{
            "click .search-button":"search"
        },
        initialize: function (options) {
            this.eb = options.eb;
        },
        search:function(){
            var data={
                type:"month",
                months:""
            };
            this.$(".active").each(function(){
                data.months=data.months+","+$(this).html().replace("月");
            });
            data.months=data.months.substr(1,data.months.length);
            this.eb.trigger("Birthday:Query",data);
        },
        render: function () {
            this.$el.html(this.template());
            this.$(".btn-group").button();
            return this;
        }
    });
});
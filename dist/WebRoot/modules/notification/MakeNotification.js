//发出通知
define([
    'jquery',
    'underscore',
    'backbone'
], function ($, _, Backbone) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        initialize: function () {

        },
        render: function () {
            this.$el.html("发出通知");
            return this;
        },
        unRender: function () {
            this.undelegateEvents();
            this.remove();
        }
    });
});
define([
    'jquery',
    'underscore',
    'backbone',
    'text!./assets/CustomerItem.html'
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "tr",
        template: _.template(tpl, null, {variable: "args"}),
        events:{
            "click .more":"click"
        },
        initialize: function (options) {
            this.eb = options.eb;
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        click:function(){
            this.eb.trigger("Customer:Click",this.model);
        },
        unRender: function () {
            this.remove();
        }
    });
});
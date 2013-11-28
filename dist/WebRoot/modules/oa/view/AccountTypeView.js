define([
    'jquery',
    'underscore',
    'backbone',
    'text!modules/base/tpl/td.html'
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "tr",
        template: _.template(tpl),
        events:{
            "click":"click"
        },
        initialize: function (options) {
            this.eb = options.eb;
        },
        click:function(){
            this.eb.trigger("AccountType:Click",this.model);
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });
});
/**
 * User: Tony  Date: 13-4-25 Time: 下午9:32
 * Future Studio
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'text!../tpl/MyRequestItem.html'
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "tr",
        template: _.template(tpl),
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;
            this.eb.on("RequestItem:Remove",function(){
                that.unRender();
            },this);
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender: function () {
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
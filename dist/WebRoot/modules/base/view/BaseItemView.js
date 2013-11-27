define([
    "jquery",
    "underscore",
    "backbone"
], function ($, _, Backbone) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        initialize: function (options) {
            this.eb = options.eb;
            this.listenTo(this.eb,"Item:Destroy",this.unRender);
            this.listenTo(this,"Item:BeforeRender",this.beforeRender);
            this.listenTo(this,"Item:AfterRender",this.afterRender);
        },
        fsInit:function(){
            return this;
        },
        getHtml:function(){
            return this.template(this.model.toJSON());
        },
        beforeRender:function(){
            return this;
        },
        render: function () {
            var that = this;

            this.trigger("Item:BeforeRender");

            this.$el.html(this.getHtml());

            setTimeout(function(){
                that.trigger("Item:AfterRender");
            },100);

            return this;
        },
        afterRender:function(){
            return this;
        },
        unRender: function () {
            this.remove();
        }
    });
});
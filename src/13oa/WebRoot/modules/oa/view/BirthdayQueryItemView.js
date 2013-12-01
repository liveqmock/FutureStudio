define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/BirthdayQueryItem.html"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        initialize: function (options) {
            this.eb = options.eb;

            var that=this;
            this.eb.on("Item:Remove",function(){
                that.unRender();
            },this);
        },
        events:{
            "click .detail-button":"detail"
        },
        detail:function(){
            this.eb.trigger("Account:Detail",this.model);
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
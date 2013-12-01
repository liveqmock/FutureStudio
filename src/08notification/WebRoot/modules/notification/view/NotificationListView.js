define([
    "jquery",
    "underscore",
    "backbone",
    "./NotificationItemView",
    "text!../tpl/NotificationList.html"
], function ($, _, Backbone, NotificationItemView, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.eb.on("Notification:Destroy",function(){
                that.unRender();
            },this);
        },
        loadCollection:function(collection){
            var that=this;
            collection.each(function(m){
                that.$('tbody').append(new NotificationItemView({
                    model:m,
                    eb:that.eb
                }).render().el);
            });
        },
        render: function () {
            this.$el.html(this.template());
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
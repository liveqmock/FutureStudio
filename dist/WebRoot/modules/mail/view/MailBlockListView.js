define([
    "jquery",
    "underscore",
    "backbone",
    "../collection/MailBlockAddressCollection",
    "./MailBlockItemView",
    "text!../tpl/MailBlockList.html"
], function ($, _, Backbone, MailBlockAddressCollection, MailBlockItemView, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;
            this.collection=new MailBlockAddressCollection();
            this.collection.on("reset",function(){
                that.reload();
            });
        },
        reload:function(){
            var that=this;
            this.eb.trigger("Mail:Block:Destroy");
            this.collection.each(function(m){
                that.$("tbody").append(new MailBlockItemView({
                    eb:that.eb,
                    model:m
                }).render().el);
            });
        },
        search:function(text){
            this.eb.trigger("Mail:Block:Search",text);
        },
        refresh:function(){
            this.collection.fetch();
        },
        render: function () {
            this.$el.html(this.template());
            this.collection.fetch();
            return this;
        }
    });
});
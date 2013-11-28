define([
    "jquery",
    "underscore",
    "backbone",
    "./BirthdayQueryItemView",
    "modules/base/collection/BaseCollection",
    "text!../tpl/BirthdayQueryResult.html"
], function ($, _, Backbone, BirthdayQueryItemView, BaseCollection,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"result",
        template: _.template(tpl),
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.collection=new BaseCollection();
            this.collection.parse=function(response){
                return response.accounts;
            };
            this.collection.on("reset",function(){
                that.load();
            });
            this.collection.url="birthday_queryBirthday.action";
        },
        load:function(){
            var that=this;
            this.eb.trigger("Item:Remove");
            this.collection.each(function(m){
                that.$("tbody").append(new BirthdayQueryItemView({
                    model:m,
                    eb:that.eb
                }).render().el);
            });
        },
        query:function(data){
            this.collection.fetch({
                data:data
            });
        },
        render: function () {
            this.$el.html(this.template());
            return this;
        }
    });
});
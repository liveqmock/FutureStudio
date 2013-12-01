define([
    'jquery',
    'underscore',
    'backbone',
    'modules/page/index',
    './CustomerItemView',
    './CustomerCollection',
    'text!./assets/CustomerList.html'
], function ($, _, Backbone,
             PageView,
             CustomerItemView,
             CustomerCollection,
             tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        className:"",
        template: _.template(tpl,null,{variable:"args"}),
        initialize: function (options) {
            this.collection=new CustomerCollection();
            this.listenTo(this.collection,"reset",function(){
                this.loaded();
            });
            this.eb = options.eb;
            this.page = 1;

            this.listenTo(this.eb,"Page:Change",function(page){
                this.page = page;
                this.fetch();
            });
        },
        render: function () {
            this.$el.html(this.template());
            this.fetch();
            return this;
        },
        fetch:function(){
            this.collection.fetch({
                data:{
                    page:this.page
                }
            });
        },
        loaded:function(){
            this.$el.html(this.template());
            if(this.pageView){
                this.pageView.unRender();
            }
            this.pageView = new PageView({
                eb:this.eb,
                page:this.collection.page,
                pageSize:this.collection.pageSize,
                count:this.collection.count
            }).render();
            this.$(".page-area").html(this.pageView.el);

            this.collection.each(function(model){
                this.$("tbody").append(new CustomerItemView({
                    model:model,
                    eb:this.eb
                }).render().el);
            },this);

        },
        unRender:function(){
            this.remove();
        }
    });
});
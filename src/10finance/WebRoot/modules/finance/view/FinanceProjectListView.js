define([
    "jquery",
    "underscore",
    "backbone",
    "../collection/FinanceProjectCollection",
    "./FinanceProjectItemView.js",
    "text!../tpl/FinanceProjectList.html"
], function ($, _, Backbone, Collection, ItemView, tpl) {
    "use strict";
    return Backbone.View.extend({
        className:"projects-list",
        template: _.template(tpl,null,{variable:"opt"}),
        events:{
            "click .button-add":"addProject"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.collection = new Collection();
            this.collection.on("reset",function(){
                that.loadCollection();
            },this);
            this.page=1;
        },
        addProject:function(){
            this.eb.trigger("Finance:Add");
        },
        loadCollection:function(){
            var that=this;
            this.eb.trigger("FinanceListItem:Destroy");
            this.collection.each(function(m){
                that.$("tbody").append(new ItemView({
                    eb:that.eb,
                    model:m
                }).render().el);
            });
        },
        fetch:function(){
            this.collection.fetch({
                data:{
                    page:this.page
                }
            });
        },
        render: function () {
            this.$el.html(this.template());
            this.fetch();
            return this;
        }
    });
});
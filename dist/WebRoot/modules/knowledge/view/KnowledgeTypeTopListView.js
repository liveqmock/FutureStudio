define([
    "jquery",
    "underscore",
    "backbone",
    "./KnowledgeTypeTopView",
    "../collection/KnowledgeTypeTopCollection",
    "text!../tpl/KnowledgeTypeTopList.html"
], function ($, _, Backbone, KnowledgeTypeTopView, Collection, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"knowledge-type-top",
        template: _.template(tpl),
        events:{
            "input .search-query":"searchQuery"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.collection=new Collection();
            this.collection.on("reset",function(){
                that.load();
            });
        },
        searchQuery:function(){
            this.eb.trigger("KnowledgeType:Top:Query",this.$("..search-query").val());
        },
        load:function(){
            var that=this;
            this.eb.trigger("TopType:Destroy");
            this.collection.each(function(m){
                that.$("tbody").append(new KnowledgeTypeTopView({
                    eb:that.eb,
                    model:m
                }).render().el);
            });
        },
        render: function () {
            this.$el.html(this.template());
            this.collection.fetch();
            return this;
        }
    });
});
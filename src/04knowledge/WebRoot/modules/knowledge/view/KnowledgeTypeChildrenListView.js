define([
    "jquery",
    "underscore",
    "backbone",
    "../collection/KnowledgeTypeChildCollection",
    "./KnowledgeTypeChildView",
    "text!../tpl/KnowledgeTypeChildrenList.html"
], function ($, _, Backbone, Collection, KnowledgeTypeChildView, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
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
            this.eb.trigger("KnowledgeType:Child:Query",this.$(".search-query").val());
        },
        load:function(){
            var that=this;
            this.eb.trigger("ChildrenType:Destroy");
            this.collection.each(function(m){
                that.$("tbody").append(new KnowledgeTypeChildView({
                    model:m,
                    eb:that.eb
                }).render().el);
            });
        },
        render: function () {
            return this;
        },
        //zhu
        loadTopChildren:function(model){
            this.$el.html(this.template(model.toJSON()));
            this.collection.fetch({
                data:{
                    id:model.get("id")
                }
            });
        },
        unRender:function(){
            //TODO
        }
    });
});
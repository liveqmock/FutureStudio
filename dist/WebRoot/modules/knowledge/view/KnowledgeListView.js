define([
    "jquery",
    "underscore",
    "backbone",
    "../collection/KnowledgeCollection",
    "./KnowledgeItemView",
    "text!../tpl/KnowledgeList.html"
], function ($, _, Backbone, Collection, KnowledgeItemView, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        className:"knowledge-list",
        events:{
            "click .button-plus":"nextPage",
            "click .button-minus":"prevPage",
            "click .button-add":"addKnowledge"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;
            this.collection=new Collection();
            this.collection.on("reset",function(){
                that.load();
            });
            this.currentPage=1;
            this.currentQuery="";
            this.currentPageSize=20;
            this.maxPage=1;
            this.maxCount=1;
        },
        nextPage:function(){
            if(this.currentPage<this.maxPage){
                this.currentPage=this.currentPage+1;
            }
            this.refresh();
        },
        prevPage:function(){
            if(this.currentPage>1){
                this.currentPage=this.currentPage-1;
            }
            this.refresh();
        },
        refresh:function(){
            this.loadList();
        },
        addKnowledge:function(){
            this.eb.trigger("Knowledge:Add",this.knowledgeType);
        },
        load:function(){
            var that=this;
            this.render();
            this.currentPage=this.collection.page;
            this.currentPageSize=this.collection.pageSize;
            this.maxPage=this.collection.countPage;
            this.maxCount=this.collection.count;
            this.$(".operate .btn").hide();
            this.eb.trigger("Knowledge:Destroy");
            if(this.maxPage>1){
                if(this.currentPage<this.maxPage){
                    this.$(".operate-area .button-plus").show();
                }
                if(this.currentPage>1){
                    this.$(".operate-area .button-minus").show();
                }
            }
            this.collection.each(function(m){
                that.$("tbody").append(new KnowledgeItemView({
                    model:m,
                    eb:that.eb
                }).render().el);
            });
        },
        render: function () {
            this.$el.html(this.template());
            return this;
        },
        loadList:function(m){
            if(m){
                this.knowledgeType=m;
            }
            this.collection.fetch({
                data:{
                    knowledgeTypeId: this.knowledgeType.get("id"),
                    page:this.currentPage,
                    pageSize:this.currentPageSize,
                    query:this.query
                }
            });
        },
        unRender:function(){
            this.eb.trigger("Knowledge:Destroy");
            this.undelegateEvents();
            this.remove();
        }
    });
});
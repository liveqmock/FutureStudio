define([
    "jquery",
    "underscore",
    "backbone",
    "./return-plan-list/ReturnPlanCollection",
    "./return-plan-list/item",
    "modules/page/index",
    "text!./tpl/ProjectReturnPlanList.html"
], function ($, _, Backbone, Collection, Item, PageView, tpl) {
    "use strict";
    return Backbone.View.extend({
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .btn-refresh":"fetch"
        },
        initialize: function () {
            this.collection = new Collection();
            this.listenTo(this.collection,"reset",this.loaded);
            this.page = 1;

            this.eb = _.extend({},Backbone.Events);
            this.listenTo(this.eb,"Page:Change",function(p){
                this.page = p;
                this.fetch();
            });
        },
        render: function () {
            this.$el.html(this.template());
            this.fetch();
            return this;
        },
        loaded:function(){
            if(this.pageView){
                this.pageView.unRender();
            }
            this.eb.trigger("Item:Destroy");
            if(this.collection.length === 0){
                this.$(".list-area").html("无数据");
            }else{
                this.collection.each(function(m){
                    this.$(".list-area").append(new Item({
                        model:m,
                        eb:this.eb
                    }).render().el);
                },this);
            }
            this.pageView = new PageView({
                eb:this.eb,
                page:this.collection.page,
                count:this.collection.count,
                pageSize:this.collection.pageSize
            }).render();
            this.$(".page-area").html(this.pageView.el);
        },
        getOption:function(){
            var data = {},
                selects = this.$(".data-area").find("select");
            _.each(selects, function(node){
                data[$(node).attr("data-property")]=$(node).val();
            });
            return data;
        },
        fetch:function(){
            var data = {
                page:this.page
            };
            data = _.extend(data,this.getOption());
            this.collection.fetch({
                data:data,
                reset:true
            });
        },
        unRender: function () {
            this.remove();
        }
    });
});
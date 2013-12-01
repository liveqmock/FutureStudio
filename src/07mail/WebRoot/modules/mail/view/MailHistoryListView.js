define([
    "jquery",
    "underscore",
    "backbone",
    "./MailHistoryView",
    "../collection/MailHistoryCollection",
    "text!../tpl/MailHistoryList.html"
], function ($, _, Backbone, MailHistoryView,
             MailHistoryCollection, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"mail-history-list",
        template: _.template(tpl),
        events:{
            "click .pagination li":"changePage"
        },
        initialize:function(options){
            var that=this;
            this.eb = options.eb;
            this.collection=new MailHistoryCollection();
            this.collection.on("reset",function(){
                that.reload();
            });
            this.dataSearch="";
            this.dataPage=1;
        },
        changePage:function(e){
            console.log($(e.target).find("a").html());
        },
        reload:function(){
            var that=this;
            this.eb.trigger("Mail:Item:Destroy");
            this.collection.each(function(m){
                that.$("tbody").append(new MailHistoryView({
                    eb:that.eb,
                    model:m
                }).render().el);
            });
        },
        render:function(){
            this.$el.html(this.template());
            this.$(".page-button").hide();
            this.reload();
            return this;
        },
        search:function(text){
            this.dataSearch=text;
            this.collection.fetch({
                data:{
                    search:this.dataSearch,
                    page:this.dataPage
                }
            });
        },
        refresh:function(){
            this.collection.fetch({
                data:{
                    search:this.dataSearch,
                    page:this.dataPage
                }
            });
        }
    });
});
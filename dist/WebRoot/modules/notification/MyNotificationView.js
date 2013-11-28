define([
    "jquery",
    "underscore",
    "backbone",
    "core",
    "./view/NotificationListView",
    "./collection/NotificationCollection",
    "text!./tpl/MyNotification.html"
], function ($, _, Backbone,core,NotificationListView,NotificationCollection,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"my-notification",
        template: _.template(tpl),
        events:{
            "click .refresh-button":"refresh",
            "click .search-button":"search",
            "click .history-button":"history"
        },
        initialize: function () {
            var that=this;
            _.bindAll(this,"history","search","refresh");
            this.collection=new NotificationCollection();
            this.collection.on("reset",function(){
                that.listView.loadCollection(that.collection);
            });
            this.eb= _.extend({},Backbone.Events);
            this.interval=null;
        },
        incrementFetch:function(){

        },
        history:function(){

        },
        search:function(){
            //TODO
        },
        fetch:function(){
            //初次抓取，刷新，都采用这个。
            this.collection.fetch();
        },
        refresh:function(){
            this.eb.trigger("NotificationItem:Destroy");
            this.fetch();
        },
        render: function () {
            if(this.interval){
                clearInterval(this.interval);
            }
            this.$el.html(this.template());
            this.listView=new NotificationListView({
                eb:this.eb
            }).render();
            //this.interval=setInterval();  设置增量抓取循环
            this.$(".data-area").html(this.listView.el);
            this.fetch();
            this.interval=window.setInterval(this.incrementFetch,1000*60*15);
            return this;
        },
        unRender:function(){
            this.eb.trigger("Notification:Destroy");
            this.undelegateEvents();
            this.remove();
        }
    });
});
define([
    "jquery",
    "underscore",
    "backbone",
    "./view/MailHistoryListView",
    "text!./tpl/MailHistory.html"
], function ($, _, Backbone, MailHistoryListView,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"mail-history",
        template: _.template(tpl),
        events:{
            "click .button-search":"search",
            "click .button-refresh":"refresh"
        },
        initialize: function () {
            _.bindAll(this,"search","refresh");
            this.eb= _.extend({},Backbone.Events);
            this.mailHistoryList=new MailHistoryListView({
                eb:this.eb
            });
        },
        search:function(){
            var text=this.$(".search-query").val();
            this.mailHistoryList.search(text);
        },
        refresh:function(){
            this.mailHistoryList.refresh();
        },
        render: function () {
            this.$el.html(this.template());
            this.mailHistoryList.render();
            this.$(".content-area").html(this.mailHistoryList.el);
            return this;
        }
    });
});
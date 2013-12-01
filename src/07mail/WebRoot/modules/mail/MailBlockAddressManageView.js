//不做分页显示全部
define([
    "jquery",
    "underscore",
    "backbone",
    "./view/MailBlockListView",
    "text!./tpl/MailBlock.html"
], function ($, _, Backbone, MailBlockListView, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        className:"mail-block-address",
        events:{
            "input .search-query":"search",
            "click .button-refresh":"refresh"
        },
        initialize: function () {
            _.bindAll(this,"search","refresh");
            this.eb= _.extend({},Backbone.Events);
            this.blockList=new MailBlockListView({
                eb:this.eb
            }).render();
        },
        search:function(){
            this.blockList.search(this.$(".search-query").val());
        },
        refresh:function(){
            this.blockList.refresh();
        },
        render: function () {
            this.$el.html(this.template());
            this.$(".content-area").html(this.blockList.el);
            return this;
        }
    });
});
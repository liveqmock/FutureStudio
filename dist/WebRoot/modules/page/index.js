define([
    'jquery',
    'underscore',
    'backbone',
    'text!./index.html'
], function ($, _, Backbone, tpl) {
    "use strict";
    /**
     * 通用页面架构：
     *入口参数
     * count:
     * pageSize:
     * page:
     * eb:
     */
    return Backbone.View.extend({
        tagName: "div",
        className:"pagination",
        template: _.template(tpl, null, {variable: "args"}),
        events:{
            "click .page":"click"
        },
        initialize: function (options) {
            this.page = parseInt(options.page,10);
            this.pageSize = parseInt(options.pageSize,10);
            this.count = parseInt(options.count,10);

            this.eb = options.eb;
            this.maxPage = parseInt((this.count-1)/this.pageSize,10)+1;
        },
        render: function () {
            this.$el.html(this.template({
                "page":this.page
            }));
            if(this.page===1){
                this.$(".prev-page").remove();
            }
            if(this.page===this.maxPage){
                this.$(".next-page").remove();
            }
            return this;
        },
        click:function(e){
            if($(e.currentTarget).hasClass("prev-page")){
                this.eb.trigger("Page:Change",this.page-1);
            }else{
                this.eb.trigger("Page:Change",this.page+1);
            }
        },
        unRender: function () {
            this.eb.trigger("Page:Destroy");
            this.remove();
        }
    });
});
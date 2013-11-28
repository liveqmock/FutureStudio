define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/FinanceProjectItem.html"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl,null,{variable:"opt"}),
        events:{
            "click .see-detail":"openDetail"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;
            this.listenTo(this.eb,"FinanceListItem:Destroy",function(){
                that.unRender();
            },this);
        },
        openDetail:function(){
            this.eb.trigger("FinanceProject:Detail",this.model);
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender:function(){
            this.remove();
        }
    });
});
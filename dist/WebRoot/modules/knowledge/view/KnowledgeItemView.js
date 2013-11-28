define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/KnowledgeItem.html"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{
            "click .button-modify":"modify"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.eb.on("Knowledge:Destroy",function(){
                that.unRender();
            },this);
            this.model.on("refresh",function(){
                that.render();
            },this);
        },
        modify:function(){
            this.eb.trigger("Knowledge:Modify",this.model);
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
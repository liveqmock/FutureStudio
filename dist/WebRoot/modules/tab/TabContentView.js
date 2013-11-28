//tab用来实体化一个tab区域，并require装载，具体的view，进行render，
//可以提供一个键值对的参数
define([
    'jquery',
    'underscore',
    'backbone'
],function($, _, Backbone){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"tab-pane",
        initialize:function(options){
            var that=this;
            this.contentView = options.contentView;

            this.contentView.on("removed",function(){
                that.trigger("removed");
            },this);
            this.$el.append(this.contentView.el);
        },
        render:function(){
            return this;
        },
        unRender:function(){
            if(this.contentView){
                this.contentView.unRender();
                this.contentView.off(null,null,this);
            }
            this.remove();
        }
    });
});
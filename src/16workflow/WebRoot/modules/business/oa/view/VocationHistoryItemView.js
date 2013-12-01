define([
    'jquery',
    'underscore',
    'backbone',
    'text!../tpl/VocationHistoryItem.html'
],function($, _, Backbone, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        initialize:function(){

        },
        render:function(){
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        }
    });
});
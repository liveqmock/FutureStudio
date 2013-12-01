define([
    'jquery',
    'underscore',
    'backbone'
],function($,_,Backbone){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"bisness-ceshi-show",
        initialize:function(){
            this.$el.html("测试显示View");
        },
        render:function(){
            return this;
        }
    });
});
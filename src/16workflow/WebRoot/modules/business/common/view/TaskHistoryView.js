//历史任务单个tr
define([
    'jquery',
    'underscore',
    'backbone',
    'text!../tpl/TaskHistoryItem.html'
],function($, _, Backbone, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        render:function(){
            this.$el.html(this.template());
            return this;
        }
    });
});

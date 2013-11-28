/** User:Tony   Date: 1/21/13 - 9:28 PM */
//组织管理视图
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!modules/base/tpl/td.html'
],function($, _, Backbone, core, tdTpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template:_.template(tdTpl),
        events:{
            "click":"click"
        },
        initialize:function(options){
            this.eb = options.eb;
        },
        click:function(){
            this.eb.trigger("User:Click",this.model);
        },
        render:function(){
            this.$el.append(this.template(this.model.toJSON()));
            return this;
        }
    });
});
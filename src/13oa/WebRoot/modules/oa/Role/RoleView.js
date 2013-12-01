define([
    'jquery',
    'underscore',
    'backbone'
],function($, _, Backbone){
    "use strict";
    return Backbone.View.extend({
        tagName:"li",
        className:"list-group-item",
        events:{
            "click":"click"
        },
        initialize:function(options){
            var that=this;
            this.eb = options.eb;

            this.eb.on("Role:Tr:Clear",function(){
                that.$el.removeClass("active");
            });
        },
        render:function(){
            this.$el.html(this.model.get("name"));
            return this;
        },
        click:function(e){
            this.eb.trigger("Role:Click",this.model);
            this.eb.trigger("Role:Tr:Clear");
            this.$el.addClass("active");
        }
    });
});
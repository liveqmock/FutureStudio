define([
    'jquery',
    'underscore',
    'backbone'
],function($, _, Backbone){
    "use strict";
    return Backbone.View.extend({
        tagName:"button",
        className:"btn role-menu-item",
        initialize:function(options){
            var that=this;
            this.eb = options.eb;

            this.$el.attr("data-id",this.model.get("id"));
            this.$el.attr("type","button");
            this.$el.html(this.model.get("name"));
            this.eb.on("Role:Function:Clear",this.clear,this);
            this.eb.on("Role:Function:"+this.model.get("id"),function(){
                that.add();
            },this);
        },
        events:{
            "click":"click"
        },
        clear:function(){
            this.$el.removeClass("btn-primary");
            this.$el.removeClass("active");
        },
        add:function(){
            this.$el.addClass("btn-primary");
            this.$el.addClass("active");
        },
        click:function(){
            if(this.$el.hasClass("btn-primary")){
                this.$el.removeClass("btn-primary");
                this.eb.trigger("Role:Function:Remove",this.model);
            }else{
                this.$el.addClass("btn-primary");
                this.eb.trigger("Role:Function:add",this.model);
            }
        },
        render:function(){
            return this;
        }
    });
});
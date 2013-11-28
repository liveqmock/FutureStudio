define([
    'jquery',
    'underscore',
    'backbone'
],function($, _, Backbone){
    "use strict";
    return Backbone.View.extend({
        tagName:"button",
        className:"role-list-check-item btn",
        initialize:function(options){
            var that=this;
            this.eb = options.eb;
            this.$el.attr("type","button");
            this.$el.append(this.model.get("name"));
            if(this.eb){
                this.eb.on("Role:Check:"+this.model.get("id"),function(){
                    that.check();
                });
            }

        },
        isCheck:function(){
            if(this.$el.hasClass("active")){
                return 1;
            }else{
                return 0;
            }
        },
        check:function(){
            this.$el.addClass("active");
        },
        render:function(){
            return this;
        }
    });
});
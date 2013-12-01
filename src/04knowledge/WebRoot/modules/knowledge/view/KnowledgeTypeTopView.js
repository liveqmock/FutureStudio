define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/KnowledgeTypeTop.html"
], function ($, _, Backbone,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{
            "click .select-td":"click",
            "click .btn":"modify"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.eb.on("TopType:Destroy",function(){
                that.unRender();
            },this);
            this.eb.on("KnowledgeType:Top:Query",function(text){
                if(that.model.get("name").contains(text)){
                    that.$el.show();
                }else{
                    that.$el.hide();
                }
                if(text===""){
                    that.$el.show();
                }
            });
        },
        render: function () {
            if(parseInt(this.model.get("statusCode"),10)===0){
                this.$el.css({
                    "background-color":"red"
                });
            }
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        modify:function(){
            //TODO
        },
        click:function(){
            this.eb.trigger("TopType:Select",this.model);
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
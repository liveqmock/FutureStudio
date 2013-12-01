define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/KnowledgeTypeChild.html"
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

            this.eb.on("ChildrenType:Destroy",function(){
                that.unRender();
            },this);
            this.eb.on("KnowledgeType:Child:Query",function(text){
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
        click:function(){
            this.eb.trigger("ChildType:Select",this.model);
        },
        modify:function(){
            //TODO
        },
        render: function () {
            var json=this.model.toJSON(),
                outRead=parseInt(this.model.get("outReader"),10);
            if(outRead>0){
                json.outReaderString="icon-plus";
            }else{
                json.outReaderString="icon-minus";
            }
            this.$el.html(this.template(json));
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
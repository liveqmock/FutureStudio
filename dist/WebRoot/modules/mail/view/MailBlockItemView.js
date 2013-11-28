define([
    "jquery",
    "underscore",
    "backbone",
    "text!../tpl/MailBlock.html"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{
            "click .btn":"remove"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;
            this.eb.on("Mail:Block:Destroy",function(){
                that.unRender();
            },this);
            this.eb.on("Mail:Block:Search",function(text){
                if(text.length===0){
                    that.$el.show();
                }else{
                    that.$el.hide();
                    if(that.model.get("mailAddress").contains(text) ||
                        that.model.get("addDate").contains(text)
                        ){
                        that.$el.show();
                    }
                }
            });
        },
        remove:function(){

        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
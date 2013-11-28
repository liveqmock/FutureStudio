define([
    "jquery",
    "underscore",
    "backbone",
    "text!./index.html"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        className:"modal hide fade",
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .button-confirm":"confirm"
        },
        initialize:function(options){
            var that = this,
                defaultOptions = {
                title:"",
                body:"",
                text:"确定",
                confirm:function(){
                    that.hide();
                }
            };
            this.listenTo(this.$el,"hidden",function(){
                this.unRender();
                this.trigger("hidden");
            });
            this.modalOption = _.extend(defaultOptions,options);
        },
        render: function () {
            this.$el.html(this.template(this.modalOption));
            this.$(".modal-body").html(this.modalOption.body);
            $("body").append(this.el);
            this.$el.modal({
                "backdrop":'static',
                "show":true
            });
            return this;
        },
        confirm:function(){
            this.modalOption.confirm();
        },
        hide:function(){
            this.$el.modal('hide');
        },
        unRender:function(){
            this.remove();
        }
    });
});
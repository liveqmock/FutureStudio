define([
    "jquery",
    "underscore",
    "backbone",
    "text!./index.html",
    "css!./index.css"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        template: _.template(tpl,null,{variable:"args"}),
        tagName:"div",
        className:"message",
        events:{
            "click":"click"
        },
        initialize:function(options){
            /**
             * 如要参数如下：
             * @type {{color: string, onClick: Function, title: string, body: string}}
             */
            var defaultOption = {
                color:"default",
                onClick:function(){

                },
                timeOut:7,
                title:"",
                body:""
            };
            this.messageOption = _.extend(defaultOption,options);
        },
        render:function(){
            this.$el.html(this.template(this.messageOption));
            this.show();
            return this;
        },
        getY:function(){
            return this.yPosition;
        },
        addPosition:function(add){
            this.yPosition = this.yPosition+add;
            this.$el.stop().animate({
                'top':this.yPosition+"px"
            },'slow');
        },
        show:function(yPosition){
            var that=this;

            this.yPosition = yPosition;

            this.$el.animate({
                'opacity':"1",
                'top':this.yPosition+"px"
            },'slow');
            this.timeOut=setTimeout(function(){
                that.clear();
                that.unRender();
            },this.messageOption.timeOut*1000);
        },
        click:function(){
            this.messageOption.onClick();
            this.clear();
            this.unRender();
        },
        clear:function(){
            if(this.timeOut){
                clearTimeout(this.timeOut);
            }
        },
        unRender:function(){
            var that=this;
            this.$el.animate({
                'opacity':"0",
                'top':"-20px"
            },'slow','swing',function(){
                that.trigger("message:close", that);
                that.off();
                that.remove();
            });

        }
    });
});
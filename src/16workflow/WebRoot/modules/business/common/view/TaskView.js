/**
 * User: Tony
 * Date: 13-4-6 --  下午4:30
 * 单个人工任务的概要显示
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/Task.html'
], function ($, _, Backbone, core, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        className:"task",
        template:_.template(tpl),
        events:{
            "click":"click"
        },
        initialize:function(options){
            var that=this;
            this.eb = options.eb;
            this.eb.on("Task:Remove",function(){
                that.unRender();
            },this);
            this.eb.on("Task:Search",function(text){
                if(text===null || text===""){
                    that.$el.show();
                }else if(null){
                    if(that.model.get("title").contains(text) ||
                        that.model.get("title").contains(text) ||
                        that.model.get("title").contains(text)){
                        that.$el.show();
                    }else{
                        that.$el.hide();
                    }
                }
            },this);
        },
        click:function(){
            core.trigger("Business:Task",this.model);
        },
        render:function(){
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender:function(){
            this.undelegateEvents();
            this.eb.off(null,null,this);
            this.remove();
        }

    });
});
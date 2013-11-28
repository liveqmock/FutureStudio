//tab用来实体化一个tab区域，并require装载，具体的view，进行render，
//可以提供一个键值对的参数
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './TabContentView',
    'text!./TabLi.html',
    'bootstrap'
],function($, _, Backbone, core, TabContentView, atpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"li",
        className:"",
        events:{
            "click .close":"close",
            "click":"show"
        },
        template:_.template(atpl),
        initialize:function(options){
            var that=this;

            this.contentView = options.contentView;
            this.contentId = options.contentId;
            this.headerName = options.headerName;
            this.eb = options.eb;

            this.content = new TabContentView({contentView:this.contentView}).render();
            this.content.$el.attr("id",this.contentId);
            this.listenTo(this.content,"removed",this.close);
            this.$el.attr("id",this.contentId+"-a");
        },
        render:function(){
            this.$el.append(this.template({
                id:this.contentId,
                name:this.headerName}));
            return this;
        },
        close:function(e){
            if(e){
                e.stopPropagation();
            }
            //TODO
            // 如果是已经active的，激活其他的tab
            this.unRender();
            if(this.$el.hasClass("active")){
                this.eb.trigger("Tab:Active:Close");
            }
        },
        show:function(){
            this.$("a").tab('show');
        },
        removeClose:function(){
            this.$("button").remove();
        },
        unRender:function(){
            if(this.content && this.content.unRender && typeof(this.content)==="function"){
                this.content.unRender();
                this.content=null;
            }
            this.remove();
        }
    });
});
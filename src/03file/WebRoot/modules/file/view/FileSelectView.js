define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/FileSelectItem.html',
    'ZeroClipboard',
    'bootstrap',
    'jquery.ui'
],function($, _, Backbone, core, tpl, ZeroClipboard){
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{

        },
        initialize:function(options){
            var that=this;

            this.selectMode = options.selectMode;
            this.eb = options.eb;

            //_.bindAll(this,"downloadFile","deleteFile","showUrl");
            this.$el.html(this.template(this.model.toJSON()));
            if(this.selectMode==="single"){
                this.$(".select-radio-td").show();
            }else{
                this.$(".select-checkbox-td").show();
            }
            this.eb.on("File:View:Destroy",function(){
                that.unRender();
            },this);
        },
        render:function(){
            this.$el.attr("file-id",this.model.get("id"));
            this.$el.draggable();
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
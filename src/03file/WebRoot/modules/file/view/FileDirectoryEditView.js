define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/FileDirectoryEdit.html',
    'bootstrap'
],function($, _, Backbone, core, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{
            "click .button-save":"buttonSave",
            "click .button-cancel":"buttonCancel"
        },
        initialize:function(options){
            this.eb = options.eb;
            this.$el.html(this.template(this.model.toJSON()));
        },
        buttonSave:function(){
            var that=this,
                data1=this.$(".input-name").val(),
                data2=this.$(".input-comments").val();
            $.ajax({
                url:"file_updateDirectory.action",
                dataType:"json",
                type:"post",
                data:{
                    id:this.model.get("id"),
                    data1:data1,
                    data2:data2
                },
                success:function(res){
                    that.unRender();
                    core.trigger("Message:Info","成功",res.message);
                    that.model.set("name",data1);
                    that.model.set("name",data2);
                },
                error:function(res){
                    core.trigger("Message:Error","失败",res.responseText);
                }
            });
        },
        render:function(){
            return this;
        },
        buttonCancel:function(){
            this.unRender();
        },
        unRender:function(){
            this.remove();
            this.eb.trigger("FileTemp:Removed");
            this.eb.trigger("FileDirectory:Refresh");
        }
    });
});
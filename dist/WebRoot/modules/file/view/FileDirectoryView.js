define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!modules/base/tpl/td.html',
    'jquery.ui'
],function($, _, Backbone, core, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{
            "click":"click"
        },
        initialize:function(options){
            var that=this;
            this.eb = options.eb;
            this.nodirecoty = options.nodirectory;

            this.eb.on("File:Directory:Destroy",function(){
                that.unRender();
            },this);
            this.eb.on("File:Directory:Search",function(value){
                var s="";
                s+=that.model.get("name");
                s+=that.model.get("comments");
                if(s.indexOf(value)>-1){
                    that.$el.show();
                }else{
                    that.$el.hide();
                }
            },this);
            this.eb.on("File:Directory:SearchClear",function(){
                that.$el.show();
            },this);
            if(this.nodirecoty){
                this.model=new Backbone.Model();
                this.model.set("id","0");
                this.model.set("name","未分组");
                this.model.set("comments","未分组文件存放于此，非实际存在");
            }
            this.$el.html(this.template(this.model.toJSON()));
        },
        render:function(){
            var that=this;
            this.$el.droppable({
                drop:function(e,ui){
                    that.$el.removeClass("active");
                    if(that.model.get("id")==="0"){
                        core.trigger("Message:Info","无法移动","无法移动文件到“未分组”文件夹");
                        return false;
                    }
                    var dragElement=$(ui.draggable[0]);
                    if(that.model.get("id")===dragElement.attr("directory-id")){
                        core.trigger("Message:Info","无法移动","无法移动文件到“原文件夹”");
                        return false;
                    }
                    that.moveFile(dragElement.attr("file-id"));
                },
                out:function(e,ui){
                    that.$el.removeClass("active");
                },
                over:function(e,ui){
                    that.$el.addClass("active");
                }
            });
            return this;
        },
        moveFile:function(fileId){
            var that=this;
            $.ajax({
                url:"file_moveFile.action",
                dataType:"json",
                type:"post",
                data:{
                    data3:fileId,
                    data4:this.model.get("id")
                },
                success:function(res){
                    core.trigger("Message:Info","移动成功",res.message);
                    that.eb.trigger("FileList:Refresh");
                },
                error:function(res){
                    core.trigger("Message:Error","移动失败",res.responseText);
                }
            });
            console.log("move"+fileId+"to "+this.model.get("id"));
        },
        click:function(){
            this.eb.trigger("File:Directory:Click",this.model);
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
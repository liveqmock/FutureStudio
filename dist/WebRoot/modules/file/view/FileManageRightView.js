define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './FileListView',
    './FileUploadView',
    './FileDirectoryEditView',
    'text!../tpl/FileManageRight.html'
],function($, _, Backbone, core, FileListView,FileUploadView,
           FileDirectoryEditView,
           tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"file-manage-right-view",
        template: _.template(tpl),
        events:{
            "click .move-up-button":"moveUp",
            "click .move-down-button":"moveDown",
            "click .update-button":"updateDir",
            "click .delete-button":"deleteDir",
            "click .upload-button":"uploadFile"
        },
        initialize:function(options){
            var that=this;
            this.eb = options.eb;

            this.eb.on("FileList:Refresh",function(){
                that.files.render();
            },this);
            this.eb.on("FileDirectory:Refresh",function(){
                that.refreshDirectoryInfo();
            },this);
            this.eb.on("FileTemp:Removed",function(){
                if(that.files){
                    this.$(".directory-files").html(this.files.el);
                }
                that.temp=null;
            },this);
            this.noDirectory=new Backbone.Model();
            this.noDirectory.set("id","0");
            this.noDirectory.set("name","未分组");
            this.noDirectory.set("comments","未分组文件存放于此，非实际存在");
        },
        render:function(){
            return this;
        },
        uploadFile:function(){
            new FileUploadView({
                model:this.model,
                eb:this.eb
            }).render();
        },
        moveUp:function(){
            this.eb.trigger("File:Directory:MoveUp",this.model);
        },
        moveDown:function(){
            this.eb.trigger("File:Directory:MoveDown",this.model);
        },
        updateDir:function(){
            //TODO
            if(this.temp){
                this.temp.unRender();
            }
            this.temp=new FileDirectoryEditView({
                eb:this.eb,
                model:this.model
            }).render();
            if(this.files){
                this.files.$el.detach();
            }
            this.$(".directory-files").html(this.temp.el);
        },
        deleteDir:function(){
            var that=this;
            if(this.files.collection.length>0){
                core.trigger("Message:Error","无法删除","文件夹不为空，不能删除");
            }else{
                $.ajax({
                    url:"file_deleteDirectory.action",
                    data:{
                        id:this.model.get("id")
                    },
                    success:function(res){
                        core.trigger("Message:Info","成功",res.message);
                        that.eb.trigger("FileDirectory:Deleted");
                        that.loadDirectory(that.noDirectory);
                    },
                    error:function(res){
                        core.trigger("Message:Error","出错",res.responseText);
                    }
                });
            }
            console.log("delete dir");
        },
        refreshDirectoryInfo:function(){
            var that=this;
            $.ajax({
                url:"fileDirectory_findDirectory.action",
                data:{
                    id:this.model.get("id")
                },
                success:function(res){
                    that.model.set("name",res.directory.name);
                    that.model.set("comments",res.directory.comments);
                    that.$el.html("");
                    that.$el.html(that.template(that.model.toJSON()));
                    if(that.files){
                        that.files.reload();
                        that.$(".directory-files").html(that.files.el);
                    }
                },
                error:function(){
                    core.trigger("Message:Error","错误","刷新文件夹信息出错");
                }
            });

        },
        loadDirectory:function(d){
            if(this.files){
                this.files.unRender();
            }
            this.eb.trigger("File:View:Destroy");
            this.$el.html("");
            this.$el.html(this.template(d.toJSON()));
            if(d.get("id")==="0"){
                this.$("td .btn").hide();
            }
            this.files=new FileListView({
                eb:this.eb,
                model:d
            });
            this.model=d;
            this.files.render();
            this.$(".directory-files").html(this.files.el);
        },
        search:function(value){
            //TODO 这里要搜索哪些字段，有待处理
        },
        unRender:function(){
            if(this.temp){
                this.temp.unRender();
                this.temp=null;
            }
            if(this.files){
                this.files.unRender();
                this.files=null;
            }
            this.eb.off(null,null,this);
            this.remove();
        }
    });
});
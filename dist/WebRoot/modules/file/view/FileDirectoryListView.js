define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './FileDirectoryView',
    '../collection/FileDirectoryCollection',
    'text!../tpl/FileDirectoryList.html'
],function($, _, Backbone, core, FileDirectoryView, FileDirectoryCollection, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"file-directory-list",
        events:{
            "input .search-query":"searchDirectory",
            "click .addDirectory":"add"
        },
        template: _.template(tpl),
        initialize:function(options){
            var that=this;
            this.eb = options.eb;

            this.collection=new FileDirectoryCollection();
            this.collection.on("reset",this.load,this);
            this.$el.html(this.template());
            this.eb.on("FileDirectory:Deleted",function(){
                that.render();
            },this);
            this.eb.on("File:Directory:MoveUp",function(directory){
                that.moveUp(directory);
            },this);
            this.eb.on("File:Directory:MoveDown",function(directory){
                that.moveDown(directory);
            },this);
        },
        moveDown:function(directory){
            var k=this.collection.indexOf(this.collection.get(directory.get("id"))),
                up;
            if(this.collection.length-1>k){
                up=this.collection.at(k+1);
                this.moveDirectory(directory.get("id"),up.get("id"));
            }else{
                core.trigger("Message:Error","不可移动","文件夹已经在末尾，不能继续下移");
            }
        },
        /**
         * 移动两个目录的位置，
         * @param data1 需要下移的目录
         * @param data2 上移的目录
         */
        moveDirectory:function(data1,data2){
            var that=this;
            $.ajax({
                url:"fileDirectory_moveDirectory.action",
                type:"post",
                dataType:"json",
                data:{
                    data1:data1,
                    data2:data2
                },
                success:function(res){
                    core.trigger("Message:Info","成功","移动目录成功");
                    that.render();
                },
                error:function(res){
                    core.trigger("Message:Error","失败","移动目录失败");
                }

            });
        },
        moveUp:function(directory){
            var k=this.collection.indexOf(this.collection.get(directory.get("id"))),
                down;
            if(k!==0){
                down =this.collection.at(k-1);
                this.moveDirectory(down.get("id"),directory.get("id"));
            }else{
                core.trigger("Message:Error","不可移动","文件夹已经在顶端，不能继续上移");
            }
        },
        addDisable:function(){
            this.$(".add-directory").hide();
        },
        searchDisable:function(){
            this.$(".search-area").hide();
        },
        add:function(){
            //新增目录
            var that=this;
            $.ajax({
                url:"file_addDirectory.action",
                type:"post",
                data:{
                    data1:this.$(".file-directory-name").val(),
                    data2:""
                },
                success:function(){
                    that.collection.fetch();
                },
                error:function(response){

                }
            });
        },
        load:function(){
            var that=this;
            this.eb.trigger("File:Directory:Destroy");
            this.$("tbody").html("");
            if(this.collection.length<1){
                this.$("tbody").html("尚未创建目录");
            }
            //尚未分类的目录
            that.$("tbody").append(new FileDirectoryView({
                eb:that.eb,
                nodirecoty:true
            }).render().el);
            this.collection.each(function(m){
                that.$("tbody").append(new FileDirectoryView({
                    eb:that.eb,
                    model:m
                }).render().el);
            });
        },
        render:function(){
            this.collection.fetch();
            return this;
        },
        searchDirectory:function(){
            var value=this.$(".search-query").val();
            if(value){
                this.eb.trigger("File:Directory:Search",value);
            }else{
                this.eb.trigger("File:Directory:SearchClear");
            }

        },
        unRender:function(){
            this.eb.trigger("File:Directory:Destroy");
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
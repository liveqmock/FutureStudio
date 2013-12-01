define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './FileView',
    '../collection/FileCollection',
    'text!../tpl/FileList.html'
],function($, _, Backbone, core, FileView, FileCollection, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"",
        template: _.template(tpl),
        events:{
            "input .search-query":"inputQuery",
            "click .button-query":"queryFile",
            "click .button-next":"nextPage",
            "click .button-prev":"prevPage"
        },
        initialize:function(options){
            var that=this;
            this.eb = options.eb;

            this.collection=new FileCollection();
            this.collection.on("reset",function(){
                that.reload();
            },this);
            this.page=1;
            this.pageSize=20;
            this.directory=null;
            this.query=null;
        },
        nextPage:function(){
            if(this.page<this.collection.countPage){
                this.page++;
                this.render();
            }
        },
        prevPage:function(){
            if(this.page>1){
                this.page--;
                this.render();
            }
        },
        inputQuery:function(){
            var query=this.$(".search-query").val();
            if(query.length===0){
                this.eb.trigger("FileQuery:Clear");
            }else{
                this.eb.trigger("FileQuery:Query",query);
            }
        },
        queryFile:function(){
            this.page=1;
            this.query=this.$(".search-query").val();
            this.render();
        },
        reload:function(){
            var that=this;
            this.eb.trigger("File:View:Destroy");
            this.$el.html(this.template());
            this.$(".page-area .btn").hide();
            if(this.page<this.collection.countPage){
                this.$(".button-next").show();
            }
            if(this.page>1){
                this.$(".button-prev").show();
            }
            this.collection.each(function(m){
                that.$(".list-area").append(new FileView({
                    eb:that.eb,
                    directoryId:that.model.get("id"),
                    model:m
                }).render().el);
            });
        },
        render:function(){
            this.collection.fetch({
                data:{
                    id:this.model.get("id"),
                    page:this.page,
                    pageSize:this.pageSize,
                    query:this.query
                },
                error:function(){
                    core.trigger("Message:Error","出错","获取文件列表出错");
                }
            });
            return this;
        },
        unRender:function(){
            this.eb.trigger("File:View:Destroy");
            this.undelegateEvents();
            this.remove();
        }
    });
});
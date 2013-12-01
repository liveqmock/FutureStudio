define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './view/FileDirectoryListView',
    './view/FileListView',
    './view/FileManageRightView',
    'text!modules/base/tpl/fluid210.html'
],function($, _, Backbone, core, FileDirectoryListView, FileListView, FileManageRightView, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"file-manage-view",
        template: _.template(tpl),
        initialize:function(){
            var that=this;
            this.$el.html(this.template());
            this.eb = _.extend({},Backbone.Events);
            this.eb.on("File:Directory:Click",function(directory){
                that.showRight(directory);
            },this);

            this.eb.on("FileDirectory:Refresh",function(){
                that.leftView.render();
                that.rightView.refreshDirectoryInfo();
            },this);
        },
        render:function(){
            this.leftView=new FileDirectoryListView({eb:this.eb}).render();
            this.$(".span2").html(this.leftView.el);
            this.rightView=new FileManageRightView({eb:this.eb});
            this.rightView.render();
            this.$(".span10").html(this.rightView.el);
            return this;
        },

        showRight:function(model){
            this.rightView.loadDirectory(model);
        },
        unRender:function(){
            if(this.rightView){
                this.rightView.unRender();
                this.rightView=null;
            }
            if(this.leftView){
                this.leftView.unRender();
                this.leftView=null;
            }
            this.eb.off(null,null,this);
            this.remove();
        }
    });
});
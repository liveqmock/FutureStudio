/**
 * FileSelectView.js    Author:QiYao
 * 文件对外提供服务
 */

define([
    "jquery",
    "underscore",
    "backbone",
    "./view/FileDirectoryListView",
    "./view/FileSelectListView",
    "text!./tpl/FileSelect.html"
], function ($, _, Backbone, FileDirectoryListView, FileSelectListView, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        className:"file-select-view",
        events:{
            "click .button-cancel":"hideModel",
            "click .button-confirm":"confirm"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;
            this.selectMode = options.selectMode;
            this.eb.on("File:Select:Destroy",function(){
                that.unRender();
            },this);
            this.left=new FileDirectoryListView({
                eb:this.eb
            });
            this.right=null;
            this.eb.on("File:Directory:Click",function(m){
                  that.loadFileList(m);
            });
        },
        confirm:function(){
            console.log("确定");
        },
        loadFileList:function(m){
            if(this.right){
                this.right.unRender();
            }
            this.right=new FileSelectListView({
                model:m,
                eb:this.eb,
                selectMode:this.selectMode
            }).render();
            this.$(".span9").html(this.right.el);
        },
        render: function () {
            var that=this;
            this.$el.html(this.template());
            this.left.render();
            this.left.addDisable();
            this.left.searchDisable();
            this.$(".span3").html(this.left.el);
            $("body").append(this.el);
            this.$(".modal").modal({
                show:true,
                backdrop:"static"
            });
            this.$(".modal").on("hidden",function(){
                that.unRender();
            });
            return this;
        },
        hideModel:function(){
            this.$(".modal").modal('hide');
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.left.unRender();
            if(this.right){
                this.right.unRender();
            }
            this.left=null;
            this.right=null;
            this.undelegateEvents();
            this.remove();
        }
    });
});
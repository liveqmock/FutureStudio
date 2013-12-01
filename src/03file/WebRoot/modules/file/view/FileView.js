define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/File.html',
    '../FileUtil',
    'ZeroClipboard',
    'bootstrap',
    'jquery.ui'
],function($, _, Backbone, core, tpl, FileUtil, ZeroClipboard){
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{
            "click .download-button":"downloadFile",
            //"click .url-button":"showUrl",
            "click .delete-button":"deleteFile"
        },
        initialize:function(options){
            var that=this,
                json=this.model.toJSON();
            json.directoryId=options.directoryId;
            this.eb = options.eb;

            this.$el.html(this.template(json));
            this.eb.on("File:View:Destroy",function(){
                that.unRender();
            },this);
            this.eb.on("FileQuery:Clear",function(){
                that.$el.show();
            },this);
            this.eb.on("FileQuery:Query",function(value){
                var s=that.model.get("name")+that.model.get("comments");
                if(s.indexOf(value)>-1){
                    that.$el.show();
                }else{
                    that.$el.hide();
                }
            },this);
//            this.$(".url-button").popover({
//                "placement":"bottom",
//                "title":"仅支持图片",
//                "trigger":"click",
//                "html":true,
//                "content":"<p>"+FileUtil.findImageFileUrl(this.model.get("id"))+"</p>"
//            });
        },
        showUrl:function(){

        },
        initCopy:function(){
            window.ZeroClipboard = ZeroClipboard;
            ZeroClipboard.setDefaults({
                moviePath: "swf/clipboard/ZeroClipboard.swf",
                amdModuleName: 'js/libs/ZeroClipboard'
            } );
            var clip = new ZeroClipboard(this.$(".url-button")[0]);
            clip.on('complete', function(client, args) {
//                this.style.display = 'none'; // "this" is the element that was clicked
//                alert("Copied text to clipboard: " + args.text );
                core.trigger("Message:Info","成功","复制到剪贴板成功,防盗链URL,本站外无法使用");
            } );
            clip.reposition();
        },
        render:function(){
            this.initCopy();
            this.$el.attr("file-id",this.model.get("id"));
            this.$(".drag").draggable({
                helper:"clone"
            });
            return this;
        },
        downloadFile:function(){
            core.trigger("File:Download","file_downloadFile.action?id="+this.model.get("id"));
        },
        deleteFile:function(){
            var that=this;
            $.ajax({
                url:"file_deleteFile.action",
                data:{
                    id:this.model.get("id")
                },
                success:function(){
                    that.eb.trigger("FileList:Refresh");
                    core.trigger("Message:Info","成功","删除文件成功");
                },
                error:function(response){
                    var html="";
                    try{
                        html=jQuery.parseJSON(response.responseText);
                    }catch(e){
                        html.message="";
                    }
                    core.trigger("Message:Error","失败",html.message);
                }
            });
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});
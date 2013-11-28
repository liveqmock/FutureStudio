define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/FileUpload.html',
    'modules/message/index',
    'jquery.form',
    'bootstrap'
],function($, _, Backbone, core, FileUpload,MessageView){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"file-upload-view",
        template: _.template(FileUpload),
        events:{
            "click .button-upload":"upload",
            "click .button-cancel":"cancel"
        },
        initialize:function(options){
            var that=this,
                json={};

            this.eb = options.eb;
            if(this.model){
                json.id=this.model.get("id");
            }else{
                json.id="0";
            }
            this.$el.html(this.template(json));
            this.$(".modal").on("hidden",function(){
                that.unRender();
            });
        },
        upload:function(){
            var that=this;
            this.$('form').ajaxSubmit({
                beforeSend: function() {
                    that.$(".progress").removeClass("hide").addClass("active");
                },
                uploadProgress: function(event, position, total, percentComplete) {
                    var percentVal = percentComplete + '%';
                    that.$(".progress .bar").css({
                        "width":percentVal
                    });
                },
                success: function(response) {
                    that.$(".progress .bar").css({
                        "width":"100%"
                    });
                    that.eb.trigger("File:Upload:Finish",response.message);
                    that.eb.trigger("FileList:Refresh");
                    MessageView.success({
                        body:"上传成功"
                    });
                    that.cancel();
                },
                error:function(){
                    MessageView.error({
                        body:"上传失败"
                    });
                }
            });
        },
        unRender:function(){
            this.undelegateEvents();
            this.remove();
        },
        cancel:function(){
            this.$(".modal").modal("hide");
        },
        render:function(){
            $("body").append(this.el);
            this.$(".modal").modal({
                backdrop:'static',
                show:true
            });
            return this;
        }
    });
});
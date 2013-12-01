define([
    'jquery',
    'underscore',
    'backbone',
    'core'
], function ($, _, Backbone,core
    ) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        initialize: function () {
        },
        render: function () {
            this.businessEventBus();
            this.tabEventBus();
            this.fileEventBus();
            return this;
        },
        businessEventBus:function(){
            core.on("Business:Task",function(model){
                require(['modules/business/common/MyTaskDetailContainerView'],function(TaskContainer){
                    var view = new TaskContainer({
                        model:model
                    });
                    view.render();
                    core.trigger("Tab:open",{
                        id:"task"+core.getCount(),
                        name:model.get("name"),
                        view:view
                    });
                });
            });
        },
        tabEventBus:function(){
            core.on("Tab:Close",function(view){
                var aId = view.$el.parent().attr("id");
                $("#"+aId+"-a").find(".close").trigger("click");
            });
        },
        fileEventBus:function(){
            core.on("File:Select",function(option){
                require(["modules/file/FileSelectView"],function(FileSelectView){
                    var eb={};
                    var selectMode="single";
                    if(option.eb){
                        eb=option.eb;
                    }else{
                        return false;
                    }
                    if(option.selectMode){
                        selectMode=option.selectMode;
                    }
                    var fs = new FileSelectView({
                        eb:eb,
                        selectMode:selectMode
                    });
                    fs.render();
                });
            });
            core.on("File:Download",function(url){
                var iframe=document.createElement("iframe");
                document.body.appendChild(iframe);
                iframe.src=url;
                iframe.style.display="none";
            });
            core.on("File:Upload",function(option){
               //上传完成后，会通过EB触发："File:Upload:Finish"  id
                require(['modules/file/view/FileUploadView'],function(FileUploadView){
                    var eb={};
                    var selectMode="single";
                    if(option.eb){
                        eb=option.eb;
                    }else{
                        return false;
                    }
                    var fs = new FileUploadView({
                        eb:eb
                    });
                    fs.render();
                });

            });
        },
        unRender: function () {
            this.undelegateEvents();
            this.remove();
        }
    });
});
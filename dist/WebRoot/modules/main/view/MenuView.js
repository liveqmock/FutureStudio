define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/base/model/BaseModel',
    'text!../tpl/Menu.html'
],function($, _, Backbone, core, BaseModel, tpl
    ){
    /**
     * 菜单，实体
     */
    "use strict";
    return Backbone.View.extend({
        tagName:"li",
        className:"studio-menu",
        events:{
            "click" :"click"
        },
        template:_.template(tpl),
        initialize:function(){
            _.bindAll(this,"click");
        },
        render:function(){
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        /**
         * 点击菜单动作
         */
        click:function(){
            //TODO
            var req = this.model.get("funcValue"),
                statusCode = parseInt(this.model.get("statusCode"),10),
                targetView,
                that=this;

            if(req.indexOf("business:")>-1){
                var re = req.substring(9,req.length);
                require([re],function(View){
                    targetView = new View();
                    this.openTab(targetView,this.model.get("name"));
                });
            }else if(statusCode === 3){
                require([req],function(View){
                    targetView = new View();
                    that.openTab(targetView,that.model.get("name"));
                });
            }

        },
        openTab:function(targetView,name){
            var options;
            if(targetView){
                targetView.render();
                options={
                    id:"menu"+core.getCount(),
                    name:name,
                    view:targetView
                };
                core.trigger("Tab:open",options);
            }
        }
    });
});
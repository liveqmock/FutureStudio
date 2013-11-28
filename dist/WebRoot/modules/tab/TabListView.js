//管理整个tab区域。将tab的信息放入一个collection里面方便进行检索。
//添加，删除tab
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './TabView',
    'modules/announce/index',
    'bootstrap'
],function($, _, Backbone, core, TabView, AnnounceView){
    "use strict";
    return Backbone.View.extend({
        tagName:"ul",
        className:"nav nav-tabs",
        id:"studio-tab",
        initialize:function(){
            this.idCount = 1;
            //打开Tab需要 id ，名字，view，三个参数
            var id,
                name,
                view,
                that=this;

            this.tabContents = $("#studio-tab-content");
            this.eb = _.extend({},Backbone.Events);
            this.listenTo(this.eb,"Tab:Active:Close",function(){
                $("#studio-tab a:last").tab('show');
            });
            this.listenTo(core,"Tab:open",function(options){
                //TODO 以后还是使用options的id替代，
                // TODO 如果已经存在了这个view，那么这个view已经被移除，然后再添加新的view进来
                that.idCount++;
                //id = options.id|| that.idCount +"Tab";
                id = that.idCount +"Tab";
                name = options.name || this.idCount + "Tab";
                view = options.view;
                that.addTab(id,name,view,true);
            });
        },
        render:function(){
            var announce1 = new AnnounceView().render();
            this.addTab("an1","消息",announce1,false);
            return this;
        },
        addTab:function(id,name,view,canClose){
            var tab;
            tab = new TabView({
                contentId:id,
                headerName:name,
                contentView:view,
                eb:this.eb
            }).render();
            if(!canClose){
                tab.removeClose();
            }
            this.$el.append(tab.el);
            this.tabContents.append(tab.content.el);
            tab.show();
        }
    });
});
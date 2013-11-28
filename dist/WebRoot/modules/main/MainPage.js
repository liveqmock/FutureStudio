define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/tab/TabListView',
    './view/NavMenuListView',
    './EventBusView',
    'privilege',
    'bootstrap',
    'jquery.cookie'
],function($, _, Backbone, core, TabListView, NavMenuListView,
		EventBusView,
		privilege){
    /**
     * 初始化，主页面view， 大型core事件的处理中心
     * 1.菜单
     * 2.登陆人信息
     * 3.tab
     */
    "use strict";
    return Backbone.View.extend({
        initialize:function(){
            var ebv = new EventBusView();
            ebv.render();
        },
        render:function(){
            var that=this;
            //初始化tab区域
            this.tabListView = new TabListView().render();
            $("#studio-tab-content").before(this.tabListView.el);
            $("#studio-tab a:last").tab('show');
            //初始化菜单区域
            this.navMenuListView = new NavMenuListView().render();
            $("#studio-nav-menu").append(this.navMenuListView.el);
            //抓取数据，
            $.ajax({
                url:"oaPrivilege_findSysFunction.action",
                type:"get",
                dataType:"json",
                success:function(resp){
                    core.sysInfo.privileges = resp.privileges;
                    for(var p=0;p<core.sysInfo.privileges.length;p++){
                        privilege.collection.add({
                            id: core.sysInfo.privileges[p].id
                        });
                    }
                    //初始化菜单基础数据
                    that.navMenuListView.collection.add(resp.menus);
                    that.navMenuListView.loadMenu();
                    //that.setCompanyInfo();
                    $(".signOut").click(function(){
                            $.ajax({
                                url:"oaUnfilter_out.action",
                                type:"get",
                                dataType:"json",
                                success:function(){
                                    window.location.href="oaSignIn.action";
                                }
                            });
                    });
                },
                error:function(){

                }
            });


            return this;
        },
        /**
         * 在页面设置公司的信息
         */
        setCompanyInfo:function(){
            this.companyInfo=decodeURIComponent($.cookie("cc").replace(/\"/g,''));
            $(".company-name").html(this.companyInfo);
            document.title=this.companyInfo;
        }
    });
});
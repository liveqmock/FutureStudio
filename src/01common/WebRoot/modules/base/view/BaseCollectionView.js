define([
    "jquery",
    "underscore",
    "backbone",
    "modules/loading/index",
    "modules/page/index"
], function ($, _, Backbone, Loading, PageView) {
    "use strict";
    return Backbone.View.extend({
        /**CollectionView
         * 需要指定：
         * this.Collection
         * this.ItemView
         * this.template
         */
        Pageable:true,//默认执行分页处理
        initialize:function(options){
            this.eb = _.extend({},Backbone.Events);
            this.listenTo(this.eb,"Page:Change",function(p){
                this.fetchData.page = p;
                this.fetch();
            });
            this.collection = new this.Collection();
            this.listenTo(this.collection,"reset",this.loaded);

            this.fetchData = {
                page : 1
            };

            if(options && options.fetchData){
                this.fetchData = _.extend(this.fetchData,options.fetchData);
            }
            this.loading = new Loading({
                target:this.$el,
                eb:this.eb
            }).render();

            this.listenTo(this,"Item:BeforeRender",this.beforeRender);
            this.listenTo(this,"Item:AfterRender",this.afterRender);

            this.fsInit();
            return this;
        },
        fsInit:function(){
            return this;
        },
        /**
         * 自定义模版方法，直接覆盖此方法
         */
        getHtml:function(){
            return this.template();
        },
        /**
         * 获取，显示ItemView的容器
         */
        getListContainer:function(){
            return this.$(".list-area");
        },
        /**
         * 获取，显示Page信息的容器
         */
        getPageContainer:function(){
            return this.$(".page-area");
        },
        getEmptyHtml:function(){
            return '<div style="display: block;text-align: center;font-size: 16px">空荡荡的</div>';
        },
        /**
         * 可以重写容器的位置：
         * this.getListContainer() 列表容器
         * this.getPageContainer() 分页容器
         */
        loaded:function(){
            var $listContainer = this.getListContainer(),
                $pageContainer;
            this.eb.trigger("Item:Destroy");
            $listContainer.html("");
            if(this.collection.length===0){
                $listContainer.html(this.getEmptyHtml());
            }else{
                this.collection.each(function(m){
                    $listContainer.append(new this.ItemView({
                        model:m,
                        eb:this.eb
                    }).render().el);
                },this);
            }
            if(this.pageView){
                this.pageView.unRender();
                this.pageView = null;
            }
            if(this.Pageable){
                $pageContainer = this.getPageContainer();
                this.pageView = new PageView({
                    page:this.collection.page,
                    pageSize:this.collection.pageSize,
                    count:this.collection.count,
                    eb:this.eb
                }).render();
                $pageContainer.html(this.pageView.el);
            }

            return this;
        },
        beforeRender:function(){
            return this;
        },
        render: function () {
            var that = this;

            this.trigger("Item:BeforeRender");

            this.$el.html(this.getHtml());

            setTimeout(function(){
                that.trigger("Item:AfterRender");
            },10);

            this.fetch();

            return this;
        },
        afterRender:function(){
            return this;
        },
        fetch:function(){
            var that = this,
                resText;

            this.fetchData.page = this.page;
            this.loading.start();
            this.collection.fetch({
                data:this.fetchData,
                reset:true,
                success:function(){
                    that.loading.stop();
                },
                error:function(res){
                    that.loading.stop();
                    resText = JSON.parse(res.responseText);
                    alert(resText.message);
                }
            });

            return this;
        },
        unRender: function () {
            this.eb.trigger("Item:Destroy");
            if(this.pageView){
                this.pageView.unRender();
                delete this.pageView;
            }
            this.remove();
            delete this.collection;
            delete this.eb;

            return this;
        }
    });
});
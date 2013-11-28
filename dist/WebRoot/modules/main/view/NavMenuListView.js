define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/main/view/NavMenuView',
    'modules/base/collection/BaseCollection'
],function($, _, Backbone, core, NavMenuView, Collection){
    /**
     * 初始化，导航菜单
     */
    return Backbone.View.extend({
        tagName:"ul",
        className:"nav nav-menu-list",
        events:{

        },
        initialize:function(){
            var that = this;
            this.collection = new Collection();
            this.collection.comparator = function(nav){
                return nav.get("priority");
            };
            this.count=1;
        },
        render:function(){
            $(".studio-nav-menu").append(this.$el);
            return this;
        },
        loadMenu:function(){
            var that = this;
            this.collection.each(function(model){
                that.$el.append(new NavMenuView({model:model}).render().el);
            });
        }
    });
});
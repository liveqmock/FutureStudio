define([
    "jquery",
    "underscore",
    "backbone",
    "./item",
    "./Collection",
    "modules/base/view/BaseCollectionView",
    "text!./tpl/item.html"
], function ($, _, Backbone, Item, Collection, BaseCollectionView, tpl) {
    "use strict";
    //现金账户，菜单管理入口
    return BaseCollectionView.extend({
        className:"pay-account",
        template: _.template(tpl,null,{variable:"args"}),
        Collection:Collection,
        ItemView:Item
    });
});
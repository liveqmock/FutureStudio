define([
    "jquery",
    "underscore",
    "backbone",
    "./item",
    "./Collection",
    "modules/base/view/BaseCollectionView",
    "text!./tpl/index.html"
], function ($, _, Backbone, Item, Collection, BaseCollectionView, tpl) {
    "use strict";
    //充值付款，管理菜单入口
    return BaseCollectionView.extend({
        className:"pay-payment",
        template: _.template(tpl,null,{variable:"args"}),
        Collection:Collection,
        ItemView:Item
    });
});
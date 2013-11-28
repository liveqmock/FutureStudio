define([
    "jquery",
    "underscore",
    "modules/base/view/BaseCollectionView",
    "./Collection",
    "./ItemView",
    "text!./tpl/index.html"
], function ($, _, BaseCollectionView, Collection, ItemView, tpl) {
    "use strict";
    /**
     * 项目收入记录
     */
    return BaseCollectionView.extend({
        Collection:Collection,
        ItemView:ItemView,
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .btn-refresh":"fetch"
        }
    });
});
define([
    "jquery",
    "underscore",
    "backbone",
    "modules/base/view/BaseItemView",
    "text!./tpl/item.html"
], function ($, _, Backbone, BaseItemView, tpl) {
    "use strict";
    return BaseItemView.extend({
        template: _.template(tpl,null,{variable:"args"})
    });
});
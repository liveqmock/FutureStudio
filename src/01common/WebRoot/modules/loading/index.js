define([
    "jquery",
    "underscore",
    "backbone",
    "css!./assets/index.css"
], function ($, _, B) {
    "use strict";
    var LoadingImgPath = "/modules/loading/assets/loading.gif";
    return B.View.extend({
        className:"fs-loading-wrapper",
        /**
         * 需要指定target
         */
        initialize: function (options) {
            this.$container = options.target.first();
        },
        render: function () {
            this.$el.append('<img src="'+LoadingImgPath+'">');
            return this;
        },
        start:function(){
            this.$container.append(this.el);
        },
        stop:function(){
            this.unRender();
        },
        unRender: function () {
            this.remove();
        }
    });
});
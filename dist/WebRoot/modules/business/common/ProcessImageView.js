/**
 * ProcessImageView.js    Author:QiYao
 * 用于显示流程的图片信息
 */

define([
    "jquery",
    "underscore",
    "backbone"
], function ($, _, Backbone) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"process-image",
        initialize: function () {
            //state 是active的才有定位信息

        },
        render: function () {
            return this;
        }
    });
});
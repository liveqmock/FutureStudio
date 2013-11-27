define([
    "underscore",
    "backbone"
], function (_, Backbone) {
    "use strict";
    return Backbone.Model.extend({
        url: "/fs/customer/customerQuery_detail.action",
        parse: function (res) {
            return res.customer;
        }
    });
});
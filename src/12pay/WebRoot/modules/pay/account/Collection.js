define([
    "underscore",
    "backbone",
    "modules/base/collection/BaseCollection"
], function (_, Backbone) {
    "use strict";
    return Backbone.Collection.extend({
        url: "/fs/pay/account/query_list.action",
        fsParse:function(res){
            return res.cashAccounts;
        }
    });
});
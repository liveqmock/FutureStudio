define([
    "underscore",
    "backbone",
    "modules/base/collection/BaseCollection"
], function (_, Backbone, BaseCollection) {
    "use strict";
    return BaseCollection.extend({
        url: "/fs/pay/payment/query_list.action",
        fsParse:function(res){
            return res.payments;
        }
    });
});
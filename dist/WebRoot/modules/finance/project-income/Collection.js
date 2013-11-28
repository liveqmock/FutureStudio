define([
    "modules/base/collection/BaseCollection"
], function (BaseCollection) {
    "use strict";
    return BaseCollection.extend({
        url: "/fs/finance/ProjectIncome/query_list.action",
        fsParse:function(res){
            return res.incomes;
        }
    });
});
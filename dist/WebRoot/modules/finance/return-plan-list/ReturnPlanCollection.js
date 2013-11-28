define([
    "underscore",
    "backbone"
], function (_, Backbone) {
    "use strict";
    return Backbone.Collection.extend({
        url: "/fs/finance/ProjectReturnPlan/query_list.action",
        Model: Backbone.Model,
        parse: function (res) {
            this.project = new Backbone.Model(res.project);
            this.page = res.page;
            this.count = res.count;
            this.pageSize = res.pageSize;
            return res.projectReturnPlans;
        }
    });
});
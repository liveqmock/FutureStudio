define([
    "underscore",
    "backbone"
], function (_, Backbone) {
    "use strict";
    return Backbone.Collection.extend({
        url: "/fs/finance/ProjectReturnPlan/query_listProject.action",
        Model: Backbone.Model,
        parse: function (res) {
            this.project = new Backbone.Model(res.project);
            return res.projectReturnPlans;
        }
    });
});
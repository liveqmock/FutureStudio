define([
    'jquery',
    'underscore',
    'backbone',
    '../model/DepartmentModel'
],function($, _, Backbone, DepartmentModel){
    "use strict";
    return Backbone.Collection.extend({
        model:DepartmentModel,
        url:"oaDepartment_findDeptTree.action",
        parse:function(response){
            return response.departments;
        },
        comparator:function(dept){
            return dept.get("priority");
        }
    });
});
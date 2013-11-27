/**
 * Tony
 */
define([
    "jquery",
    "underscore",
    "backbone"
], function ($, _, Backbone) {
    "use strict";
	var sysInfo = {};
    var core = {
            initialize: function() {
                this.sysinfo = _.extend({}, sysInfo);
                this.count=10000;
                _.extend(this, Backbone.Events);
            },
            getCount:function(){
                this.count++;
                return this.count;
            }
        };

    core.initialize();
    core.sysInfo = {};

    return core;
});


/**
 * Tony
 */
requirejs.config({
    urlArgs: "v=1.0.0",
    paths:{
        "jquery":"js/libs/jquery-1.8.3",
        "jquery.fsspinner":"js/libs/jquery.fsspinner",
        "jquery.cookie":"js/libs/jquery.cookie",
        "jquery.autoellipsis":"js/libs/jquery.autoellipsis-1.0.10",
        "jquery.ztree":"js/libs/jquery.ztree.all-3.5",
        "jquery.form":"js/libs/jquery.form",
        "jquery.ui":"js/libs/jquery.ui.1.10.3.customer",
        "bootstrap":"js/libs/bootstrap",
        "domReady":"js/libs/domReady",
        "text":"js/libs/text",
        "underscore":"js/libs/underscore",
        "backbone":"js/libs/backbone",
        "ZeroClipboard":"js/libs/ZeroClipboard",
        "core":"js/core",
        "fputil":"js/fputil",
        "studio":"js/studio",
        "privilege":"modules/base/view/PrivilegeView"
    },

    shim: {
        "underscore":{
            exports:"_"
        },
        "jquery.fsspinner":["jquery"],
        "jquery.cookie":["jquery"],
        "jquery.ztree":["jquery"],
        "jquery.form":["jquery"],
        "jquery.ui":["jquery"],
        "bootstrap": ["jquery"],
        "backbone":["jquery","underscore"],
        "ZeroClipboard":{
            exports:"ZeroClipboard"
        }
    },

    map: {
        '*': {
        'css': 'js/libs/css'
        }
    }
});

require([
    "jquery",
    "studio"
], function ($, studio) {
    "use strict";
    if(!window.console && !console.log){
        window.console = {
            log : function(){},
            warn : function(){},
            error : function(){},
            time : function(){},
            timeEnd : function(){}
        };
    }
    $(function() {
        studio.init();
    });
});


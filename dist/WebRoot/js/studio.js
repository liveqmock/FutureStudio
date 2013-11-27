/**
 * Tony
 */
define([
    "jquery",
    "underscore",
    "backbone",
    "core",
    "modules/main/MainPage",
    "bootstrap",
    "jquery.cookie"
], function ($, _, Backbone, core, Main) {
    "use strict";
    var setupAjax = function () {
            $.ajaxSetup({
                cache: false
            });
            $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
                if(!$.cookie("ci") || !$.cookie("ca")){
                    window.location="signin.jsp";
                }
                var originalContext = originalOptions.context || originalOptions;
                originalContext._error = originalOptions.error;
                options.error = function(jqx, setting, error){
                    if(jqx.responseText.indexOf("needLogin")>-1){
                        window.location = "signin.jsp";
                    }else{
                        originalOptions.error(jqx, setting, error);
                    }
                };
            });
        },

        setupSystemWideEvent = function () {
            $(window).resize(function () {
                core.trigger("window:resize");
                core.trigger("clear:temporary");
            });

            $("html").bind("click contextmenu", function () {
                core.trigger("clear:temporary");
            });

            $('body').on("click contextmenu", ".temporary", function (e) {
                    e.stopPropagation();
            });
        };



    return {
        init:function () {
            setupAjax();
            setupSystemWideEvent();
            var m = new Main().render();
        }
    };
});


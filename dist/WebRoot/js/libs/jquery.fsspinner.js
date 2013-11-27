/**
 * Tony
 */
(function($){

    $.fn.extend({
        fsSpinner: function(options) {

            var defaults = {
                disable: false
            };

            var options =  $.extend(defaults, options);

            return this.each(function() {

                var o =options,
                    ele = $(this),
                    spinnerInterval,
                    spinnerOuter,
                    spinnerInner,
                    pos = 0;


                if(o.disable) {
                    clearInterval(ele.data("spinnerInterval"));
                    $("div.spinner", ele).remove();
                    $(this).removeClass("spinner-wrapper");

                } else {
                    spinnerOuter= $("<div class='spinner'></div>");
                    spinnerInner = $("<div></div>");

                    spinnerOuter.append(spinnerInner);
                    ele.append(spinnerOuter);
                    ele.addClass("spinner-wrapper");
                    ele.data("spinnerInterval", setInterval(function () {
                        pos = (pos + 1) % 12;
                        spinnerInner.css("top", -pos * 42 + "px");
                    }, 56));

                }
            });
        }
    });
})(jQuery);
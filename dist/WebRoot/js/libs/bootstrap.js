/**
* bootstrap.js v3.0.0 by @fat and @mdo
* Copyright 2013 Twitter Inc.
* http://www.apache.org/licenses/LICENSE-2.0
*
*add by tony slider datepiker datetimepicker
*/
if (!jQuery) { throw new Error("Bootstrap requires jQuery") }

/* ========================================================================
 * Bootstrap: transition.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#transitions
 * ========================================================================
 * Copyright 2013 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // CSS TRANSITION SUPPORT (Shoutout: http://www.modernizr.com/)
  // ============================================================

  function transitionEnd() {
    var el = document.createElement('bootstrap')

    var transEndEventNames = {
      'WebkitTransition' : 'webkitTransitionEnd'
    , 'MozTransition'    : 'transitionend'
    , 'OTransition'      : 'oTransitionEnd otransitionend'
    , 'transition'       : 'transitionend'
    }

    for (var name in transEndEventNames) {
      if (el.style[name] !== undefined) {
        return { end: transEndEventNames[name] }
      }
    }
  }

  // http://blog.alexmaccaw.com/css-transitions
  $.fn.emulateTransitionEnd = function (duration) {
    var called = false, $el    = this
    $(this).one($.support.transition.end, function () { called = true })
    var callback = function () { if (!called) $($el).trigger($.support.transition.end) }
    setTimeout(callback, duration)
    return this
  }

  $(function () {
    $.support.transition = transitionEnd()
  })

}(window.jQuery);

/* ========================================================================
 * Bootstrap: alert.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#alerts
 * ========================================================================
 * Copyright 2013 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // ALERT CLASS DEFINITION
  // ======================

  var dismiss = '[data-dismiss="alert"]'
  var Alert   = function (el) {
    $(el).on('click', dismiss, this.close)
  }

  Alert.prototype.close = function (e) {
    var $this    = $(this)
    var selector = $this.attr('data-target')

    if (!selector) {
      selector = $this.attr('href')
      selector = selector && selector.replace(/.*(?=#[^\s]*$)/, '') // strip for ie7
    }

    var $parent = $(selector)

    if (e) e.preventDefault()

    if (!$parent.length) {
      $parent = $this.hasClass('alert') ? $this : $this.parent()
    }

    $parent.trigger(e = $.Event('close.bs.alert'))

    if (e.isDefaultPrevented()) return

    $parent.removeClass('in')

    function removeElement() {
      $parent.trigger('closed.bs.alert').remove()
    }

    $.support.transition && $parent.hasClass('fade') ?
      $parent
        .one($.support.transition.end, removeElement)
        .emulateTransitionEnd(150) :
      removeElement()
  }


  // ALERT PLUGIN DEFINITION
  // =======================

  var old = $.fn.alert

  $.fn.alert = function (option) {
    return this.each(function () {
      var $this = $(this)
      var data  = $this.data('bs.alert')

      if (!data) $this.data('bs.alert', (data = new Alert(this)))
      if (typeof option == 'string') data[option].call($this)
    })
  }

  $.fn.alert.Constructor = Alert


  // ALERT NO CONFLICT
  // =================

  $.fn.alert.noConflict = function () {
    $.fn.alert = old
    return this
  }


  // ALERT DATA-API
  // ==============

  $(document).on('click.bs.alert.data-api', dismiss, Alert.prototype.close)

}(window.jQuery);

/* ========================================================================
 * Bootstrap: button.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#buttons
 * ========================================================================
 * Copyright 2013 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // BUTTON PUBLIC CLASS DEFINITION
  // ==============================

  var Button = function (element, options) {
    this.$element = $(element)
    this.options  = $.extend({}, Button.DEFAULTS, options)
  }

  Button.DEFAULTS = {
    loadingText: 'loading...'
  }

  Button.prototype.setState = function (state) {
    var d    = 'disabled'
    var $el  = this.$element
    var val  = $el.is('input') ? 'val' : 'html'
    var data = $el.data()

    state = state + 'Text'

    if (!data.resetText) $el.data('resetText', $el[val]())

    $el[val](data[state] || this.options[state])

    // push to event loop to allow forms to submit
    setTimeout(function () {
      state == 'loadingText' ?
        $el.addClass(d).attr(d, d) :
        $el.removeClass(d).removeAttr(d);
    }, 0)
  }

  Button.prototype.toggle = function () {
    var $parent = this.$element.closest('[data-toggle="buttons"]')

    if ($parent.length) {
      var $input = this.$element.find('input')
        .prop('checked', !this.$element.hasClass('active'))
        .trigger('change')
      if ($input.prop('type') === 'radio') $parent.find('.active').removeClass('active')
    }

    this.$element.toggleClass('active')
  }


  // BUTTON PLUGIN DEFINITION
  // ========================

  var old = $.fn.button

  $.fn.button = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.button')
      var options = typeof option == 'object' && option

      if (!data) $this.data('bs.button', (data = new Button(this, options)))

      if (option == 'toggle') data.toggle()
      else if (option) data.setState(option)
    })
  }

  $.fn.button.Constructor = Button


  // BUTTON NO CONFLICT
  // ==================

  $.fn.button.noConflict = function () {
    $.fn.button = old
    return this
  }


  // BUTTON DATA-API
  // ===============

  $(document).on('click.bs.button.data-api', '[data-toggle^=button]', function (e) {
    var $btn = $(e.target)
    if (!$btn.hasClass('btn')) $btn = $btn.closest('.btn')
    $btn.button('toggle')
    e.preventDefault()
  })

}(window.jQuery);

/* ========================================================================
 * Bootstrap: carousel.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#carousel
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // CAROUSEL CLASS DEFINITION
  // =========================

  var Carousel = function (element, options) {
    this.$element    = $(element)
    this.$indicators = this.$element.find('.carousel-indicators')
    this.options     = options
    this.paused      =
    this.sliding     =
    this.interval    =
    this.$active     =
    this.$items      = null

    this.options.pause == 'hover' && this.$element
      .on('mouseenter', $.proxy(this.pause, this))
      .on('mouseleave', $.proxy(this.cycle, this))
  }

  Carousel.DEFAULTS = {
    interval: 5000
  , pause: 'hover'
  }

  Carousel.prototype.cycle =  function (e) {
    e || (this.paused = false)

    this.interval && clearInterval(this.interval)

    this.options.interval
      && !this.paused
      && (this.interval = setInterval($.proxy(this.next, this), this.options.interval))

    return this
  }

  Carousel.prototype.getActiveIndex = function () {
    this.$active = this.$element.find('.item.active')
    this.$items  = this.$active.parent().children()

    return this.$items.index(this.$active)
  }

  Carousel.prototype.to = function (pos) {
    var that        = this
    var activeIndex = this.getActiveIndex()

    if (pos > (this.$items.length - 1) || pos < 0) return

    if (this.sliding)       return this.$element.one('slid', function () { that.to(pos) })
    if (activeIndex == pos) return this.pause().cycle()

    return this.slide(pos > activeIndex ? 'next' : 'prev', $(this.$items[pos]))
  }

  Carousel.prototype.pause = function (e) {
    e || (this.paused = true)

    if (this.$element.find('.next, .prev').length && $.support.transition.end) {
      this.$element.trigger($.support.transition.end)
      this.cycle(true)
    }

    this.interval = clearInterval(this.interval)

    return this
  }

  Carousel.prototype.next = function () {
    if (this.sliding) return
    return this.slide('next')
  }

  Carousel.prototype.prev = function () {
    if (this.sliding) return
    return this.slide('prev')
  }

  Carousel.prototype.slide = function (type, next) {
    var $active   = this.$element.find('.item.active')
    var $next     = next || $active[type]()
    var isCycling = this.interval
    var direction = type == 'next' ? 'left' : 'right'
    var fallback  = type == 'next' ? 'first' : 'last'
    var that      = this

    this.sliding = true

    isCycling && this.pause()

    $next = $next.length ? $next : this.$element.find('.item')[fallback]()

    var e = $.Event('slide.bs.carousel', { relatedTarget: $next[0], direction: direction })

    if ($next.hasClass('active')) return

    if (this.$indicators.length) {
      this.$indicators.find('.active').removeClass('active')
      this.$element.one('slid', function () {
        var $nextIndicator = $(that.$indicators.children()[that.getActiveIndex()])
        $nextIndicator && $nextIndicator.addClass('active')
      })
    }

    if ($.support.transition && this.$element.hasClass('slide')) {
      this.$element.trigger(e)
      if (e.isDefaultPrevented()) return
      $next.addClass(type)
      $next[0].offsetWidth // force reflow
      $active.addClass(direction)
      $next.addClass(direction)
      $active
        .one($.support.transition.end, function () {
          $next.removeClass([type, direction].join(' ')).addClass('active')
          $active.removeClass(['active', direction].join(' '))
          that.sliding = false
          setTimeout(function () { that.$element.trigger('slid') }, 0)
        })
        .emulateTransitionEnd(600)
    } else {
      this.$element.trigger(e)
      if (e.isDefaultPrevented()) return
      $active.removeClass('active')
      $next.addClass('active')
      this.sliding = false
      this.$element.trigger('slid')
    }

    isCycling && this.cycle()

    return this
  }


  // CAROUSEL PLUGIN DEFINITION
  // ==========================

  var old = $.fn.carousel

  $.fn.carousel = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.carousel')
      var options = $.extend({}, Carousel.DEFAULTS, $this.data(), typeof option == 'object' && option)
      var action  = typeof option == 'string' ? option : options.slide

      if (!data) $this.data('bs.carousel', (data = new Carousel(this, options)))
      if (typeof option == 'number') data.to(option)
      else if (action) data[action]()
      else if (options.interval) data.pause().cycle()
    })
  }

  $.fn.carousel.Constructor = Carousel


  // CAROUSEL NO CONFLICT
  // ====================

  $.fn.carousel.noConflict = function () {
    $.fn.carousel = old
    return this
  }


  // CAROUSEL DATA-API
  // =================

  $(document).on('click.bs.carousel.data-api', '[data-slide], [data-slide-to]', function (e) {
    var $this   = $(this), href
    var $target = $($this.attr('data-target') || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '')) //strip for ie7
    var options = $.extend({}, $target.data(), $this.data())
    var slideIndex = $this.attr('data-slide-to')
    if (slideIndex) options.interval = false

    $target.carousel(options)

    if (slideIndex = $this.attr('data-slide-to')) {
      $target.data('bs.carousel').to(slideIndex)
    }

    e.preventDefault()
  })

  $(window).on('load', function () {
    $('[data-ride="carousel"]').each(function () {
      var $carousel = $(this)
      $carousel.carousel($carousel.data())
    })
  })

}(window.jQuery);

/* ========================================================================
 * Bootstrap: collapse.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#collapse
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // COLLAPSE PUBLIC CLASS DEFINITION
  // ================================

  var Collapse = function (element, options) {
    this.$element      = $(element)
    this.options       = $.extend({}, Collapse.DEFAULTS, options)
    this.transitioning = null

    if (this.options.parent) this.$parent = $(this.options.parent)
    if (this.options.toggle) this.toggle()
  }

  Collapse.DEFAULTS = {
    toggle: true
  }

  Collapse.prototype.dimension = function () {
    var hasWidth = this.$element.hasClass('width')
    return hasWidth ? 'width' : 'height'
  }

  Collapse.prototype.show = function () {
    if (this.transitioning || this.$element.hasClass('in')) return

    var startEvent = $.Event('show.bs.collapse')
    this.$element.trigger(startEvent)
    if (startEvent.isDefaultPrevented()) return

    var actives = this.$parent && this.$parent.find('> .accordion-group > .in')

    if (actives && actives.length) {
      var hasData = actives.data('bs.collapse')
      if (hasData && hasData.transitioning) return
      actives.collapse('hide')
      hasData || actives.data('bs.collapse', null)
    }

    var dimension = this.dimension()

    this.$element
      .removeClass('collapse')
      .addClass('collapsing')
      [dimension](0)

    this.transitioning = 1

    var complete = function () {
      this.$element
        .removeClass('collapsing')
        .addClass('in')
        [dimension]('auto')
      this.transitioning = 0
      this.$element.trigger('shown.bs.collapse')
    }

    if (!$.support.transition) return complete.call(this)

    var scrollSize = $.camelCase(['scroll', dimension].join('-'))

    this.$element
      .one($.support.transition.end, $.proxy(complete, this))
      .emulateTransitionEnd(350)
      [dimension](this.$element[0][scrollSize])
  }

  Collapse.prototype.hide = function () {
    if (this.transitioning || !this.$element.hasClass('in')) return

    var startEvent = $.Event('hide.bs.collapse')
    this.$element.trigger(startEvent)
    if (startEvent.isDefaultPrevented()) return

    var dimension = this.dimension()

    this.$element
      [dimension](this.$element[dimension]())
      [0].offsetHeight

    this.$element
      .addClass('collapsing')
      .removeClass('collapse')
      .removeClass('in')

    this.transitioning = 1

    var complete = function () {
      this.transitioning = 0
      this.$element
        .trigger('hidden.bs.collapse')
        .removeClass('collapsing')
        .addClass('collapse')
    }

    if (!$.support.transition) return complete.call(this)

    this.$element
      [dimension](0)
      .one($.support.transition.end, $.proxy(complete, this))
      .emulateTransitionEnd(350)
  }

  Collapse.prototype.toggle = function () {
    this[this.$element.hasClass('in') ? 'hide' : 'show']()
  }


  // COLLAPSE PLUGIN DEFINITION
  // ==========================

  var old = $.fn.collapse

  $.fn.collapse = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.collapse')
      var options = $.extend({}, Collapse.DEFAULTS, $this.data(), typeof option == 'object' && option)

      if (!data) $this.data('bs.collapse', (data = new Collapse(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.collapse.Constructor = Collapse


  // COLLAPSE NO CONFLICT
  // ====================

  $.fn.collapse.noConflict = function () {
    $.fn.collapse = old
    return this
  }


  // COLLAPSE DATA-API
  // =================

  $(document).on('click.bs.collapse.data-api', '[data-toggle=collapse]', function (e) {
    var $this   = $(this), href
    var target  = $this.attr('data-target')
        || e.preventDefault()
        || (href = $this.attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '') //strip for ie7
    var $target = $(target)
    var data    = $target.data('bs.collapse')
    var option  = data ? 'toggle' : $this.data()
    var parent  = $this.attr('data-parent')
    var $parent = parent && $(parent)

    if (!data || !data.transitioning) {
      if ($parent) $parent.find('[data-toggle=collapse][data-parent=' + parent + ']').not($this).addClass('collapsed')
      $this[$target.hasClass('in') ? 'addClass' : 'removeClass']('collapsed')
    }

    $target.collapse(option)
  })

}(window.jQuery);

/* ========================================================================
 * Bootstrap: dropdown.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#dropdowns
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // DROPDOWN CLASS DEFINITION
  // =========================

  var backdrop = '.dropdown-backdrop'
  var toggle   = '[data-toggle=dropdown]'
  var Dropdown = function (element) {
    var $el = $(element).on('click.bs.dropdown', this.toggle)
  }

  Dropdown.prototype.toggle = function (e) {
    var $this = $(this)

    if ($this.is('.disabled, :disabled')) return

    var $parent  = getParent($this)
    var isActive = $parent.hasClass('open')

    clearMenus()

    if (!isActive) {
      if ('ontouchstart' in document.documentElement) {
        // if mobile we we use a backdrop because click events don't delegate
        $('<div class="dropdown-backdrop"/>').insertAfter($(this)).on('click', clearMenus)
      }

      $parent.trigger(e = $.Event('show.bs.dropdown'))

      if (e.isDefaultPrevented()) return

      $parent
        .toggleClass('open')
        .trigger('shown.bs.dropdown')
    }

    $this.focus()

    return false
  }

  Dropdown.prototype.keydown = function (e) {
    if (!/(38|40|27)/.test(e.keyCode)) return

    var $this = $(this)

    e.preventDefault()
    e.stopPropagation()

    if ($this.is('.disabled, :disabled')) return

    var $parent  = getParent($this)
    var isActive = $parent.hasClass('open')

    if (!isActive || (isActive && e.keyCode == 27)) {
      if (e.which == 27) $parent.find(toggle).focus()
      return $this.click()
    }

    var $items = $('[role=menu] li:not(.divider):visible a', $parent)

    if (!$items.length) return

    var index = $items.index($items.filter(':focus'))

    if (e.keyCode == 38 && index > 0)                 index--                        // up
    if (e.keyCode == 40 && index < $items.length - 1) index++                        // down
    if (!~index)                                      index=0

    $items.eq(index).focus()
  }

  function clearMenus() {
    $(backdrop).remove()
    $(toggle).each(function (e) {
      var $parent = getParent($(this))
      if (!$parent.hasClass('open')) return
      $parent.trigger(e = $.Event('hide.bs.dropdown'))
      if (e.isDefaultPrevented()) return
      $parent.removeClass('open').trigger('hidden.bs.dropdown')
    })
  }

  function getParent($this) {
    var selector = $this.attr('data-target')

    if (!selector) {
      selector = $this.attr('href')
      selector = selector && /#/.test(selector) && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
    }

    var $parent = selector && $(selector)

    return $parent && $parent.length ? $parent : $this.parent()
  }


  // DROPDOWN PLUGIN DEFINITION
  // ==========================

  var old = $.fn.dropdown

  $.fn.dropdown = function (option) {
    return this.each(function () {
      var $this = $(this)
      var data  = $this.data('dropdown')

      if (!data) $this.data('dropdown', (data = new Dropdown(this)))
      if (typeof option == 'string') data[option].call($this)
    })
  }

  $.fn.dropdown.Constructor = Dropdown


  // DROPDOWN NO CONFLICT
  // ====================

  $.fn.dropdown.noConflict = function () {
    $.fn.dropdown = old
    return this
  }


  // APPLY TO STANDARD DROPDOWN ELEMENTS
  // ===================================

  $(document)
    .on('click.bs.dropdown.data-api', clearMenus)
    .on('click.bs.dropdown.data-api', '.dropdown form', function (e) { e.stopPropagation() })
    .on('click.bs.dropdown.data-api'  , toggle, Dropdown.prototype.toggle)
    .on('keydown.bs.dropdown.data-api', toggle + ', [role=menu]' , Dropdown.prototype.keydown)

}(window.jQuery);

/* ========================================================================
 * Bootstrap: modal.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#modals
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // MODAL CLASS DEFINITION
  // ======================

  var Modal = function (element, options) {
    this.options   = options
    this.$element  = $(element).on('click.dismiss.modal', '[data-dismiss="modal"]', $.proxy(this.hide, this))
    this.$backdrop =
    this.isShown   = null

    if (this.options.remote) this.$element.find('.modal-body').load(this.options.remote)
  }

  Modal.DEFAULTS = {
      backdrop: true
    , keyboard: true
    , show: true
  }

  Modal.prototype.toggle = function () {
    return this[!this.isShown ? 'show' : 'hide']()
  }

  Modal.prototype.show = function () {
    var that = this
    var e    = $.Event('show.bs.modal')

    this.$element.trigger(e)

    if (this.isShown || e.isDefaultPrevented()) return

    this.isShown = true

    this.escape()

    this.backdrop(function () {
      var transition = $.support.transition && that.$element.hasClass('fade')

      if (!that.$element.parent().length) {
        that.$element.appendTo(document.body) // don't move modals dom position
      }

      that.$element.show()

      if (transition) {
        that.$element[0].offsetWidth // force reflow
      }

      that.$element
        .addClass('in')
        .attr('aria-hidden', false)

      that.enforceFocus()

      transition ?
        that.$element
          .one($.support.transition.end, function () {
            that.$element.focus().trigger('shown.bs.modal')
          })
          .emulateTransitionEnd(300) :
        that.$element.focus().trigger('shown.bs.modal')
    })
  }

  Modal.prototype.hide = function (e) {
    if (e) e.preventDefault()

    e = $.Event('hide.bs.modal')

    this.$element.trigger(e)

    if (!this.isShown || e.isDefaultPrevented()) return

    this.isShown = false

    this.escape()

    $(document).off('focusin.bs.modal')

    this.$element
      .removeClass('in')
      .attr('aria-hidden', true)

    $.support.transition && this.$element.hasClass('fade') ?
      this.$element
        .one($.support.transition.end, $.proxy(this.hideModal, this))
        .emulateTransitionEnd(300) :
      this.hideModal()
  }

  Modal.prototype.enforceFocus = function () {
    $(document)
      .off('focusin.bs.modal') // guard against infinite focus loop
      .on('focusin.bs.modal', $.proxy(function (e) {
        if (this.$element[0] !== e.target && !this.$element.has(e.target).length) {
          this.$element.focus()
        }
      }, this))
  }

  Modal.prototype.escape = function () {
    if (this.isShown && this.options.keyboard) {
      this.$element.on('keyup.dismiss.bs.modal', $.proxy(function (e) {
        e.which == 27 && this.hide()
      }, this))
    } else if (!this.isShown) {
      this.$element.off('keyup.dismiss.bs.modal')
    }
  }

  Modal.prototype.hideModal = function () {
    var that = this
    this.$element.hide()
    this.backdrop(function () {
      that.removeBackdrop()
      that.$element.trigger('hidden.bs.modal')
    })
  }

  Modal.prototype.removeBackdrop = function () {
    this.$backdrop && this.$backdrop.remove()
    this.$backdrop = null
  }

  Modal.prototype.backdrop = function (callback) {
    var that    = this
    var animate = this.$element.hasClass('fade') ? 'fade' : ''

    if (this.isShown && this.options.backdrop) {
      var doAnimate = $.support.transition && animate

      this.$backdrop = $('<div class="modal-backdrop ' + animate + '" />')
        .appendTo(document.body)

      this.$element.on('click', $.proxy(function (e) {
        if (e.target !== e.currentTarget) return
        this.options.backdrop == 'static'
          ? this.$element[0].focus.call(this.$element[0])
          : this.hide.call(this)
      }, this))

      if (doAnimate) this.$backdrop[0].offsetWidth // force reflow

      this.$backdrop.addClass('in')

      if (!callback) return

      doAnimate ?
        this.$backdrop
          .one($.support.transition.end, callback)
          .emulateTransitionEnd(150) :
        callback()

    } else if (!this.isShown && this.$backdrop) {
      this.$backdrop.removeClass('in')

      $.support.transition && this.$element.hasClass('fade')?
        this.$backdrop
          .one($.support.transition.end, callback)
          .emulateTransitionEnd(150) :
        callback()

    } else if (callback) {
      callback()
    }
  }


  // MODAL PLUGIN DEFINITION
  // =======================

  var old = $.fn.modal

  $.fn.modal = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.modal')
      var options = $.extend({}, Modal.DEFAULTS, $this.data(), typeof option == 'object' && option)

      if (!data) $this.data('bs.modal', (data = new Modal(this, options)))
      if (typeof option == 'string') data[option]()
      else if (options.show) data.show()
    })
  }

  $.fn.modal.Constructor = Modal


  // MODAL NO CONFLICT
  // =================

  $.fn.modal.noConflict = function () {
    $.fn.modal = old
    return this
  }


  // MODAL DATA-API
  // ==============

  $(document).on('click.bs.modal.data-api', '[data-toggle="modal"]', function (e) {
    var $this   = $(this)
    var href    = $this.attr('href')
    var $target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, ''))) //strip for ie7
    var option  = $target.data('modal') ? 'toggle' : $.extend({ remote:!/#/.test(href) && href }, $target.data(), $this.data())

    e.preventDefault()

    $target
      .modal(option)
      .one('hide', function () {
        $this.is(':visible') && $this.focus()
      })
  })

  $(function () {
    var $body = $(document.body)
      .on('shown.bs.modal',  '.modal', function () { $body.addClass('modal-open') })
      .on('hidden.bs.modal', '.modal', function () { $body.removeClass('modal-open') })
  })

}(window.jQuery);

/* ========================================================================
 * Bootstrap: tooltip.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#affix
 * Inspired by the original jQuery.tipsy by Jason Frame
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // TOOLTIP PUBLIC CLASS DEFINITION
  // ===============================

  var Tooltip = function (element, options) {
    this.type       =
    this.options    =
    this.enabled    =
    this.timeout    =
    this.hoverState =
    this.$element   = null

    this.init('tooltip', element, options)
  }

  Tooltip.DEFAULTS = {
    animation: true
  , placement: 'top'
  , selector: false
  , template: '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
  , trigger: 'hover focus'
  , title: ''
  , delay: 0
  , html: false
  , container: false
  }

  Tooltip.prototype.init = function (type, element, options) {
    this.enabled  = true
    this.type     = type
    this.$element = $(element)
    this.options  = this.getOptions(options)

    var triggers = this.options.trigger.split(' ')

    for (var i = triggers.length; i--;) {
      var trigger = triggers[i]

      if (trigger == 'click') {
        this.$element.on('click.' + this.type, this.options.selector, $.proxy(this.toggle, this))
      } else if (trigger != 'manual') {
        var eventIn  = trigger == 'hover' ? 'mouseenter' : 'focus'
        var eventOut = trigger == 'hover' ? 'mouseleave' : 'blur'

        this.$element.on(eventIn + '.' + this.type, this.options.selector, $.proxy(this.enter, this))
        this.$element.on(eventOut + '.' + this.type, this.options.selector, $.proxy(this.leave, this))
      }
    }

    this.options.selector ?
      (this._options = $.extend({}, this.options, { trigger: 'manual', selector: '' })) :
      this.fixTitle()
  }

  Tooltip.prototype.getDefaults = function () {
    return Tooltip.DEFAULTS
  }

  Tooltip.prototype.getOptions = function (options) {
    options = $.extend({}, this.getDefaults(), this.$element.data(), options)

    if (options.delay && typeof options.delay == 'number') {
      options.delay = {
        show: options.delay
      , hide: options.delay
      }
    }

    return options
  }

  Tooltip.prototype.enter = function (obj) {
    var defaults = this.getDefaults()
    var options  = {}

    this._options && $.each(this._options, function (key, value) {
      if (defaults[key] != value) options[key] = value
    })

    var self = obj instanceof this.constructor ?
      obj : $(obj.currentTarget)[this.type](options).data('bs.' + this.type)

    clearTimeout(self.timeout)

    if (!self.options.delay || !self.options.delay.show) return self.show()

    self.hoverState = 'in'
    self.timeout    = setTimeout(function () {
      if (self.hoverState == 'in') self.show()
    }, self.options.delay.show)
  }

  Tooltip.prototype.leave = function (obj) {
    var self = obj instanceof this.constructor ?
      obj : $(obj.currentTarget)[this.type](this._options).data('bs.' + this.type)

    clearTimeout(self.timeout)

    if (!self.options.delay || !self.options.delay.hide) return self.hide()

    self.hoverState = 'out'
    self.timeout    = setTimeout(function () {
      if (self.hoverState == 'out') self.hide()
    }, self.options.delay.hide)
  }

  Tooltip.prototype.show = function () {
    var e = $.Event('show.bs.'+ this.type)

    if (this.hasContent() && this.enabled) {
      this.$element.trigger(e)

      if (e.isDefaultPrevented()) return

      var $tip = this.tip()

      this.setContent()

      if (this.options.animation) $tip.addClass('fade')

      var placement = typeof this.options.placement == 'function' ?
        this.options.placement.call(this, $tip[0], this.$element[0]) :
        this.options.placement

      var autoToken = /\s?auto?\s?/i
      var autoPlace = autoToken.test(placement)
      if (autoPlace) placement = placement.replace(autoToken, '') || 'top'

      $tip
        .detach()
        .css({ top: 0, left: 0, display: 'block' })
        .addClass(placement)

      this.options.container ? $tip.appendTo(this.options.container) : $tip.insertAfter(this.$element)

      var pos          = this.getPosition()
      var actualWidth  = $tip[0].offsetWidth
      var actualHeight = $tip[0].offsetHeight

      if (autoPlace) {
        var $parent = this.$element.parent()

        var orgPlacement = placement
        var docScroll    = document.documentElement.scrollTop || document.body.scrollTop
        var parentWidth  = this.options.container == 'body' ? window.innerWidth  : $parent.outerWidth()
        var parentHeight = this.options.container == 'body' ? window.innerHeight : $parent.outerHeight()
        var parentLeft   = this.options.container == 'body' ? 0 : $parent.offset().left

        placement = placement == 'bottom' && pos.top   + pos.height  + actualHeight - docScroll > parentHeight  ? 'top'    :
                    placement == 'top'    && pos.top   - docScroll   - actualHeight < 0                         ? 'bottom' :
                    placement == 'right'  && pos.right + actualWidth > parentWidth                              ? 'left'   :
                    placement == 'left'   && pos.left  - actualWidth < parentLeft                               ? 'right'  :
                    placement

        $tip
          .removeClass(orgPlacement)
          .addClass(placement)
      }

      var calculatedOffset = this.getCalcuatedOffset(placement, pos, actualWidth, actualHeight)

      this.applyPlacement(calculatedOffset, placement)
      this.$element.trigger('shown.bs.' + this.type)
    }
  }

  Tooltip.prototype.applyPlacement = function(offset, placement) {
    var replace
    var $tip   = this.tip()
    var width  = $tip[0].offsetWidth
    var height = $tip[0].offsetHeight

    // manually read margins because getBoundingClientRect includes difference
    var marginTop = parseInt($tip.css('margin-top'), 10)
    var marginLeft = parseInt($tip.css('margin-left'), 10)

    // we must check for NaN for ie 8/9
    if (isNaN(marginTop))  marginTop  = 0
    if (isNaN(marginLeft)) marginLeft = 0

    offset.top  = offset.top  + marginTop
    offset.left = offset.left + marginLeft

    $tip
      .offset(offset)
      .addClass('in')

    // check to see if placing tip in new offset caused the tip to resize itself
    var actualWidth  = $tip[0].offsetWidth
    var actualHeight = $tip[0].offsetHeight

    if (placement == 'top' && actualHeight != height) {
      replace = true
      offset.top = offset.top + height - actualHeight
    }

    if (/bottom|top/.test(placement)) {
      var delta = 0

      if (offset.left < 0) {
        delta       = offset.left * -2
        offset.left = 0

        $tip.offset(offset)

        actualWidth  = $tip[0].offsetWidth
        actualHeight = $tip[0].offsetHeight
      }

      this.replaceArrow(delta - width + actualWidth, actualWidth, 'left')
    } else {
      this.replaceArrow(actualHeight - height, actualHeight, 'top')
    }

    if (replace) $tip.offset(offset)
  }

  Tooltip.prototype.replaceArrow = function(delta, dimension, position) {
    this.arrow().css(position, delta ? (50 * (1 - delta / dimension) + "%") : '')
  }

  Tooltip.prototype.setContent = function () {
    var $tip  = this.tip()
    var title = this.getTitle()

    $tip.find('.tooltip-inner')[this.options.html ? 'html' : 'text'](title)
    $tip.removeClass('fade in top bottom left right')
  }

  Tooltip.prototype.hide = function () {
    var that = this
    var $tip = this.tip()
    var e    = $.Event('hide.bs.' + this.type)

    function complete() { $tip.detach() }

    this.$element.trigger(e)

    if (e.isDefaultPrevented()) return

    $tip.removeClass('in')

    $.support.transition && this.$tip.hasClass('fade') ?
      $tip
        .one($.support.transition.end, complete)
        .emulateTransitionEnd(150) :
      complete()

    this.$element.trigger('hidden.bs.' + this.type)

    return this
  }

  Tooltip.prototype.fixTitle = function () {
    var $e = this.$element
    if ($e.attr('title') || typeof($e.attr('data-original-title')) != 'string') {
      $e.attr('data-original-title', $e.attr('title') || '').attr('title', '')
    }
  }

  Tooltip.prototype.hasContent = function () {
    return this.getTitle()
  }

  Tooltip.prototype.getPosition = function () {
    var el = this.$element[0]
    return $.extend({}, (typeof el.getBoundingClientRect == 'function') ? el.getBoundingClientRect() : {
      width: el.offsetWidth
    , height: el.offsetHeight
    }, this.$element.offset())
  }

  Tooltip.prototype.getCalcuatedOffset = function (placement, pos, actualWidth, actualHeight) {
    return placement == 'bottom' ? { top: pos.top + pos.height,   left: pos.left + pos.width / 2 - actualWidth / 2  } :
           placement == 'top'    ? { top: pos.top - actualHeight, left: pos.left + pos.width / 2 - actualWidth / 2  } :
           placement == 'left'   ? { top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left - actualWidth } :
        /* placement == 'right' */ { top: pos.top + pos.height / 2 - actualHeight / 2, left: pos.left + pos.width   }
  }

  Tooltip.prototype.getTitle = function () {
    var title
    var $e = this.$element
    var o  = this.options

    title = $e.attr('data-original-title')
      || (typeof o.title == 'function' ? o.title.call($e[0]) :  o.title)

    return title
  }

  Tooltip.prototype.tip = function () {
    return this.$tip = this.$tip || $(this.options.template)
  }

  Tooltip.prototype.arrow = function () {
    return this.$arrow = this.$arrow || this.tip().find('.tooltip-arrow')
  }

  Tooltip.prototype.validate = function () {
    if (!this.$element[0].parentNode) {
      this.hide()
      this.$element = null
      this.options  = null
    }
  }

  Tooltip.prototype.enable = function () {
    this.enabled = true
  }

  Tooltip.prototype.disable = function () {
    this.enabled = false
  }

  Tooltip.prototype.toggleEnabled = function () {
    this.enabled = !this.enabled
  }

  Tooltip.prototype.toggle = function (e) {
    var self = e ? $(e.currentTarget)[this.type](this._options).data('bs.' + this.type) : this
    self.tip().hasClass('in') ? self.leave(self) : self.enter(self)
  }

  Tooltip.prototype.destroy = function () {
    this.hide().$element.off('.' + this.type).removeData('bs.' + this.type)
  }


  // TOOLTIP PLUGIN DEFINITION
  // =========================

  var old = $.fn.tooltip

  $.fn.tooltip = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.tooltip')
      var options = typeof option == 'object' && option

      if (!data) $this.data('bs.tooltip', (data = new Tooltip(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.tooltip.Constructor = Tooltip


  // TOOLTIP NO CONFLICT
  // ===================

  $.fn.tooltip.noConflict = function () {
    $.fn.tooltip = old
    return this
  }

}(window.jQuery);

/* ========================================================================
 * Bootstrap: popover.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#popovers
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // POPOVER PUBLIC CLASS DEFINITION
  // ===============================

  var Popover = function (element, options) {
    this.init('popover', element, options)
  }

  if (!$.fn.tooltip) throw new Error('Popover requires tooltip.js')

  Popover.DEFAULTS = $.extend({} , $.fn.tooltip.Constructor.DEFAULTS, {
    placement: 'right'
  , trigger: 'click'
  , content: ''
  , template: '<div class="popover"><div class="arrow"></div><h3 class="popover-title"></h3><div class="popover-content"></div></div>'
  })


  // NOTE: POPOVER EXTENDS tooltip.js
  // ================================

  Popover.prototype = $.extend({}, $.fn.tooltip.Constructor.prototype)

  Popover.prototype.constructor = Popover

  Popover.prototype.getDefaults = function () {
    return Popover.DEFAULTS
  }

  Popover.prototype.setContent = function () {
    var $tip    = this.tip()
    var title   = this.getTitle()
    var content = this.getContent()

    $tip.find('.popover-title')[this.options.html ? 'html' : 'text'](title)
    $tip.find('.popover-content')[this.options.html ? 'html' : 'text'](content)

    $tip.removeClass('fade top bottom left right in')

    $tip.find('.popover-title:empty').hide()
  }

  Popover.prototype.hasContent = function () {
    return this.getTitle() || this.getContent()
  }

  Popover.prototype.getContent = function () {
    var $e = this.$element
    var o  = this.options

    return $e.attr('data-content')
      || (typeof o.content == 'function' ?
            o.content.call($e[0]) :
            o.content)
  }

  Popover.prototype.arrow =function () {
    return this.$arrow = this.$arrow || this.tip().find('.arrow')
  }

  Popover.prototype.tip = function () {
    if (!this.$tip) this.$tip = $(this.options.template)
    return this.$tip
  }


  // POPOVER PLUGIN DEFINITION
  // =========================

  var old = $.fn.popover

  $.fn.popover = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.popover')
      var options = typeof option == 'object' && option

      if (!data) $this.data('bs.popover', (data = new Popover(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.popover.Constructor = Popover


  // POPOVER NO CONFLICT
  // ===================

  $.fn.popover.noConflict = function () {
    $.fn.popover = old
    return this
  }

}(window.jQuery);

/* ========================================================================
 * Bootstrap: scrollspy.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#scrollspy
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // SCROLLSPY CLASS DEFINITION
  // ==========================

  function ScrollSpy(element, options) {
    var href
    var process  = $.proxy(this.process, this)

    this.$element       = $(element).is('body') ? $(window) : $(element)
    this.$body          = $('body')
    this.$scrollElement = this.$element.on('scroll.bs.scroll-spy.data-api', process)
    this.options        = $.extend({}, ScrollSpy.DEFAULTS, options)
    this.selector       = (this.options.target
      || ((href = $(element).attr('href')) && href.replace(/.*(?=#[^\s]+$)/, '')) //strip for ie7
      || '') + ' .nav li > a'
    this.offsets        = $([])
    this.targets        = $([])
    this.activeTarget   = null

    this.refresh()
    this.process()
  }

  ScrollSpy.DEFAULTS = {
    offset: 10
  }

  ScrollSpy.prototype.refresh = function () {
    var offsetMethod = this.$element[0] == window ? 'offset' : 'position'

    this.offsets = $([])
    this.targets = $([])

    var self     = this
    var $targets = this.$body
      .find(this.selector)
      .map(function () {
        var $el   = $(this)
        var href  = $el.data('target') || $el.attr('href')
        var $href = /^#\w/.test(href) && $(href)

        return ($href
          && $href.length
          && [[ $href[offsetMethod]().top + (!$.isWindow(self.$scrollElement.get(0)) && self.$scrollElement.scrollTop()), href ]]) || null
      })
      .sort(function (a, b) { return a[0] - b[0] })
      .each(function () {
        self.offsets.push(this[0])
        self.targets.push(this[1])
      })
  }

  ScrollSpy.prototype.process = function () {
    var scrollTop    = this.$scrollElement.scrollTop() + this.options.offset
    var scrollHeight = this.$scrollElement[0].scrollHeight || this.$body[0].scrollHeight
    var maxScroll    = scrollHeight - this.$scrollElement.height()
    var offsets      = this.offsets
    var targets      = this.targets
    var activeTarget = this.activeTarget
    var i

    if (scrollTop >= maxScroll) {
      return activeTarget != (i = targets.last()[0]) && this.activate(i)
    }

    for (i = offsets.length; i--;) {
      activeTarget != targets[i]
        && scrollTop >= offsets[i]
        && (!offsets[i + 1] || scrollTop <= offsets[i + 1])
        && this.activate( targets[i] )
    }
  }

  ScrollSpy.prototype.activate = function (target) {
    this.activeTarget = target

    $(this.selector)
      .parents('.active')
      .removeClass('active')

    var selector = this.selector
      + '[data-target="' + target + '"],'
      + this.selector + '[href="' + target + '"]'

    var active = $(selector)
      .parents('li')
      .addClass('active')

    if (active.parent('.dropdown-menu').length)  {
      active = active
        .closest('li.dropdown')
        .addClass('active')
    }

    active.trigger('activate')
  }


  // SCROLLSPY PLUGIN DEFINITION
  // ===========================

  var old = $.fn.scrollspy

  $.fn.scrollspy = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.scrollspy')
      var options = typeof option == 'object' && option

      if (!data) $this.data('bs.scrollspy', (data = new ScrollSpy(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.scrollspy.Constructor = ScrollSpy


  // SCROLLSPY NO CONFLICT
  // =====================

  $.fn.scrollspy.noConflict = function () {
    $.fn.scrollspy = old
    return this
  }


  // SCROLLSPY DATA-API
  // ==================

  $(window).on('load', function () {
    $('[data-spy="scroll"]').each(function () {
      var $spy = $(this)
      $spy.scrollspy($spy.data())
    })
  })

}(window.jQuery);

/* ========================================================================
 * Bootstrap: tab.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#tabs
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // TAB CLASS DEFINITION
  // ====================

  var Tab = function (element) {
    this.element = $(element)
  }

  Tab.prototype.show = function () {
    var $this    = this.element
    var $ul      = $this.closest('ul:not(.dropdown-menu)')
    var selector = $this.attr('data-target')

    if (!selector) {
      selector = $this.attr('href')
      selector = selector && selector.replace(/.*(?=#[^\s]*$)/, '') //strip for ie7
    }

    if ($this.parent('li').hasClass('active')) return

    var previous = $ul.find('.active:last a')[0]
    var e        = $.Event('show.bs.tab', {
      relatedTarget: previous
    })

    $this.trigger(e)

    if (e.isDefaultPrevented()) return

    var $target = $(selector)

    this.activate($this.parent('li'), $ul)
    this.activate($target, $target.parent(), function () {
      $this.trigger({
        type: 'shown.bs.tab'
      , relatedTarget: previous
      })
    })
  }

  Tab.prototype.activate = function (element, container, callback) {
    var $active    = container.find('> .active')
    var transition = callback
      && $.support.transition
      && $active.hasClass('fade')

    function next() {
      $active
        .removeClass('active')
        .find('> .dropdown-menu > .active')
        .removeClass('active')

      element.addClass('active')

      if (transition) {
        element[0].offsetWidth // reflow for transition
        element.addClass('in')
      } else {
        element.removeClass('fade')
      }

      if (element.parent('.dropdown-menu')) {
        element.closest('li.dropdown').addClass('active')
      }

      callback && callback()
    }

    transition ?
      $active
        .one($.support.transition.end, next)
        .emulateTransitionEnd(150) :
      next()

    $active.removeClass('in')
  }


  // TAB PLUGIN DEFINITION
  // =====================

  var old = $.fn.tab

  $.fn.tab = function ( option ) {
    return this.each(function () {
      var $this = $(this)
      var data  = $this.data('bs.tab')

      if (!data) $this.data('bs.tab', (data = new Tab(this)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.tab.Constructor = Tab


  // TAB NO CONFLICT
  // ===============

  $.fn.tab.noConflict = function () {
    $.fn.tab = old
    return this
  }


  // TAB DATA-API
  // ============

  $(document).on('click.bs.tab.data-api', '[data-toggle="tab"], [data-toggle="pill"]', function (e) {
    e.preventDefault()
    $(this).tab('show')
  })

}(window.jQuery);

/* ========================================================================
 * Bootstrap: affix.js v3.0.0
 * http://twbs.github.com/bootstrap/javascript.html#affix
 * ========================================================================
 * Copyright 2012 Twitter, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ======================================================================== */


+function ($) { "use strict";

  // AFFIX CLASS DEFINITION
  // ======================

  var Affix = function (element, options) {
    this.options = $.extend({}, Affix.DEFAULTS, options)
    this.$window = $(window)
      .on('scroll.bs.affix.data-api', $.proxy(this.checkPosition, this))
      .on('click.bs.affix.data-api',  $.proxy(this.checkPositionWithEventLoop, this))

    this.$element = $(element)
    this.affixed  =
    this.unpin    = null

    this.checkPosition()
  }

  Affix.RESET = 'affix affix-top affix-bottom'

  Affix.DEFAULTS = {
    offset: 0
  }

  Affix.prototype.checkPositionWithEventLoop = function () {
    setTimeout($.proxy(this.checkPosition, this), 1)
  }

  Affix.prototype.checkPosition = function () {
    if (!this.$element.is(':visible')) return

    var scrollHeight = $(document).height()
    var scrollTop    = this.$window.scrollTop()
    var position     = this.$element.offset()
    var offset       = this.options.offset
    var offsetTop    = offset.top
    var offsetBottom = offset.bottom

    if (typeof offset != 'object')         offsetBottom = offsetTop = offset
    if (typeof offsetTop == 'function')    offsetTop    = offset.top()
    if (typeof offsetBottom == 'function') offsetBottom = offset.bottom()

    var affix = this.unpin   != null && (scrollTop + this.unpin <= position.top) ? false :
                offsetBottom != null && (position.top + this.$element.height() >= scrollHeight - offsetBottom) ? 'bottom' :
                offsetTop    != null && (scrollTop <= offsetTop) ? 'top' : false

    if (this.affixed === affix) return
    if (this.unpin) this.$element.css('top', '')

    this.affixed = affix
    this.unpin   = affix == 'bottom' ? position.top - scrollTop : null

    this.$element.removeClass(Affix.RESET).addClass('affix' + (affix ? '-' + affix : ''))

    if (affix == 'bottom') {
      this.$element.offset({ top: document.body.offsetHeight - offsetBottom - this.$element.height() })
    }
  }


  // AFFIX PLUGIN DEFINITION
  // =======================

  var old = $.fn.affix

  $.fn.affix = function (option) {
    return this.each(function () {
      var $this   = $(this)
      var data    = $this.data('bs.affix')
      var options = typeof option == 'object' && option

      if (!data) $this.data('bs.affix', (data = new Affix(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.affix.Constructor = Affix


  // AFFIX NO CONFLICT
  // =================

  $.fn.affix.noConflict = function () {
    $.fn.affix = old
    return this
  }


  // AFFIX DATA-API
  // ==============

  $(window).on('load', function () {
    $('[data-spy="affix"]').each(function () {
      var $spy = $(this)
      var data = $spy.data()

      data.offset = data.offset || {}

      if (data.offsetBottom) data.offset.bottom = data.offsetBottom
      if (data.offsetTop)    data.offset.top    = data.offsetTop

      $spy.affix(data)
    })
  })

}(window.jQuery);


//add by tony
//slider
!function( $ ) {

    var Slider = function(element, options) {
        this.element = $(element);
        this.picker = $('<div class="slider">'+
            '<div class="slider-track">'+
            '<div class="slider-selection"></div>'+
            '<div class="slider-handle"></div>'+
            '<div class="slider-handle"></div>'+
            '</div>'+
            '<div class="tooltip"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'+
            '</div>')
            .insertBefore(this.element)
            .append(this.element);
        this.id = this.element.data('slider-id')||options.id;
        if (this.id) {
            this.picker[0].id = this.id;
        }

        if (typeof Modernizr !== 'undefined' && Modernizr.touch) {
            this.touchCapable = true;
        }

        var tooltip = this.element.data('slider-tooltip')||options.tooltip;

        this.tooltip = this.picker.find('.tooltip');
        this.tooltipInner = this.tooltip.find('div.tooltip-inner');

        this.orientation = this.element.data('slider-orientation')||options.orientation;
        switch(this.orientation) {
            case 'vertical':
                this.picker.addClass('slider-vertical');
                this.stylePos = 'top';
                this.mousePos = 'pageY';
                this.sizePos = 'offsetHeight';
                this.tooltip.addClass('right')[0].style.left = '100%';
                break;
            default:
                this.picker
                    .addClass('slider-horizontal')
                    .css('width', this.element.outerWidth());
                this.orientation = 'horizontal';
                this.stylePos = 'left';
                this.mousePos = 'pageX';
                this.sizePos = 'offsetWidth';
                this.tooltip.addClass('top')[0].style.top = -this.tooltip.outerHeight() - 14 + 'px';
                break;
        }

        this.min = this.element.data('slider-min')||options.min;
        this.max = this.element.data('slider-max')||options.max;
        this.step = this.element.data('slider-step')||options.step;
        this.value = this.element.data('slider-value')||options.value;
        if (this.value[1]) {
            this.range = true;
        }

        this.selection = this.element.data('slider-selection')||options.selection;
        this.selectionEl = this.picker.find('.slider-selection');
        if (this.selection === 'none') {
            this.selectionEl.addClass('hide');
        }
        this.selectionElStyle = this.selectionEl[0].style;


        this.handle1 = this.picker.find('.slider-handle:first');
        this.handle1Stype = this.handle1[0].style;
        this.handle2 = this.picker.find('.slider-handle:last');
        this.handle2Stype = this.handle2[0].style;

        var handle = this.element.data('slider-handle')||options.handle;
        switch(handle) {
            case 'round':
                this.handle1.addClass('round');
                this.handle2.addClass('round');
                break
            case 'triangle':
                this.handle1.addClass('triangle');
                this.handle2.addClass('triangle');
                break
        }

        if (this.range) {
            this.value[0] = Math.max(this.min, Math.min(this.max, this.value[0]));
            this.value[1] = Math.max(this.min, Math.min(this.max, this.value[1]));
        } else {
            this.value = [ Math.max(this.min, Math.min(this.max, this.value))];
            this.handle2.addClass('hide');
            if (this.selection == 'after') {
                this.value[1] = this.max;
            } else {
                this.value[1] = this.min;
            }
        }
        this.diff = this.max - this.min;
        this.percentage = [
            (this.value[0]-this.min)*100/this.diff,
            (this.value[1]-this.min)*100/this.diff,
            this.step*100/this.diff
        ];

        this.offset = this.picker.offset();
        this.size = this.picker[0][this.sizePos];

        this.formater = options.formater;

        this.layout();

        if (this.touchCapable) {
            // Touch: Bind touch events:
            this.picker.on({
                touchstart: $.proxy(this.mousedown, this)
            });
        } else {
            this.picker.on({
                mousedown: $.proxy(this.mousedown, this)
            });
        }

        if (tooltip === 'show') {
            this.picker.on({
                mouseenter: $.proxy(this.showTooltip, this),
                mouseleave: $.proxy(this.hideTooltip, this)
            });
        } else {
            this.tooltip.addClass('hide');
        }
    };

    Slider.prototype = {
        constructor: Slider,

        over: false,
        inDrag: false,

        showTooltip: function(){
            this.tooltip.addClass('in');
            //var left = Math.round(this.percent*this.width);
            //this.tooltip.css('left', left - this.tooltip.outerWidth()/2);
            this.over = true;
        },

        hideTooltip: function(){
            if (this.inDrag === false) {
                this.tooltip.removeClass('in');
            }
            this.over = false;
        },

        layout: function(){
            this.handle1Stype[this.stylePos] = this.percentage[0]+'%';
            this.handle2Stype[this.stylePos] = this.percentage[1]+'%';
            if (this.orientation == 'vertical') {
                this.selectionElStyle.top = Math.min(this.percentage[0], this.percentage[1]) +'%';
                this.selectionElStyle.height = Math.abs(this.percentage[0] - this.percentage[1]) +'%';
            } else {
                this.selectionElStyle.left = Math.min(this.percentage[0], this.percentage[1]) +'%';
                this.selectionElStyle.width = Math.abs(this.percentage[0] - this.percentage[1]) +'%';
            }
            if (this.range) {
                this.tooltipInner.text(
                    this.formater(this.value[0]) +
                        ' : ' +
                        this.formater(this.value[1])
                );
                this.tooltip[0].style[this.stylePos] = this.size * (this.percentage[0] + (this.percentage[1] - this.percentage[0])/2)/100 - (this.orientation === 'vertical' ? this.tooltip.outerHeight()/2 : this.tooltip.outerWidth()/2) +'px';
            } else {
                this.tooltipInner.text(
                    this.formater(this.value[0])
                );
                this.tooltip[0].style[this.stylePos] = this.size * this.percentage[0]/100 - (this.orientation === 'vertical' ? this.tooltip.outerHeight()/2 : this.tooltip.outerWidth()/2) +'px';
            }
        },

        mousedown: function(ev) {

            // Touch: Get the original event:
            if (this.touchCapable && ev.type === 'touchstart') {
                ev = ev.originalEvent;
            }

            this.offset = this.picker.offset();
            this.size = this.picker[0][this.sizePos];

            var percentage = this.getPercentage(ev);

            if (this.range) {
                var diff1 = Math.abs(this.percentage[0] - percentage);
                var diff2 = Math.abs(this.percentage[1] - percentage);
                this.dragged = (diff1 < diff2) ? 0 : 1;
            } else {
                this.dragged = 0;
            }

            this.percentage[this.dragged] = percentage;
            this.layout();

            if (this.touchCapable) {
                // Touch: Bind touch events:
                $(document).on({
                    touchmove: $.proxy(this.mousemove, this),
                    touchend: $.proxy(this.mouseup, this)
                });
            } else {
                $(document).on({
                    mousemove: $.proxy(this.mousemove, this),
                    mouseup: $.proxy(this.mouseup, this)
                });
            }

            this.inDrag = true;
            var val = this.calculateValue();
            this.element.trigger({
                type: 'slideStart',
                value: val
            }).trigger({
                    type: 'slide',
                    value: val
                });
            return false;
        },

        mousemove: function(ev) {

            // Touch: Get the original event:
            if (this.touchCapable && ev.type === 'touchmove') {
                ev = ev.originalEvent;
            }

            var percentage = this.getPercentage(ev);
            if (this.range) {
                if (this.dragged === 0 && this.percentage[1] < percentage) {
                    this.percentage[0] = this.percentage[1];
                    this.dragged = 1;
                } else if (this.dragged === 1 && this.percentage[0] > percentage) {
                    this.percentage[1] = this.percentage[0];
                    this.dragged = 0;
                }
            }
            this.percentage[this.dragged] = percentage;
            this.layout();
            var val = this.calculateValue();
            this.element
                .trigger({
                    type: 'slide',
                    value: val
                })
                .data('value', val)
                .prop('value', val);
            return false;
        },

        mouseup: function(ev) {
            if (this.touchCapable) {
                // Touch: Bind touch events:
                $(document).off({
                    touchmove: this.mousemove,
                    touchend: this.mouseup
                });
            } else {
                $(document).off({
                    mousemove: this.mousemove,
                    mouseup: this.mouseup
                });
            }

            this.inDrag = false;
            if (this.over == false) {
                this.hideTooltip();
            }
            this.element;
            var val = this.calculateValue();
            this.element
                .trigger({
                    type: 'slideStop',
                    value: val
                })
                .data('value', val)
                .prop('value', val);
            return false;
        },

        calculateValue: function() {
            var val;
            if (this.range) {
                val = [
                    (this.min + Math.round((this.diff * this.percentage[0]/100)/this.step)*this.step),
                    (this.min + Math.round((this.diff * this.percentage[1]/100)/this.step)*this.step)
                ];
                this.value = val;
            } else {
                val = (this.min + Math.round((this.diff * this.percentage[0]/100)/this.step)*this.step);
                this.value = [val, this.value[1]];
            }
            return val;
        },

        getPercentage: function(ev) {
            if (this.touchCapable) {
                ev = ev.touches[0];
            }
            var percentage = (ev[this.mousePos] - this.offset[this.stylePos])*100/this.size;
            percentage = Math.round(percentage/this.percentage[2])*this.percentage[2];
            return Math.max(0, Math.min(100, percentage));
        },

        getValue: function() {
            if (this.range) {
                return this.value;
            }
            return this.value[0];
        },

        setValue: function(val) {
            this.value = val;

            if (this.range) {
                this.value[0] = Math.max(this.min, Math.min(this.max, this.value[0]));
                this.value[1] = Math.max(this.min, Math.min(this.max, this.value[1]));
            } else {
                this.value = [ Math.max(this.min, Math.min(this.max, this.value))];
                this.handle2.addClass('hide');
                if (this.selection == 'after') {
                    this.value[1] = this.max;
                } else {
                    this.value[1] = this.min;
                }
            }
            this.diff = this.max - this.min;
            this.percentage = [
                (this.value[0]-this.min)*100/this.diff,
                (this.value[1]-this.min)*100/this.diff,
                this.step*100/this.diff
            ];
            this.layout();
        }
    };

    $.fn.slider = function ( option, val ) {
        return this.each(function () {
            var $this = $(this),
                data = $this.data('slider'),
                options = typeof option === 'object' && option;
            if (!data)  {
                $this.data('slider', (data = new Slider(this, $.extend({}, $.fn.slider.defaults,options))));
            }
            if (typeof option == 'string') {
                data[option](val);
            }
        })
    };

    $.fn.slider.defaults = {
        min: 0,
        max: 10,
        step: 1,
        orientation: 'horizontal',
        value: 5,
        selection: 'before',
        tooltip: 'show',
        handle: 'round',
        formater: function(value) {
            return value;
        }
    };

    $.fn.slider.Constructor = Slider;

}( window.jQuery );

//datepicker
!function( $ ) {

    function UTCDate(){
        return new Date(Date.UTC.apply(Date, arguments));
    }
    function UTCToday(){
        var today = new Date();
        return UTCDate(today.getUTCFullYear(), today.getUTCMonth(), today.getUTCDate());
    }

    // Picker object

    var Datepicker = function(element, options) {
        var that = this;

        this.element = $(element);
        this.language = options.language||this.element.data('date-language')||"en";
        this.language = this.language in dates ? this.language : this.language.split('-')[0]; //Check if "de-DE" style date is available, if not language should fallback to 2 letter code eg "de"
        this.language = this.language in dates ? this.language : "en";
        this.isRTL = dates[this.language].rtl||false;
        this.format = DPGlobal.parseFormat(options.format||this.element.data('date-format')||dates[this.language].format||'mm/dd/yyyy');
        this.isInline = false;
        this.isInput = this.element.is('input');
        this.component = this.element.is('.date') ? this.element.find('.add-on, .btn') : false;
        this.hasInput = this.component && this.element.find('input').length;
        if(this.component && this.component.length === 0)
            this.component = false;

        this.forceParse = true;
        if ('forceParse' in options) {
            this.forceParse = options.forceParse;
        } else if ('dateForceParse' in this.element.data()) {
            this.forceParse = this.element.data('date-force-parse');
        }

        this.picker = $(DPGlobal.template);
        this._buildEvents();
        this._attachEvents();

        if(this.isInline) {
            this.picker.addClass('datepicker-inline').appendTo(this.element);
        } else {
            this.picker.addClass('datepicker-dropdown dropdown-menu');
        }
        if (this.isRTL){
            this.picker.addClass('datepicker-rtl');
            this.picker.find('.prev i, .next i')
                .toggleClass('icon-arrow-left icon-arrow-right');
        }

        this.autoclose = false;
        if ('autoclose' in options) {
            this.autoclose = options.autoclose;
        } else if ('dateAutoclose' in this.element.data()) {
            this.autoclose = this.element.data('date-autoclose');
        }

        this.keyboardNavigation = true;
        if ('keyboardNavigation' in options) {
            this.keyboardNavigation = options.keyboardNavigation;
        } else if ('dateKeyboardNavigation' in this.element.data()) {
            this.keyboardNavigation = this.element.data('date-keyboard-navigation');
        }

        this.viewMode = this.startViewMode = 0;
        switch(options.startView || this.element.data('date-start-view')){
            case 2:
            case 'decade':
                this.viewMode = this.startViewMode = 2;
                break;
            case 1:
            case 'year':
                this.viewMode = this.startViewMode = 1;
                break;
        }

        this.minViewMode = options.minViewMode||this.element.data('date-min-view-mode')||0;
        if (typeof this.minViewMode === 'string') {
            switch (this.minViewMode) {
                case 'months':
                    this.minViewMode = 1;
                    break;
                case 'years':
                    this.minViewMode = 2;
                    break;
                default:
                    this.minViewMode = 0;
                    break;
            }
        }

        this.viewMode = this.startViewMode = Math.max(this.startViewMode, this.minViewMode);

        this.todayBtn = (options.todayBtn||this.element.data('date-today-btn')||false);
        this.todayHighlight = (options.todayHighlight||this.element.data('date-today-highlight')||false);

        this.calendarWeeks = false;
        if ('calendarWeeks' in options) {
            this.calendarWeeks = options.calendarWeeks;
        } else if ('dateCalendarWeeks' in this.element.data()) {
            this.calendarWeeks = this.element.data('date-calendar-weeks');
        }
        if (this.calendarWeeks)
            this.picker.find('tfoot th.today')
                .attr('colspan', function(i, val){
                    return parseInt(val) + 1;
                });

        this._allow_update = false;

        this.weekStart = ((options.weekStart||this.element.data('date-weekstart')||dates[this.language].weekStart||0) % 7);
        this.weekEnd = ((this.weekStart + 6) % 7);
        this.startDate = -Infinity;
        this.endDate = Infinity;
        this.daysOfWeekDisabled = [];
        this.setStartDate(options.startDate||this.element.data('date-startdate'));
        this.setEndDate(options.endDate||this.element.data('date-enddate'));
        this.setDaysOfWeekDisabled(options.daysOfWeekDisabled||this.element.data('date-days-of-week-disabled'));
        this.fillDow();
        this.fillMonths();
        this.setRange(options.range);

        this._allow_update = true;

        this.update();
        this.showMode();

        if(this.isInline) {
            this.show();
        }
    };

    Datepicker.prototype = {
        constructor: Datepicker,

        _events: [],
        _secondaryEvents: [],
        _applyEvents: function(evs){
            for (var i=0, el, ev; i<evs.length; i++){
                el = evs[i][0];
                ev = evs[i][1];
                el.on(ev);
            }
        },
        _unapplyEvents: function(evs){
            for (var i=0, el, ev; i<evs.length; i++){
                el = evs[i][0];
                ev = evs[i][1];
                el.off(ev);
            }
        },
        _buildEvents: function(){
            if (this.isInput) { // single input
                this._events = [
                    [this.element, {
                        focus: $.proxy(this.show, this),
                        keyup: $.proxy(this.update, this),
                        keydown: $.proxy(this.keydown, this)
                    }]
                ];
            }
            else if (this.component && this.hasInput){ // component: input + button
                this._events = [
                    // For components that are not readonly, allow keyboard nav
                    [this.element.find('input'), {
                        focus: $.proxy(this.show, this),
                        keyup: $.proxy(this.update, this),
                        keydown: $.proxy(this.keydown, this)
                    }],
                    [this.component, {
                        click: $.proxy(this.show, this)
                    }]
                ];
            }
            else if (this.element.is('div')) {  // inline datepicker
                this.isInline = true;
            }
            else {
                this._events = [
                    [this.element, {
                        click: $.proxy(this.show, this)
                    }]
                ];
            }

            this._secondaryEvents = [
                [this.picker, {
                    click: $.proxy(this.click, this)
                }],
                [$(window), {
                    resize: $.proxy(this.place, this)
                }],
                [$(document), {
                    mousedown: $.proxy(function (e) {
                        // Clicked outside the datepicker, hide it
                        if ($(e.target).closest('.datepicker.datepicker-inline, .datepicker.datepicker-dropdown').length === 0) {
                            this.hide();
                        }
                    }, this)
                }]
            ];
        },
        _attachEvents: function(){
            this._detachEvents();
            this._applyEvents(this._events);
        },
        _detachEvents: function(){
            this._unapplyEvents(this._events);
        },
        _attachSecondaryEvents: function(){
            this._detachSecondaryEvents();
            this._applyEvents(this._secondaryEvents);
        },
        _detachSecondaryEvents: function(){
            this._unapplyEvents(this._secondaryEvents);
        },

        show: function(e) {
            if (!this.isInline)
                this.picker.appendTo('body');
            this.picker.show();
            this.height = this.component ? this.component.outerHeight() : this.element.outerHeight();
            this.place();
            this._attachSecondaryEvents();
            if (e) {
                e.preventDefault();
            }
            this.element.trigger({
                type: 'show',
                date: this.date
            });
        },

        hide: function(e){
            if(this.isInline) return;
            if (!this.picker.is(':visible')) return;
            this.picker.hide().detach();
            this._detachSecondaryEvents();
            this.viewMode = this.startViewMode;
            this.showMode();

            if (
                this.forceParse &&
                    (
                        this.isInput && this.element.val() ||
                            this.hasInput && this.element.find('input').val()
                        )
                )
                this.setValue();
            this.element.trigger({
                type: 'hide',
                date: this.date
            });
        },

        remove: function() {
            this.hide();
            this._detachEvents();
            this._detachSecondaryEvents();
            this.picker.remove();
            delete this.element.data().datepicker;
            if (!this.isInput) {
                delete this.element.data().date;
            }
        },

        getDate: function() {
            var d = this.getUTCDate();
            return new Date(d.getTime() + (d.getTimezoneOffset()*60000));
        },

        getUTCDate: function() {
            return this.date;
        },

        setDate: function(d) {
            this.setUTCDate(new Date(d.getTime() - (d.getTimezoneOffset()*60000)));
        },

        setUTCDate: function(d) {
            this.date = d;
            this.setValue();
        },

        setValue: function() {
            var formatted = this.getFormattedDate();
            if (!this.isInput) {
                if (this.component){
                    this.element.find('input').val(formatted);
                }
                this.element.data('date', formatted);
            } else {
                this.element.val(formatted);
            }
        },

        getFormattedDate: function(format) {
            if (format === undefined)
                format = this.format;
            return DPGlobal.formatDate(this.date, format, this.language);
        },

        setStartDate: function(startDate){
            this.startDate = startDate||-Infinity;
            if (this.startDate !== -Infinity) {
                this.startDate = DPGlobal.parseDate(this.startDate, this.format, this.language);
            }
            this.update();
            this.updateNavArrows();
        },

        setEndDate: function(endDate){
            this.endDate = endDate||Infinity;
            if (this.endDate !== Infinity) {
                this.endDate = DPGlobal.parseDate(this.endDate, this.format, this.language);
            }
            this.update();
            this.updateNavArrows();
        },

        setDaysOfWeekDisabled: function(daysOfWeekDisabled){
            this.daysOfWeekDisabled = daysOfWeekDisabled||[];
            if (!$.isArray(this.daysOfWeekDisabled)) {
                this.daysOfWeekDisabled = this.daysOfWeekDisabled.split(/,\s*/);
            }
            this.daysOfWeekDisabled = $.map(this.daysOfWeekDisabled, function (d) {
                return parseInt(d, 10);
            });
            this.update();
            this.updateNavArrows();
        },

        place: function(){
            if(this.isInline) return;
            var zIndex = parseInt(this.element.parents().filter(function() {
                return $(this).css('z-index') != 'auto';
            }).first().css('z-index'))+10;
            var offset = this.component ? this.component.parent().offset() : this.element.offset();
            var height = this.component ? this.component.outerHeight(true) : this.element.outerHeight(true);
            this.picker.css({
                top: offset.top + height,
                left: offset.left,
                zIndex: zIndex
            });
        },

        _allow_update: true,
        update: function(){
            if (!this._allow_update) return;

            var date, fromArgs = false;
            if(arguments && arguments.length && (typeof arguments[0] === 'string' || arguments[0] instanceof Date)) {
                date = arguments[0];
                fromArgs = true;
            } else {
                date = this.isInput ? this.element.val() : this.element.data('date') || this.element.find('input').val();
            }

            this.date = DPGlobal.parseDate(date, this.format, this.language);

            if(fromArgs) this.setValue();

            if (this.date < this.startDate) {
                this.viewDate = new Date(this.startDate);
            } else if (this.date > this.endDate) {
                this.viewDate = new Date(this.endDate);
            } else {
                this.viewDate = new Date(this.date);
            }
            this.fill();
        },

        fillDow: function(){
            var dowCnt = this.weekStart,
                html = '<tr>';
            if(this.calendarWeeks){
                var cell = '<th class="cw">&nbsp;</th>';
                html += cell;
                this.picker.find('.datepicker-days thead tr:first-child').prepend(cell);
            }
            while (dowCnt < this.weekStart + 7) {
                html += '<th class="dow">'+dates[this.language].daysMin[(dowCnt++)%7]+'</th>';
            }
            html += '</tr>';
            this.picker.find('.datepicker-days thead').append(html);
        },

        fillMonths: function(){
            var html = '',
                i = 0;
            while (i < 12) {
                html += '<span class="month">'+dates[this.language].monthsShort[i++]+'</span>';
            }
            this.picker.find('.datepicker-months td').html(html);
        },

        setRange: function(range){
            if (!range || !range.length)
                delete this.range;
            else
                this.range = $.map(range, function(d){ return d.valueOf(); });
            this.fill();
        },

        getClassNames: function(date){
            var cls = [],
                year = this.viewDate.getUTCFullYear(),
                month = this.viewDate.getUTCMonth(),
                currentDate = this.date.valueOf(),
                today = new Date();
            if (date.getUTCFullYear() < year || (date.getUTCFullYear() == year && date.getUTCMonth() < month)) {
                cls.push('old');
            } else if (date.getUTCFullYear() > year || (date.getUTCFullYear() == year && date.getUTCMonth() > month)) {
                cls.push('new');
            }
            // Compare internal UTC date with local today, not UTC today
            if (this.todayHighlight &&
                date.getUTCFullYear() == today.getFullYear() &&
                date.getUTCMonth() == today.getMonth() &&
                date.getUTCDate() == today.getDate()) {
                cls.push('today');
            }
            if (currentDate && date.valueOf() == currentDate) {
                cls.push('active');
            }
            if (date.valueOf() < this.startDate || date.valueOf() > this.endDate ||
                $.inArray(date.getUTCDay(), this.daysOfWeekDisabled) !== -1) {
                cls.push('disabled');
            }
            if (this.range){
                if (date > this.range[0] && date < this.range[this.range.length-1]){
                    cls.push('range');
                }
                if ($.inArray(date.valueOf(), this.range) != -1){
                    cls.push('selected');
                }
            }
            return cls;
        },

        fill: function() {
            var d = new Date(this.viewDate),
                year = d.getUTCFullYear(),
                month = d.getUTCMonth(),
                startYear = this.startDate !== -Infinity ? this.startDate.getUTCFullYear() : -Infinity,
                startMonth = this.startDate !== -Infinity ? this.startDate.getUTCMonth() : -Infinity,
                endYear = this.endDate !== Infinity ? this.endDate.getUTCFullYear() : Infinity,
                endMonth = this.endDate !== Infinity ? this.endDate.getUTCMonth() : Infinity,
                currentDate = this.date && this.date.valueOf();
            this.picker.find('.datepicker-days thead th.switch')
                .text(dates[this.language].months[month]+' '+year);
            this.picker.find('tfoot th.today')
                .text(dates[this.language].today)
                .toggle(this.todayBtn !== false);
            this.updateNavArrows();
            this.fillMonths();
            var prevMonth = UTCDate(year, month-1, 28,0,0,0,0),
                day = DPGlobal.getDaysInMonth(prevMonth.getUTCFullYear(), prevMonth.getUTCMonth());
            prevMonth.setUTCDate(day);
            prevMonth.setUTCDate(day - (prevMonth.getUTCDay() - this.weekStart + 7)%7);
            var nextMonth = new Date(prevMonth);
            nextMonth.setUTCDate(nextMonth.getUTCDate() + 42);
            nextMonth = nextMonth.valueOf();
            var html = [];
            var clsName;
            while(prevMonth.valueOf() < nextMonth) {
                if (prevMonth.getUTCDay() == this.weekStart) {
                    html.push('<tr>');
                    if(this.calendarWeeks){
                        // ISO 8601: First week contains first thursday.
                        // ISO also states week starts on Monday, but we can be more abstract here.
                        var
                        // Start of current week: based on weekstart/current date
                            ws = new Date(+prevMonth + (this.weekStart - prevMonth.getUTCDay() - 7) % 7 * 864e5),
                        // Thursday of this week
                            th = new Date(+ws + (7 + 4 - ws.getUTCDay()) % 7 * 864e5),
                        // First Thursday of year, year from thursday
                            yth = new Date(+(yth = UTCDate(th.getUTCFullYear(), 0, 1)) + (7 + 4 - yth.getUTCDay())%7*864e5),
                        // Calendar week: ms between thursdays, div ms per day, div 7 days
                            calWeek =  (th - yth) / 864e5 / 7 + 1;
                        html.push('<td class="cw">'+ calWeek +'</td>');

                    }
                }
                clsName = this.getClassNames(prevMonth);
                clsName.push('day');
                html.push('<td class="'+clsName.join(' ')+'">'+prevMonth.getUTCDate() + '</td>');
                if (prevMonth.getUTCDay() == this.weekEnd) {
                    html.push('</tr>');
                }
                prevMonth.setUTCDate(prevMonth.getUTCDate()+1);
            }
            this.picker.find('.datepicker-days tbody').empty().append(html.join(''));
            var currentYear = this.date && this.date.getUTCFullYear();

            var months = this.picker.find('.datepicker-months')
                .find('th:eq(1)')
                .text(year)
                .end()
                .find('span').removeClass('active');
            if (currentYear && currentYear == year) {
                months.eq(this.date.getUTCMonth()).addClass('active');
            }
            if (year < startYear || year > endYear) {
                months.addClass('disabled');
            }
            if (year == startYear) {
                months.slice(0, startMonth).addClass('disabled');
            }
            if (year == endYear) {
                months.slice(endMonth+1).addClass('disabled');
            }

            html = '';
            year = parseInt(year/10, 10) * 10;
            var yearCont = this.picker.find('.datepicker-years')
                .find('th:eq(1)')
                .text(year + '-' + (year + 9))
                .end()
                .find('td');
            year -= 1;
            for (var i = -1; i < 11; i++) {
                html += '<span class="year'+(i == -1 || i == 10 ? ' old' : '')+(currentYear == year ? ' active' : '')+(year < startYear || year > endYear ? ' disabled' : '')+'">'+year+'</span>';
                year += 1;
            }
            yearCont.html(html);
        },

        updateNavArrows: function() {
            if (!this._allow_update) return;

            var d = new Date(this.viewDate),
                year = d.getUTCFullYear(),
                month = d.getUTCMonth();
            switch (this.viewMode) {
                case 0:
                    if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear() && month <= this.startDate.getUTCMonth()) {
                        this.picker.find('.prev').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.prev').css({visibility: 'visible'});
                    }
                    if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear() && month >= this.endDate.getUTCMonth()) {
                        this.picker.find('.next').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.next').css({visibility: 'visible'});
                    }
                    break;
                case 1:
                case 2:
                    if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()) {
                        this.picker.find('.prev').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.prev').css({visibility: 'visible'});
                    }
                    if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()) {
                        this.picker.find('.next').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.next').css({visibility: 'visible'});
                    }
                    break;
            }
        },

        click: function(e) {
            e.preventDefault();
            var target = $(e.target).closest('span, td, th');
            if (target.length == 1) {
                switch(target[0].nodeName.toLowerCase()) {
                    case 'th':
                        switch(target[0].className) {
                            case 'switch':
                                this.showMode(1);
                                break;
                            case 'prev':
                            case 'next':
                                var dir = DPGlobal.modes[this.viewMode].navStep * (target[0].className == 'prev' ? -1 : 1);
                                switch(this.viewMode){
                                    case 0:
                                        this.viewDate = this.moveMonth(this.viewDate, dir);
                                        break;
                                    case 1:
                                    case 2:
                                        this.viewDate = this.moveYear(this.viewDate, dir);
                                        break;
                                }
                                this.fill();
                                break;
                            case 'today':
                                var date = new Date();
                                date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), 0, 0, 0);

                                this.showMode(-2);
                                var which = this.todayBtn == 'linked' ? null : 'view';
                                this._setDate(date, which);
                                break;
                        }
                        break;
                    case 'span':
                        if (!target.is('.disabled')) {
                            this.viewDate.setUTCDate(1);
                            if (target.is('.month')) {
                                var day = 1;
                                var month = target.parent().find('span').index(target);
                                var year = this.viewDate.getUTCFullYear();
                                this.viewDate.setUTCMonth(month);
                                this.element.trigger({
                                    type: 'changeMonth',
                                    date: this.viewDate
                                });
                                if ( this.minViewMode == 1 ) {
                                    this._setDate(UTCDate(year, month, day,0,0,0,0));
                                }
                            } else {
                                var year = parseInt(target.text(), 10)||0;
                                var day = 1;
                                var month = 0;
                                this.viewDate.setUTCFullYear(year);
                                this.element.trigger({
                                    type: 'changeYear',
                                    date: this.viewDate
                                });
                                if ( this.minViewMode == 2 ) {
                                    this._setDate(UTCDate(year, month, day,0,0,0,0));
                                }
                            }
                            this.showMode(-1);
                            this.fill();
                        }
                        break;
                    case 'td':
                        if (target.is('.day') && !target.is('.disabled')){
                            var day = parseInt(target.text(), 10)||1;
                            var year = this.viewDate.getUTCFullYear(),
                                month = this.viewDate.getUTCMonth();
                            if (target.is('.old')) {
                                if (month === 0) {
                                    month = 11;
                                    year -= 1;
                                } else {
                                    month -= 1;
                                }
                            } else if (target.is('.new')) {
                                if (month == 11) {
                                    month = 0;
                                    year += 1;
                                } else {
                                    month += 1;
                                }
                            }
                            this._setDate(UTCDate(year, month, day,0,0,0,0));
                        }
                        break;
                }
            }
        },

        _setDate: function(date, which){
            if (!which || which == 'date')
                this.date = date;
            if (!which || which  == 'view')
                this.viewDate = date;
            this.fill();
            this.setValue();
            this.element.trigger({
                type: 'changeDate',
                date: this.date
            });
            var element;
            if (this.isInput) {
                element = this.element;
            } else if (this.component){
                element = this.element.find('input');
            }
            if (element) {
                element.change();
                if (this.autoclose && (!which || which == 'date')) {
                    this.hide();
                }
            }
        },

        moveMonth: function(date, dir){
            if (!dir) return date;
            var new_date = new Date(date.valueOf()),
                day = new_date.getUTCDate(),
                month = new_date.getUTCMonth(),
                mag = Math.abs(dir),
                new_month, test;
            dir = dir > 0 ? 1 : -1;
            if (mag == 1){
                test = dir == -1
                    // If going back one month, make sure month is not current month
                    // (eg, Mar 31 -> Feb 31 == Feb 28, not Mar 02)
                    ? function(){ return new_date.getUTCMonth() == month; }
                    // If going forward one month, make sure month is as expected
                    // (eg, Jan 31 -> Feb 31 == Feb 28, not Mar 02)
                    : function(){ return new_date.getUTCMonth() != new_month; };
                new_month = month + dir;
                new_date.setUTCMonth(new_month);
                // Dec -> Jan (12) or Jan -> Dec (-1) -- limit expected date to 0-11
                if (new_month < 0 || new_month > 11)
                    new_month = (new_month + 12) % 12;
            } else {
                // For magnitudes >1, move one month at a time...
                for (var i=0; i<mag; i++)
                    // ...which might decrease the day (eg, Jan 31 to Feb 28, etc)...
                    new_date = this.moveMonth(new_date, dir);
                // ...then reset the day, keeping it in the new month
                new_month = new_date.getUTCMonth();
                new_date.setUTCDate(day);
                test = function(){ return new_month != new_date.getUTCMonth(); };
            }
            // Common date-resetting loop -- if date is beyond end of month, make it
            // end of month
            while (test()){
                new_date.setUTCDate(--day);
                new_date.setUTCMonth(new_month);
            }
            return new_date;
        },

        moveYear: function(date, dir){
            return this.moveMonth(date, dir*12);
        },

        dateWithinRange: function(date){
            return date >= this.startDate && date <= this.endDate;
        },

        keydown: function(e){
            if (this.picker.is(':not(:visible)')){
                if (e.keyCode == 27) // allow escape to hide and re-show picker
                    this.show();
                return;
            }
            var dateChanged = false,
                dir, day, month,
                newDate, newViewDate;
            switch(e.keyCode){
                case 27: // escape
                    this.hide();
                    e.preventDefault();
                    break;
                case 37: // left
                case 39: // right
                    if (!this.keyboardNavigation) break;
                    dir = e.keyCode == 37 ? -1 : 1;
                    if (e.ctrlKey){
                        newDate = this.moveYear(this.date, dir);
                        newViewDate = this.moveYear(this.viewDate, dir);
                    } else if (e.shiftKey){
                        newDate = this.moveMonth(this.date, dir);
                        newViewDate = this.moveMonth(this.viewDate, dir);
                    } else {
                        newDate = new Date(this.date);
                        newDate.setUTCDate(this.date.getUTCDate() + dir);
                        newViewDate = new Date(this.viewDate);
                        newViewDate.setUTCDate(this.viewDate.getUTCDate() + dir);
                    }
                    if (this.dateWithinRange(newDate)){
                        this.date = newDate;
                        this.viewDate = newViewDate;
                        this.setValue();
                        this.update();
                        e.preventDefault();
                        dateChanged = true;
                    }
                    break;
                case 38: // up
                case 40: // down
                    if (!this.keyboardNavigation) break;
                    dir = e.keyCode == 38 ? -1 : 1;
                    if (e.ctrlKey){
                        newDate = this.moveYear(this.date, dir);
                        newViewDate = this.moveYear(this.viewDate, dir);
                    } else if (e.shiftKey){
                        newDate = this.moveMonth(this.date, dir);
                        newViewDate = this.moveMonth(this.viewDate, dir);
                    } else {
                        newDate = new Date(this.date);
                        newDate.setUTCDate(this.date.getUTCDate() + dir * 7);
                        newViewDate = new Date(this.viewDate);
                        newViewDate.setUTCDate(this.viewDate.getUTCDate() + dir * 7);
                    }
                    if (this.dateWithinRange(newDate)){
                        this.date = newDate;
                        this.viewDate = newViewDate;
                        this.setValue();
                        this.update();
                        e.preventDefault();
                        dateChanged = true;
                    }
                    break;
                case 13: // enter
                    this.hide();
                    e.preventDefault();
                    break;
                case 9: // tab
                    this.hide();
                    break;
            }
            if (dateChanged){
                this.element.trigger({
                    type: 'changeDate',
                    date: this.date
                });
                var element;
                if (this.isInput) {
                    element = this.element;
                } else if (this.component){
                    element = this.element.find('input');
                }
                if (element) {
                    element.change();
                }
            }
        },

        showMode: function(dir) {
            if (dir) {
                this.viewMode = Math.max(this.minViewMode, Math.min(2, this.viewMode + dir));
            }
            /*
             vitalets: fixing bug of very special conditions:
             jquery 1.7.1 + webkit + show inline datepicker in bootstrap popover.
             Method show() does not set display css correctly and datepicker is not shown.
             Changed to .css('display', 'block') solve the problem.
             See https://github.com/vitalets/x-editable/issues/37

             In jquery 1.7.2+ everything works fine.
             */
            //this.picker.find('>div').hide().filter('.datepicker-'+DPGlobal.modes[this.viewMode].clsName).show();
            this.picker.find('>div').hide().filter('.datepicker-'+DPGlobal.modes[this.viewMode].clsName).css('display', 'block');
            this.updateNavArrows();
        }
    };

    var DateRangePicker = function(element, options){
        this.element = $(element);
        this.inputs = $.map(options.inputs, function(i){ return i.jquery ? i[0] : i; });
        delete options.inputs;

        $(this.inputs)
            .datepicker(options)
            .bind('changeDate', $.proxy(this.dateUpdated, this));

        this.pickers = $.map(this.inputs, function(i){ return $(i).data('datepicker'); });
        this.updateDates();
    };
    DateRangePicker.prototype = {
        updateDates: function(){
            this.dates = $.map(this.pickers, function(i){ return i.date; });
            this.updateRanges();
        },
        updateRanges: function(){
            var range = $.map(this.dates, function(d){ return d.valueOf(); });
            $.each(this.pickers, function(i, p){
                p.setRange(range);
            });
        },
        dateUpdated: function(e){
            var dp = $(e.target).data('datepicker'),
                new_date = e.date,
                i = $.inArray(e.target, this.inputs),
                l = this.inputs.length;
            if (i == -1) return;

            if (new_date < this.dates[i]){
                // Date being moved earlier/left
                while (i>=0 && new_date < this.dates[i]){
                    this.pickers[i--].setUTCDate(new_date);
                }
            }
            else if (new_date > this.dates[i]){
                // Date being moved later/right
                while (i<l && new_date > this.dates[i]){
                    this.pickers[i++].setUTCDate(new_date);
                }
            }
            this.updateDates();
        },
        remove: function(){
            $.map(this.pickers, function(p){ p.remove(); });
            delete this.element.data().datepicker;
        }
    };

    var old = $.fn.datepicker;
    $.fn.datepicker = function ( option ) {
        var args = Array.apply(null, arguments);
        args.shift();
        return this.each(function () {
            var $this = $(this),
                data = $this.data('datepicker'),
                options = typeof option == 'object' && option;
            if (!data) {
                if ($this.is('.input-daterange') || options.inputs){
                    var opts = {
                        inputs: options.inputs || $this.find('input').toArray()
                    };
                    $this.data('datepicker', (data = new DateRangePicker(this, $.extend(opts, $.fn.datepicker.defaults,options))));
                }
                else{
                    $this.data('datepicker', (data = new Datepicker(this, $.extend({}, $.fn.datepicker.defaults,options))));
                }
            }
            if (typeof option == 'string' && typeof data[option] == 'function') {
                data[option].apply(data, args);
            }
        });
    };

    $.fn.datepicker.defaults = {
    };
    $.fn.datepicker.Constructor = Datepicker;
    var dates = $.fn.datepicker.dates = {
        en: {
            days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
            daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
            daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
            months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            today: "Today"
        }
    };

    var DPGlobal = {
        modes: [
            {
                clsName: 'days',
                navFnc: 'Month',
                navStep: 1
            },
            {
                clsName: 'months',
                navFnc: 'FullYear',
                navStep: 1
            },
            {
                clsName: 'years',
                navFnc: 'FullYear',
                navStep: 10
            }],
        isLeapYear: function (year) {
            return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0));
        },
        getDaysInMonth: function (year, month) {
            return [31, (DPGlobal.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
        },
        validParts: /dd?|DD?|mm?|MM?|yy(?:yy)?/g,
        nonpunctuation: /[^ -\/:-@\[\u3400-\u9fff-`{-~\t\n\r]+/g,
        parseFormat: function(format){
            // IE treats \0 as a string end in inputs (truncating the value),
            // so it's a bad format delimiter, anyway
            var separators = format.replace(this.validParts, '\0').split('\0'),
                parts = format.match(this.validParts);
            if (!separators || !separators.length || !parts || parts.length === 0){
                throw new Error("Invalid date format.");
            }
            return {separators: separators, parts: parts};
        },
        parseDate: function(date, format, language) {
            if (date instanceof Date) return date;
            if (/^[\-+]\d+[dmwy]([\s,]+[\-+]\d+[dmwy])*$/.test(date)) {
                var part_re = /([\-+]\d+)([dmwy])/,
                    parts = date.match(/([\-+]\d+)([dmwy])/g),
                    part, dir;
                date = new Date();
                for (var i=0; i<parts.length; i++) {
                    part = part_re.exec(parts[i]);
                    dir = parseInt(part[1]);
                    switch(part[2]){
                        case 'd':
                            date.setUTCDate(date.getUTCDate() + dir);
                            break;
                        case 'm':
                            date = Datepicker.prototype.moveMonth.call(Datepicker.prototype, date, dir);
                            break;
                        case 'w':
                            date.setUTCDate(date.getUTCDate() + dir * 7);
                            break;
                        case 'y':
                            date = Datepicker.prototype.moveYear.call(Datepicker.prototype, date, dir);
                            break;
                    }
                }
                return UTCDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), 0, 0, 0);
            }
            var parts = date && date.match(this.nonpunctuation) || [],
                date = new Date(),
                parsed = {},
                setters_order = ['yyyy', 'yy', 'M', 'MM', 'm', 'mm', 'd', 'dd'],
                setters_map = {
                    yyyy: function(d,v){ return d.setUTCFullYear(v); },
                    yy: function(d,v){ return d.setUTCFullYear(2000+v); },
                    m: function(d,v){
                        v -= 1;
                        while (v<0) v += 12;
                        v %= 12;
                        d.setUTCMonth(v);
                        while (d.getUTCMonth() != v)
                            d.setUTCDate(d.getUTCDate()-1);
                        return d;
                    },
                    d: function(d,v){ return d.setUTCDate(v); }
                },
                val, filtered, part;
            setters_map['M'] = setters_map['MM'] = setters_map['mm'] = setters_map['m'];
            setters_map['dd'] = setters_map['d'];
            date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), 0, 0, 0);
            var fparts = format.parts.slice();
            // Remove noop parts
            if (parts.length != fparts.length) {
                fparts = $(fparts).filter(function(i,p){
                    return $.inArray(p, setters_order) !== -1;
                }).toArray();
            }
            // Process remainder
            if (parts.length == fparts.length) {
                for (var i=0, cnt = fparts.length; i < cnt; i++) {
                    val = parseInt(parts[i], 10);
                    part = fparts[i];
                    if (isNaN(val)) {
                        switch(part) {
                            case 'MM':
                                filtered = $(dates[language].months).filter(function(){
                                    var m = this.slice(0, parts[i].length),
                                        p = parts[i].slice(0, m.length);
                                    return m == p;
                                });
                                val = $.inArray(filtered[0], dates[language].months) + 1;
                                break;
                            case 'M':
                                filtered = $(dates[language].monthsShort).filter(function(){
                                    var m = this.slice(0, parts[i].length),
                                        p = parts[i].slice(0, m.length);
                                    return m == p;
                                });
                                val = $.inArray(filtered[0], dates[language].monthsShort) + 1;
                                break;
                        }
                    }
                    parsed[part] = val;
                }
                for (var i=0, s; i<setters_order.length; i++){
                    s = setters_order[i];
                    if (s in parsed && !isNaN(parsed[s]))
                        setters_map[s](date, parsed[s]);
                }
            }
            return date;
        },
        formatDate: function(date, format, language){
            var val = {
                d: date.getUTCDate(),
                D: dates[language].daysShort[date.getUTCDay()],
                DD: dates[language].days[date.getUTCDay()],
                m: date.getUTCMonth() + 1,
                M: dates[language].monthsShort[date.getUTCMonth()],
                MM: dates[language].months[date.getUTCMonth()],
                yy: date.getUTCFullYear().toString().substring(2),
                yyyy: date.getUTCFullYear()
            };
            val.dd = (val.d < 10 ? '0' : '') + val.d;
            val.mm = (val.m < 10 ? '0' : '') + val.m;
            var date = [],
                seps = $.extend([], format.separators);
            for (var i=0, cnt = format.parts.length; i < cnt; i++) {
                if (seps.length)
                    date.push(seps.shift());
                date.push(val[format.parts[i]]);
            }
            return date.join('');
        },
        headTemplate: '<thead>'+
            '<tr>'+
            '<th class="prev"><i class="icon-arrow-left"/></th>'+
            '<th colspan="5" class="switch"></th>'+
            '<th class="next"><i class="icon-arrow-right"/></th>'+
            '</tr>'+
            '</thead>',
        contTemplate: '<tbody><tr><td colspan="7"></td></tr></tbody>',
        footTemplate: '<tfoot><tr><th colspan="7" class="today"></th></tr></tfoot>'
    };
    DPGlobal.template = '<div class="datepicker">'+
        '<div class="datepicker-days">'+
        '<table class=" table-condensed">'+
        DPGlobal.headTemplate+
        '<tbody></tbody>'+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '<div class="datepicker-months">'+
        '<table class="table-condensed">'+
        DPGlobal.headTemplate+
        DPGlobal.contTemplate+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '<div class="datepicker-years">'+
        '<table class="table-condensed">'+
        DPGlobal.headTemplate+
        DPGlobal.contTemplate+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '</div>';

    $.fn.datepicker.DPGlobal = DPGlobal;


    /* DATEPICKER NO CONFLICT
     * =================== */

    $.fn.datepicker.noConflict = function(){
        $.fn.datepicker = old;
        return this;
    };


    /* DATEPICKER DATA-API
     * ================== */

    $(document).on(
        'focus.datepicker.data-api click.datepicker.data-api',
        '[data-provide="datepicker"]',
        function(e){
            var $this = $(this);
            if ($this.data('datepicker')) return;
            e.preventDefault();
            // component click requires us to explicitly show it
            $this.datepicker('show');
        }
    );
    $(function(){
        $('[data-provide="datepicker-inline"]').datepicker();
    });

}( window.jQuery );
/**
 * Simplified Chinese translation for bootstrap-datepicker
 */
;(function($){
    $.fn.datepicker.dates['zh-CN'] = {
        days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
        months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        today: "今日"
    };
}(jQuery));

!function( $ ) {

    function UTCDate(){
        return new Date(Date.UTC.apply(Date, arguments));
    }
    function UTCToday(){
        var today = new Date();
        return UTCDate(today.getUTCFullYear(), today.getUTCMonth(), today.getUTCDate(), today.getUTCHours(), today.getUTCMinutes(), today.getUTCSeconds(), 0);
    }

    // Picker object

    var Datetimepicker = function(element, options) {
        var that = this;

        this.element = $(element);
        this.language = options.language || this.element.data('date-language') || "en";
        this.language = this.language in dates ? this.language : "en";
        this.isRTL = dates[this.language].rtl || false;
        this.formatType = options.formatType || this.element.data('format-type') || 'standard';
        this.format = DPGlobal.parseFormat(options.format || this.element.data('date-format') || DPGlobal.getDefaultFormat(this.formatType, 'input'), this.formatType);
        this.isInline = false;
        this.isVisible = false;
        this.isInput = this.element.is('input');
        this.component = this.element.is('.date') ? this.element.find('.add-on .icon-th, .add-on .icon-time, .add-on .icon-calendar').parent() : false;
        this.componentReset = this.element.is('.date') ? this.element.find('.add-on .icon-remove').parent() : false;
        this.hasInput = this.component && this.element.find('input').length;
        if (this.component && this.component.length === 0) {
            this.component = false;
        }
        this.linkField = options.linkField || this.element.data('link-field') || false;
        this.linkFormat = DPGlobal.parseFormat(options.linkFormat || this.element.data('link-format') || DPGlobal.getDefaultFormat(this.formatType, 'link'), this.formatType);
        this.minuteStep = options.minuteStep || this.element.data('minute-step') || 5;
        this.pickerPosition = options.pickerPosition || this.element.data('picker-position') || 'bottom-right';
        this.showMeridian = options.showMeridian || this.element.data('show-meridian') || false;
        this.initialDate = options.initialDate || new Date();

        this._attachEvents();

        this.minView = 0;
        if ('minView' in options) {
            this.minView = options.minView;
        } else if ('minView' in this.element.data()) {
            this.minView = this.element.data('min-view');
        }
        this.minView = DPGlobal.convertViewMode(this.minView);

        this.maxView = DPGlobal.modes.length-1;
        if ('maxView' in options) {
            this.maxView = options.maxView;
        } else if ('maxView' in this.element.data()) {
            this.maxView = this.element.data('max-view');
        }
        this.maxView = DPGlobal.convertViewMode(this.maxView);

        this.startViewMode = 2;
        if ('startView' in options) {
            this.startViewMode = options.startView;
        } else if ('startView' in this.element.data()) {
            this.startViewMode = this.element.data('start-view');
        }
        this.startViewMode = DPGlobal.convertViewMode(this.startViewMode);
        this.viewMode = this.startViewMode;

        this.viewSelect = this.minView;
        if ('viewSelect' in options) {
            this.viewSelect = options.viewSelect;
        } else if ('viewSelect' in this.element.data()) {
            this.viewSelect = this.element.data('view-select');
        }
        this.viewSelect = DPGlobal.convertViewMode(this.viewSelect);

        this.forceParse = true;
        if ('forceParse' in options) {
            this.forceParse = options.forceParse;
        } else if ('dateForceParse' in this.element.data()) {
            this.forceParse = this.element.data('date-force-parse');
        }

        this.picker = $(DPGlobal.template)
            .appendTo(this.isInline ? this.element : 'body')
            .on({
                click: $.proxy(this.click, this),
                mousedown: $.proxy(this.mousedown, this)
            });

        if (this.isInline) {
            this.picker.addClass('datetimepicker-inline');
        } else {
            if (this.component && this.pickerPosition == 'bottom-left') {
                this.picker.addClass('datetimepicker-dropdown-left dropdown-menu');
            } else {
                this.picker.addClass('datetimepicker-dropdown dropdown-menu');
            }
        }
        if (this.isRTL){
            this.picker.addClass('datetimepicker-rtl');
            this.picker.find('.prev i, .next i')
                .toggleClass('icon-arrow-left icon-arrow-right');
        }
        $(document).on('mousedown', function (e) {
            // Clicked outside the datetimepicker, hide it
            if ($(e.target).closest('.datetimepicker').length === 0) {
                that.hide();
            }
        });

        this.autoclose = false;
        if ('autoclose' in options) {
            this.autoclose = options.autoclose;
        } else if ('dateAutoclose' in this.element.data()) {
            this.autoclose = this.element.data('date-autoclose');
        }

        this.keyboardNavigation = true;
        if ('keyboardNavigation' in options) {
            this.keyboardNavigation = options.keyboardNavigation;
        } else if ('dateKeyboardNavigation' in this.element.data()) {
            this.keyboardNavigation = this.element.data('date-keyboard-navigation');
        }

        this.todayBtn = (options.todayBtn || this.element.data('date-today-btn') || false);
        this.todayHighlight = (options.todayHighlight || this.element.data('date-today-highlight') || false);

        this.weekStart = ((options.weekStart || this.element.data('date-weekstart') || dates[this.language].weekStart || 0) % 7);
        this.weekEnd = ((this.weekStart + 6) % 7);
        this.startDate = -Infinity;
        this.endDate = Infinity;
        this.daysOfWeekDisabled = [];
        this.setStartDate(options.startDate || this.element.data('date-startdate'));
        this.setEndDate(options.endDate || this.element.data('date-enddate'));
        this.setDaysOfWeekDisabled(options.daysOfWeekDisabled || this.element.data('date-days-of-week-disabled'));
        this.fillDow();
        this.fillMonths();
        this.update();
        this.showMode();

        if(this.isInline) {
            this.show();
        }
    };

    Datetimepicker.prototype = {
        constructor: Datetimepicker,

        _events: [],
        _attachEvents: function(){
            this._detachEvents();
            if (this.isInput) { // single input
                this._events = [
                    [this.element, {
                        focus: $.proxy(this.show, this),
                        keyup: $.proxy(this.update, this),
                        keydown: $.proxy(this.keydown, this)
                    }]
                ];
            }
            else if (this.component && this.hasInput){ // component: input + button
                this._events = [
                    // For components that are not readonly, allow keyboard nav
                    [this.element.find('input'), {
                        focus: $.proxy(this.show, this),
                        keyup: $.proxy(this.update, this),
                        keydown: $.proxy(this.keydown, this)
                    }],
                    [this.component, {
                        click: $.proxy(this.show, this)
                    }]
                ];
                if (this.componentReset) {
                    this._events.push([
                        this.componentReset,
                        {click: $.proxy(this.reset, this)}
                    ]);
                }
            }
            else if (this.element.is('div')) {  // inline datetimepicker
                this.isInline = true;
            }
            else {
                this._events = [
                    [this.element, {
                        click: $.proxy(this.show, this)
                    }]
                ];
            }
            for (var i=0, el, ev; i<this._events.length; i++){
                el = this._events[i][0];
                ev = this._events[i][1];
                el.on(ev);
            }
        },

        _detachEvents: function(){
            for (var i=0, el, ev; i<this._events.length; i++){
                el = this._events[i][0];
                ev = this._events[i][1];
                el.off(ev);
            }
            this._events = [];
        },

        show: function(e) {
            this.picker.show();
            this.height = this.component ? this.component.outerHeight() : this.element.outerHeight();
            if (this.forceParse) {
                this.update();
            }
            this.place();
            $(window).on('resize', $.proxy(this.place, this));
            if (e) {
                e.stopPropagation();
                e.preventDefault();
            }
            this.isVisible = true;
            this.element.trigger({
                type: 'show',
                date: this.date
            });
        },

        hide: function(e){
            if(!this.isVisible) return;
            if(this.isInline) return;
            this.picker.hide();
            $(window).off('resize', this.place);
            this.viewMode = this.startViewMode;
            this.showMode();
            if (!this.isInput) {
                $(document).off('mousedown', this.hide);
            }

            if (
                this.forceParse &&
                    (
                        this.isInput && this.element.val()  ||
                            this.hasInput && this.element.find('input').val()
                        )
                )
                this.setValue();
            this.isVisible = false;
            this.element.trigger({
                type: 'hide',
                date: this.date
            });
        },

        remove: function() {
            this._detachEvents();
            this.picker.remove();
            delete this.element.data().datetimepicker;
        },

        getDate: function() {
            var d = this.getUTCDate();
            return new Date(d.getTime() + (d.getTimezoneOffset()*60000));
        },

        getUTCDate: function() {
            return this.date;
        },

        setDate: function(d) {
            this.setUTCDate(new Date(d.getTime() - (d.getTimezoneOffset()*60000)));
        },

        setUTCDate: function(d) {
            if (d >= this.startDate && d <= this.endDate) {
                this.date = d;
                this.setValue();
                this.viewDate = this.date;
                this.fill();
            } else {
                this.element.trigger({
                    type: 'outOfRange',
                    date: d,
                    startDate: this.startDate,
                    endDate: this.endDate
                });
            }
        },

        setValue: function() {
            var formatted = this.getFormattedDate();
            if (!this.isInput) {
                if (this.component){
                    this.element.find('input').prop('value', formatted);
                }
                this.element.data('date', formatted);
            } else {
                this.element.prop('value', formatted);
            }
            if (this.linkField) {
                $('#' + this.linkField).val(this.getFormattedDate(this.linkFormat));
            }
        },

        getFormattedDate: function(format) {
            if(format == undefined) format = this.format;
            return DPGlobal.formatDate(this.date, format, this.language, this.formatType);
        },

        setStartDate: function(startDate){
            this.startDate = startDate || -Infinity;
            if (this.startDate !== -Infinity) {
                this.startDate = DPGlobal.parseDate(this.startDate, this.format, this.language, this.formatType);
            }
            this.update();
            this.updateNavArrows();
        },

        setEndDate: function(endDate){
            this.endDate = endDate || Infinity;
            if (this.endDate !== Infinity) {
                this.endDate = DPGlobal.parseDate(this.endDate, this.format, this.language, this.formatType);
            }
            this.update();
            this.updateNavArrows();
        },

        setDaysOfWeekDisabled: function(daysOfWeekDisabled){
            this.daysOfWeekDisabled = daysOfWeekDisabled || [];
            if (!$.isArray(this.daysOfWeekDisabled)) {
                this.daysOfWeekDisabled = this.daysOfWeekDisabled.split(/,\s*/);
            }
            this.daysOfWeekDisabled = $.map(this.daysOfWeekDisabled, function (d) {
                return parseInt(d, 10);
            });
            this.update();
            this.updateNavArrows();
        },

        place: function(){
            if(this.isInline) return;
            var zIndex = parseInt(this.element.parents().filter(function() {
                return $(this).css('z-index') != 'auto';
            }).first().css('z-index'))+10;
            var offset, left;
            if (this.component) {
                offset = this.component.offset();
                left = offset.left;
                if (this.pickerPosition == 'bottom-left') {
                    left += this.component.outerWidth() - this.picker.outerWidth();
                }
            } else {
                offset = this.element.offset();
                left = offset.left;
            }
            this.picker.css({
                top: offset.top + this.height,
                left: left,
                zIndex: zIndex
            });
        },

        update: function(){
            var date, fromArgs = false;
            if(arguments && arguments.length && (typeof arguments[0] === 'string' || arguments[0] instanceof Date)) {
                date = arguments[0];
                fromArgs = true;
            } else {
                date = this.isInput ? this.element.prop('value') : this.element.data('date') || this.element.find('input').prop('value') || this.initialDate;
            }

            if (!date) {
                date = new Date();
                fromArgs = false;
            }

            this.date = DPGlobal.parseDate(date, this.format, this.language, this.formatType);

            if (fromArgs) this.setValue();

            if (this.date < this.startDate) {
                this.viewDate = new Date(this.startDate);
            } else if (this.date > this.endDate) {
                this.viewDate = new Date(this.endDate);
            } else {
                this.viewDate = new Date(this.date);
            }
            this.fill();
        },

        fillDow: function(){
            var dowCnt = this.weekStart,
                html = '<tr>';
            while (dowCnt < this.weekStart + 7) {
                html += '<th class="dow">'+dates[this.language].daysMin[(dowCnt++)%7]+'</th>';
            }
            html += '</tr>';
            this.picker.find('.datetimepicker-days thead').append(html);
        },

        fillMonths: function(){
            var html = '',
                i = 0;
            while (i < 12) {
                html += '<span class="month">'+dates[this.language].monthsShort[i++]+'</span>';
            }
            this.picker.find('.datetimepicker-months td').html(html);
        },

        fill: function() {
            if (this.date == null || this.viewDate == null) {
                return;
            }
            var d = new Date(this.viewDate),
                year = d.getUTCFullYear(),
                month = d.getUTCMonth(),
                dayMonth = d.getUTCDate(),
                hours = d.getUTCHours(),
                minutes = d.getUTCMinutes(),
                startYear = this.startDate !== -Infinity ? this.startDate.getUTCFullYear() : -Infinity,
                startMonth = this.startDate !== -Infinity ? this.startDate.getUTCMonth() : -Infinity,
                endYear = this.endDate !== Infinity ? this.endDate.getUTCFullYear() : Infinity,
                endMonth = this.endDate !== Infinity ? this.endDate.getUTCMonth() : Infinity,
                currentDate = (new UTCDate(this.date.getUTCFullYear(), this.date.getUTCMonth(), this.date.getUTCDate())).valueOf(),
                today = new Date();
            this.picker.find('.datetimepicker-days thead th:eq(1)')
                .text(dates[this.language].months[month]+' '+year);
            this.picker.find('.datetimepicker-hours thead th:eq(1)')
                .text(dayMonth+' '+dates[this.language].months[month]+' '+year);
            this.picker.find('.datetimepicker-minutes thead th:eq(1)')
                .text(dayMonth+' '+dates[this.language].months[month]+' '+year);
            this.picker.find('tfoot th.today')
                .text(dates[this.language].today)
                .toggle(this.todayBtn !== false);
            this.updateNavArrows();
            this.fillMonths();
            /*var prevMonth = UTCDate(year, month, 0,0,0,0,0);
             prevMonth.setUTCDate(prevMonth.getDate() - (prevMonth.getUTCDay() - this.weekStart + 7)%7);*/
            var prevMonth = UTCDate(year, month-1, 28,0,0,0,0),
                day = DPGlobal.getDaysInMonth(prevMonth.getUTCFullYear(), prevMonth.getUTCMonth());
            prevMonth.setUTCDate(day);
            prevMonth.setUTCDate(day - (prevMonth.getUTCDay() - this.weekStart + 7)%7);
            var nextMonth = new Date(prevMonth);
            nextMonth.setUTCDate(nextMonth.getUTCDate() + 42);
            nextMonth = nextMonth.valueOf();
            var html = [];
            var clsName;
            while(prevMonth.valueOf() < nextMonth) {
                if (prevMonth.getUTCDay() == this.weekStart) {
                    html.push('<tr>');
                }
                clsName = '';
                if (prevMonth.getUTCFullYear() < year || (prevMonth.getUTCFullYear() == year && prevMonth.getUTCMonth() < month)) {
                    clsName += ' old';
                } else if (prevMonth.getUTCFullYear() > year || (prevMonth.getUTCFullYear() == year && prevMonth.getUTCMonth() > month)) {
                    clsName += ' new';
                }
                // Compare internal UTC date with local today, not UTC today
                if (this.todayHighlight &&
                    prevMonth.getUTCFullYear() == today.getFullYear() &&
                    prevMonth.getUTCMonth() == today.getMonth() &&
                    prevMonth.getUTCDate() == today.getDate()) {
                    clsName += ' today';
                }
                if (prevMonth.valueOf() == currentDate) {
                    clsName += ' active';
                }
                if ((prevMonth.valueOf() + 86400000) <= this.startDate || prevMonth.valueOf() > this.endDate ||
                    $.inArray(prevMonth.getUTCDay(), this.daysOfWeekDisabled) !== -1) {
                    clsName += ' disabled';
                }
                html.push('<td class="day'+clsName+'">'+prevMonth.getUTCDate() + '</td>');
                if (prevMonth.getUTCDay() == this.weekEnd) {
                    html.push('</tr>');
                }
                prevMonth.setUTCDate(prevMonth.getUTCDate()+1);
            }
            this.picker.find('.datetimepicker-days tbody').empty().append(html.join(''));

            html = [];
            var txt = '', meridian = '', meridianOld = '';
            for (var i=0;i<24;i++) {
                var actual = UTCDate(year, month, dayMonth, i);
                clsName = '';
                // We want the previous hour for the startDate
                if ((actual.valueOf() + 3600000) <= this.startDate || actual.valueOf() > this.endDate) {
                    clsName += ' disabled';
                } else if (hours == i) {
                    clsName += ' active';
                }
                if (this.showMeridian && dates[this.language].meridiem.length == 2) {
                    meridian = (i<12?dates[this.language].meridiem[0]:dates[this.language].meridiem[1]);
                    if (meridian != meridianOld) {
                        if (meridianOld != '') {
                            html.push('</fieldset>');
                        }
                        html.push('<fieldset class="hour"><legend>'+meridian.toUpperCase()+'</legend>');
                    }
                    meridianOld = meridian;
                    txt = (i%12?i%12:12);
                    html.push('<span class="hour'+clsName+' hour_'+(i<12?'am':'pm')+'">'+txt+'</span>');
                    if (i == 23) {
                        html.push('</fieldset>');
                    }
                } else {
                    txt = i+':00';
                    html.push('<span class="hour'+clsName+'">'+txt+'</span>');
                }
            }
            this.picker.find('.datetimepicker-hours td').html(html.join(''));

            html = [];
            txt = '', meridian = '', meridianOld = '';
            for(var i=0;i<60;i+=this.minuteStep) {
                var actual = UTCDate(year, month, dayMonth, hours, i, 0);
                clsName = '';
                if (actual.valueOf() < this.startDate || actual.valueOf() > this.endDate) {
                    clsName += ' disabled';
                } else if (Math.floor(minutes/this.minuteStep) == Math.floor(i/this.minuteStep)) {
                    clsName += ' active';
                }
                if (this.showMeridian && dates[this.language].meridiem.length == 2) {
                    meridian = (hours<12?dates[this.language].meridiem[0]:dates[this.language].meridiem[1]);
                    if (meridian != meridianOld) {
                        if (meridianOld != '') {
                            html.push('</fieldset>');
                        }
                        html.push('<fieldset class="minute"><legend>'+meridian.toUpperCase()+'</legend>');
                    }
                    meridianOld = meridian;
                    txt = (hours%12?hours%12:12);
                    //html.push('<span class="minute'+clsName+' minute_'+(hours<12?'am':'pm')+'">'+txt+'</span>');
                    html.push('<span class="minute'+clsName+'">'+txt+':'+(i<10?'0'+i:i)+'</span>');
                    if (i == 59) {
                        html.push('</fieldset>');
                    }
                } else {
                    txt = i+':00';
                    //html.push('<span class="hour'+clsName+'">'+txt+'</span>');
                    html.push('<span class="minute'+clsName+'">'+hours+':'+(i<10?'0'+i:i)+'</span>');
                }
            }
            this.picker.find('.datetimepicker-minutes td').html(html.join(''));

            var currentYear = this.date.getUTCFullYear();
            var months = this.picker.find('.datetimepicker-months')
                .find('th:eq(1)')
                .text(year)
                .end()
                .find('span').removeClass('active');
            if (currentYear == year) {
                months.eq(this.date.getUTCMonth()).addClass('active');
            }
            if (year < startYear || year > endYear) {
                months.addClass('disabled');
            }
            if (year == startYear) {
                months.slice(0, startMonth).addClass('disabled');
            }
            if (year == endYear) {
                months.slice(endMonth+1).addClass('disabled');
            }

            html = '';
            year = parseInt(year/10, 10) * 10;
            var yearCont = this.picker.find('.datetimepicker-years')
                .find('th:eq(1)')
                .text(year + '-' + (year + 9))
                .end()
                .find('td');
            year -= 1;
            for (var i = -1; i < 11; i++) {
                html += '<span class="year'+(i == -1 || i == 10 ? ' old' : '')+(currentYear == year ? ' active' : '')+(year < startYear || year > endYear ? ' disabled' : '')+'">'+year+'</span>';
                year += 1;
            }
            yearCont.html(html);
        },

        updateNavArrows: function() {
            var d = new Date(this.viewDate),
                year = d.getUTCFullYear(),
                month = d.getUTCMonth(),
                day = d.getUTCDate(),
                hour = d.getUTCHours();
            switch (this.viewMode) {
                case 0:
                    if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()
                        && month <= this.startDate.getUTCMonth()
                        && day <= this.startDate.getUTCDate()
                        && hour <= this.startDate.getUTCHours()) {
                        this.picker.find('.prev').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.prev').css({visibility: 'visible'});
                    }
                    if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()
                        && month >= this.endDate.getUTCMonth()
                        && day >= this.endDate.getUTCDate()
                        && hour >= this.endDate.getUTCHours()) {
                        this.picker.find('.next').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.next').css({visibility: 'visible'});
                    }
                    break;
                case 1:
                    if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()
                        && month <= this.startDate.getUTCMonth()
                        && day <= this.startDate.getUTCDate()) {
                        this.picker.find('.prev').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.prev').css({visibility: 'visible'});
                    }
                    if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()
                        && month >= this.endDate.getUTCMonth()
                        && day >= this.endDate.getUTCDate()) {
                        this.picker.find('.next').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.next').css({visibility: 'visible'});
                    }
                    break;
                case 2:
                    if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()
                        && month <= this.startDate.getUTCMonth()) {
                        this.picker.find('.prev').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.prev').css({visibility: 'visible'});
                    }
                    if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()
                        && month >= this.endDate.getUTCMonth()) {
                        this.picker.find('.next').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.next').css({visibility: 'visible'});
                    }
                    break;
                case 3:
                case 4:
                    if (this.startDate !== -Infinity && year <= this.startDate.getUTCFullYear()) {
                        this.picker.find('.prev').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.prev').css({visibility: 'visible'});
                    }
                    if (this.endDate !== Infinity && year >= this.endDate.getUTCFullYear()) {
                        this.picker.find('.next').css({visibility: 'hidden'});
                    } else {
                        this.picker.find('.next').css({visibility: 'visible'});
                    }
                    break;
            }
        },

        click: function(e) {
            e.stopPropagation();
            e.preventDefault();
            var target = $(e.target).closest('span, td, th, legend');
            if (target.length == 1) {
                if (target.is('.disabled')) {
                    this.element.trigger({
                        type: 'outOfRange',
                        date: this.viewDate,
                        startDate: this.startDate,
                        endDate: this.endDate
                    });
                    return;
                }
                switch(target[0].nodeName.toLowerCase()) {
                    case 'th':
                        switch(target[0].className) {
                            case 'switch':
                                this.showMode(1);
                                break;
                            case 'prev':
                            case 'next':
                                var dir = DPGlobal.modes[this.viewMode].navStep * (target[0].className == 'prev' ? -1 : 1);
                                switch(this.viewMode){
                                    case 0:
                                        this.viewDate = this.moveHour(this.viewDate, dir);
                                        break;
                                    case 1:
                                        this.viewDate = this.moveDate(this.viewDate, dir);
                                        break;
                                    case 2:
                                        this.viewDate = this.moveMonth(this.viewDate, dir);
                                        break;
                                    case 3:
                                    case 4:
                                        this.viewDate = this.moveYear(this.viewDate, dir);
                                        break;
                                }
                                this.fill();
                                break;
                            case 'today':
                                var date = new Date();
                                date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds(), 0);

                                this.viewMode = this.startViewMode;
                                this.showMode(0);
                                this._setDate(date);
                                this.fill();
                                if (this.autoclose) {
                                    this.hide();
                                }
                                break;
                        }
                        break;
                    case 'span':
                        if (!target.is('.disabled')) {
                            var year    = this.viewDate.getUTCFullYear(),
                                month   = this.viewDate.getUTCMonth(),
                                day     = this.viewDate.getUTCDate(),
                                hours   = this.viewDate.getUTCHours(),
                                minutes = this.viewDate.getUTCMinutes(),
                                seconds = this.viewDate.getUTCSeconds();

                            if (target.is('.month')) {
                                this.viewDate.setUTCDate(1);
                                month = target.parent().find('span').index(target);
                                day   = this.viewDate.getUTCDate();
                                this.viewDate.setUTCMonth(month);
                                this.element.trigger({
                                    type: 'changeMonth',
                                    date: this.viewDate
                                });
                                if (this.viewSelect >= 3) {
                                    this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
                                }
                            } else if (target.is('.year')) {
                                this.viewDate.setUTCDate(1);
                                year = parseInt(target.text(), 10) || 0;
                                this.viewDate.setUTCFullYear(year);
                                this.element.trigger({
                                    type: 'changeYear',
                                    date: this.viewDate
                                });
                                if (this.viewSelect >= 4) {
                                    this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
                                }
                            } else if (target.is('.hour')){
                                hours = parseInt(target.text(), 10) || 0;
                                if (target.hasClass('hour_am') || target.hasClass('hour_pm')) {
                                    if (hours == 12 && target.hasClass('hour_am')) {
                                        hours = 0;
                                    } else if (hours != 12 && target.hasClass('hour_pm')) {
                                        hours += 12;
                                    }
                                }
                                this.viewDate.setUTCHours(hours);
                                this.element.trigger({
                                    type: 'changeHour',
                                    date: this.viewDate
                                });
                                if (this.viewSelect >= 1) {
                                    this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
                                }
                            } else if (target.is('.minute')){
                                minutes = parseInt(target.text().substr(target.text().indexOf(':')+1), 10) || 0;
                                this.viewDate.setUTCMinutes(minutes);
                                this.element.trigger({
                                    type: 'changeMinute',
                                    date: this.viewDate
                                });
                                if (this.viewSelect >= 0) {
                                    this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
                                }
                            }
                            if (this.viewMode != 0) {
                                var oldViewMode = this.viewMode;
                                this.showMode(-1);
                                this.fill();
                                if (oldViewMode == this.viewMode && this.autoclose) {
                                    this.hide();
                                }
                            } else {
                                this.fill();
                                if (this.autoclose) {
                                    this.hide();
                                }
                            }
                        }
                        break;
                    case 'td':
                        if (target.is('.day') && !target.is('.disabled')){
                            var day = parseInt(target.text(), 10) || 1;
                            var year = this.viewDate.getUTCFullYear(),
                                month = this.viewDate.getUTCMonth(),
                                hours = this.viewDate.getUTCHours(),
                                minutes = this.viewDate.getUTCMinutes(),
                                seconds = this.viewDate.getUTCSeconds();
                            if (target.is('.old')) {
                                if (month === 0) {
                                    month = 11;
                                    year -= 1;
                                } else {
                                    month -= 1;
                                }
                            } else if (target.is('.new')) {
                                if (month == 11) {
                                    month = 0;
                                    year += 1;
                                } else {
                                    month += 1;
                                }
                            }
                            this.viewDate.setUTCDate(day);
                            this.viewDate.setUTCMonth(month);
                            this.viewDate.setUTCFullYear(year);
                            this.element.trigger({
                                type: 'changeDay',
                                date: this.viewDate
                            });
                            if (this.viewSelect >= 2) {
                                this._setDate(UTCDate(year, month, day, hours, minutes, seconds, 0));
                            }
                        }
                        var oldViewMode = this.viewMode;
                        this.showMode(-1);
                        this.fill();
                        if (oldViewMode == this.viewMode && this.autoclose) {
                            this.hide();
                        }
                        break;
                }
            }
        },

        _setDate: function(date, which){
            if (!which || which == 'date')
                this.date = date;
            if (!which || which  == 'view')
                this.viewDate = date;
            this.fill();
            this.element.trigger({
                type: 'changeDate',
                date: this.date
            });
            this.setValue();
            var element;
            if (this.isInput) {
                element = this.element;
            } else if (this.component){
                element = this.element.find('input');
            }
            if (element) {
                element.change();
                if (this.autoclose && (!which || which == 'date')) {
                    //this.hide();
                }
            }
        },

        moveMinute: function(date, dir){
            if (!dir) return date;
            var new_date = new Date(date.valueOf());
            //dir = dir > 0 ? 1 : -1;
            new_date.setUTCMinutes(new_date.getUTCMinutes() + (dir * this.minuteStep));
            return new_date;
        },

        moveHour: function(date, dir){
            if (!dir) return date;
            var new_date = new Date(date.valueOf());
            //dir = dir > 0 ? 1 : -1;
            new_date.setUTCHours(new_date.getUTCHours() + dir);
            return new_date;
        },

        moveDate: function(date, dir){
            if (!dir) return date;
            var new_date = new Date(date.valueOf());
            //dir = dir > 0 ? 1 : -1;
            new_date.setUTCDate(new_date.getUTCDate() + dir);
            return new_date;
        },

        moveMonth: function(date, dir){
            if (!dir) return date;
            var new_date = new Date(date.valueOf()),
                day = new_date.getUTCDate(),
                month = new_date.getUTCMonth(),
                mag = Math.abs(dir),
                new_month, test;
            dir = dir > 0 ? 1 : -1;
            if (mag == 1){
                test = dir == -1
                    // If going back one month, make sure month is not current month
                    // (eg, Mar 31 -> Feb 31 == Feb 28, not Mar 02)
                    ? function(){ return new_date.getUTCMonth() == month; }
                    // If going forward one month, make sure month is as expected
                    // (eg, Jan 31 -> Feb 31 == Feb 28, not Mar 02)
                    : function(){ return new_date.getUTCMonth() != new_month; };
                new_month = month + dir;
                new_date.setUTCMonth(new_month);
                // Dec -> Jan (12) or Jan -> Dec (-1) -- limit expected date to 0-11
                if (new_month < 0 || new_month > 11)
                    new_month = (new_month + 12) % 12;
            } else {
                // For magnitudes >1, move one month at a time...
                for (var i=0; i<mag; i++)
                    // ...which might decrease the day (eg, Jan 31 to Feb 28, etc)...
                    new_date = this.moveMonth(new_date, dir);
                // ...then reset the day, keeping it in the new month
                new_month = new_date.getUTCMonth();
                new_date.setUTCDate(day);
                test = function(){ return new_month != new_date.getUTCMonth(); };
            }
            // Common date-resetting loop -- if date is beyond end of month, make it
            // end of month
            while (test()){
                new_date.setUTCDate(--day);
                new_date.setUTCMonth(new_month);
            }
            return new_date;
        },

        moveYear: function(date, dir){
            return this.moveMonth(date, dir*12);
        },

        dateWithinRange: function(date){
            return date >= this.startDate && date <= this.endDate;
        },

        keydown: function(e){
            if (this.picker.is(':not(:visible)')){
                if (e.keyCode == 27) // allow escape to hide and re-show picker
                    this.show();
                return;
            }
            var dateChanged = false,
                dir, day, month,
                newDate, newViewDate;
            switch(e.keyCode){
                case 27: // escape
                    this.hide();
                    e.preventDefault();
                    break;
                case 37: // left
                case 39: // right
                    if (!this.keyboardNavigation) break;
                    dir = e.keyCode == 37 ? -1 : 1;
                    viewMode = this.viewMode;
                    if (e.ctrlKey) {
                        viewMode += 2;
                    } else if (e.shiftKey) {
                        viewMode += 1;
                    }
                    if (viewMode == 4) {
                        newDate = this.moveYear(this.date, dir);
                        newViewDate = this.moveYear(this.viewDate, dir);
                    } else if (viewMode == 3) {
                        newDate = this.moveMonth(this.date, dir);
                        newViewDate = this.moveMonth(this.viewDate, dir);
                    } else if (viewMode == 2) {
                        newDate = this.moveDate(this.date, dir);
                        newViewDate = this.moveDate(this.viewDate, dir);
                    } else if (viewMode == 1) {
                        newDate = this.moveHour(this.date, dir);
                        newViewDate = this.moveHour(this.viewDate, dir);
                    } else if (viewMode == 0) {
                        newDate = this.moveMinute(this.date, dir);
                        newViewDate = this.moveMinute(this.viewDate, dir);
                    }
                    if (this.dateWithinRange(newDate)){
                        this.date = newDate;
                        this.viewDate = newViewDate;
                        this.setValue();
                        this.update();
                        e.preventDefault();
                        dateChanged = true;
                    }
                    break;
                case 38: // up
                case 40: // down
                    if (!this.keyboardNavigation) break;
                    dir = e.keyCode == 38 ? -1 : 1;
                    viewMode = this.viewMode;
                    if (e.ctrlKey) {
                        viewMode += 2;
                    } else if (e.shiftKey) {
                        viewMode += 1;
                    }
                    if (viewMode == 4) {
                        newDate = this.moveYear(this.date, dir);
                        newViewDate = this.moveYear(this.viewDate, dir);
                    } else if (viewMode == 3) {
                        newDate = this.moveMonth(this.date, dir);
                        newViewDate = this.moveMonth(this.viewDate, dir);
                    } else if (viewMode == 2) {
                        newDate = this.moveDate(this.date, dir * 7);
                        newViewDate = this.moveDate(this.viewDate, dir * 7);
                    } else if (viewMode == 1) {
                        if (this.showMeridian) {
                            newDate = this.moveHour(this.date, dir * 6);
                            newViewDate = this.moveHour(this.viewDate, dir * 6);
                        } else {
                            newDate = this.moveHour(this.date, dir * 4);
                            newViewDate = this.moveHour(this.viewDate, dir * 4);
                        }
                    } else if (viewMode == 0) {
                        newDate = this.moveMinute(this.date, dir * 4);
                        newViewDate = this.moveMinute(this.viewDate, dir * 4);
                    }
                    if (this.dateWithinRange(newDate)){
                        this.date = newDate;
                        this.viewDate = newViewDate;
                        this.setValue();
                        this.update();
                        e.preventDefault();
                        dateChanged = true;
                    }
                    break;
                case 13: // enter
                    if (this.viewMode != 0) {
                        var oldViewMode = this.viewMode;
                        this.showMode(-1);
                        this.fill();
                        if (oldViewMode == this.viewMode && this.autoclose) {
                            this.hide();
                        }
                    } else {
                        this.fill();
                        if (this.autoclose) {
                            this.hide();
                        }
                    }
                    e.preventDefault();
                    break;
                case 9: // tab
                    this.hide();
                    break;
            }
            if (dateChanged){
                this.element.trigger({
                    type: 'changeDate',
                    date: this.date
                });
                var element;
                if (this.isInput) {
                    element = this.element;
                } else if (this.component){
                    element = this.element.find('input');
                }
                if (element) {
                    element.change();
                }
            }
        },

        showMode: function(dir) {
            if (dir) {
                var newViewMode = Math.max(0, Math.min(DPGlobal.modes.length - 1, this.viewMode + dir));
                if (newViewMode >= this.minView && newViewMode <= this.maxView) {
                    this.viewMode = newViewMode;
                }
            }
            /*
             vitalets: fixing bug of very special conditions:
             jquery 1.7.1 + webkit + show inline datetimepicker in bootstrap popover.
             Method show() does not set display css correctly and datetimepicker is not shown.
             Changed to .css('display', 'block') solve the problem.
             See https://github.com/vitalets/x-editable/issues/37

             In jquery 1.7.2+ everything works fine.
             */
            //this.picker.find('>div').hide().filter('.datetimepicker-'+DPGlobal.modes[this.viewMode].clsName).show();
            this.picker.find('>div').hide().filter('.datetimepicker-'+DPGlobal.modes[this.viewMode].clsName).css('display', 'block');
            this.updateNavArrows();
        },

        reset: function(e) {
            this._setDate(null, 'date');
        }
    };

    $.fn.datetimepicker = function ( option ) {
        var args = Array.apply(null, arguments);
        args.shift();
        return this.each(function () {
            var $this = $(this),
                data = $this.data('datetimepicker'),
                options = typeof option == 'object' && option;
            if (!data) {
                $this.data('datetimepicker', (data = new Datetimepicker(this, $.extend({}, $.fn.datetimepicker.defaults,options))));
            }
            if (typeof option == 'string' && typeof data[option] == 'function') {
                data[option].apply(data, args);
            }
        });
    };

    $.fn.datetimepicker.defaults = {
    };
    $.fn.datetimepicker.Constructor = Datetimepicker;
    var dates = $.fn.datetimepicker.dates = {
        en: {
            days: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
            daysShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
            daysMin: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"],
            months: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            monthsShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            meridiem: ["am", "pm"],
            suffix: ["st", "nd", "rd", "th"],
            today: "Today"
        }
    };

    var DPGlobal = {
        modes: [
            {
                clsName: 'minutes',
                navFnc: 'Hours',
                navStep: 1
            },
            {
                clsName: 'hours',
                navFnc: 'Date',
                navStep: 1
            },
            {
                clsName: 'days',
                navFnc: 'Month',
                navStep: 1
            },
            {
                clsName: 'months',
                navFnc: 'FullYear',
                navStep: 1
            },
            {
                clsName: 'years',
                navFnc: 'FullYear',
                navStep: 10
            }],
        isLeapYear: function (year) {
            return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0))
        },
        getDaysInMonth: function (year, month) {
            return [31, (DPGlobal.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month]
        },
        getDefaultFormat: function (type, field) {
            if (type == "standard") {
                if (field == 'input')
                    return 'yyyy-mm-dd hh:ii';
                else
                    return 'yyyy-mm-dd hh:ii:ss';
            } else if (type == "php") {
                if (field == 'input')
                    return 'Y-m-d H:i';
                else
                    return 'Y-m-d H:i:s';
            } else {
                throw new Error("Invalid format type.");
            }
        },
        validParts: function (type) {
            if (type == "standard") {
                return /hh?|HH?|p|P|ii?|ss?|dd?|DD?|mm?|MM?|yy(?:yy)?/g;
            } else if (type == "php") {
                return /[dDjlNwzFmMnStyYaABgGhHis]/g;
            } else {
                throw new Error("Invalid format type.");
            }
        },
        nonpunctuation: /[^ -\/:-@\[-`{-~\t\n\rTZ]+/g,
        parseFormat: function(format, type){
            // IE treats \0 as a string end in inputs (truncating the value),
            // so it's a bad format delimiter, anyway
            var separators = format.replace(this.validParts(type), '\0').split('\0'),
                parts = format.match(this.validParts(type));
            if (!separators || !separators.length || !parts || parts.length == 0){
                throw new Error("Invalid date format.");
            }
            return {separators: separators, parts: parts};
        },
        parseDate: function(date, format, language, type) {
            if (date instanceof Date) {
                var dateUTC = new Date(date.valueOf() - date.getTimezoneOffset() * 60000);
                dateUTC.setMilliseconds(0);
                return dateUTC;
            }
            if (/^\d{4}\-\d{1,2}\-\d{1,2}$/.test(date)) {
                format = this.parseFormat('yyyy-mm-dd', type);
            }
            if (/^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}$/.test(date)) {
                format = this.parseFormat('yyyy-mm-dd hh:ii', type);
            }
            if (/^\d{4}\-\d{1,2}\-\d{1,2}[T ]\d{1,2}\:\d{1,2}\:\d{1,2}[Z]{0,1}$/.test(date)) {
                format = this.parseFormat('yyyy-mm-dd hh:ii:ss', type);
            }
            if (/^[-+]\d+[dmwy]([\s,]+[-+]\d+[dmwy])*$/.test(date)) {
                var part_re = /([-+]\d+)([dmwy])/,
                    parts = date.match(/([-+]\d+)([dmwy])/g),
                    part, dir;
                date = new Date();
                for (var i=0; i<parts.length; i++) {
                    part = part_re.exec(parts[i]);
                    dir = parseInt(part[1]);
                    switch(part[2]){
                        case 'd':
                            date.setUTCDate(date.getUTCDate() + dir);
                            break;
                        case 'm':
                            date = Datetimepicker.prototype.moveMonth.call(Datetimepicker.prototype, date, dir);
                            break;
                        case 'w':
                            date.setUTCDate(date.getUTCDate() + dir * 7);
                            break;
                        case 'y':
                            date = Datetimepicker.prototype.moveYear.call(Datetimepicker.prototype, date, dir);
                            break;
                    }
                }
                return UTCDate(date.getUTCFullYear(), date.getUTCMonth(), date.getUTCDate(), date.getUTCHours(), date.getUTCMinutes(), date.getUTCSeconds(), 0);
            }
            var parts = date && date.match(this.nonpunctuation) || [],
                date = new Date(0, 0, 0, 0, 0, 0, 0),
                parsed = {},
                setters_order = ['hh', 'h', 'ii', 'i', 'ss', 's', 'yyyy', 'yy', 'M', 'MM', 'm', 'mm', 'D', 'DD', 'd', 'dd', 'H', 'HH', 'p', 'P'],
                setters_map = {
                    hh: function(d,v){ return d.setUTCHours(v); },
                    h:  function(d,v){ return d.setUTCHours(v); },
                    HH: function(d,v){ return d.setUTCHours(v==12?0:v); },
                    H:  function(d,v){ return d.setUTCHours(v==12?0:v); },
                    ii: function(d,v){ return d.setUTCMinutes(v); },
                    i:  function(d,v){ return d.setUTCMinutes(v); },
                    ss: function(d,v){ return d.setUTCSeconds(v); },
                    s:  function(d,v){ return d.setUTCSeconds(v); },
                    yyyy: function(d,v){ return d.setUTCFullYear(v); },
                    yy: function(d,v){ return d.setUTCFullYear(2000+v); },
                    m: function(d,v){
                        v -= 1;
                        while (v<0) v += 12;
                        v %= 12;
                        d.setUTCMonth(v);
                        while (d.getUTCMonth() != v)
                            d.setUTCDate(d.getUTCDate()-1);
                        return d;
                    },
                    d: function(d,v){ return d.setUTCDate(v); },
                    p: function(d,v){ return d.setUTCHours(v==1?d.getUTCHours()+12:d.getUTCHours()); }
                },
                val, filtered, part;
            setters_map['M'] = setters_map['MM'] = setters_map['mm'] = setters_map['m'];
            setters_map['dd'] = setters_map['d'];
            setters_map['P'] = setters_map['p'];
            date = UTCDate(date.getFullYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
            if (parts.length == format.parts.length) {
                for (var i=0, cnt = format.parts.length; i < cnt; i++) {
                    val = parseInt(parts[i], 10);
                    part = format.parts[i];
                    if (isNaN(val)) {
                        switch(part) {
                            case 'MM':
                                filtered = $(dates[language].months).filter(function(){
                                    var m = this.slice(0, parts[i].length),
                                        p = parts[i].slice(0, m.length);
                                    return m == p;
                                });
                                val = $.inArray(filtered[0], dates[language].months) + 1;
                                break;
                            case 'M':
                                filtered = $(dates[language].monthsShort).filter(function(){
                                    var m = this.slice(0, parts[i].length),
                                        p = parts[i].slice(0, m.length);
                                    return m == p;
                                });
                                val = $.inArray(filtered[0], dates[language].monthsShort) + 1;
                                break;
                            case 'p':
                            case 'P':
                                val = $.inArray(parts[i].toLowerCase(), dates[language].meridiem);
                                break;
                        }
                    }
                    parsed[part] = val;
                }
                for (var i=0, s; i<setters_order.length; i++){
                    s = setters_order[i];
                    if (s in parsed && !isNaN(parsed[s]))
                        setters_map[s](date, parsed[s])
                }
            }
            return date;
        },
        formatDate: function(date, format, language, type){
            if (date == null) {
                return '';
            }
            var val;
            if (type == 'standard') {
                val = {
                    // year
                    yy: date.getUTCFullYear().toString().substring(2),
                    yyyy: date.getUTCFullYear(),
                    // month
                    m: date.getUTCMonth() + 1,
                    M: dates[language].monthsShort[date.getUTCMonth()],
                    MM: dates[language].months[date.getUTCMonth()],
                    // day
                    d: date.getUTCDate(),
                    D: dates[language].daysShort[date.getUTCDay()],
                    DD: dates[language].days[date.getUTCDay()],
                    p: (dates[language].meridiem.length==2?dates[language].meridiem[date.getUTCHours()<12?0:1]:''),
                    // hour
                    h: date.getUTCHours(),
                    // minute
                    i: date.getUTCMinutes(),
                    // second
                    s: date.getUTCSeconds()
                };
                val.H  = (val.h%12==0? 12 : val.h%12);
                val.HH = (val.H < 10 ? '0' : '') + val.H;
                val.P  = val.p.toUpperCase();
                val.hh = (val.h < 10 ? '0' : '') + val.h;
                val.ii = (val.i < 10 ? '0' : '') + val.i;
                val.ss = (val.s < 10 ? '0' : '') + val.s;
                val.dd = (val.d < 10 ? '0' : '') + val.d;
                val.mm = (val.m < 10 ? '0' : '') + val.m;
            } else if (type == 'php') {
                // php format
                val = {
                    // year
                    y: date.getUTCFullYear().toString().substring(2),
                    Y: date.getUTCFullYear(),
                    // month
                    F: dates[language].months[date.getUTCMonth()],
                    M: dates[language].monthsShort[date.getUTCMonth()],
                    n: date.getUTCMonth() + 1,
                    t: DPGlobal.getDaysInMonth(date.getUTCFullYear(), date.getUTCMonth()),
                    // day
                    j: date.getUTCDate(),
                    l: dates[language].days[date.getUTCDay()],
                    D: dates[language].daysShort[date.getUTCDay()],
                    w: date.getUTCDay(), // 0 -> 6
                    N: (date.getUTCDay()==0?7:date.getUTCDay()),       // 1 -> 7
                    S: (date.getUTCDate()%10<=dates[language].suffix.length?dates[language].suffix[date.getUTCDate()%10-1]:''),
                    // hour
                    a: (dates[language].meridiem.length==2?dates[language].meridiem[date.getUTCHours()<12?0:1]:''),
                    g: (date.getUTCHours()%12==0?12:date.getUTCHours()%12),
                    G: date.getUTCHours(),
                    // minute
                    i: date.getUTCMinutes(),
                    // second
                    s: date.getUTCSeconds()
                };
                val.m = (val.n < 10 ? '0' : '') + val.n;
                val.d = (val.j < 10 ? '0' : '') + val.j;
                val.A = val.a.toString().toUpperCase();
                val.h = (val.g < 10 ? '0' : '') + val.g;
                val.H = (val.G < 10 ? '0' : '') + val.G;
                val.i = (val.i < 10 ? '0' : '') + val.i;
                val.s = (val.s < 10 ? '0' : '') + val.s;
            } else {
                throw new Error("Invalid format type.");
            }
            var date = [],
                seps = $.extend([], format.separators);
            for (var i=0, cnt = format.parts.length; i < cnt; i++) {
                if (seps.length)
                    date.push(seps.shift())
                date.push(val[format.parts[i]]);
            }
            return date.join('');
        },
        convertViewMode: function(viewMode){
            switch (viewMode) {
                case 4:
                case 'decade':
                    viewMode = 4;
                    break;
                case 3:
                case 'year':
                    viewMode = 3;
                    break;
                case 2:
                case 'month':
                    viewMode = 2;
                    break;
                case 1:
                case 'day':
                    viewMode = 1;
                    break;
                case 0:
                case 'hour':
                    viewMode = 0;
                    break;
            }

            return viewMode;
        },
        headTemplate: '<thead>'+
            '<tr>'+
            '<th class="prev"><i class="icon-arrow-left"/></th>'+
            '<th colspan="5" class="switch"></th>'+
            '<th class="next"><i class="icon-arrow-right"/></th>'+
            '</tr>'+
            '</thead>',
        contTemplate: '<tbody><tr><td colspan="7"></td></tr></tbody>',
        footTemplate: '<tfoot><tr><th colspan="7" class="today"></th></tr></tfoot>'
    };
    DPGlobal.template = '<div class="datetimepicker">'+
        '<div class="datetimepicker-minutes">'+
        '<table class=" table-condensed">'+
        DPGlobal.headTemplate+
        DPGlobal.contTemplate+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '<div class="datetimepicker-hours">'+
        '<table class=" table-condensed">'+
        DPGlobal.headTemplate+
        DPGlobal.contTemplate+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '<div class="datetimepicker-days">'+
        '<table class=" table-condensed">'+
        DPGlobal.headTemplate+
        '<tbody></tbody>'+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '<div class="datetimepicker-months">'+
        '<table class="table-condensed">'+
        DPGlobal.headTemplate+
        DPGlobal.contTemplate+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '<div class="datetimepicker-years">'+
        '<table class="table-condensed">'+
        DPGlobal.headTemplate+
        DPGlobal.contTemplate+
        DPGlobal.footTemplate+
        '</table>'+
        '</div>'+
        '</div>';

    $.fn.datetimepicker.DPGlobal = DPGlobal;

}( window.jQuery );
/**
 * Simplified Chinese translation for bootstrap-datetimepicker
 */
;(function($){
    $.fn.datetimepicker.dates['zh-CN'] = {
        days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
        months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        today: "今日",
        suffix: [],
        meridiem: []
    };
}(jQuery));


/**
 *bootstrap select
 */
!function($) {
    var Selectpicker = function(element, options, e) {
        if (e ) {
            e.stopPropagation();
            e.preventDefault();
        }
        this.$element = $(element);
        this.$newElement = null;
        this.button = null;

        //Merge defaults, options and data-attributes to make our options
        this.options = $.extend({}, $.fn.selectpicker.defaults, this.$element.data(), typeof options == 'object' && options);

        //If we have no title yet, check the attribute 'title' (this is missed by jq as its not a data-attribute
        if(this.options.title==null)
            this.options.title = this.$element.attr('title');

        //Expose public methods
        this.val = Selectpicker.prototype.val;
        this.render = Selectpicker.prototype.render;
        this.init();
    };

    Selectpicker.prototype = {

        constructor: Selectpicker,

        init: function (e) {
            var _this = this;
            this.$element.hide();
            this.multiple = this.$element.prop('multiple');


            var classList = this.$element.attr('class') !== undefined ? this.$element.attr('class').split(/\s+/) : '';
            var id = this.$element.attr('id');
            this.$element.after( this.createView() );
            this.$newElement = this.$element.next('.select');
            var select = this.$newElement;
            var menu = this.$newElement.find('.dropdown-menu');
            var menuArrow = this.$newElement.find('.dropdown-arrow');
            var menuA = menu.find('li > a');
            var liHeight = select.addClass('open').find('.dropdown-menu li > a').outerHeight();
            select.removeClass('open');
            var divHeight = menu.find('li .divider').outerHeight(true);
            var selectOffset_top = this.$newElement.offset().top;
            var size = 0;
            var menuHeight = 0;
            var selectHeight = this.$newElement.outerHeight();
            this.button = this.$newElement.find('> button');
            if (id !== undefined) {
                this.button.attr('id', id);
                $('label[for="' + id + '"]').click(function(){ select.find('button#'+id).focus(); })
            }
            for (var i = 0; i < classList.length; i++) {
                if(classList[i] != 'selectpicker') {
                    this.$newElement.addClass(classList[i]);
                }
            }
            //If we are multiple, then add the show-tick class by default
            if(this.multiple) {
                this.$newElement.addClass('select-multiple');
            }
            this.button.addClass(this.options.style);
            menu.addClass(this.options.menuStyle);
            menuArrow.addClass(function() {
                if (_this.options.menuStyle) {
                    return _this.options.menuStyle.replace('dropdown-', 'dropdown-arrow-');
                }
            });
            this.checkDisabled();
            this.checkTabIndex();
            this.clickListener();
            var menuPadding = parseInt(menu.css('padding-top')) + parseInt(menu.css('padding-bottom')) + parseInt(menu.css('border-top-width')) + parseInt(menu.css('border-bottom-width'));
            if (this.options.size == 'auto') {
                function getSize() {
                    var selectOffset_top_scroll = selectOffset_top - $(window).scrollTop();
                    var windowHeight = window.innerHeight;
                    var menuExtras = menuPadding + parseInt(menu.css('margin-top')) + parseInt(menu.css('margin-bottom')) + 2;
                    var selectOffset_bot = windowHeight - selectOffset_top_scroll - selectHeight - menuExtras;
                    menuHeight = selectOffset_bot;
                    if (select.hasClass('dropup')) {
                        menuHeight = selectOffset_top_scroll - menuExtras;
                    }
                    menu.css({'max-height' : menuHeight + 'px', 'overflow-y' : 'auto', 'min-height' : liHeight*3 + 'px'});
                }
                getSize();
                $(window).resize(getSize);
                $(window).scroll(getSize);
                this.$element.bind('DOMNodeInserted', getSize);
            } else if (this.options.size && this.options.size != 'auto' && menu.find('li').length > this.options.size) {
                var optIndex = menu.find("li > *").filter(':not(.divider)').slice(0,this.options.size).last().parent().index();
                var divLength = menu.find("li").slice(0,optIndex + 1).find('.divider').length;
                menuHeight = liHeight*this.options.size + divLength*divHeight + menuPadding;
                menu.css({'max-height' : menuHeight + 'px', 'overflow-y' : 'scroll'});
            }

            //Listen for updates to the DOM and re render...
            this.$element.bind('DOMNodeInserted', $.proxy(this.reloadLi, this));

            this.render();
        },

        createDropdown: function() {
            var drop =
                "<div class='btn-group select'>" +
                    "<i class='dropdown-arrow'></i>" +
                    "<button class='btn dropdown-toggle clearfix' data-toggle='dropdown'>" +
                    "<span class='filter-option pull-left'></span>&nbsp;" +
                    "<span class='caret'></span>" +
                    "</button>" +
                    "<ul class='dropdown-menu' role='menu'>" +
                    "</ul>" +
                    "</div>";

            return $(drop);
        },


        createView: function() {
            var $drop = this.createDropdown();
            var $li = this.createLi();
            $drop.find('ul').append($li);
            return $drop;
        },

        reloadLi: function() {
            //Remove all children.
            this.destroyLi();
            //Re build
            $li = this.createLi();
            this.$newElement.find('ul').append( $li );
            //render view
            this.render();
        },

        destroyLi:function() {
            this.$newElement.find('li').remove();
        },

        createLi: function() {

            var _this = this;
            var _li = [];
            var _liA = [];
            var _liHtml = '';

            this.$element.find('option').each(function(){
                _li.push($(this).text());
            });

            this.$element.find('option').each(function(index) {
                //Get the class and text for the option
                var optionClass = $(this).attr("class") !== undefined ? $(this).attr("class") : '';
                var text =  $(this).text();
                var subtext = $(this).data('subtext') !== undefined ? '<small class="muted">'+$(this).data('subtext')+'</small>' : '';

                //Append any subtext to the main text.
                text+=subtext;

                if ($(this).parent().is('optgroup') && $(this).data('divider') != true) {
                    if ($(this).index() == 0) {
                        //Get the opt group label
                        var label = $(this).parent().attr('label');
                        var labelSubtext = $(this).parent().data('subtext') !== undefined ? '<small class="muted">'+$(this).parent().data('subtext')+'</small>' : '';
                        label += labelSubtext;

                        if ($(this)[0].index != 0) {
                            _liA.push(
                                '<div class="divider"></div>'+
                                    '<dt>'+label+'</dt>'+
                                    _this.createA(text, "opt " + optionClass )
                            );
                        } else {
                            _liA.push(
                                '<dt>'+label+'</dt>'+
                                    _this.createA(text, "opt " + optionClass ));
                        }
                    } else {
                        _liA.push( _this.createA(text, "opt " + optionClass )  );
                    }
                } else if ($(this).data('divider') == true) {
                    _liA.push('<div class="divider"></div>');
                } else {
                    _liA.push( _this.createA(text, optionClass ) );
                }
            });

            if (_li.length > 0) {
                for (var i = 0; i < _li.length; i++) {
                    var $option = this.$element.find('option').eq(i);
                    _liHtml += "<li rel=" + i + ">" + _liA[i] + "</li>";
                }
            }

            //If we dont have a selected item, and we dont have a title, select the first element so something is set in the button
            if(this.$element.find('option:selected').length==0 && !_this.options.title) {
                this.$element.find('option').eq(0).prop('selected', true).attr('selected', 'selected');
            }

            return $(_liHtml);
        },

        createA:function(test, classes) {
            return '<a tabindex="-1" href="#" class="'+classes+'">' +
                '<span class="pull-left">' + test + '</span>' +
                '</a>';

        },

        render:function() {
            var _this = this;

            //Set width of select
            if (this.options.width == 'auto') {
                var ulWidth = this.$newElement.find('.dropdown-menu').css('width');
                this.$newElement.css('width',ulWidth);
            } else if (this.options.width && this.options.width != 'auto') {
                this.$newElement.css('width',this.options.width);
            }

            //Update the LI to match the SELECT
            this.$element.find('option').each(function(index) {
                _this.setDisabled(index, $(this).is(':disabled') || $(this).parent().is(':disabled') );
                _this.setSelected(index, $(this).is(':selected') );
            });



            var selectedItems = this.$element.find('option:selected').map(function(index,value) {
                if($(this).attr('title')!=undefined) {
                    return $(this).attr('title');
                } else {
                    return $(this).text();
                }
            }).toArray();

            //Convert all the values into a comma delimited string
            var title = selectedItems.join(", ");

            //If this is multi select, and the selectText type is count, the show 1 of 2 selected etc..
            if(_this.multiple && _this.options.selectedTextFormat.indexOf('count') > -1) {
                var max = _this.options.selectedTextFormat.split(">");
                if( (max.length>1 && selectedItems.length > max[1]) || (max.length==1 && selectedItems.length>=2)) {
                    title = selectedItems.length +' of ' + this.$element.find('option').length + ' selected';
                }
            }

            //If we dont have a title, then use the default, or if nothing is set at all, use the not selected text
            if(!title) {
                title = _this.options.title != undefined ? _this.options.title : _this.options.noneSelectedText;
            }

            this.$element.next('.select').find('.filter-option').html( title );
        },



        setSelected:function(index, selected) {
            if(selected) {
                this.$newElement.find('li').eq(index).addClass('selected');
            } else {
                this.$newElement.find('li').eq(index).removeClass('selected');
            }
        },

        setDisabled:function(index, disabled) {
            if(disabled) {
                this.$newElement.find('li').eq(index).addClass('disabled');
            } else {
                this.$newElement.find('li').eq(index).removeClass('disabled');
            }
        },

        checkDisabled: function() {
            if (this.$element.is(':disabled')) {
                this.button.addClass('disabled');
                this.button.click(function(e) {
                    e.preventDefault();
                });
            }
        },

        checkTabIndex: function() {
            if (this.$element.is('[tabindex]')) {
                var tabindex = this.$element.attr("tabindex");
                this.button.attr('tabindex', tabindex);
            }
        },

        clickListener: function() {
            var _this = this;

            $('body').on('touchstart.dropdown', '.dropdown-menu', function (e) { e.stopPropagation(); });



            this.$newElement.on('click', 'li a', function(e){
                var clickedIndex = $(this).parent().index(),
                    $this = $(this).parent(),
                    $select = $this.parents('.select');


                //Dont close on multi choice menu
                if(_this.multiple) {
                    e.stopPropagation();
                }

                e.preventDefault();

                //Dont run if we have been disabled
                if ($select.prev('select').not(':disabled') && !$(this).parent().hasClass('disabled')){
                    //Deselect all others if not multi select box
                    if (!_this.multiple) {
                        $select.prev('select').find('option').removeAttr('selected');
                        $select.prev('select').find('option').eq(clickedIndex).prop('selected', true).attr('selected', 'selected');
                    }
                    //Else toggle the one we have chosen if we are multi selet.
                    else {
                        var selected = $select.prev('select').find('option').eq(clickedIndex).prop('selected');

                        if(selected) {
                            $select.prev('select').find('option').eq(clickedIndex).removeAttr('selected');
                        } else {
                            $select.prev('select').find('option').eq(clickedIndex).prop('selected', true).attr('selected', 'selected');
                        }
                    }


                    $select.find('.filter-option').html($this.text());
                    $select.find('button').focus();

                    // Trigger select 'change'
                    $select.prev('select').trigger('change');
                }

            });

            this.$newElement.on('click', 'li.disabled a, li dt, li .divider', function(e) {
                e.preventDefault();
                e.stopPropagation();
                $select = $(this).parent().parents('.select');
                $select.find('button').focus();
            });

            this.$element.on('change', function(e) {
                _this.render();
            });
        },

        val:function(value) {

            if(value!=undefined) {
                this.$element.val( value );

                this.$element.trigger('change');
                return this.$element;
            } else {
                return this.$element.val();
            }
        }

    };

    $.fn.selectpicker = function(option, event) {
        //get the args of the outer function..
        var args = arguments;
        var value;
        var chain = this.each(function () {
            var $this = $(this),
                data = $this.data('selectpicker'),
                options = typeof option == 'object' && option;

            if (!data) {
                $this.data('selectpicker', (data = new Selectpicker(this, options, event)));
            } else {
                for(var i in option) {
                    data[i]=option[i];
                }
            }

            if (typeof option == 'string') {
                //Copy the value of option, as once we shift the arguments
                //it also shifts the value of option.
                property = option;
                if(data[property] instanceof Function) {
                    [].shift.apply(args);
                    value = data[property].apply(data, args);
                } else {
                    value = data[property];
                }
            }
        });

        if(value!=undefined) {
            return value;
        } else {
            return chain;
        }
    };

    $.fn.selectpicker.defaults = {
        style: null,
        size: 'auto',
        title: null,
        selectedTextFormat : 'values',
        noneSelectedText : 'Nothing selected',
        width: null,
        menuStyle: null,
        toggleSize: null
    }

}(window.jQuery);

//bootstrap switch  v1.3 http://www.larentis.eu/switch/

!function ($) {
    "use strict";

    $.fn['bootstrapSwitch'] = function (method) {
        var methods = {
            init: function () {
                return this.each(function () {
                        var $element = $(this)
                            , $div
                            , $switchLeft
                            , $switchRight
                            , $label
                            , myClasses = ""
                            , classes = $element.attr('class')
                            , color
                            , moving
                            , onLabel = "ON"
                            , offLabel = "OFF"
                            , icon = false;

                        $.each(['switch-mini', 'switch-small', 'switch-large'], function (i, el) {
                            if (classes.indexOf(el) >= 0)
                                myClasses = el;
                        });

                        $element.addClass('has-switch');

                        if ($element.data('on') !== undefined)
                            color = "switch-" + $element.data('on');

                        if ($element.data('on-label') !== undefined)
                            onLabel = $element.data('on-label');

                        if ($element.data('off-label') !== undefined)
                            offLabel = $element.data('off-label');

                        if ($element.data('icon') !== undefined)
                            icon = $element.data('icon');

                        $switchLeft = $('<span>')
                            .addClass("switch-left")
                            .addClass(myClasses)
                            .addClass(color)
                            .html(onLabel);

                        color = '';
                        if ($element.data('off') !== undefined)
                            color = "switch-" + $element.data('off');

                        $switchRight = $('<span>')
                            .addClass("switch-right")
                            .addClass(myClasses)
                            .addClass(color)
                            .html(offLabel);

                        $label = $('<label>')
                            .html("&nbsp;")
                            .addClass(myClasses)
                            .attr('for', $element.find('input').attr('id'));

                        if (icon) {
                            $label.html('<i class="' + icon + '"></i>');
                        }

                        $div = $element.find(':checkbox').wrap($('<div>')).parent().data('animated', false);

                        if ($element.data('animated') !== false)
                            $div.addClass('switch-animate').data('animated', true);

                        $div
                            .append($switchLeft)
                            .append($label)
                            .append($switchRight);

                        $element.find('>div').addClass(
                            $element.find('input').is(':checked') ? 'switch-on' : 'switch-off'
                        );

                        if ($element.find('input').is(':disabled'))
                            $(this).addClass('deactivate');

                        var changeStatus = function ($this) {
                            $this.siblings('label').trigger('mousedown').trigger('mouseup').trigger('click');
                        };

                        $element.on('keydown', function (e) {
                            if (e.keyCode === 32) {
                                e.stopImmediatePropagation();
                                e.preventDefault();
                                changeStatus($(e.target).find('span:first'));
                            }
                        });

                        $switchLeft.on('click', function (e) {
                            changeStatus($(this));
                        });

                        $switchRight.on('click', function (e) {
                            changeStatus($(this));
                        });

                        $element.find('input').on('change', function (e) {
                            var $this = $(this)
                                , $element = $this.parent()
                                , thisState = $this.is(':checked')
                                , state = $element.is('.switch-off');

                            e.preventDefault();

                            $element.css('left', '');

                            if (state === thisState) {

                                if (thisState)
                                    $element.removeClass('switch-off').addClass('switch-on');
                                else $element.removeClass('switch-on').addClass('switch-off');

                                if ($element.data('animated') !== false)
                                    $element.addClass("switch-animate");

                                $element.parent().trigger('switch-change', {'el': $this, 'value': thisState})
                            }
                        });

                        $element.find('label').on('mousedown touchstart', function (e) {
                            var $this = $(this);
                            moving = false;

                            e.preventDefault();
                            e.stopImmediatePropagation();

                            $this.closest('div').removeClass('switch-animate');

                            if ($this.closest('.has-switch').is('.deactivate'))
                                $this.unbind('click');
                            else {
                                $this.on('mousemove touchmove', function (e) {
                                    var $element = $(this).closest('.switch')
                                        , relativeX = (e.pageX || e.originalEvent.targetTouches[0].pageX) - $element.offset().left
                                        , percent = (relativeX / $element.width()) * 100
                                        , left = 25
                                        , right = 75;

                                    moving = true;

                                    if (percent < left)
                                        percent = left;
                                    else if (percent > right)
                                        percent = right;

                                    $element.find('>div').css('left', (percent - right) + "%")
                                });

                                $this.on('click touchend', function (e) {
                                    var $this = $(this)
                                        , $target = $(e.target)
                                        , $myCheckBox = $target.siblings('input');

                                    e.stopImmediatePropagation();
                                    e.preventDefault();

                                    $this.unbind('mouseleave');

                                    if (moving)
                                        $myCheckBox.prop('checked', !(parseInt($this.parent().css('left')) < -25));
                                    else $myCheckBox.prop("checked", !$myCheckBox.is(":checked"));

                                    moving = false;
                                    $myCheckBox.trigger('change');
                                });

                                $this.on('mouseleave', function (e) {
                                    var $this = $(this)
                                        , $myCheckBox = $this.siblings('input');

                                    e.preventDefault();
                                    e.stopImmediatePropagation();

                                    $this.unbind('mouseleave');
                                    $this.trigger('mouseup');

                                    $myCheckBox.prop('checked', !(parseInt($this.parent().css('left')) < -25)).trigger('change');
                                });

                                $this.on('mouseup', function (e) {
                                    e.stopImmediatePropagation();
                                    e.preventDefault();

                                    $(this).unbind('mousemove');
                                });
                            }
                        });
                    }
                );
            },
            toggleActivation: function () {
                $(this).toggleClass('deactivate');
            },
            isActive: function () {
                return !$(this).hasClass('deactivate');
            },
            setActive: function (active) {
                if (active)
                    $(this).removeClass('deactivate');
                else $(this).addClass('deactivate');
            },
            toggleState: function (skipOnChange) {
                var $input = $(this).find('input:checkbox');
                $input.prop('checked', !$input.is(':checked')).trigger('change', skipOnChange);
            },
            setState: function (value, skipOnChange) {
                $(this).find('input:checkbox').prop('checked', value).trigger('change', skipOnChange);
            },
            status: function () {
                return $(this).find('input:checkbox').is(':checked');
            },
            destroy: function () {
                var $div = $(this).find('div')
                    , $checkbox;

                $div.find(':not(input:checkbox)').remove();

                $checkbox = $div.children();
                $checkbox.unwrap().unwrap();

                $checkbox.unbind('change');

                return $checkbox;
            }
        };

        if (methods[method])
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        else if (typeof method === 'object' || !method)
            return methods.init.apply(this, arguments);
        else
            $.error('Method ' + method + ' does not exist!');
    };
}(jQuery);

$(function () {
    $('.switch')['bootstrapSwitch']();
});
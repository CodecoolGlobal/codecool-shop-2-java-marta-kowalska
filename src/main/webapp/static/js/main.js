var userLoggedIn = true;

jQuery(document).ready(function() {
    listenCheckboxStates();
    listenMinicartIframeSizeChanges();
});


/////////////////////// DOM ///////////////////////
/**
 * @summary Adds a 'checked' class to the checkbox's parent element if the checkbox is checked.
 */
function listenCheckboxStates() {

    let checkboxStateInputs = jQuery('.checkbox-state > input[type="checkbox"]');

    checkboxStateInputs.each(function() {
        let checkbox = jQuery(this);
        checkbox.attr('checked', false);
    });

    checkboxStateInputs.change(function() {
        let checkbox = jQuery(this);
        let container = checkbox.parent();
        let isChecked = checkbox.is(':checked');

        checkbox.attr('checked', !isChecked ? false : 'checked');
        container.setDomClass("checked", isChecked);
    });

}

/**
 * @summary Resizes an iframe automatically
 */
jQuery.prototype.resizeIframe = function() {

    let topOffset = this.offset().top;
    let windowHeight = jQuery(window).height() - 10;

    let targetHeight = Math.floor(windowHeight - topOffset);

    this[0].style.height = targetHeight + 'px';
}

/**
 * @summary Updates the Mini cart iframe's height automatically
 */
function listenMinicartIframeSizeChanges() {
    let iframes = jQuery('#mini-cart iframe');

    iframes.on("load",function() {
        let iframe = jQuery(this);
        iframe.resizeIframe();
        iframe.setDomClass('loaded', true);
    });

    jQuery(window).resize(function() {
        iframes.each(function() {
            jQuery(this).resizeIframe();
        });
    });
}

/////////////////////// MISC ///////////////////////
/**
 * @summary Gets a cookies by its name
 * @param name The name of the cookie
 * @returns {String|Boolean} The value of the cookie, or False if its not there.
 */
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return false;
}

/**
 * @summary Sets/Creates a cookie
 * @param {String} name The name of the cookie
 * @param {String} value The value of the cookie
 * @param {Number} hours How many hours we want to store it? (Leave empty if forever)
 */
function setCookie(name, value, hours = undefined) {
    let expires = "";

    if (hours) {
        let date = new Date();
        date.setTime(date.getTime() + (hours*60*60*1000));

        expires = "; expires=" + date.toUTCString();
    }

    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

/**
 * @summary Sets a given class for an element by a Boolean
 * @param {String} className The selected class as String
 * @param {Boolean} has We want to use this class?
 * @returns {jQuery} The selected object as jQuery
 */
jQuery.prototype.setDomClass = function(className, has = true) {
    let obj = jQuery(this);

    try {
        if (obj !== undefined) {
            if (obj.length === 1) {
                if (obj.hasClass(className) && !has)
                    obj.removeClass(className);

                if (!obj.hasClass(className) && has)
                    obj.addClass(className);
            }
            else {
                obj.each(function () {
                    let _obj = jQuery(this);

                    if (_obj.hasClass(className) && !has)
                        _obj.removeClass(className);

                    if (!_obj.hasClass(className) && has)
                        _obj.addClass(className);
                });
            }
        }
    } catch(e) {
        // its okay
    }

    return obj;
}

/**
 * @summary Sets a class if a boolean is true, and one if its false.
 * @param {String} classTrue The class we want to use if the Boolean is TRUE
 * @param {String} classFalse The class we want to use if the Boolean is FALSE
 * @param {Boolean} has The boolean itself
 * @returns {jQuery} The selected object as jQuery
 */
jQuery.prototype.flipDomClass = function(classTrue, classFalse, has = true) {
    return this.setDomClass(classTrue, has).setDomClass(classFalse, !has);
}
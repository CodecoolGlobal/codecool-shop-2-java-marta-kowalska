var localCart = null;
var userLoggedIn = true;
const cartCookieKey = 'USER_CART';

/////////////////////// INIT ///////////////////////
jQuery(document).ready(function () {
    getCart();

    console.log(localCart);
});

/////////////////////// MISC ///////////////////////
/**
 * @summary Gets a cookies by its name
 * @param name The name of the cookie
 * @returns {String} The value of the cookie, or False if its not there.
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
function setCookie(name, value, hours) {
    let expires = "";

    if (hours) {
        let date = new Date();
        date.setTime(date.getTime() + (hours*60*60*1000));

        expires = "; expires=" + date.toUTCString();
    }

    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

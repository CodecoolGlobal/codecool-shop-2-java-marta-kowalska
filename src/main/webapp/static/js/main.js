var localCart = null;
var userLoggedIn = false;
const cartCookieKey = 'USER_CART';

/////////////////////// INIT ///////////////////////
jQuery(document).ready(function () {
    getCart();
    
    console.log(localCart);
});


/////////////////////// CART ///////////////////////
function getCart() {
    console.log(localCart);

    if (localCart !== null)
        return;

    localCart = {};

    if (userLoggedIn)
    {
        // todo: fetch
    }
    else
    {
        let cartCookieString = getCookie(cartCookieKey);
        if (cartCookieString === false)
            return;

        localCart = JSON.parse(decodeURIComponent(cartCookieString));
    }
}

function setCart() {
    if (localCart === null)
        return;

    if (userLoggedIn)
    {
        // todo: fetch
    }
    else
    {
        let cartJSONString = JSON.stringify(localCart);
        setCookie(cartCookieKey, encodeURIComponent(cartJSONString));
    }
}

function addToCart(productID, productName) {

    getCart();

    if (localCart[productID] === undefined)
    {
        localCart[productID] =  {
                                    productID: productID,
                                    productName: productName,
                                    quantity: 1
                                };
    }
    else
        localCart[productID].quantity ++;

    setCart();
}

function removeFromCart(productID) {

    getCart();

    if (localCart[productID] === undefined)
        return;

    if (localCart[productID].quantity <= 1)
        delete localCart[productID];

    else
        localCart[productID].quantity --;

    setCart();
}

/////////////////////// MISC ///////////////////////
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return false;
}

function setCookie(name, value, hours) {
    let expires = "";

    if (hours) {
        let date = new Date();
        date.setTime(date.getTime() + (hours*60*60*1000));

        expires = "; expires=" + date.toUTCString();
    }

    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

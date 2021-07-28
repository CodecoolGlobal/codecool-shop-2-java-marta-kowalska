var localCart = null;
var userLoggedIn = true;
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

    if (userLoggedIn)
        return;

    localCart = {};

    let cartCookieString = getCookie(cartCookieKey);
    if (cartCookieString === false)
        return;

    localCart = JSON.parse(decodeURIComponent(cartCookieString));
}

function setCart() {
    if (localCart === null)
        return;

    if (userLoggedIn)
        return;

    let cartJSONString = JSON.stringify(localCart);
    setCookie(cartCookieKey, encodeURIComponent(cartJSONString));
}

function addToCart(productID, productName) {

    getCart();

    if (userLoggedIn) {

        let result = manageRemoteCartItem(productID, 'add');

    }
    else {
        if (localCart[productID] === undefined) {
            localCart[productID] = {
                productID: productID,
                productName: productName,
                quantity: 1
            };
        } else
            localCart[productID].quantity++;
    }

    setCart();
}

function removeFromCart(productID) {

    getCart();

    if (userLoggedIn) {

        let result = manageRemoteCartItem(productID, 'remove');

    }
    else {
        if (localCart[productID] === undefined)
            return;

        if (localCart[productID].quantity <= 1)
            delete localCart[productID];

        else
            localCart[productID].quantity--;
    }

    setCart();
}

function deleteFromCart(productID) {
    getCart()

    if (userLoggedIn) {

        let result = manageRemoteCartItem(productID, 'delete');

    }
    else {
        if (localCart[productID] === undefined)
            return;

        delete localCart[productID];
    }

    setCart();
}

async function manageRemoteCartItem(productID, action) {

    let response = await fetch('/post', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: {
            productId: productID,
            action: action
        }
    });

    return response.json();
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

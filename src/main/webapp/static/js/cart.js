let localCart = null;
const cartCookieKey = 'USER_CART';

/**
 * @summary Adds a new item to the cart if its not there, otherwise adds one more to it.
 * @param {Number} productID The ID of the product
 * @param {String} productName The name of the product
 */
function addToCart(productID, productName) {

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'add').then(function (result) {
            console.log(result);
        });

    }
    else {

        getCart();

        if (localCart[productID] === undefined) {
            localCart[productID] = {
                productID: productID,
                productName: productName,
                quantity: 1
            };
        } else
            localCart[productID].quantity++;

        setCart();

    }
}

/**
 * @summary Removes an item from the cart if its there, if it has only 1, deletes it completely
 * @param productID The ID of the product
 */
function removeFromCart(productID) {

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'remove').then(function (result) {
            console.log(result);
        });

    }
    else {

        getCart();

        if (localCart[productID] === undefined)
            return;

        if (localCart[productID].quantity <= 1)
            delete localCart[productID];

        else
            localCart[productID].quantity--;

        setCart();

    }
}


/**
 * @summary Deletes an item from the cart completely
 * @param productID The ID of the product
 */
function deleteFromCart(productID) {

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'delete').then(function (result) {
            console.log(result);
        });

    }
    else {

        getCart()

        if (localCart[productID] === undefined)
            return;

        delete localCart[productID];

        setCart();

    }
}

/**
 * @summary Gets the local cart from the cookies if no user logged in.
 */
function getCart() {

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

/**
 * @summary Writes the local cart to the cookies if no user logged in.
 */
function setCart() {
    if (localCart === null)
        return;

    if (userLoggedIn)
        return;

    let cartJSONString = JSON.stringify(localCart);
    setCookie(cartCookieKey, encodeURIComponent(cartJSONString));
}

/**
 * @summary Creates a fetch to the servlet to do things with the cart items.
 * @param {Number} productID The ID of the product
 * @param {String} action The action (add/remove/delete)
 */
async function manageRemoteCartItem(productID, action) {

    let body = {
        productId: productID,
        action: action
    };

    console.log(body);

    return jQuery.ajax({
        method: "POST",
        url: "/cart",
        data: body
    });
}
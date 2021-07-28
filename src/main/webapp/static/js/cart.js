let localCart = null;
const cartCookieKey = 'USER_CART';

// INIT
jQuery(document).ready(function () {

    if (userLoggedIn)
        getLocalCart();

});

/**
 * @summary Adds a new item to the cart if its not there, otherwise adds one more to it.
 * @param {Number} productID The ID of the product
 * @param {String} productName The name of the product
 */
function addToCart(productID, productName) {

    function incrementCartSum(productID) {
        let itemContainer = document.querySelector(`.cartItem[data-product_id="${productID}"]`);
        if (itemContainer===null){
            return;
        }
        let qty = itemContainer.querySelector(".qty");
        qty.value++;
        let subtotal = itemContainer.querySelector(".productsTotal span");
        let price = parseFloat(subtotal.dataset.product_price) + parseFloat(subtotal.innerText)
        subtotal.innerHTML = (Math.round(price * 100)/100).toString();

    }

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'add').then(function (result) {
            incrementCartSum(productID);
            console.log(result);
        });

    }
    else {

        getLocalCart();

        if (localCart[productID] === undefined) {
            localCart[productID] = {
                productID: productID,
                productName: productName,
                quantity: 1
            };
        } else
            localCart[productID].quantity++;

        setLocalCart();

    }
}

/**
 * @summary Removes an item from the cart if its there, if it has only 1, deletes it completely
 * @param productID The ID of the product
 */
function removeFromCart(productID) {

    function decrementItemInCart() {
        let itemContainer = document.querySelector(`.cartItem[data-product_id="${productID}"]`);
        if (itemContainer===null){
            return;
        }
        let qty = itemContainer.querySelector(".qty");
        qty.value--;
        if (qty.value==0){
            itemContainer.remove();
        }
        let subtotal = itemContainer.querySelector(".productsTotal span");
        let price = parseFloat(subtotal.innerText) - parseFloat(subtotal.dataset.product_price)

        console.log(price)
        subtotal.innerHTML = (Math.round(price * 100)/100).toString();
    }

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'remove').then(function (result) {
            decrementItemInCart()
            console.log(result);
        });

    }
    else {

        getLocalCart();

        if (localCart[productID] === undefined)
            return;

        if (localCart[productID].quantity <= 1)
            delete localCart[productID];

        else
            localCart[productID].quantity--;

        setLocalCart();

    }
}


/**
 * @summary Deletes an item from the cart completely
 * @param productID The ID of the product
 */
function deleteFromCart(productID) {

    function deleteItemInCart() {
        let itemContainer = document.querySelector(`.cartItem[data-product_id="${productID}"]`);
        let subtotal = document.querySelector(".subtotal")
        let total = document.querySelector(".total")
        if (itemContainer===null){
            return;
        }

        itemContainer.remove();

    }

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'delete').then(function (result) {
            deleteItemInCart();
            console.log(result);
        });

    }
    else {

        getLocalCart()

        if (localCart[productID] === undefined)
            return;

        delete localCart[productID];

        setLocalCart();

    }
}

/**
 * @summary Gets the local cart from the cookies if no user logged in.
 */
function getLocalCart() {

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
function setLocalCart() {
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
 * @return {Promise} The ajax promise
 */
async function manageRemoteCartItem(productID, action) {

    return jQuery.ajax({
        method: "POST",
        url: "/cart",
        data: {
            productId: productID,
            action: action
        }
    });
}
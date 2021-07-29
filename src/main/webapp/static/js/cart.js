let localCart = null;
const cartCookieKey = 'USER_CART';

// INIT
jQuery(document).ready(function () {
    $("[type='number']").keypress(function (evt) {
        evt.preventDefault();
    });

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
        let itemPrice = itemContainer.querySelector(".productsTotal span");
        let price = parseFloat(itemPrice.innerText) + parseFloat(itemPrice.dataset.product_price)
        changeSums(itemPrice.dataset.product_price, "increment");
        itemPrice.innerHTML = (Math.round(price * 100)/100).toString();

    }

    cartCounterIconChange();

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'add').then(function (result) {
            incrementCartSum(productID);
            // console.log(result);
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
            checkIfCartIsEmpty()
        }
        let itemPrice = itemContainer.querySelector(".productsTotal span");
        let price = parseFloat(itemPrice.innerText) - parseFloat(itemPrice.dataset.product_price)
        changeSums(itemPrice.dataset.product_price, "decrement");
        itemPrice.innerHTML = (Math.round(price * 100)/100).toString();
    }

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'remove').then(function (result) {
            decrementItemInCart()
            // console.log(result);
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
        if (itemContainer===null){
            return;
        }
        let itemPrice = itemContainer.querySelector(".productsTotal span");
        changeSums(itemPrice.innerText, "decrement");
        itemContainer.remove();
        checkIfCartIsEmpty()
    }

    if (userLoggedIn) {

        manageRemoteCartItem(productID, 'delete').then(function (result) {
            deleteItemInCart();
            // console.log(result);
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
 * @summary Changes the Cart icon's number label when a new object added to/removed from the cart
 * @param {Number} direction The number that we add to the cart items count
 */
function cartCounterIconChange(direction = 1){
    let cartCounter = document.querySelector("#cart-counter");
    cartCounter.innerText = parseInt(cartCounter.innerText) + direction;
    cartCounter.classList.add("animation");
    setTimeout(() =>{
        cartCounter.classList.remove("animation");
    }, 800);
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

function changeSums(price, change) {
    let subtotal = document.querySelector(".subtotal")
    let total = document.querySelector(".total")
    let newSubPrice=0;
    let newTotalPrice =0;
    if(change==="decrement") {
        newSubPrice = parseFloat(subtotal.innerText) - parseFloat(price)
        newTotalPrice = parseFloat(total.innerText) - parseFloat(price)
    } else {
        newSubPrice = parseFloat(subtotal.innerText) + parseFloat(price)
        newTotalPrice = parseFloat(total.innerText) + parseFloat(price)
    }
    subtotal.innerHTML = (Math.round(newSubPrice * 100) / 100).toString();
    total.innerHTML = (Math.round(newTotalPrice * 100) / 100).toString();
}


function checkIfCartIsEmpty() {
    let cartContainer = document.querySelector(`.cartContainer`);
    if (cartContainer.children.length===0){
        document.querySelector(".summary").remove();
        document.querySelector("h2").innerText="Shopping cart is empty!";
    }
}

package com.codecool.shop.model.shoppingCart;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Product, Integer> shoppingCart;
    private int id;
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);

    public float getShoppingCartSummary(){
        float sum = 0;
        for(Product product : shoppingCart.keySet()){
           sum += (product.getPriceForCart() * shoppingCart.get(product));
        }
        logger.info("Shopping cart sum created. " +sum);
        return sum;
    }

    public ShoppingCart() {
        this.shoppingCart = new HashMap<>();
    }

    public ShoppingCart getShoppingCart() {
        return this;
    }

    public void setShoppingCart(HashMap<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getCartItemCount(){
        int sum = 0;
        for(Product product : shoppingCart.keySet()){
            sum += shoppingCart.get(product);
        }
        logger.info("Shopping cart items counted. " +sum);
        return sum;
    }

    public void addToShoppingCart(Product product){
        if(shoppingCart.containsKey(product)){
            shoppingCart.put(product, shoppingCart.get(product) + 1);
        }else {
            shoppingCart.put(product,1);
            logger.info("Item added to Shopping cart. " +product.getName());
        }

    }

    public void removeOneItemFromShoppingCart(Product product) {
        if (shoppingCart.get(product) > 1) {
            shoppingCart.put(product, shoppingCart.get(product) - 1);
        } else {
            shoppingCart.remove(product);
            logger.info("1 of product removed from Shopping cart. " +product.getName());
        }
    }

    public void removeItemFromShoppingCart(Product product) {
        logger.info("Item removed from Shopping cart. " +product.getName());
        shoppingCart.remove(product);
    }

    public HashMap<Product, Integer> getCart() {
        return this.shoppingCart;
    }

    public void clearCart() {
        shoppingCart = new HashMap<>();
        logger.debug("Shopping cart emptied. ");
    }

    public void setId(int id) {
        this.id = id;
    }
}

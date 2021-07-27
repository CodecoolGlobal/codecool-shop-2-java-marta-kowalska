package com.codecool.shop.model.shoppingCart;

import com.codecool.shop.model.product.Product;

import java.util.HashMap;

public class ShoppingCart {

    private HashMap<Product, Integer> shoppingCart;


    public float getShoppingCartSummary(){
        float sum = 0;
        for(Product product : shoppingCart.keySet()){
           sum += (product.getPriceForCart() * shoppingCart.get(product));
        }
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
        return sum;
    }

    public void addToShoppingCart(Product product){
        if(shoppingCart.containsKey(product)){
            shoppingCart.put(product, shoppingCart.get(product) + 1);
        }else {
            shoppingCart.put(product,1);
        }

    }

    public void removeFromShoppingCart(Product product) {
        if (shoppingCart.get(product) > 1) {
            shoppingCart.put(product, shoppingCart.get(product) - 1);
        } else {
            shoppingCart.remove(product);
        }


    }

    public HashMap<Product, Integer> getCart() {
        return this.shoppingCart;
    }
}

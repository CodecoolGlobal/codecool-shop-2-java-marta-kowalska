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

    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(HashMap<Product, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addToShoppingCart(Product product){
        if(shoppingCart.containsKey(product)){
            shoppingCart.put(product, shoppingCart.get(product) + 1);
        }else {
            shoppingCart.put(product,1);
        }

    }
}

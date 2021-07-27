package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.shoppingCart.ShoppingCart;

import java.util.HashMap;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private ShoppingCart shoppingCart = new ShoppingCart();

    private static ShoppingCartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        shoppingCart.addToShoppingCart(product);
    }

    @Override
    public void remove(Product product) {
        shoppingCart.removeFromShoppingCart(product);
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return shoppingCart.getCart();
    }

    @Override
    public ShoppingCart getCart() {
       return shoppingCart.getShoppingCart();
    }
}

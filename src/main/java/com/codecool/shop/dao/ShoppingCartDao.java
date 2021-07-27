package com.codecool.shop.dao;

import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.shoppingCart.ShoppingCart;

import java.util.HashMap;

public interface ShoppingCartDao {

        void add(Product product);
        void remove(Product product);
        HashMap<Product, Integer> getAll();
        ShoppingCart getCart();

}




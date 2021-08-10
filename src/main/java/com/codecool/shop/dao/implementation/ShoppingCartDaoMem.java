package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.shoppingCart.ShoppingCart;

import javax.sql.DataSource;
import java.util.HashMap;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private static DataSource dataSource;

    private ShoppingCart shoppingCart = new ShoppingCart();

    private static ShoppingCartDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShoppingCartDaoMem(DataSource dataSource) {
        ShoppingCartDaoMem.dataSource = dataSource;
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem(dataSource);
        }
        return instance;
    }

    public static ShoppingCartDaoMem getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ShoppingCartDaoMem(dataSource);
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        shoppingCart.addToShoppingCart(product);
    }

    @Override
    public void remove(Product product) {
        shoppingCart.removeOneItemFromShoppingCart(product);
    }

    @Override
    public HashMap<Product, Integer> getAll() {
        return shoppingCart.getCart();
    }

    @Override
    public ShoppingCart getCart() {
       return shoppingCart.getShoppingCart();
    }

    @Override
    public void deleteCart() {
        shoppingCart.clearCart();
    }
}

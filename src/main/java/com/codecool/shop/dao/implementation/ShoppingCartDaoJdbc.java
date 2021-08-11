package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.shoppingCart.ShoppingCart;

import javax.sql.DataSource;
import java.util.HashMap;

public class ShoppingCartDaoJdbc implements ShoppingCartDao {
    private static DataSource dataSource;

    private ShoppingCart shoppingCart = new ShoppingCart();

    private static ShoppingCartDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ShoppingCartDaoJdbc(DataSource dataSource) {
        ShoppingCartDaoJdbc.dataSource = dataSource;
    }


    public static ShoppingCartDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new ShoppingCartDaoJdbc(dataSource);
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

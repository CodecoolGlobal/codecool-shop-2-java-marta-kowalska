package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.product.Product;
import com.codecool.shop.model.shoppingCart.ShoppingCart;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ShoppingCartDaoJdbc implements ShoppingCartDao {
    private static DataSource dataSource;
    private static int shoppingCartId = 1;


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
        try (Connection conn = dataSource.getConnection()) {
            if(getProductQty(product) == 0){
                String sql = "INSERT INTO cart_item VALUES (DEFAULT, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setFloat(1, product.getDefaultPrice());
                statement.setString(2, product.getDefaultCurrency().toString());
                statement.setInt(3, 1);
                statement.setInt(4, shoppingCartId);
                statement.setInt(5, product.getId());
                statement.executeUpdate();
            } else {
                String sql = "UPDATE cart_item SET quantity = quantity + 1 WHERE product_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, product.getId());
                statement.executeUpdate();
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    private int getProductQty(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT quantity FROM cart_item WHERE product_id=? AND shopping_cart_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, product.getId());
            statement.setInt(2, shoppingCartId);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt(1);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
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

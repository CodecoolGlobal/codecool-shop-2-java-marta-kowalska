package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    private static DataSource dataSource;
    private List<Order> data = new ArrayList<>();
    private static OrderDaoJdbc instance = null;
    private Logger logger = LoggerFactory.getLogger(OrderDaoJdbc.class);
    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoJdbc(DataSource dataSource) {
        OrderDaoJdbc.dataSource = dataSource;
    }

    public static OrderDaoJdbc getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new OrderDaoJdbc(dataSource);
        }
        return instance;
    }

    @Override
    public int add(String firstName,
                    String lastName,
                    String email,
                    String phoneNumber,
                    String country,
                    String city,
                    String zipcode,
                    String address) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO webshop_order VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, country);
            statement.setString(6, city);
            statement.setString(7, zipcode);
            statement.setString(8, address);
            ResultSet rs = statement.executeQuery();
            rs.next();
            ShoppingCartDaoJdbc shoppingCart = ShoppingCartDaoJdbc.getInstance(dataSource);
            addOrderItems(rs.getInt(1),shoppingCart.getAll());
            changeOrderStatus(rs.getInt(1));
            createNewCart();
            return rs.getInt(1);

        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }

    private void createNewCart() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO shopping_cart VALUES (DEFAULT, false)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();

        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }

    private void changeOrderStatus(int orderId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE shopping_cart SET checked_out = true WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.executeUpdate();

        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }

    }

    private void addOrderItems(int orderId, HashMap<Product, Integer> items) {
        try (Connection conn = dataSource.getConnection()) {
            for (Product product: items.keySet()){
                String sql = "INSERT INTO order_item VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setFloat(1, product.getDefaultPrice());
                statement.setString(2, product.getDefaultCurrency().toString());
                statement.setInt(3, items.get(product));
                statement.setInt(4, orderId);
                statement.setInt(5, product.getId());
                statement.setString(6, product.getName());
                statement.setString(7, product.getDescription());
                statement.setString(8, product.getImageWithoutPath());
                statement.setInt(9, product.getCategoryId());
                statement.setInt(10, product.getSupplierId());
                statement.executeUpdate();
            }
        } catch(SQLException e){
            logger.error("Sql Error" + e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public Order find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Order> getAll() {
        return data;
    }

    @Override
    public int getOrderId(Order order) {
        return order.getId();
    }
}

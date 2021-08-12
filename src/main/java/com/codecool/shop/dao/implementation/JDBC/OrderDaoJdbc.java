package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {
    private static DataSource dataSource;
    private List<Order> data = new ArrayList<>();
    private static OrderDaoJdbc instance = null;
    private Logger logger = LoggerFactory.getLogger(ShoppingCartDaoJdbc.class);
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
            return rs.getInt(1);
            // TODO create order items (same as cart items)
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

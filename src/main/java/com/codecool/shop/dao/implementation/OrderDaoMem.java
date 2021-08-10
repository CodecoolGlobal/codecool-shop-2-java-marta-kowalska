package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.product.ProductCategory;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {
    private static DataSource dataSource;
    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem(DataSource dataSource) {
        OrderDaoMem.dataSource = dataSource;
    }

    public static OrderDaoMem getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new OrderDaoMem(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        order.setId(data.size() + 1);
        data.add(order);
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

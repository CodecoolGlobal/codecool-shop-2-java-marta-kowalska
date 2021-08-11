package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.product.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {
    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }


    public void add(Order order) {
        order.setId(data.size() + 1);
        data.add(order);
    }

    @Override
    public int add(String firstName, String lastName, String email, String phoneNumber, String country, String city, String zipcode, String address) {
        return 0;
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

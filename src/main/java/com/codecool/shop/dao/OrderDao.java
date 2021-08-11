package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    int add(String firstName,
             String lastName,
             String email,
             String phoneNumber,
             String country,
             String city,
             String zipcode,
             String address);

    Order find(int id);
    void remove(int id);

    List<Order> getAll();

    int getOrderId(Order order);
}

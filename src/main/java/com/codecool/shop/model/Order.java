package com.codecool.shop.model;


import com.codecool.shop.model.shoppingCart.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Order {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);
    int id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String country;
    String city;
    String zipcode;
    String address;

    public Order(
                 String firstName,
                 String lastName,
                 String email,
                 String phoneNumber,
                 String country,
                 String city,
                 String zipcode,
                 String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.zipcode = zipcode;
        this.address = address;
        logger.info("Order added. " +this.toString());
        logger.warn("Not payed yet. ");
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

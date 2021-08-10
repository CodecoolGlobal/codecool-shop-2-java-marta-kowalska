package com.codecool.shop.model;


public class Order {
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

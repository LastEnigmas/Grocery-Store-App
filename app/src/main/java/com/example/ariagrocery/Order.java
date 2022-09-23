package com.example.ariagrocery;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<GrocerItem> items;
    private String address;
    private String email;
    private String zipcode;
    private String phonenumber;
    private double price;
    private String paymentmethod;
    private boolean success;

    public Order(ArrayList<GrocerItem> items, String address, String email, String zipcode, String phonenumber, double price, String paymentmethod, boolean success) {
        this.id=Utils.getOrderId();
        this.items = items;
        this.address = address;
        this.email = email;
        this.zipcode = zipcode;
        this.phonenumber = phonenumber;
        this.price = price;
        this.paymentmethod = paymentmethod;
        this.success = success;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<GrocerItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GrocerItem> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", price=" + price +
                ", paymentmethod='" + paymentmethod + '\'' +
                ", success=" + success +
                '}';
    }
}

package com.example.bt_07;


import java.sql.Date;

public class Order {
    int id;
    String name;
    double price;
    int quantity;
    String dateOrder;

    public Order() {
    }

    public Order(String name, double price, int quantity, String dateOrder) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dateOrder = dateOrder;
    }

    public Order(int id, String name, double price, int quantity, String dateOrder) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dateOrder = dateOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }
}

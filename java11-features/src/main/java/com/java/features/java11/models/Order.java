package com.java.features.java11.models;

public class Order {
    private final Integer id;
    private final String productName;
    private final int quantity;
    private final double price;

    public Order(Integer id, String productName, int quantity, double price) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

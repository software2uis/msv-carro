package com.tuempresa.shoppingcart.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;      // Asegúrate de que este campo exista
    private String name;
    private double price;

    // Constructor
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters y Setters
    public String getId() {
        return id;  // Este método debe existir
    }

    public void setId(String id) {
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
}

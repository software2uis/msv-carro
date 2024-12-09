package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class Product implements Serializable {
    private String idMongo;

    private String name;

    private double price;
    private int quantity;

    private String imageUrl;

    public Product(String idMongo, String name, double price, int quantity, String imageUrl) {
        this.idMongo = idMongo;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    // Getters y Setters
    public String getIdMongo() {
        return idMongo;
    }

    public void setIdMongo(String idMongo) {
        this.idMongo = idMongo;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

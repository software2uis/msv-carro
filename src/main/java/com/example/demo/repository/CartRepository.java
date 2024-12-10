package com.example.demo.repository;

import com.example.demo.model.Product;

import java.util.List;

public interface  CartRepository{// src/main/java/com/example/demo/repository/CartRepository.java

void updateProductQuantity(String userId, String productId, int quantity);
    void addProduct(String userId, Product product);
    void removeProduct(String userId, String productId);
    List<Product> getCartContents(String userId);
    boolean cartExists(String userId);
    void createCart(String userId);  // Método para crear un carrito vacío
}

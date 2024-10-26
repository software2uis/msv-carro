package com.tuempresa.shoppingcart.repository;

import com.tuempresa.shoppingcart.model.Product;

import java.util.List;

public interface CartRepository {
    void addProduct(Product product);
    void removeProduct(String id);
    List<Product> getCartContents(); // Este es el método que necesitas
}

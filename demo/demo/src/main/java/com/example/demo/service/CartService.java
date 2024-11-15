package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addProduct(String userId, Product product) {
        if (!cartRepository.cartExists(userId)) {
            createCart(userId);  // Crea un carrito vacío si no existe
        }
        cartRepository.addProduct(userId, product);
    }

    public void removeProduct(String userId, String productId) {
        cartRepository.removeProduct(userId, productId);
    }

    public List<Product> getCartContents(String userId) {
        return cartRepository.getCartContents(userId);
    }

    public double calculateTotal(String userId) {
        List<Product> products = getCartContents(userId);
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void createCart(String userId) {
        cartRepository.createCart(userId);  // Método para crear un carrito vacío
    }
}

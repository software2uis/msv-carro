package com.tuempresa.shoppingcart.service;

import com.tuempresa.shoppingcart.model.Product;
import com.tuempresa.shoppingcart.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Este método obtiene el contenido del carrito
    public List<Product> getCart() {
        return cartRepository.getCartContents(); // Cambia getCart() por getCartContents()
    }
    //  método para el Total
    public double getCartTotal() {
        List<Product> products = cartRepository.getCartContents();
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
    // Otros métodos, como agregar y eliminar productos...
}

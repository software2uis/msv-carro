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
    public void syncCart(String userId, List<Product> frontendProducts) {
        if (!cartRepository.cartExists(userId)) {
            createCart(userId);  // Crea un carrito vacío si no existe
        }

        // Elimina productos no presentes en el JSON enviado desde el frontend
        List<Product> existingProducts = getCartContents(userId);
        for (Product existingProduct : existingProducts) {
            if (frontendProducts.stream().noneMatch(p -> p.getIdMongo().equals(existingProduct.getIdMongo()))) {
                removeProduct(userId, existingProduct.getIdMongo());
            }
        }

        // Agrega o actualiza productos enviados desde el frontend
        for (Product product : frontendProducts) {
            addProduct(userId, product); // Actualiza cantidades si es necesario
        }
    }
    
}

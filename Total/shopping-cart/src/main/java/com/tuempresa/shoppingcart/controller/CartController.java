package com.tuempresa.shoppingcart.controller;

import com.tuempresa.shoppingcart.model.Product;
import com.tuempresa.shoppingcart.service.CartService; 
import com.tuempresa.shoppingcart.repository.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final CartService cartService; 

    public CartController(CartRepository cartRepository, CartService cartService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    // Método para agregar múltiples productos al carrito
    @PostMapping("/add")
    public ResponseEntity<String> addProductsToCart(@RequestBody List<Product> products) {
        for (Product product : products) {
            cartRepository.addProduct(product);
        }
        return ResponseEntity.ok("Productos añadidos al carrito");
    }

    // Método para eliminar un producto del carrito
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable String id) {
        cartRepository.removeProduct(id);
        return ResponseEntity.ok("Producto eliminado del carrito");
    }

    // Método para obtener el contenido del carrito
    @GetMapping
    public ResponseEntity<List<Product>> getCartContents() {
        List<Product> cartContents = cartRepository.getCartContents();
        return ResponseEntity.ok(cartContents);
    }

    // Método para obtener el total $ del carrito
    @GetMapping("/total")
    public ResponseEntity<Double> getCartTotal() {
        double total = cartService.getCartTotal(); 
        return ResponseEntity.ok(total);
    }
}

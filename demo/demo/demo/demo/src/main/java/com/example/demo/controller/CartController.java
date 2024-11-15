package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Endpoint para añadir productos al carrito
    @PostMapping("/add/{userId}")
    public ResponseEntity<String> addProductsToCart(@PathVariable String userId, @RequestBody List<Product> products) {
        for (Product product : products) {
            cartService.addProduct(userId, product);
        }
        return ResponseEntity.ok("Productos añadidos al carrito de usuario " + userId);
    }

    // Endpoint para eliminar un producto del carrito
    @DeleteMapping("/remove/{userId}/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable String userId, @PathVariable String productId) {
        cartService.removeProduct(userId, productId);
        return ResponseEntity.ok("Producto eliminado del carrito del usuario " + userId);
    }

    // Endpoint para obtener el contenido del carrito de un usuario específico
    @GetMapping("/contents/{userId}")
    public ResponseEntity<List<Product>> getCartContents(@PathVariable String userId) {
        List<Product> cartContents = cartService.getCartContents(userId);
        return ResponseEntity.ok(cartContents);
    }

    // Endpoint para calcular el total del carrito de un usuario específico
    @GetMapping("/total/{userId}")
    public ResponseEntity<Double> calculateTotal(@PathVariable String userId) {
        double total = cartService.calculateTotal(userId);
        return ResponseEntity.ok(total);
    }

    // Nuevo endpoint para crear un carrito vacío para un usuario específico
    @PostMapping("/create/{userId}")
    public ResponseEntity<String> createEmptyCart(@PathVariable String userId) {
        cartService.createCart(userId);
        return ResponseEntity.ok("Carrito vacío creado para el usuario " + userId);
    }
    @PostMapping("/sync/{userId}")
    public ResponseEntity<String> syncCart(@PathVariable String userId, @RequestBody List<Product> products) {
    cartService.syncCart(userId, products);
    return ResponseEntity.ok("Carrito sincronizado para el usuario " + userId);
    }

}

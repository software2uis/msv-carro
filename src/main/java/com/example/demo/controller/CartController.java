package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.UserValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final UserValidationService userValidationService;

    public CartController(CartService cartService, UserValidationService userValidationService) {
        this.cartService = cartService;
        this.userValidationService = userValidationService;
    }

    private boolean isUserLoggedIn() {
        // Actualiza el estado de la sesión antes de validar
        userValidationService.checkActiveSession();
        return userValidationService.isActiveSession();
    }

    private String getActiveUsername() {
        return userValidationService.getActiveUsername();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductsToCart(@RequestBody List<Product> products) {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        String username = getActiveUsername();
        for (Product product : products) {
            cartService.addProduct(username, product);
        }
        return ResponseEntity.ok("Productos añadidos al carrito de " + username + ".");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable String productId) {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        String username = getActiveUsername();
        cartService.removeProduct(username, productId);
        return ResponseEntity.ok("Producto eliminado del carrito de " + username + ".");
    }

    @GetMapping("/contents")
    public ResponseEntity<List<Product>> getCartContents() {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(403).body(null);
        }
        String username = getActiveUsername();
        List<Product> cartContents = cartService.getCartContents(username);
        return ResponseEntity.ok(cartContents);
    }

    @GetMapping("/total")
    public ResponseEntity<String> calculateTotal() {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        String username = getActiveUsername();
        double total = cartService.calculateTotal(username);
        return ResponseEntity.ok("El total del carrito de " + username + " es: " + total + ".");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmptyCart() {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        String username = getActiveUsername();
        cartService.createCart(username);
        return ResponseEntity.ok("Carrito vacío creado para " + username + ".");
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncCart(@RequestBody List<Product> products) {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        String username = getActiveUsername();
        cartService.syncCart(username, products);
        return ResponseEntity.ok("Carrito sincronizado para " + username + ".");
    }
}

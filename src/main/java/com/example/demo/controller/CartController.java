package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.UserValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;
    private final UserValidationService userValidationService;

    public CartController(CartService cartService, UserValidationService userValidationService) {
        this.cartService = cartService;
        this.userValidationService = userValidationService;
    }

    private boolean isUserLoggedIn(String username) {
        // Actualiza el estado de la sesión antes de validar
        userValidationService.checkActiveSession(username);
        return userValidationService.isActiveSession();
    }

    private String getActiveUsername() {
        return userValidationService.getActiveUsername();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProductsToCart(@RequestBody List<Product> products, @RequestParam String username) {
        if (!isUserLoggedIn(username)) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        for (Product product : products) {
            cartService.addProduct(username, product);
        }
        return ResponseEntity.ok("Productos añadidos al carrito de " + username + ".");
    }

    // src/main/java/com/example/demo/controller/CartController.java

@PutMapping("/update-quantity")
public ResponseEntity<String> updateProductQuantity(@RequestParam String username, @RequestParam String productId, @RequestParam int quantity) {
    if (!isUserLoggedIn(username)) {
        return ResponseEntity.status(403).body("No hay una sesión activa.");
    }
    cartService.updateProductQuantity(username, productId, quantity);
    return ResponseEntity.ok("Cantidad del producto actualizada en el carrito de " + username + ".");
}

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable String productId, @RequestParam String username) {
        if (!isUserLoggedIn(username)) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        cartService.removeProduct(username, productId);
        return ResponseEntity.ok("Producto eliminado del carrito de " + username + ".");
    }

    @GetMapping("/contents")
    public ResponseEntity<List<Product>> getCartContents(@RequestParam String username  ) {
        if (!isUserLoggedIn(username)) {
            return ResponseEntity.status(403).body(null);
        }
        List<Product> cartContents = cartService.getCartContents(username);
        return ResponseEntity.ok(cartContents);
    }

    @GetMapping("/total")
    public ResponseEntity<String> calculateTotal(@RequestParam String username) {
        if (!isUserLoggedIn(username)) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        double total = cartService.calculateTotal(username);
        return ResponseEntity.ok("El total del carrito de " + username + " es: " + total + ".");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmptyCart(@RequestParam String username) {
        if (!isUserLoggedIn(username)) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        cartService.createCart(username);
        return ResponseEntity.ok("Carrito vacío creado para " + username + ".");
    }

    @PostMapping("/sync")
    public ResponseEntity<String> syncCart(@RequestBody List<Product> products, @RequestParam String username   ) {
        if (!isUserLoggedIn(username)) {
            return ResponseEntity.status(403).body("No hay una sesión activa.");
        }
        cartService.syncCart(username, products);
        return ResponseEntity.ok("Carrito sincronizado para " + username + ".");
    }
}

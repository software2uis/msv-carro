package com.tuempresa.shoppingcart.controller;

import com.tuempresa.shoppingcart.model.Product;
import com.tuempresa.shoppingcart.repository.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
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
    public ResponseEntity<Double> getCartTotal() {
        double total = cartService.calculateTotal();
        return ResponseEntity.ok(total);
    }
}

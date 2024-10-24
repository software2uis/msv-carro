package Controller;

import Model.CartItem;
import Services.CartItemService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    
    @Autowired
    private CartItemService cartItemService;

    // Obtener todos los ítems del carrito
    @GetMapping("/items")
    public List<CartItem> getItems() {
        return cartItemService.getAllItems();
    }

    // Añadir un ítem al carrito
    @PostMapping("/items")
    public CartItem addItem(@RequestBody CartItem item) {
        return cartItemService.addItem(item);
    }

    // Actualizar un ítem del carrito
    @PutMapping("/items/{id}")
    public CartItem updateItem(@PathVariable Long id, @RequestBody CartItem updatedItem) {
        return cartItemService.updateItem(id, updatedItem);
    }

    // Eliminar un ítem del carrito
    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        cartItemService.deleteItem(id);
    }

    // Obtener un ítem por su ID
    @GetMapping("/items/{id}")
    public CartItem getItem(@PathVariable Long id) {
        return cartItemService.getItem(id).orElse(null);
    }

    // Obtener el subtotal del carrito
    @GetMapping("/subtotal")
    public double getSubtotal() {
        return cartItemService.calculateSubtotal();
    }

    // Obtener el total del carrito (incluyendo impuestos o tasas si es necesario)
    @GetMapping("/total")
    public double getTotal() {
        return cartItemService.calculateTotal();
    }
}

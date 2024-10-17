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

    @GetMapping("/items")
    public List<CartItem> getItems() {
        return cartItemService.getAllItems();
    }

    @PostMapping("/items")
    public CartItem addItem(@RequestBody CartItem item) {
        return cartItemService.addItem(item);
    }

    @PutMapping("/items/{id}")
    public CartItem updateItem(@PathVariable Long id, @RequestBody CartItem updatedItem) {
        return cartItemService.updateItem(id, updatedItem);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        cartItemService.deleteItem(id);
    }

    @GetMapping("/items/{id}")
    public CartItem getItem(@PathVariable Long id) {
        return cartItemService.getItem(id).orElse(null);
    }
}
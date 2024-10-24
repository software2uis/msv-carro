package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(Long itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
    }

    public double getSubtotal() {
        return items.stream()
                    .mapToDouble(CartItem::getSubtotal)
                    .sum();
    }

    public double getTotal() {
        double subtotal = getSubtotal();
        double tax = subtotal * 0.19; // Por ejemplo, un impuesto del 19%
        return subtotal + tax;
    }

    // Getters y Setters
    public List<CartItem> getItems() {
        return items;
    }
}

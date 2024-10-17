package Services;

import Model.CartItem;
import Repository.CartItemRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    @Autowired
    
    private CartItemRepository cartItemRepository;

// Obtener todos los ítems del carrito
    public List<CartItem> getAllItems() {
        return cartItemRepository.findAll();
    }

// Agregar un nuevo ítem al carrito
    public CartItem addItem(CartItem item) {
        return cartItemRepository.save(item);
    }

// Eliminar un ítem del carrito por ID
    public void deleteItem(Long id) {
        cartItemRepository.deleteById(id);
    }

// Obtener un ítem específico por ID
    public Optional<CartItem> getItem(Long id) {
        return cartItemRepository.findById(id);
    }

// Actualizar un ítem existente
    public CartItem updateItem(Long id, CartItem updatedItem) {
        if (cartItemRepository.existsById(id)) {
            CartItem existingItem = cartItemRepository.findById(id).get();

            existingItem.setNombre(updatedItem.getNombre());
            existingItem.setCantidad(updatedItem.getCantidad());
            existingItem.setPrecio(updatedItem.getPrecio());

            return cartItemRepository.save(existingItem);
        }
        return null;
    }
}
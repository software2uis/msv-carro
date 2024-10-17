package Repository;

import java.util.List;
import java.util.Optional;

import Model.CartItem;


//ya esta puesta la dependencia en el pom.xml para agregar base de datos de Redis

public interface CartItemRepository {

	List<CartItem> findAll();

	CartItem save(CartItem item);

	void deleteById(Long id);

	Optional<CartItem> findById(Long id);

	boolean existsById(Long id);

}

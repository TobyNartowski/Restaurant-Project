package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.model.Product;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

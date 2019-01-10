package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.model.Ingredient;
import restaurant.model.Storage;

import java.util.Map;


@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

}

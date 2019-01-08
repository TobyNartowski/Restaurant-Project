package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.model.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {
}

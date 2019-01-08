package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.model.Address;

@Repository
public interface AdressRepository extends JpaRepository<Address, Long> {
}

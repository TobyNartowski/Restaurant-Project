package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}

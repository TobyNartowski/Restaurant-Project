package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select c from client c\n" +
            "where c.name = ?1 and c.last_name = ?2 and phone_number = ?3",
    nativeQuery = true)
    Client findClient(String firstname, String lastname, String phone);
}

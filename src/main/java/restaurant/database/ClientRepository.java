package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurant.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "select case when count(c) > 0 then true else false end from client c where c.login = :login", nativeQuery = true)
    boolean exists(@Param("login") String login);

    @Query(value = "select case when count(c) > 0 then true else false end from client c where c.login = :login and c.hash = :hash", nativeQuery = true)
    boolean auth(@Param("login") String login, @Param("hash") String hash);

    @Query(name = "Client.findByLogin", nativeQuery = true)
    Client getClientByLogin(@Param("login") String login);

    @Query(value = "select c from client c\n" +
            "where c.name = ?1 and c.last_name = ?2 and phone_number = ?3",
            nativeQuery = true)
    Client findClient(String firstname, String lastname, String phone);
}

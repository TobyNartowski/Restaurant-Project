package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select a.id from address a\n" +
            "where a.city = ?1 and a.street = ?2 and a.number = ?3", nativeQuery = true)
    public Long findId(String city, String street, String number);
}

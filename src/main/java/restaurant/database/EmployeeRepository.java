package restaurant.database;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employee e\n" +
            "where type = 'WAITER' limit 1;", nativeQuery = true)
    Employee getFirstWaiter();

}

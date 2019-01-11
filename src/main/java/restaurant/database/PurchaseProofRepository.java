package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.model.PurchaseProof;

@Repository
public interface PurchaseProofRepository extends JpaRepository<PurchaseProof, Long> {
}

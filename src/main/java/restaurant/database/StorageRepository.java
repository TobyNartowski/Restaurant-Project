package restaurant.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.data.Wrapper;
import restaurant.model.StorageEntity;

import java.util.List;


@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Long> {

    @Query(value = "select i.name from ingredient i, product p, product_ingredient_list pl, storage_ingredient_list s where\n" +
            "p.name = ?1 and p.id = pl.product_id and pl.ingredient_list_id = i.id and i.name = s.ingredient_list_key",
    nativeQuery = true)
    List<String> getIngredientList(String product);

    @Query(value = "select s.ingredient_list from ingredient i, storage_ingredient_list s\n" +
            "where i.name = ?1 and i.name = ingredient_list_key ;", nativeQuery = true)
    Integer getIngredientQuantity(String ingredient);

    @Query(value = "select i.name as \"ingredient\", s.quantity as \"quantity\" from ingredient i, storage s\n" +
            "where i.id = s.ingredient_id", nativeQuery = true)
    Wrapper loadIngredientsFromDatabase();
//todo pobierac dane z bazy danych jak kolwiek

}

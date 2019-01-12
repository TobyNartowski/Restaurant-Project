package restaurant.model;

import restaurant.database.ProductRepository;
import restaurant.database.StorageRepository;

import javax.persistence.*;
import java.util.*;

@Entity
public class Storage {

    @Transient
    private static Storage instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @ElementCollection
        private Map<String, Integer> ingredientList = new HashMap<>();

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }


    public void loadIngredientsFromDatabase(StorageRepository repository, ProductRepository productRepository) {
        List<Product> products = productRepository.findAll();

        for (int i = 0; i < products.size(); i++) {
            List<String> ingredients = repository.getIngredientList(products.get(i).getName());
            for (String ingredient : ingredients) {
                ingredientList.put(ingredient, repository.getIngredientQuantity(ingredient));
            }
        }
    }

    public void printIngredientList() {
        System.out.println("Current ingredients:");
        for (Map.Entry<String, Integer> entry : ingredientList.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Integer> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(Map<String, Integer> ingredientList) {
        this.ingredientList = ingredientList;
    }
}

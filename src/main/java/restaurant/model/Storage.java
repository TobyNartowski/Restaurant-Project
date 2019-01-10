package restaurant.model;

import restaurant.database.IngredientRepository;
import restaurant.database.StorageRepository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Storage {

    @Transient
    private static Storage instance;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @ElementCollection
        private Map<Ingredient, Integer> ingredientList;

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    //todo profileDEV
    public void firstInit(IngredientRepository ingredientRepository) {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        ingredientList = new HashMap<>();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientList.put(ingredients.get(i), 100);
        }
    }

    public void initialize(StorageRepository repository) {
        //productList = repository.findAll();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Ingredient, Integer> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(Map<Ingredient, Integer> ingredientList) {
        this.ingredientList = ingredientList;
    }
}

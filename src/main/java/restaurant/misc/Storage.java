package restaurant.misc;

import restaurant.data.Wrapper;
import restaurant.database.IngredientRepository;
import restaurant.database.StorageRepository;
import restaurant.model.Ingredient;
import restaurant.model.StorageEntity;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Storage {
    private static Storage instance;

    private Map<String, Integer> ingredientList = new HashMap<>();

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public List<Wrapper> loadIngredientsFromDatabase() {
        List<Wrapper> list  = new LinkedList<>();
        //list.forEach(System.out::print);
        return list;
    }

    public void saveIngredientsInDatabase(StorageRepository repository, IngredientRepository ingredients) {
        List<Ingredient> keys = ingredients.findAll();
        for (int i = 0; i < ingredientList.size(); i++) {
            repository.save(new StorageEntity(keys.get(i), ingredientList.get(keys.get(i).getName())));
        }
    }

    public void printIngredientList() {
        System.out.println("Current ingredients:");
        for (Map.Entry<String, Integer> entry : ingredientList.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public Map<String, Integer> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(Map<String, Integer> ingredientList) {
        this.ingredientList = ingredientList;
    }
}

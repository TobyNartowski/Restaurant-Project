package restaurant.misc;

import restaurant.database.IngredientRepository;
import restaurant.database.StorageRepository;
import restaurant.model.Ingredient;
import restaurant.model.StorageEntity;

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

    public Map<String, Integer> loadIngredients() {
        StorageRepository storageRepository = ContextWrapper.getContext().getBean(StorageRepository.class);
        List<StorageEntity> listIngredient = storageRepository.findAll();

        for (int i = 0; i < listIngredient.size(); i++) {
            System.out.println(i + "  " + listIngredient.get(i).getIngredient() + "  " + listIngredient.get(i).getQuantity());
            this.ingredientList.put(listIngredient.get(i).getIngredient().getName(), listIngredient.get(i).getQuantity());
        }
        return this.ingredientList;
    }

    public void saveIngredientsInDatabase() {
        StorageRepository storageRepository = ContextWrapper.getContext().getBean(StorageRepository.class);
        IngredientRepository ingredientRepository = ContextWrapper.getContext().getBean(IngredientRepository.class);
        List<Ingredient> keys = ingredientRepository.findAll();
        for (int i = 0; i < ingredientList.size(); i++) {
            storageRepository.save(new StorageEntity(keys.get(i), ingredientList.get(keys.get(i).getName())));
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

package restaurant.misc;

import restaurant.database.IngredientRepository;
import restaurant.model.Ingredient;

import java.util.List;

public class IngredientFlyweight {

    private static Ingredient[] data;

    public static Ingredient getIngredient(String name) {
        if (data == null) {
            IngredientRepository ingredientRepository = ContextWrapper.getContext().getBean(IngredientRepository.class);
            List<Ingredient> fetchData = ingredientRepository.findAll();
            data = new Ingredient[fetchData.size()];
            data = fetchData.toArray(data);
        }

        for (Ingredient ingredient : data) {
            if (ingredient.getName().toLowerCase().equals(name.toLowerCase())) {
                return ingredient;
            }
        }
        return null;
    }
}

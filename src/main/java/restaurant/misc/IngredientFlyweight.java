package restaurant.misc;

import restaurant.model.Ingredient;

public class IngredientFlyweight {

    public static Ingredient getIngredient(String name) {
        for (Ingredient ingredient : data) {
            if (ingredient.getName().toLowerCase().equals(name.toLowerCase())) {
                return ingredient;
            }
        }
        return null;
    }
    private static Ingredient[] data = {
            new Ingredient("Dough", 300),
            new Ingredient("Tomato sauce", 100),
            new Ingredient("Cheese", 200),
            new Ingredient("Ham", 300),
            new Ingredient("Beef", 500),
            new Ingredient("Bun", 300),
            new Ingredient("Mayonese", 100),
            new Ingredient("Mustard", 100),
            new Ingredient("Tomato", 60),
            new Ingredient("Hot-dog bun", 300),
            new Ingredient("Sausage", 300),
            new Ingredient("Orange", 100),
            new Ingredient("Water", 50),
            new Ingredient("BBQ sauce", 150),
            new Ingredient("Onion", 50),
            new Ingredient("Peppers", 150),
            new Ingredient("Mushrooms", 75),
            new Ingredient("Garlic", 75),
            new Ingredient("Olives", 50),
            new Ingredient("Bacon", 250),
            new Ingredient("Pineapple", 150),
            new Ingredient("Salami", 200),
            new Ingredient("Chicken", 300),
            new Ingredient("Mozzarella", 250),
            new Ingredient("Croutons", 50),
            new Ingredient("Lettuce", 50),
            new Ingredient("Rucola", 100),
            new Ingredient("Cucumber", 100),
            new Ingredient("Red Onion", 100),
            new Ingredient("Oregano", 100),
            new Ingredient("Corn", 150),
            new Ingredient("Sour Cream", 75),
            new Ingredient("Spinach", 150)
    };
}

package restaurant.misc;

import restaurant.model.Ingredient;
import restaurant.model.Product;

public class RemoveIngredientDecorator extends Product {

    public RemoveIngredientDecorator(Product product, Ingredient ingredient) {
        this.ingredientList = product.getIngredientList();
        this.name = product.getName();
        this.price = product.getPrice() - ingredient.getPrice();
        this.ingredientList.remove(ingredient);

    }
}

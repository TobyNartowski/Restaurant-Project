package restaurant.misc;

import restaurant.model.Ingredient;
import restaurant.model.Product;

public class AddIngredientDecorator extends Product {

    public AddIngredientDecorator(Product product, Ingredient ingredient) {
        this.ingredientList = product.getIngredientList();
        this.name = product.getName();
        this.price = product.getPrice() + ingredient.getPrice();
        this.ingredientList.add(ingredient);
    }
}

package restaurant.data;

public class Wrapper {
    protected String ingredient;
    protected Integer quantity;

    public Wrapper(String ingredient, Integer quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}


package restaurant.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    protected Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    protected List<Ingredient> ingredientList = new ArrayList<>();
    protected String name;
    protected long price;
    private boolean decorable = true;

    public Product() {}

    public Product(String name, long price, List<Ingredient> ingredientList) {
        this.name = name;
        this.price = price;
        this.ingredientList = ingredientList;
    }
    public Product(String name, long price, List<Ingredient> ingredientList, boolean decorable) {
        this.name = name;
        this.price = price;
        this.ingredientList = ingredientList;
        this.decorable = decorable;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public long getPrice() {
        return price;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public boolean removeIngredient(Ingredient ingredient) {
        return ingredientList.remove(ingredient);
    }

    public boolean isDecorable() {
        return decorable;
    }

    public void setDecorable(boolean decorable) {
        this.decorable = decorable;
    }

    public void copy(Product diff) {
        name = diff.getName();
        ingredientList = diff.getIngredientList();
        price = diff.getPrice();
    }

    public String getDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean leastOne = false;
        for (Ingredient ingredient : ingredientList) {
            stringBuilder.append(ingredient).append(", ");
            leastOne = true;
        }
        if (leastOne) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name).append(" [");
        boolean leastOne = false;
        for (Ingredient ingredient : ingredientList) {
            stringBuilder.append(ingredient).append(", ");
            leastOne = true;
        }
        if (leastOne) {
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "]");
        }
        return stringBuilder.toString();
    }
}
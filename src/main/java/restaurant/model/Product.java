package restaurant.model;

import org.hibernate.annotations.Fetch;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Ingredient> ingredientList;
    private String name;
    private long price;

    public Product() {}

    public Product(String name, long price, List<Ingredient> ingredientList) {
        this.name = name;
        this.price = price;
        this.ingredientList = ingredientList;
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

    public void setListaSkladnikow(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public long getPrice() {
        return price;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
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
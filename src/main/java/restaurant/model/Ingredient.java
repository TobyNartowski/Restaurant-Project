package restaurant.model;

import org.springframework.beans.factory.annotation.Autowired;
import restaurant.database.IngredientRepository;

import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Ingredient {

    /*
    private IngredientRepository ingredientRepository;

    @Transient public static final Ingredient dough = new Ingredient("Ciasto", 30);
    @Transient public static final Ingredient pizzaSauce = new Ingredient("Sos do pizzy", 10);
    @Transient public static final Ingredient cheese = new Ingredient("Ser", 20);
    @Transient public static final Ingredient ham = new Ingredient("Szynka", 30);

    @Autowired
    public Ingredient(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;

    public Ingredient(String name, long price) {
        this.name = name;
        this.price = price;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
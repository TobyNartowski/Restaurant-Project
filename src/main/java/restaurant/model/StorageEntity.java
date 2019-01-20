package restaurant.model;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class StorageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @ManyToOne
        private Ingredient ingredient;
    private Integer quantity;

    public StorageEntity() {}

    public StorageEntity(Ingredient ingredients, Integer quantity) {
        this.ingredient = ingredients;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

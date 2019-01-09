package restaurant.model;

import javax.persistence.*;

@Entity
public class Storage {

    @Transient
    private static Storage instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Ingredient ingredient;
    private int quantity;

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
}

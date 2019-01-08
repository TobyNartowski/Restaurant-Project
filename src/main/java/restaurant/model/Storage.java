package restaurant.model;

import javax.persistence.*;

@Entity
public class Storage {

    @Transient
    public static Storage instance;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
}

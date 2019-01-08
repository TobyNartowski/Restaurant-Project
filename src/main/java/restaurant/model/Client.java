package restaurant.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client extends User {

    @Id
    @GeneratedValue
    private Long id;
    //private List<Order> orderList;
    private String login;
    private String hash;

    public Client() {}

    public Client(String name, String lastName, Address address, long phoneNumber, String login, String hash) {
        super(name, lastName, address, phoneNumber);
        this.login = login;
        this.hash = hash;
    }

    public List<Order> getOrderList() {
        return null;
    }

    public int getDiscount() {
        return 0;
    }

    public Order getOrder() {
        return null;
    }

    public Order getCurrentOrder() {
        return null;
    }

    public String getLogin() {
        return login;
    }

    public String getHash() {
        return hash;
    }

    public void reserve(int tableNumber, int people) {

    }
}

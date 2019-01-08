package restaurant.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends User{

    @Id
    @GeneratedValue
    private Long id;
    //private List<Order> orderList;
    private String login;
    private String hash;
    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Client() {}

    public Client(String name, String lastName, Adress adress, long phoneNumber, String login, String hash) {
        super(name, lastName, adress, phoneNumber);
        this.login = login;
        this.hash = hash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

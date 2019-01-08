package restaurant.model;

import java.util.List;

public class Client extends User {

    private List<Order> orderList;
    private String login;
    private String hash;

    public Client(String name, String lastName, Adress adress, long phoneNumber, String login, String hash) {
        super(name, lastName, adress, phoneNumber);
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

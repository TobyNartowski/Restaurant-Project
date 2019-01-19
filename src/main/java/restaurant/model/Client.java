package restaurant.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "client_orders",
        joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
    private List<Order> orderList;
    @OneToOne
    private Address address;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_reservation")
    private Reservation reservation;
    private String login;
    private String hash;
    private String firstName;
    private String lastName;
    private long phoneNumber;

    public Client() {}

    public Client(String login, String hash) {
        this.login = login;
        this.hash = hash;
    }

    public Client(String firstName, String lastName, Address address, long phoneNumber, String login, String hash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.hash = hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}

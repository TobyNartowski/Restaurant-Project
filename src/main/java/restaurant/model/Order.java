package restaurant.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order implements Cloneable {

    public enum Status {
        UTWORZONE("Utworzone"),
        ODRZUCONE("Odrzucone"),
        ODEBRANE("Odebrane"),
        PRZEKAZANE_DO_KUCHNI("Przekazane do kuchni"),
        GOTOWE_DO_DOSTARCZENIA("Gotowe do dostarczenia"),
        W_TRAKCIE_DOSTAWY("W trakcie dostawy"),
        DOSTARCZONE("Dostarczone"),
        ZREALIZOWANE("Zrealizowane");

        private String note;
        private Date date;
        private Employee employee;
        private static Status[] statusValues = values();

        Status(Employee employee) {
            this.employee = employee;
        }

        Status(String note) {
            this.note = note;
        }

        public Status nextStatus(Employee employee) {
            Status newStatus = statusValues[(this.ordinal() + 1) % statusValues.length];
            newStatus.setEmployee(employee);
            return newStatus;
        }

        public Date getDate() {
            return date;
        }

        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }
    }

    @Transient
    static private Order instance = new Order();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @ElementCollection
        private Map<Product, Integer> productList = new HashMap<>();
    @Enumerated(EnumType.STRING)
        private Status status;
    @ManyToMany(mappedBy = "orderList")
    private List<Client> clientList;
    @OneToOne
    @JoinColumn(name = "purchase_id")
        private PurchaseProof purchaseProof = new PurchaseProof();
    @OneToOne
        private Address deliveryAddress;
    @OneToOne
        private Reservation table;
    @OneToOne
    @JoinColumn(name = "employee_order")
        private Employee employee;
    private boolean payment = false;
    private String note;

    public Order() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Order getPrototype() {
        return instance;
    }

    public void addProduct(Product product, int amount) {
        productList.put(product, amount);
    }

    public boolean deleteProduct(int id, int amount) {
        return false;
    }

    public Status getStatus() {
        return status;
    }

    public boolean getPayment() {
        return payment;
    }

    public void completePayment() {
        payment = true;
    }

    public void nextStatus(Employee employee) {
        status.nextStatus(employee);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PurchaseProof getPurchaseProof() {
        return null;
    }

    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<Product, Integer> productList) {
        this.productList = productList;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setPurchaseProof(PurchaseProof purchaseProof) {
        this.purchaseProof = purchaseProof;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public Reservation getTable() {
        return table;
    }

    public void setTable(Reservation table) {
        this.table = table;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("-- Zamowienie --\n");
        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            stringBuilder.append(entry.getKey()).append("\n");
        }
        return stringBuilder.toString();
    }
}
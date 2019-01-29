package restaurant.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order implements Cloneable {

    public enum Type {
        RESTAURANT, DELIVERY
    }

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
    @ManyToMany
    @JoinTable(name = "orders_product_list",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")})
        private List<Product> productList = new ArrayList<>();
    @Enumerated(EnumType.STRING)
        private Status status;
    @Enumerated(EnumType.STRING)
        private Type type;
    @ManyToMany
    @JoinTable(name = "clientList",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    private List<Client> clientList;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
        private PurchaseProof purchaseProof = new PurchaseProof();
    @OneToOne(fetch = FetchType.LAZY)
        private Address deliveryAddress;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
        private Reservation table;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_order")
        private Employee employee;
    private boolean payment = false;
    private String note;
    private Long customPhoneNumber;

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

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Long longId) {
        for (Product product : productList) {
            if (product.getId().equals(longId)) {
                productList.remove(product);
                return;
            }
        }
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
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

    public Long getCustomPhoneNumber() {
        return customPhoneNumber;
    }

    public void setCustomPhoneNumber(long customPhoneNumber) {
        this.customPhoneNumber = customPhoneNumber;
    }

    public long getTotal() {
        long totalPrice = 0L;
        for (Product product : productList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (type == Type.DELIVERY) {
            stringBuilder.append("-- Zamówienie na wynos --\n");
        } else {
            stringBuilder.append("-- Zamówienie do restauracji --\n");
        }
        for (Product x : productList) {
            stringBuilder.append(x).append("\n");
        }
        return stringBuilder.toString();
    }
}
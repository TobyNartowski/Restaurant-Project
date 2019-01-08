package restaurant.model;

import javax.persistence.*;
import java.util.*;

// TODO: Translate to english

@Entity
@Table(name = "orders")
public class Order implements Cloneable {

    public enum Status {
        ODRZUCONE("Odrzucone"),
        ODEBRANE("Odebrane"),
        PRZEKAZANE_DO_KUCHNI("Przekazane do kuchni"),
        GOTOWE_DO_DOSTARCZENIA("Gotowe do dostarczenia"),
        W_TRAKCIE_DOSTAWY("W trakcie dostawy"),
        DOSTARCZONE("Dostarczone"),
        ZREALIZOWANE("Zrealizowane");

        private String note;
        private Date date;
        //private Employee employee;

        Status(String note) {
            this.note = note;
        }

        public void nextStatus(Employee employee) {

        }

        public Date getDate() {
            return date;
        }

        public Employee getEmployee() {
            return null; //employee;
        }
    }

    @Transient
    static private Order instance = new Order();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private Map<Product, Integer> productList = new HashMap<>();
    private boolean payment = false;
    @Enumerated(EnumType.STRING)
    private Status status;
    //private List<PurchaseProof> purchaseProof;
    //private Address deliveryAddress;
    //private Reservation table;

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

    public void changeStatus(Status status, Employee employee) {

    }

    public List<PurchaseProof> getPurchaseProof() {
        return null;
    }

    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<Product, Integer> productList) {
        this.productList = productList;
    }

    /*public void rozdzielDowodZakupu(List<Map<Product, Integer>> podzielonaListaProduktow) {
        TODO
    }*/

    @Override
    public Object clone() throws CloneNotSupportedException {
        Order clonedOrder = (Order) super.clone();

        Map<Product, Integer> copiedProductList = new HashMap<>();
        if (!instance.getProductList().isEmpty()) {
            for (Map.Entry<Product, Integer> entry : instance.getProductList().entrySet()) {
                copiedProductList.put(entry.getKey(), entry.getValue());
            }
        }
        clonedOrder.setProductList(copiedProductList);

        return clonedOrder;
    }
}
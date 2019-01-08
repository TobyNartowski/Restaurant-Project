package restaurant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {

    public enum Status {
        //todo
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
            return null;//employee;
        }
    }

    public enum Payment {
        NIEZREALIZOWANA, ZREALIZOWANA;
           // todo
        public boolean zrealizuj() {
            return false;
        }
    }

    @Id
    @GeneratedValue
    private Long id;
    //private Map<Product, Integer> productList;
    private Payment payment;
    private Status status;
    //private List<PurchaseProof> purchaseProof;
    //private Adress deliveryAdress;
    //private Reservation table;

    public Order() {}

    public void addProduct(Product product, int amount) {

    }

    public boolean deleteProduct(int id, int amount) {
        return false;
    }

    public Status getStatus() {
        return status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void completePayment() { }

    public void changeStatus(Status status, Employee employee) {

    }

    public List<PurchaseProof> getPurchaseProof() {
        return null;
    }

    /*public void rozdzielDowodZakupu(List<Map<Product, Integer>> podzielonaListaProduktow) {
        TODO
    }*/
}
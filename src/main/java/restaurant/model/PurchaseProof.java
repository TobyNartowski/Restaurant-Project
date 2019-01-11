package restaurant.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseProof {

    public enum PurchaseType {
        BILL("Paragon"), FACTURE("Faktura");

        private String value;

        PurchaseType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue
        private Long id;
    @OneToOne(mappedBy = "purchaseProof")
        private Order order;
    @Enumerated(EnumType.STRING)
        private PurchaseType type;
    private String companyData;
    private String NIP;
    private int printoutNumber;
    private Date data;
//    private Map<Product, Integer> listaProduktow;

    public PurchaseProof() {}

    PurchaseProof(Order order, PurchaseType type) {
        this.order = order;
        this.type = type;
    }

    public Integer getSumaNaleznosci() {
        return null;
    }

    public void zmienTyp() {
    }

    public void wydrukujDowoduZakupu() {

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyData() {
        return companyData;
    }

    public void setCompanyData(String companyData) {
        this.companyData = companyData;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public int getPrintoutNumber() {
        return printoutNumber;
    }

    public void setPrintoutNumber(int printoutNumber) {
        this.printoutNumber = printoutNumber;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public PurchaseType getType() {
        return type;
    }

    public void setType(PurchaseType type) {
        this.type = type;
    }
}

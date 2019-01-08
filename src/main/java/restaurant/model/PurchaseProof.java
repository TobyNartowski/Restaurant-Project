package restaurant.model;

import java.util.Date;
import java.util.Map;

public class PurchaseProof {

    public enum TypDowoduZakupu {
        PARAGON, FAKTURA;

        public void zmienTyp() {

        }
    }

    private String DANE_FIRMY;
    private String NIP;
    private int NUMER_WYDRUKU;
    private Date data;
    private Map<Product, Integer> listaProduktow;
    private TypDowoduZakupu typ;

    PurchaseProof(Map<Product, Integer> listaProduktow, TypDowoduZakupu typ) {
        this.listaProduktow = listaProduktow;
        this.typ = typ;
    }

    public Integer getSumaNaleznosci() {
        return null;
    }

    public void zmienTyp() {

    }

    public void wydrukujDowoduZakupu() {

    }
}

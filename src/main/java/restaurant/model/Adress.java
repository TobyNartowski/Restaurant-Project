package restaurant.model;

import javax.persistence.*;

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idadres")
    private Long id;
    private String city;
    private String street;
    private String number;

    public Adress() {}

    public Adress(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMiasto() {
        return city;
    }

    public void setMiasto(String city) {
        this.city = city;
    }

    public String getUlica() {
        return street;
    }

    public void setUlica(String street) {
        this.street = street;
    }

    public String getNumer() {
        return number;
    }

    public void setNumer(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Adress" + id + "( " + city + " " + street + " " + number + ")";
    }
}
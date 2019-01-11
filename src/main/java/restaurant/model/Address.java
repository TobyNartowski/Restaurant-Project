package restaurant.model;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    private String city;
    private String street;
    private String number;

    public Address() {}

    public Address(String city, String street, String number) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
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
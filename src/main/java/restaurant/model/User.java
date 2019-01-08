package restaurant.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private Adress adress;
    private long phoneNumber;

    public User() {}

    public User(String name, String lastName, Adress adress, long phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public long getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
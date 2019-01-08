package restaurant.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String lastName;
    private long phoneNumber;
    @OneToOne
    private Address address;

    public User() {}

    public User(String name, String lastName, Address address, long phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public long getphoneNumber() {
        return phoneNumber;
    }

    public void setphoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
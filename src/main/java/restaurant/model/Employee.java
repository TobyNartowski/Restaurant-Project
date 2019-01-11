package restaurant.model;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Entity
public class Employee {
    public enum Type {
        WAITER, SUPPLIER, COOK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @OneToOne
        private Address address;
    @Enumerated(EnumType.STRING)
        private Type type;
    @OneToOne(mappedBy = "employee")
        private Order order;
    private String rfid;
    private String name;
    private String lastName;
    private long phoneNumber;

    public Employee() {}

    public Employee(String name, String lastName, Address adress, long phoneNumber, Type type) {
        this.name = name;
        this.lastName = lastName;
        this.address = adress;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.rfid = generateRandomRfid();
    }

    private String generateRandomRfid() {
        byte[] bytes = new byte[12];

        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.nextBytes(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (byte oneByte : bytes) {
            String theHex = Integer.toHexString(oneByte & 0xFF).toUpperCase();
            stringBuilder.append(theHex.length() == 1 ? "0" + theHex : theHex);
        }

        return stringBuilder.toString();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public void changeStatus(Order order) {
    }

    public Order.Status checkOrderStatus(Order order) {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
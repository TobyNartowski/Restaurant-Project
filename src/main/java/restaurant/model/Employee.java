package restaurant.model;


public class Employee extends User {

    private String rfid;

    public Employee() {}

    public Employee(String name, String lastName, Adress adress, long phoneNumber) {
        super(name, lastName, adress, phoneNumber);
    }

    public String getRfid() {
        return rfid;
    }

    public void changeStatus(Order order) {

    }

    public Order.Status checkOrderStatus(Order order) {
        return null;
    }
}
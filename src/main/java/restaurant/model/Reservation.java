package restaurant.model;

import java.util.Date;


public class Reservation {


    private Long id;
    private int tableNumber;
    private Date date;
    private int numberOfPeople;
    private Client bookkeeper;

    public Reservation() {}

    public Reservation(int tableNumber, int numberOfPeople, Client bookkeeper) {
        this.tableNumber = tableNumber;
        this.numberOfPeople = numberOfPeople;
        this.bookkeeper = bookkeeper;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Client getBookkeeper() {
        return bookkeeper;
    }

    public void setBookkeeper(Client bookkeeper) {
        this.bookkeeper = bookkeeper;
    }

    public boolean changeDate(Date newdate) {
        return false;
    }

    public void changeNumberOfPeople(int numberOfPeople) {

    }

    public void reserve() {

    }
}

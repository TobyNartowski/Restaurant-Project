package restaurant.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import restaurant.database.*;
import restaurant.exception.EmptyClassException;
import restaurant.model.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddOrder {
    private ApplicationContext ctx;
    private Client client;
    private ClientRepository clientRepository;
    private Reservation reservation;
    private ReservationRepository reservationRepository;
    private PurchaseProof proof;
    private Employee employee;
    private EmployeeRepository employeeRepository;
    private Address address;
    private AddressRepository addressRepository;
    private Order order;
    private Map<Product, Integer> productList = new HashMap<>();
    private ProductRepository productRepository;

    @Autowired
    public AddOrder(ApplicationContext ctx) {
        this.ctx = ctx;
        this.clientRepository = ctx.getBean(ClientRepository.class);
        this.reservationRepository = ctx.getBean(ReservationRepository.class);
        this.employeeRepository = ctx.getBean(EmployeeRepository.class);
        this.addressRepository = ctx.getBean(AddressRepository.class);
        this.productRepository = ctx.getBean(ProductRepository.class);
    }

    //zamowienie w restauracji domyslnie ustawiamy na id = 1L
    public void setClient(Long id) {
        client = clientRepository.getOne(id);
    }

    //utworzenie dla konkretnego klienta
    public void setClient(String firstname, String lastname, String phonenumber) {
        client = clientRepository.findClient(firstname, lastname, phonenumber);
    }

    //tworzymy rezerwacje jako brak rezerwacji
    public void setReservation() {
        if(client.getId() != 1) {
            setClient(1L);
        }
        else {
            reservation = new Reservation(0, 0, client);
        }
    }

    //utworzenie rezerwacji przy kliencie nie pustym
    public void setReservation(int table, int people) throws EmptyClassException {
        if(client != null)
        reservation = new Reservation(table, people, client);
        else throw new EmptyClassException();
    }

    //utworzenie rezerwacji i klienta
    public void setReservation(int table, int people, String firstname, String lastname, String phone) {
        setClient(firstname, lastname, phone);
        reservation = new Reservation(table, people, client);
    }

    public void setProof(PurchaseProof.PurchaseType type) {
        proof = new PurchaseProof();
        proof.setType(type);

    }

    //do wyznaczania pierwszego
    public void setEmployee(Long id) {
        employee = employeeRepository.getOne(id);
    }

    //do naprawy bo juz kurwa nie mam sily
    public void setEmployee(Employee.Type type) {
        //employee = employeeRepository.getEmployeeByType(type);
    }

    public void setAddress(Long id) {
        address = addressRepository.getOne(id);
    }

    //dokoncze kurwa jutro
    public void setAddress(String city, String street, String number) {

    }

    //set order
    public void setOrderType(Order.Type type) {
        order.setType(type);
    }

    //kurwa jebane pobieranie z bazy danych musze zrobic ja jebe
    public void setProducts() {
        //productList.put()
    }

    public void addOrder() throws EmptyClassException {
        OrderRepository orderRepository = ctx.getBean(OrderRepository.class);

        order = new Order();

        if(proof != null || employee != null || address != null || reservation != null) {
            proof.setOrder(order);
            order.setPurchaseProof(proof);
            order.setEmployee(employee);
            order.setDeliveryAddress(address);
            order.setTable(reservation);
        } else throw new EmptyClassException();

        order.setStatus(Order.Status.UTWORZONE);

        order.setClientList(Arrays.asList(client));
        DataManager dataManager = new DataManager(ctx);
        dataManager.loadStorage();

        productList.put(DataManager.getProduct(3), 44);
        productList.put(DataManager.getProduct(5), 15);

        order.setProductList(productList);

        orderRepository.save(order);
    }
}

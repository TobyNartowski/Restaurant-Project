package restaurant.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import restaurant.database.*;
import restaurant.exception.EmptyClassException;
import restaurant.misc.Storage;
import restaurant.model.*;

import java.util.*;

public class AddOrder {
    private ApplicationContext ctx;
    private Client client;
    private ClientRepository clientRepository;
    private Reservation reservation;
    private PurchaseProof proof;
    private Employee employee;
    private EmployeeRepository employeeRepository;
    private Address address;
    private AddressRepository addressRepository;
    private Order order;
    private Order.Type type;
    private List<Product> productList = new LinkedList<>();
    private Map<String, Integer> ingredientList;
    private ProductRepository productRepository;
    private List<String> productNames = new LinkedList<>();

    @Autowired
    public AddOrder(ApplicationContext ctx) {
        this.ctx = ctx;
        this.clientRepository = ctx.getBean(ClientRepository.class);
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
//        client = clientRepository.findClient(firstname, lastname, phonenumber);
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
        this.type = type;
    }

    //kurwa jebane pobieranie z bazy danych musze zrobic ja jebe
    public void setProducts() {
        //productList.put()
    }

    public void addProduct(String productName) {
        //lista bo sie jebie
        productNames.add(productName);
        List<Product> products = productRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getName() == productName) {
                productList.add(products.get(i));
//                for (int j = 0; j < productList.get(productName); j++) {
//                }
            }
        }
    }

    public void addOrder() throws EmptyClassException {
        OrderRepository orderRepository = ctx.getBean(OrderRepository.class);

        order = new Order();

        if(proof != null || employee != null || address != null || reservation != null || productList.size() != 0
            || type != null) {
            proof.setOrder(order);
            order.setPurchaseProof(proof);
            order.setType(type);
            order.setEmployee(employee);
            order.setDeliveryAddress(address);
            order.setTable(reservation);
            order.setProductList(productList);
        } else throw new EmptyClassException();

        order.setStatus(Order.Status.UTWORZONE);

        order.setClientList(Arrays.asList(client));

        orderRepository.save(order);
    }
}

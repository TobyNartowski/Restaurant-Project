/*
package restaurant.trash;

import org.springframework.context.ConfigurableApplicationContext;
import restaurant.database.*;
import restaurant.exception.WrongOrderStateException;
import restaurant.model.*;

import java.util.*;

public class OrderFacade {
    private static ConfigurableApplicationContext ctx;
    private static Address address = null;

    public static void sendOrder(Order order) throws WrongOrderStateException {
        if (order.getProductList().isEmpty() || order.getStatus() != Order.Status.UTWORZONE) {
            throw new WrongOrderStateException();
        } else if (ctx == null) {
            throw new NullPointerException("Application contxt");
        }

        OrderRepository orderRepository = ctx.getBean(OrderRepository.class);
        EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);

        PurchaseProof proof = new PurchaseProof(order, PurchaseProof.PurchaseType.BILL);
        proof.setData(new Date());

        Map<Product, Integer> productMap = new HashMap<>();
        //DODAJEMY DO MAPY WYMAGANE PRODUKTY Z BAZY DANYCH

        Reservation reservation = new Reservation();
        reservation.setDate(new Date());
        reservation.setNumberOfPeople(0);


        if(order.getDeliveryAddress() == null) {
            address = new Address("Kielce", "Resteuracyjna", "2");
        }
        Employee employee = employeeRepository.findFirstByTypeEquals("WAITER");

        order.setPurchaseProof(proof);
        order.setProductList(productMap);
        order.setTable(order.getTable());
        order.setDeliveryAddress(address);
        order.setEmployee(employee);
        System.out.println("Saving");

        orderRepository.save(order);

    }

    public static void setCtx(ConfigurableApplicationContext ctx) {
        OrderFacade.ctx = ctx;
    }

    public static void sendOrder(Order order, String city, String street, String number) throws WrongOrderStateException {
        Address address = new Address(city, street, number);
        order.setDeliveryAddress(address);
        sendOrder(order);
    }

    public void printOrder(Long id) {
        OrderRepository orderRepository = ctx.getBean(OrderRepository.class);
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        PurchaseProofRepository proofRepository = ctx.getBean(PurchaseProofRepository.class);
        AddressRepository addressRepository = ctx.getBean(AddressRepository.class);
        ReservationRepository reservationRepository = ctx.getBean(ReservationRepository.class);

        orderRepository.findById(id);
        //wypisz
        Product product = productRepository.getOne(id);
        //wypisz
        product.getName();
        product.getPrice();
        product.getIngredientList();

        //wypisz
        proofRepository.getOne(id).toString();

        //wypisz
        addressRepository.getOne(id).toString();

        Reservation reservation = reservationRepository.getOne(id);
        if(reservation.getNumberOfPeople() != 0) {
            //wypisz
            reservation.getNumberOfPeople();
            reservation.getTableNumber();
            reservation.getDate();
        }
    }
}
*/
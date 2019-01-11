package restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.database.*;
import restaurant.exception.OrderEmptyFieldException;
import restaurant.model.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);

        saveDummyData(ctx);
        ctx.close();
    }

    private static final Ingredient dough = new Ingredient("Dough", 300);
    private static final Ingredient sauce = new Ingredient("Sauce", 100);
    private static final Ingredient cheese = new Ingredient("Cheese", 200);
    private static final Ingredient ham = new Ingredient("Ham", 300);

    private static final Ingredient orange = new Ingredient("Orange", 100);
    private static final Ingredient water = new Ingredient("Water", 50);

    private static final Product pizza = new Product("Pizza", 1100, Arrays.asList(
            dough, sauce, cheese, ham
    ));

    private static final Product juice = new Product("Orange juice", 300, Arrays.asList(
            orange, water
    ));

    public static void saveDummyData(ConfigurableApplicationContext ctx) {
        AdressRepository adressRepository = ctx.getBean(AdressRepository.class);
        EmployeeRepository employeeRepository = ctx.getBean(EmployeeRepository.class);

        IngredientRepository ingredientRepository = ctx.getBean(IngredientRepository.class);
        ingredientRepository.save(dough);
        ingredientRepository.save(sauce);
        ingredientRepository.save(cheese);
        ingredientRepository.save(ham);
        ingredientRepository.save(orange);
        ingredientRepository.save(water);

        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        productRepository.save(pizza);
        productRepository.save(juice);

        OrderRepository orderRepository = ctx.getBean(OrderRepository.class);

        try {
            OrderBuilder orderBuilder = new OrderBuilder();
            orderBuilder.addProduct(pizza, 1);
            orderBuilder.addProduct(juice, 2);
            Order order = orderBuilder.build();

            orderRepository.save(order);
            Optional<Order> result = orderRepository.findById(1L);
            if (result.isPresent()) {
                System.out.println(order);
            }
        } catch (OrderEmptyFieldException e) {
            e.printStackTrace();
        }

        Address address = new Address("Kielce","Warszawska", "55");
        adressRepository.save(address);
        Employee employee = new Employee("First", "Worker", address, 111222333, Employee.Type.WAITER);
        employeeRepository.save(employee);
        Employee employee2 = new Employee("Second", "Worker", address, 444555666, Employee.Type.COOK);
        employeeRepository.save(employee2);

        StorageRepository storageRepository = ctx.getBean(StorageRepository.class);
        Storage storage = Storage.getInstance();
        storage.loadIngredientsFromDatabase(ingredientRepository);

        storageRepository.save(storage);
        ///storage.initialize(storageRepository);
        storage.printIngredientList();
    }
}
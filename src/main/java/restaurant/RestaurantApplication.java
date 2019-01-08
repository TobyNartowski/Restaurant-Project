package restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.database.AdressRepository;
import restaurant.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        Product pizza = new Product("Pizza", 1000 , Arrays.asList(
                new Ingredient("Dough", 200),
                new Ingredient("Tomato sauce", 50),
                new Ingredient("Cheese", 300),
                new Ingredient("Ham", 300)
        ));

        Product juice = new Product("Orange juice", 200, Arrays.asList(
                new Ingredient("Water", 10),
                new Ingredient("Orange", 150)
        ));

        try {
            Order prototype = Order.getPrototype();
            prototype.addProduct(pizza, 2);
            prototype.addProduct(juice, 1);

            Order order = (Order) prototype.clone();
            order.getProductList().forEach((x, y) -> {
                System.out.println("* " + x.getName() + " " + y);
            });
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Storage storage = Storage.getInstance();

        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);
        AdressRepository repository = ctx.getBean(AdressRepository.class);

        List<Address> adresy = new ArrayList<>();
        adresy.add(new Address("Kielce","Mloda", "57"));
        adresy.add(new Address("Warszawa", "asd", "123"));
        adresy.add(new Address("Gdansk", "Piewsza", "1234"));

        adresy.forEach(repository::save);

        List<Address> addressList = repository.findAll();
        Optional<Address> addresOptional = repository.findById(2L);
        if(addresOptional.isPresent())
        System.out.println(addresOptional);
        addressList.forEach(System.out::println);

    }
}


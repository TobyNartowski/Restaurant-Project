package restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.database.AdressRepository;
import restaurant.database.IngredientRepository;
import restaurant.database.OrderRepository;
import restaurant.database.ProductRepository;
import restaurant.exception.OrderEmptyFieldException;
import restaurant.model.*;

import java.util.*;

@SpringBootApplication
public class RestaurantApplication {

    // TODO: toString

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

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);
        AdressRepository repository = ctx.getBean(AdressRepository.class);


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

        ctx.close();
    }
}
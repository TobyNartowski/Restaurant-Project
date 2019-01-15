package restaurant;

/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.data.DataManager;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.exception.OrderEmptyFieldException;
import restaurant.misc.AddIngredientDecorator;
import restaurant.misc.DeliveryBuilder;
import restaurant.misc.IngredientFlyweight;
import restaurant.model.*;

import java.util.ArrayList;
import java.util.List;

// Extends Application class
@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);

        List<Ingredient> list = new ArrayList<>();
        list.add(IngredientFlyweight.getIngredient("Hot-dog bun"));
        list.add(IngredientFlyweight.getIngredient("Sausage"));

        Product product = new Product("Hot-dog", 700, list);

        product = new AddIngredientDecorator(product, IngredientFlyweight.getIngredient("Tomato sauce"));
        System.out.println("-- Utworzony produkt --\n" + product + "\n");

        // Template method
        try {
            DeliveryBuilder builder = new DeliveryBuilder();
            builder.addProduct(product, 2);
            builder.addDeliveryAddress(new Address("Kielce", "Warszawska", "20"));
            Order order = builder.build();
            System.out.println(order);
        } catch (OrderEmptyFieldException e) {
            e.printStackTrace();
        }

        ctx.close();
    }

    public static void addData(ConfigurableApplicationContext ctx) {
        try {
            DataManager dataManager = new DataManager(ctx);
            dataManager.addDummyData(Client.class);
            dataManager.addDummyData(Product.class);
            dataManager.addDummyData(Employee.class);
            dataManager.loadStorage();
        } catch (ClassIsNotEntityException e) {
            e.printStackTrace();
        }
    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templates/home.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        //primaryStage.show();

    }
*/
}
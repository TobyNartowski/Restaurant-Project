package restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.data.DataManager;
import restaurant.database.IngredientRepository;
import restaurant.database.ProductRepository;
import restaurant.database.StorageRepository;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.model.*;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestaurantApplication extends Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);
//        try {
//            DataManager dataManager = new DataManager(ctx);
//            dataManager.addDummyData(Client.class);
//            dataManager.addDummyData(Product.class);
//            dataManager.addDummyData(Employee.class);
//             dataManager.loadStorage();
//        } catch (ClassIsNotEntityException e) {
//            e.printStackTrace();
//        }


        StorageRepository storageRepository = ctx.getBean(StorageRepository.class);
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        IngredientRepository ingredientRepository = ctx.getBean(IngredientRepository.class);
//        //Storage.getInstance().firstInit(ingredientRepository);
//        DataManager dataManager = new DataManager(ctx);
//        dataManager.loadStorage();

        //launch(args);

//        save ingredients in datrabase
//        Storage.getInstance().saveIngredientsInDatabase(storageRepository, ingredientRepository);

        ctx.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("templates/home.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        //zprimaryStage.show();

    }
}
package restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.data.AddOrder;
import restaurant.data.DataManager;
import restaurant.database.ProductRepository;
import restaurant.database.StorageRepository;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.exception.EmptyClassException;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Storage;
import restaurant.model.*;

import java.util.List;

@SpringBootApplication
public class RestaurantApplication extends Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);
        ContextWrapper.initWrapper(ctx);

        launch(args);
        //always on start LOAD INGREDIENTS !
        StorageRepository storageRepository = ctx.getBean(StorageRepository.class);
        Storage.getInstance().loadIngredientsFromDatabase(storageRepository);

        try {
            AddOrder addOrder = new AddOrder(ctx);
            addOrder.setEmployee(1L);
            addOrder.setClient(1L);
            addOrder.setAddress(1L);
            addOrder.setOrderType(Order.Type.DELIVERY);
            addOrder.setProof(PurchaseProof.PurchaseType.BILL);
            addOrder.setReservation(2, 2);
            addOrder.addProduct("Dracula");
            addOrder.addOrder();
        } catch (EmptyClassException e) {
            e.printStackTrace();
        }
        ctx.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("prism.lcdtext", "false");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        Scene scene = new Scene(root, 480, 640);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toString());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
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
}
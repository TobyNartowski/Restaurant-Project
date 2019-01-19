package restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.data.AddOrder;
import restaurant.data.DataManager;
import restaurant.database.EmployeeRepository;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.model.*;

@SpringBootApplication
public class RestaurantApplication extends Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);
//        launch(args);
        AddOrder order = new AddOrder(ctx);
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
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import restaurant.controller.DraggableWindow;
import restaurant.data.DataManager;
import restaurant.database.IngredientRepository;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Storage;
import restaurant.model.Employee;
import restaurant.model.Ingredient;
import restaurant.model.Product;

@SpringBootApplication
public class RestaurantApplication extends Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RestaurantApplication.class, args);
        ContextWrapper.initWrapper(ctx);
        loadDatabaseWithDummyData(ctx);
        launch(args);
        ctx.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("prism.lcdtext", "false");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        Scene scene = new Scene(root, DraggableWindow.SMALL_WINDOW_WIDTH, DraggableWindow.SMALL_WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toString());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void loadDatabaseWithDummyData(ApplicationContext context) {
        if (!context.getBean(IngredientRepository.class).getAllIngredients().isEmpty()) {
            return;
        }

        DataManager dataManager = new DataManager(context);
        try {
            dataManager.addDummyData(Ingredient.class);
            dataManager.addDummyData(Product.class);
            dataManager.addDummyData(Employee.class);
            dataManager.loadStorage();
            dataManager.generateFinishedOrders();
            Storage.getInstance().saveIngredientsInDatabase();
        } catch (ClassIsNotEntityException e) {
            e.printStackTrace();
        }
    }
}
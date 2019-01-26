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
import restaurant.database.ClientRepository;
import restaurant.exception.ClassIsNotEntityException;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Session;
import restaurant.misc.Storage;
import restaurant.model.Client;
import restaurant.model.Ingredient;
import restaurant.model.Product;

@SpringBootApplication
public class RestaurantApplication extends Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(RestaurantApplication.class, args);
        ContextWrapper.initWrapper(ctx);
        launch(args);
        ctx.close();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("prism.lcdtext", "false");

        // Use only for debug, login as random client to dashboard
        ClientRepository clientRepository = ContextWrapper.getContext().getBean(ClientRepository.class);
        Client client = clientRepository.getClientByLogin("TobyNartowski");
        Session.setSession(client);

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
        Scene scene = new Scene(root, DraggableWindow.LARGE_WINDOW_WIDTH, DraggableWindow.LARGE_WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toString());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        /*
        // Load start window
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        Scene scene = new Scene(root, DraggableWindow.SMALL_WINDOW_WIDTH, DraggableWindow.SMALL_WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toString());
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }

    private void loadDatabaseWithDummyData(ApplicationContext context) {
        DataManager dataManager = new DataManager(context);
        try {
            dataManager.addDummyData(Ingredient.class);
            dataManager.addDummyData(Product.class);
            dataManager.loadStorage();
            Storage.getInstance().saveIngredientsInDatabase();
        } catch (ClassIsNotEntityException e) {
            e.printStackTrace();
        }
    }
}
package restaurant.thread.fx;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoadWindow implements Runnable {

    private Pane pane;
    private String windowTitle;
    private String fxmlDirectory;
    private double width;
    private double height;

    public LoadWindow(Pane pane, String windowTitle, String fxmlDirectory, double width, double height) {
        this.pane = pane;
        this.windowTitle = windowTitle;
        this.fxmlDirectory = fxmlDirectory;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlDirectory)), width, height);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.getScene().getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
                stage.show();
                pane.getScene().getWindow().hide();
            } catch (IOException e) {
                System.err.println("Internal error: " + e.getCause());
            }
        });
    }

}

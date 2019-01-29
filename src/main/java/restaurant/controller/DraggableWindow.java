package restaurant.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import restaurant.misc.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class DraggableWindow implements Initializable {
    private static double x;
    private static double y;

    public static final double LARGE_WINDOW_WIDTH = 1280;
    public static final double LARGE_WINDOW_HEIGHT = 720;
    public static final double SMALL_WINDOW_WIDTH = 480;
    public static final double SMALL_WINDOW_HEIGHT = 640;

    @FXML
    protected Pane pane;

    @FXML
    protected void onExitClick() {
        Session.logOut();
        System.exit(0);
    }

    protected void setDraggingEvents() {
        pane.setOnMousePressed(event -> {
            x = pane.getScene().getWindow().getX() - event.getScreenX();
            y = pane.getScene().getWindow().getY() - event.getScreenY();
        });

        pane.setOnMouseDragged(event -> {
            pane.getScene().getWindow().setX(event.getScreenX() + x);
            pane.getScene().getWindow().setY(event.getScreenY() + y);
        });
    }

    public static void generateAlert(Pane pane, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setX(pane.getScene().getWindow().getX() + (pane.getScene().getWindow().getWidth() / 2.0) - (alert.getDialogPane().getWidth() / 2.0));
        alert.setY(pane.getScene().getWindow().getY() + (pane.getScene().getWindow().getHeight() / 2.0) - (alert.getDialogPane().getHeight() / 2.0) - 72.0);
        alert.getDialogPane().getStylesheets().add(DraggableWindow.class.getResource("/styles/style.css").toString());
        ((Stage) alert.getDialogPane().getScene().getWindow()).initStyle(StageStyle.UNDECORATED);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();
    }
}

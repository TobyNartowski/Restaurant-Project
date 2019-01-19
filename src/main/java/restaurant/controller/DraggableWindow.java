package restaurant.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class DraggableWindow implements Initializable {
    private static double x;
    private static double y;

    @FXML
    private Pane pane;

    protected void generateAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/styles/style.css").toString());
        ((Stage) alert.getDialogPane().getScene().getWindow()).initStyle(StageStyle.UNDECORATED);
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setOnMousePressed(event -> {
            x = pane.getScene().getWindow().getX() - event.getScreenX();
            y = pane.getScene().getWindow().getY() - event.getScreenY();
        });

        pane.setOnMouseDragged(event -> {
            pane.getScene().getWindow().setX(event.getScreenX() + x);
            pane.getScene().getWindow().setY(event.getScreenY() + y);
        });
    }
}

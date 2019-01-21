package restaurant.controller;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import restaurant.thread.Worker;
import restaurant.thread.db.LoginUser;
import restaurant.thread.fx.LoadPane;

public class Start extends DraggableWindow {

    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    @FXML
    private void onLoginClick() {
        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            generateAlert(pane, "Wszystkie pola muszą być uzupełnione!");
        } else {
            Worker.newTask(new LoginUser(pane, loginField.getText(), passwordField.getText()));
        }
    }

    @FXML
    private void onEnter() {
        onLoginClick();
    }

    private void centerStage(Stage stage, double width, double height) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }

    @FXML
    private void onRegisterClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/register.fxml"));
    }

    @FXML
    private void onEmployeeClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/employee.fxml"));
    }
}

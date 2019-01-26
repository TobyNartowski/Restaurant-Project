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

    @FXML
    private void onRegisterClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/register.fxml"));
    }

    @FXML
    private void onEmployeeClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/employee.fxml"));
    }
}

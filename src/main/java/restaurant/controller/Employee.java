package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import restaurant.thread.Worker;
import restaurant.thread.db.LoginEmployee;
import restaurant.thread.fx.LoadPane;

public class Employee extends DraggableWindow {

    @FXML
    private TextField idField;
    @FXML
    private TextField passwordField;

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/start.fxml"));
    }

    @FXML
    private void onLoginClick() {
        if (idField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            generateAlert(pane, "Wszystkie pola muszą być uzupełnione!");
        } else {
            Worker.newTask(new LoginEmployee(pane, idField.getText(), passwordField.getText()));
        }
    }
}

package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import restaurant.thread.Worker;
import restaurant.thread.db.RegisterUser;
import restaurant.thread.fx.LoadPane;

public class Register extends DraggableWindow {

    @FXML
    private Pane pane;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField repeatField;

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/start.fxml"));
    }

    @FXML
    private void onRegisterClick() {
        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty() || repeatField.getText().isEmpty()) {
            generateAlert("Wszystkie pola muszą być uzupełnione!");
        } else {
            if (!passwordField.getText().equals(repeatField.getText())) {
                generateAlert("Hasła nie zgadzają się!");
            } else {
                Worker.newTask(new RegisterUser(pane, loginField.getText(), passwordField.getText()));
            }
        }
    }

    @FXML
    private void onExitClick() {
        System.exit(0);
    }
}

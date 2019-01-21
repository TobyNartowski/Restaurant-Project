package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import restaurant.thread.Worker;
import restaurant.thread.db.RegisterUser;
import restaurant.thread.fx.LoadPane;

public class Register extends DraggableWindow {

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
    private void onEnter() {
        onRegisterClick();
    }

    @FXML
    private void onRegisterClick() {
        if (loginField.getText().isEmpty() || passwordField.getText().isEmpty() || repeatField.getText().isEmpty()) {
            generateAlert(pane, "Wszystkie pola muszą być uzupełnione!");
        } else {
            if (!passwordField.getText().equals(repeatField.getText())) {
                generateAlert(pane, "Hasła nie zgadzają się!");
            } else {
                Worker.newTask(new RegisterUser(pane, loginField.getText(), passwordField.getText()));
            }
        }
    }
}

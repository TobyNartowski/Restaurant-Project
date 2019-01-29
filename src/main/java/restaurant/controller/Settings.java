package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import restaurant.exception.SessionNotSet;
import restaurant.misc.Session;
import restaurant.model.Client;
import restaurant.thread.Worker;
import restaurant.thread.db.UpdateUser;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings extends DraggableWindow {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField numberField;

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/dashboard.fxml"));
    }

    @FXML
    private void onEnter() {
        onChangeClick();
    }

    @FXML
    private void onChangeClick() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                cityField.getText().isEmpty() || streetField.getText().isEmpty() || numberField.getText().isEmpty()) {
            generateAlert(pane, "Wszystkie pola muszą być uzupełnione!");
        } else {
            Worker.newTask(new UpdateUser(pane, firstNameField.getText(), lastNameField.getText(),
                    phoneField.getText(), cityField.getText(), streetField.getText(), numberField.getText()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();
        try {
            Client client = Session.getClient();
            firstNameField.setText(client.getName());
            lastNameField.setText(client.getLastName());
            phoneField.setText(Long.toString(client.getPhoneNumber()));
            cityField.setText(client.getAddress().getCity());
            streetField.setText(client.getAddress().getStreet());
            numberField.setText(client.getAddress().getNumber());
        } catch (SessionNotSet e) {
            throw new IllegalStateException();
        }
    }
}

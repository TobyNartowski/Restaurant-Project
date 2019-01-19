package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import restaurant.misc.Session;
import restaurant.thread.Worker;
import restaurant.thread.db.AddUserDetails;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddClientDetails extends DraggableWindow {

    @FXML
    private Label headerLabel;
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
        Session.logOut();
        Worker.newTask(new LoadPane(pane, "/fxml/register.fxml"));
    }

    @FXML
    private void onRegisterClick() {
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                cityField.getText().isEmpty() || streetField.getText().isEmpty() || numberField.getText().isEmpty()) {
            generateAlert(pane, "Wszystkie pola muszą być uzupełnione!");
        } else {
            Worker.newTask(new AddUserDetails(pane, firstNameField.getText(), lastNameField.getText(), phoneField.getText(),
                    cityField.getText(), streetField.getText(), numberField.getText()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        headerLabel.setText("Witaj " + Session.getClient().getLogin());
    }
}

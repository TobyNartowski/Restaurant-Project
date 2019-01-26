package restaurant.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import restaurant.exception.SessionNotSet;
import restaurant.misc.Builder;
import restaurant.misc.Session;
import restaurant.model.Address;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderChooseDelivery extends DraggableWindow {

    @FXML
    private TextField cityField;

    @FXML
    private TextField streetField;

    @FXML
    private TextField houseNumberField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button nextButton;

    @FXML
    private void onNextClick() {
        if (cityField.getText().isEmpty() || streetField.getText().isEmpty() ||
                houseNumberField.getText().isEmpty() || phoneNumberField.getText().isEmpty()) {
            DraggableWindow.generateAlert(pane, "Wszystkie pola muszą być uzupełnione!");
        } else {
            try {
                Long customPhoneNumber = Long.parseLong(phoneNumberField.getText());
                if (!customPhoneNumber.equals(Session.getClient().getPhoneNumber())) {
                    Builder.getBuilder().addCustomPhoneNumber(customPhoneNumber);
                }

                Builder.getBuilder().addDeliveryAddress(
                        new Address(cityField.getText(), streetField.getText(), houseNumberField.getText()));

                Worker.newTask(new LoadPane(pane, "/fxml/order_choose_payment.fxml"));
            } catch (NumberFormatException e) {
                DraggableWindow.generateAlert(pane, "Niepoprawy numer telefonu!");
            } catch (SessionNotSet ex) {
                throw new IllegalStateException();
            }
        }
    }

    @FXML
    public void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/order_add_products.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();
        nextButton.requestFocus();

        try {
            Address clientAddress = Session.getClient().getAddress();
            if (Builder.getBuilder().getCustomPhoneNumber() != null) {
                phoneNumberField.setText(Long.toString(Builder.getBuilder().getCustomPhoneNumber()));
            } else {
                phoneNumberField.setText(Long.toString(Session.getClient().getPhoneNumber()));
            }

            if (Builder.getBuilder().getDeliveryAddress() != null) {
                Address customAddress = Builder.getBuilder().getDeliveryAddress();
                cityField.setText(customAddress.getCity());
                streetField.setText(customAddress.getStreet());
                houseNumberField.setText(customAddress.getNumber());
            } else {
                cityField.setText(clientAddress.getCity());
                streetField.setText(clientAddress.getStreet());
                houseNumberField.setText(clientAddress.getNumber());
            }
        } catch (SessionNotSet sessionNotSet) {
            throw new IllegalStateException();
        }
    }
}

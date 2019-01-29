package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import restaurant.misc.Builder;
import restaurant.model.Order;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderChoosePayment extends DraggableWindow {

    @FXML
    private Label cashLabel;

    @FXML
    private void onOnlinePaymentClick() {
        Builder.getBuilder().completeOnlinePayment();
        Worker.newTask(new LoadPane(pane, "/fxml/order_add_note.fxml"));

    }

    @FXML
    private void onLocalPaymentClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/order_add_note.fxml"));

    }

    @FXML
    public void onBackClick() {
        if (Builder.getBuilder().getOrderType() == Order.Type.DELIVERY) {
            Worker.newTask(new LoadPane(pane, "/fxml/order_choose_delivery.fxml"));
        } else {
            Worker.newTask(new LoadPane(pane, "/fxml/order_add_products.fxml"));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();
        if (Builder.getBuilder().getOrderType() == Order.Type.DELIVERY) {
            cashLabel.setText("PRZY ODBIORZE");
        } else {
            cashLabel.setText("W RESTAURACJI");
        }
    }
}
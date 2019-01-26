package restaurant.controller;

import javafx.fxml.FXML;
import restaurant.misc.Builder;
import restaurant.model.Order;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

public class OrderChoosePayment extends DraggableWindow {

    @FXML
    public void onBackClick() {
        if (Builder.getBuilder().getOrderType() == Order.Type.DELIVERY) {
            Worker.newTask(new LoadPane(pane, "/fxml/order_choose_delivery.fxml"));
        } else {
            Worker.newTask(new LoadPane(pane, "/fxml/order_add_products.fxml"));
        }
    }
}

package restaurant.controller;

import javafx.fxml.FXML;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

public class OrderChooseDelivery extends DraggableWindow {

    @FXML
    public void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/order_add_products.fxml"));
    }
}

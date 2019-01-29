package restaurant.controller;

import javafx.fxml.FXML;
import restaurant.misc.Builder;
import restaurant.model.Product;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderAddNote extends DraggableWindow {

    @FXML
    public void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/order_choose_payment.fxml"));
    }

    private void showProduct(Product product) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();

        Builder.getBuilder().getProductList().forEach(this::showProduct);
    }
}

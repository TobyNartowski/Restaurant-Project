package restaurant.controller;

import javafx.fxml.FXML;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

public class AddOrder extends DraggableWindow {

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/choose_order_type.fxml"));
    }

    @FXML
    private void onNextClick() {

    }


}

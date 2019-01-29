package restaurant.controller;

import javafx.fxml.FXML;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

public class OrderCreated extends DraggableWindow {

    @FXML
    private void onNextClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/dashboard.fxml"));
    }
}

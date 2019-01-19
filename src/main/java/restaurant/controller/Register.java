package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

public class Register {

    @FXML
    private Pane pane;

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/start.fxml"));
    }

    @FXML
    private void onExitClick() {
        System.exit(0);
    }
}

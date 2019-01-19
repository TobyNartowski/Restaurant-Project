package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Dashboard {

    @FXML
    private Pane pane;

    @FXML
    private void onExitClick() {
        System.exit(0);
    }
}

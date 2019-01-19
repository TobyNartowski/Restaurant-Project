package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Dashboard extends DraggableWindow {

    @FXML
    private Pane pane;

    @FXML
    private void onExitClick() {
        System.exit(0);
    }
}

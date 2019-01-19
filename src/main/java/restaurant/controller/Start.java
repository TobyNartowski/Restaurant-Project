package restaurant.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
public class Start implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void onExitClick() {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import restaurant.misc.Session;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadWindow;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard extends DraggableWindow {

    @FXML
    private Label welcomeText;

    @FXML
    private void logOut() {
        Session.logOut();
        Worker.newTask(new LoadWindow(pane, "Restauracja", "/fxml/start.fxml", 480, 640));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        welcomeText.setText("Welcome " + Session.getClient().getName() + " " + Session.getClient().getLastName() + "!");
    }
}

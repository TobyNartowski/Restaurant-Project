package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import restaurant.exception.SessionNotSet;
import restaurant.misc.Session;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;
import restaurant.thread.fx.LoadWindow;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Dashboard extends DraggableWindow {

    @FXML
    private Label headerText;

    @FXML
    private Label subtitleText;

    @FXML
    private void onAddOrderClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/order_choose_type.fxml"));
    }

    @FXML
    private void onSettingsClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/settings.fxml"));
    }

    @FXML
    private void logOut() {
        Session.logOut();
        Worker.newTask(new LoadWindow(pane, "Restauracja", "/fxml/start.fxml", SMALL_WINDOW_WIDTH, SMALL_WINDOW_HEIGHT));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();
        try {
            headerText.setText("Cześć, " + Session.getClient().getName() + "!");
        } catch (SessionNotSet sessionNotSet) {
            headerText.setText("Cześć!");
        }

        switch (new Random().nextInt(5)) {
            case 0:
            case 1:
                subtitleText.setText("Na co masz dzisiaj ochotę?");
                break;
            case 2:
                subtitleText.setText("Wszystkie nasze dania są robione z najlepszych składników, sprawdź sam!");
                break;
            case 3:
            case 4:
                subtitleText.setText("Co dzisiaj zjesz?");
                break;
        }
    }
}

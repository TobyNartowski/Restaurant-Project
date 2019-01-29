package restaurant.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import restaurant.exception.SessionNotSet;
import restaurant.misc.Session;
import restaurant.model.Order;
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
    private void onHistoryClick() {
        try {
            boolean orderFound = false;

            for (Order order : Session.getClient().getOrderList()) {
                if (order.getStatus() == Order.Status.ZREALIZOWANE) {
                    orderFound = true;
                }
            }

            if (!orderFound) {
                 Platform.runLater(() -> generateAlert(pane, "Brak zamówień!"));
            } else {
                Worker.newTask(new LoadPane(pane, "/fxml/history.fxml"));
            }
        } catch (SessionNotSet sessionNotSet) {
            sessionNotSet.printStackTrace();
        }
    }

    @FXML
    private void currentOrderClick() {
        try {
            boolean orderFound = false;

            for (Order order : Session.getClient().getOrderList()) {
                if (order.getStatus() != Order.Status.ZREALIZOWANE) {
                    orderFound = true;
                }
            }

            if (!orderFound) {
                Platform.runLater(() -> generateAlert(pane, "Brak zamówień!"));
            } else {
                Worker.newTask(new LoadPane(pane, "/fxml/current_order.fxml"));
            }
        } catch (SessionNotSet sessionNotSet) {
            sessionNotSet.printStackTrace();
        }
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

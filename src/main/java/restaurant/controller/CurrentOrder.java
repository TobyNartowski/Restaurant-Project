package restaurant.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import restaurant.exception.SessionNotSet;
import restaurant.misc.Money;
import restaurant.misc.Session;
import restaurant.model.Order;
import restaurant.model.Product;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentOrder extends DraggableWindow {

    @FXML
    private VBox orderWrapper;

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/dashboard.fxml"));
    }

    private void showOrder(Order order) {
        VBox orderContainer = new VBox();
        orderContainer.getStyleClass().add("order-item");
        orderContainer.setSpacing(32.0);

        Label orderHeader = new Label("Zamowienie nr " + order.getId() + " - " + order.getStatus());
        orderHeader.setFont(Font.font(32.0));
        orderHeader.getStyleClass().add("order-product-header");
        orderContainer.getChildren().add(orderHeader);

        showProducts(order, orderContainer);

        orderWrapper.getChildren().add(orderContainer);
    }

    static void showProducts(Order order, VBox orderContainer) {
        for (Product product : order.getProductList()) {
            HBox productContainer = new HBox();
            productContainer.setAlignment(Pos.CENTER_LEFT);
            productContainer.getStyleClass().add("order-item");
            productContainer.setPrefWidth(200.0);
            productContainer.setPrefHeight(36.0);

            Label productName = new Label(product.getName());
            productName.setPrefWidth(382.0);
            HBox.setMargin(productName, new Insets(0, 0, 0, 20));
            productName.getStyleClass().add("order-product-name");

            Label productDescription = new Label(product.getDescription());
            productDescription.setPrefWidth(540.0);
            productDescription.getStyleClass().add("order-product-description");

            Label productPrice = new Label(Money.convertToString(product.getPrice()));
            productPrice.setPrefWidth(120.0);
            productPrice.getStyleClass().add("order-product-price");

            productContainer.getChildren().addAll(productName, productDescription, productPrice);
            orderContainer.getChildren().add(productContainer);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();

        try {
            for (Order order : Session.getClient().getOrderList()) {
                if (order.getStatus() != Order.Status.ZREALIZOWANE) {
                    showOrder(order);
                }
            }
        } catch (SessionNotSet sessionNotSet) {
            throw new IllegalStateException();
        }
    }
}

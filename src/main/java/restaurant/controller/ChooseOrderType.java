package restaurant.controller;

import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import restaurant.misc.Builder;
import restaurant.model.Order;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseOrderType extends DraggableWindow {

    @FXML
    private ImageView deliveryImage;

    @FXML
    private ImageView restaurantImage;

    @FXML
    private StackPane deliveryPane;

    @FXML
    private StackPane restaurantPane;

    @FXML
    private void onDeliveryClick() {
        Builder.getBuilder().newOrder(Order.Type.DELIVERY);
        Worker.newTask(new LoadPane(pane, "/fxml/add_order.fxml"));
    }

    @FXML
    private void onRestaurantClick() {
        Builder.getBuilder().newOrder(Order.Type.RESTAURANT);
        Worker.newTask(new LoadPane(pane, "/fxml/add_order.fxml"));
    }

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/dashboard.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();
        deliveryImage.setImage(new Image(getClass().getResource("/img/delivery.jpg").toString()));
        restaurantImage.setImage(new Image(getClass().getResource("/img/restaurant.jpg").toString()));

        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1);
        grayscale.setBrightness(-0.3);
        deliveryImage.setEffect(grayscale);
        restaurantImage.setEffect(grayscale);

        deliveryPane.setOnMouseEntered(event -> deliveryImage.setEffect(null));
        deliveryPane.setOnMouseExited(event -> deliveryImage.setEffect(grayscale));

        restaurantPane.setOnMouseEntered(event -> restaurantImage.setEffect(null));
        restaurantPane.setOnMouseExited(event -> restaurantImage.setEffect(grayscale));
    }
}

package restaurant.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import restaurant.database.AddressRepository;
import restaurant.database.OrderRepository;
import restaurant.exception.OrderEmptyFieldException;
import restaurant.exception.SessionNotSet;
import restaurant.misc.Builder;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Money;
import restaurant.misc.Session;
import restaurant.model.Address;
import restaurant.model.Order;
import restaurant.model.Product;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class OrderAddNote extends DraggableWindow {

    @FXML
    private VBox productPane;

    @FXML
    private VBox attributePane;

    @FXML
    public void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/order_choose_payment.fxml"));
    }

    @FXML
    private void onNextClick() {
        try {
            Order order = Builder.getBuilder().build();

            if (order.getType() == Order.Type.DELIVERY) {
                AddressRepository addressRepository = ContextWrapper.getContext().getBean(AddressRepository.class);
                Address address = order.getDeliveryAddress();
                Long id;
                if ((id = addressRepository.findId(address.getCity(), address.getStreet(), address.getStreet())) != null) {
                    order.setDeliveryAddress(addressRepository.getOne(id));
                } else {
                    addressRepository.save(address);
                }
            }

            order.setClientList(Arrays.asList(Session.getClient()));
            OrderRepository orderRepository = ContextWrapper.getContext().getBean(OrderRepository.class);
            orderRepository.save(order);
            Session.getClient().addOrder(order);
        } catch (OrderEmptyFieldException e) {
            generateAlert(pane, "Błąd zamówienia!");
            throw new IllegalStateException();
        } catch (SessionNotSet ex) {
            throw new IllegalStateException();
        }
        Worker.newTask(new LoadPane(pane, "/fxml/order_created.fxml"));
    }

    private void showProduct(Product product) {
        HBox productContainer = new HBox();
        productContainer.setAlignment(Pos.CENTER_LEFT);
        productContainer.getStyleClass().add("order-item");
        productContainer.setPrefHeight(48.0);

        Label productName = new Label(product.getName());
        productName.setPrefWidth(420.0);
        HBox.setMargin(productName, new Insets(0, 20, 0, 20));
        productName.getStyleClass().add("order-product-name");

        Label productPrice = new Label(Money.convertToString(product.getPrice()));
        productPrice.setPrefWidth(128.0);
        productPrice.getStyleClass().add("order-product-price");

        productContainer.getChildren().addAll(productName, productPrice);
        productPane.getChildren().add(productContainer);
    }

    private void addAttribute(String key, String value) {
        HBox attributeContainer = new HBox();
        attributeContainer.setAlignment(Pos.CENTER_LEFT);
        attributeContainer.setPrefHeight(48.0);

        Label keyLabel = new Label(key);
        keyLabel.setPrefWidth(347.0);
        HBox.setMargin(keyLabel, new Insets(0, 20, 0, 20));
        keyLabel.getStyleClass().add("order-product-name");

        Label valueLabel = new Label(value);
        valueLabel.setPrefWidth(300.0);
        valueLabel.getStyleClass().add("order-product-price");

        attributeContainer.getChildren().addAll(keyLabel, valueLabel);
        attributePane.getChildren().add(attributeContainer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();

        Builder.getBuilder().getProductList().forEach(this::showProduct);
        addAttribute("Razem", Builder.getBuilder().getOrderTotal());
        addAttribute("Rodzaj zamówienia", Builder.getBuilder().getOrderType() == Order.Type.DELIVERY ? "Na wynos" : "W restauracji");
        addAttribute("Zapłacono", Builder.getBuilder().isPaymentComplete() ? "Tak" : "Nie");
        if (Builder.getBuilder().getOrderType() == Order.Type.DELIVERY) {
            Address address = Builder.getBuilder().getDeliveryAddress();
            addAttribute("Miasto", address.getCity());
            addAttribute("Ulica", address.getStreet());
            addAttribute("Numer", address.getNumber());
        }
    }
}

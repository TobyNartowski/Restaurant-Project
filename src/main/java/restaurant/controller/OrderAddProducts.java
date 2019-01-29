package restaurant.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import restaurant.database.ProductRepository;
import restaurant.misc.Builder;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Money;
import restaurant.model.Order;
import restaurant.model.Product;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderAddProducts extends DraggableWindow {

    private long[] productCounterArray;
    private int emptyCounter;

    @FXML
    private VBox orderContainer;

    @FXML
    private void onBackClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/order_choose_type.fxml"));
    }

    @FXML
    private Label totalLabel;

    @FXML
    private FontAwesomeIconView nextButton;

    @FXML
    private void onNextClick() {
        if (Builder.getBuilder().getOrderType() == Order.Type.DELIVERY) {
            Worker.newTask(new LoadPane(pane, "/fxml/order_choose_delivery.fxml"));
        } else {
            Worker.newTask(new LoadPane(pane, "/fxml/order_choose_payment.fxml"));
        }
    }

    private void updateProductStatus(Label productCount, long productId) {
        productCount.setText(Long.toString(productCounterArray[Math.toIntExact(productId - 1)]));
        totalLabel.setText("TWOJE ZAMÓWIENIE: " + Builder.getBuilder().getOrderTotal());

        if (emptyCounter == 0) {
            nextButton.setDisable(true);
            nextButton.getStyleClass().add("clickable-disabled");
        } else {
            nextButton.setDisable(false);
            nextButton.getStyleClass().remove("clickable-disabled");
        }
    }

    private void showProduct(Product product) {
        HBox productContainer = new HBox();
        productContainer.setAlignment(Pos.CENTER_LEFT);
        productContainer.getStyleClass().add("order-item");
        productContainer.setPrefWidth(200.0);
        productContainer.setPrefHeight(64.0);
        productContainer.setSpacing(24.0);

        Label productName = new Label(product.getName());
        productName.setPrefWidth(312.0);
        HBox.setMargin(productName, new Insets(0, 0, 0, 20));
        productName.getStyleClass().add("order-product-name");

        Label productDescription = new Label(product.getDescription());
        productDescription.setPrefWidth(396.0);
        productDescription.getStyleClass().add("order-product-description");

        Label productPrice = new Label(Money.convertToString(product.getPrice()));
        productPrice.setPrefWidth(100.0);
        productPrice.getStyleClass().add("order-product-price");

        Label productCount = new Label(Long.toString(productCounterArray[Math.toIntExact(product.getId() - 1)]));
        productCount.setPrefWidth(20.0);
        productCount.getStyleClass().add("order-product-count");

        FontAwesomeIconView productPlus = new FontAwesomeIconView(FontAwesomeIcon.PLUS);
        productPlus.setGlyphSize(32.0);
        productPlus.getStyleClass().add("clickable");

        FontAwesomeIconView productMinus = new FontAwesomeIconView(FontAwesomeIcon.MINUS);
        productMinus.setGlyphSize(32.0);
        productMinus.setDisable(true);
        productMinus.getStyleClass().add("clickable");
        productMinus.getStyleClass().add("clickable-disabled");

        long alreadyAddedCount = Builder.getBuilder().getProductList().stream().filter(i -> i.getId().equals(product.getId())).count();
        for (int i = 0; i < alreadyAddedCount; i++) {
            productCounterArray[Math.toIntExact(product.getId() - 1)]++;
            emptyCounter++;
            productMinus.setDisable(false);
            productMinus.getStyleClass().remove("clickable-disabled");
        }
        updateProductStatus(productCount, product.getId());

        productPlus.setOnMouseReleased((MouseEvent) -> {
            Builder.getBuilder().addProduct(product);
            productCounterArray[Math.toIntExact(product.getId() - 1)]++;
            emptyCounter++;
            updateProductStatus(productCount, product.getId());
            productMinus.setDisable(false);
            productMinus.getStyleClass().remove("clickable-disabled");
        });

        productMinus.setOnMouseReleased((MouseEvent) -> {
            Builder.getBuilder().removeProduct(product.getId());
            productCounterArray[Math.toIntExact(product.getId() - 1)]--;
            emptyCounter--;
            updateProductStatus(productCount, product.getId());
            if (productCounterArray[Math.toIntExact(product.getId() - 1)] == 0) {
                productMinus.setDisable(true);
                productMinus.getStyleClass().add("clickable-disabled");
            }
        });

        productContainer.getChildren().addAll(productName, productDescription, productPrice, productCount,
                productPlus, productMinus);
        orderContainer.getChildren().add(productContainer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDraggingEvents();

        ProductRepository productRepository = ContextWrapper.getContext().getBean(ProductRepository.class);
        List<Product> productList = productRepository.findAll();
        productCounterArray = new long[productList.size()];
        emptyCounter = 0;
        productList.forEach(this::showProduct);
    }
}

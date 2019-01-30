package restaurant.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import restaurant.misc.AddIngredientDecorator;
import restaurant.misc.IngredientFlyweight;
import restaurant.misc.Money;
import restaurant.misc.RemoveIngredientDecorator;
import restaurant.model.Ingredient;
import restaurant.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Decorator implements Initializable {

    private static Map<Product, List<Ingredient>> productHistory = new HashMap<>();

    private final static Ingredient[] decorators = {
            IngredientFlyweight.getIngredient("Mustard"),
            IngredientFlyweight.getIngredient("Mayonese"),
            IngredientFlyweight.getIngredient("Cheese"),
            IngredientFlyweight.getIngredient("Rucola")
    };

    static Product selectedProduct;
    private static Product decorator;

    @FXML
    private Pane pane;

    @FXML
    private Label headerLabel;

    @FXML
    private VBox ingredientsContainer;

    @FXML
    private void onExitClick() {
        OrderAddProducts.refreshProduct(selectedProduct);
        selectedProduct = null;
        decorator = null;
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    static void drawDecoratorWindow() {
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(Decorator.class.getResource("/fxml/decorator.fxml")), 720, 480);
            scene.setFill(Color.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.getScene().getStylesheets().add(Decorator.class.getResource("/styles/style.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateLabels(Map.Entry<Ingredient, Integer> entry, Label ingredientCount, Label ingredientPrice) {
        ingredientCount.setText(Integer.toString(entry.getValue()));
        if (entry.getValue() == 0) {
            ingredientPrice.setText("+ " + Money.convertToString(entry.getKey().getPrice()));
        } else {
            ingredientPrice.setText("+ " + Money.convertToString(entry.getKey().getPrice() * entry.getValue()));
        }
    }

    private void addDecorators(Map<Ingredient, Integer> decoratorMap) {
        for (Map.Entry<Ingredient, Integer> entry : decoratorMap.entrySet()) {
            HBox ingredientWrapper = new HBox();
            ingredientWrapper.setAlignment(Pos.CENTER_LEFT);
            ingredientWrapper.getStyleClass().add("order-item");
            ingredientWrapper.setPrefHeight(64.0);
            ingredientWrapper.setSpacing(20.0);

            Label ingredientName = new Label(entry.getKey().getName());
            ingredientName.setPrefWidth(290.0);
            HBox.setMargin(ingredientName, new Insets(0, 0, 0, 10));
            ingredientName.getStyleClass().add("order-product-name");

            Label ingredientPrice = new Label("+ " + Money.convertToString(entry.getKey().getPrice()));
            ingredientPrice.setPrefWidth(120.0);
            ingredientPrice.getStyleClass().add("order-product-price");

            Label ingredientCount = new Label(Integer.toString(entry.getValue()));
            ingredientCount.setPrefWidth(20.0);
            ingredientCount.getStyleClass().add("order-product-count");

            FontAwesomeIconView ingredientPlus = new FontAwesomeIconView(FontAwesomeIcon.PLUS);
            ingredientPlus.setGlyphSize(32.0);
            ingredientPlus.getStyleClass().add("clickable");

            FontAwesomeIconView ingredientMinus = new FontAwesomeIconView(FontAwesomeIcon.MINUS);
            ingredientMinus.setGlyphSize(32.0);
            ingredientMinus.getStyleClass().add("clickable");
            if (entry.getValue() == 0) {
                ingredientMinus.getStyleClass().add("clickable-disabled");
                ingredientMinus.setDisable(true);
            }

            ingredientPlus.setOnMouseReleased((MouseEvent) -> {
                if (productHistory.get(selectedProduct) == null) {
                    productHistory.put(selectedProduct, new LinkedList<>(Arrays.asList(entry.getKey())));
                } else {
                    productHistory.get(selectedProduct).add(entry.getKey());
                }
                decorator = new AddIngredientDecorator(selectedProduct, entry.getKey());
                entry.setValue(entry.getValue() + 1);
                updateLabels(entry, ingredientCount, ingredientPrice);
                ingredientMinus.setDisable(false);
                ingredientMinus.getStyleClass().remove("clickable-disabled");
                if (decorator != null) {
                    selectedProduct.copy(decorator);
                }
            });

            ingredientMinus.setOnMouseReleased((MouseEvent) -> {
                productHistory.get(selectedProduct).remove(entry.getKey());
                decorator = new RemoveIngredientDecorator(selectedProduct, entry.getKey());
                entry.setValue(entry.getValue() - 1);
                updateLabels(entry, ingredientCount, ingredientPrice);
                if (entry.getValue() == 0) {
                    ingredientMinus.setDisable(true);
                    ingredientMinus.getStyleClass().add("clickable-disabled");
                }
                if (decorator != null) {
                    selectedProduct.copy(decorator);
                }
            });

            ingredientWrapper.getChildren().addAll(ingredientName, ingredientPrice, ingredientCount,
                    ingredientPlus, ingredientMinus);
            ingredientsContainer.getChildren().add(ingredientWrapper);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Map<Ingredient, Integer> decoratorMap = new HashMap<>();
        if (productHistory.get(selectedProduct) != null) {
            for (Ingredient entry : decorators) {
                decoratorMap.put(entry, Math.toIntExact(productHistory.get(selectedProduct).stream()
                        .filter(i -> i.equals(entry)).count()));
            }
        } else {
            for (Ingredient entry : decorators) {
                decoratorMap.put(entry, 0);
            }
        }

        headerLabel.setText("Dodaj składniki do " + selectedProduct.getName());
        addDecorators(decoratorMap);
    }
}
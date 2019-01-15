package restaurant.misc;

import restaurant.exception.OrderEmptyFieldException;
import restaurant.model.Order;
import restaurant.model.Product;
import restaurant.model.PurchaseProof;

import java.util.Map;

public abstract class Builder {

    protected Order order = new Order();

    public void addProduct(Product product, int amount) {
        order.addProduct(product, amount);
    }

    public void addProductList(Map<Product, Integer> products) {
        order.setProductList(products);
    }

    public void addOrderNote(String note) {
        order.setNote(note);
    }

    public void addPurchaseProof(PurchaseProof purchaseProof) {
        order.setPurchaseProof(purchaseProof);
    }

    public abstract Order build() throws OrderEmptyFieldException;
}

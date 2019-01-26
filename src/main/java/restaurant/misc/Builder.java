package restaurant.misc;

import restaurant.exception.OrderEmptyFieldException;
import restaurant.model.*;

import java.util.List;
import java.util.Map;

public class Builder {

    private static Builder instance;

    public static Builder getBuilder() {
        if (instance == null) {
            instance = new Builder();
        }
        return instance;
    }

    protected Order order = new Order();

    public void newOrder(Order.Type type) {
        order = new Order();
        order.setType(type);
    }

    public void addProduct(Product product) {
        order.addProduct(product);
    }

    public void removeProduct(Product product) {
        order.removeProduct(product);
    }

    public void addProductList(List<Product> products) {
        order.setProductList(products);
    }

    public List<Product> getProductList() {
        return order.getProductList();
    }

    public void addOrderNote(String note) {
        order.setNote(note);
    }

    public void addPurchaseProof(PurchaseProof purchaseProof) {
        order.setPurchaseProof(purchaseProof);
    }

    public void addDeliveryAddress(Address address) {
        if (order.getType() == Order.Type.RESTAURANT) {
            throw new IllegalStateException("Restaurant order cannot have a delivery address");
        }
        order.setDeliveryAddress(address);

    }

    public void addReservation(Reservation table) {
        order.setTable(table);
    }

    public String getOrderTotal() {
        return Money.convertToString(order.getTotal());
    }

    public Order.Type getOrderType() {
        return order.getType();
    }

    public Order build() throws OrderEmptyFieldException {
        if (order.getProductList().isEmpty()) {
            throw new OrderEmptyFieldException();
        }

        order.setStatus(Order.Status.UTWORZONE);
        return order;
    }
}

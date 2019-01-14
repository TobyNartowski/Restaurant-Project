package restaurant.misc;

import restaurant.exception.OrderEmptyFieldException;
import restaurant.model.Order;
import restaurant.model.Product;
import restaurant.model.Reservation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderBuilder {

    private Order order = new Order();

    public void addProduct(Product product, int amount) {
        order.addProduct(product, amount);
    }

    public void addProductList(Map<Product, Integer> products) {
        order.setProductList(products);
    }

    public void addOrderNote(String note) {
        order.setNote(note);
    }

    public void addTable(Reservation table) {
        order.setTable(table);
    }

    public Order build() throws OrderEmptyFieldException {
        if (order.getProductList().isEmpty()) {
            throw new OrderEmptyFieldException();
        }
        order.setStatus(Order.Status.UTWORZONE);

        return order;
    }
}

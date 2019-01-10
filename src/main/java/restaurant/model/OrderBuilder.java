package restaurant.model;

import restaurant.exception.OrderEmptyFieldException;

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

    public void asignEmployee(Employee employee) {
        order.setEmployee(employee);
    }

    public Order build() throws OrderEmptyFieldException {
        if (order.getProductList().isEmpty()) {
            throw new OrderEmptyFieldException();
        }
        order.setStatus(Order.Status.UTWORZONE);
        // todo order.getStatus().setEmployee(employee);

        return order;
    }
}

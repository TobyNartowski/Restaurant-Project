package restaurant.misc;

import restaurant.exception.WrongOrderStateException;
import restaurant.model.Order;

public class OrderFacade {

    public static void sendOrder(Order order) throws WrongOrderStateException {
        if (order.getProductList().isEmpty() || order.getStatus() != Order.Status.UTWORZONE) {
            throw new WrongOrderStateException();
        }
    }
}

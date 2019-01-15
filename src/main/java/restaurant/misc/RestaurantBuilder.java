package restaurant.misc;

import restaurant.exception.OrderEmptyFieldException;
import restaurant.model.Order;
import restaurant.model.Reservation;

public class RestaurantBuilder extends Builder {

    public void addReservation(Reservation table) {
        order.setTable(table);
    }

    @Override
    public Order build() throws OrderEmptyFieldException {
        if (order.getProductList().isEmpty()) {
            throw new OrderEmptyFieldException();
        }
        order.setStatus(Order.Status.UTWORZONE);
        order.setType(Order.Type.RESTAURANT);
        return order;
    }
}

package restaurant.misc;

import restaurant.exception.OrderEmptyFieldException;
import restaurant.model.Address;
import restaurant.model.Order;

public class DeliveryBuilder extends Builder {

    public void addDeliveryAddress(Address address) {
        order.setDeliveryAddress(address);
    }

    @Override
    public Order build() throws OrderEmptyFieldException {
        if (order.getProductList().isEmpty()) {
            throw new OrderEmptyFieldException();
        }
        order.setStatus(Order.Status.UTWORZONE);
        order.setType(Order.Type.DELIVERY);
        return order;
    }
}

package restaurant.misc;

import restaurant.model.Order;

public class MediatorClient extends MediatorPerson {

    public MediatorClient(Mediator mediator, Order.Status status) {
        super(mediator);
        this.status = status;
    }

    @Override
    public Order.Status updateStatus() {
        return status;
    }
}

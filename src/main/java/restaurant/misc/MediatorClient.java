package restaurant.misc;

import restaurant.model.Order;

public class MediatorClient extends MediatorPerson {

    private Order.Status status;

    public MediatorClient(Mediator mediator, Order.Status status) {
        super(mediator);
        this.status = status;
    }

    @Override
    public void updateStatus() {

    }
}

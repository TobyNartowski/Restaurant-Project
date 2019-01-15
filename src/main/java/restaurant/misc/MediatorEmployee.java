package restaurant.misc;

import restaurant.model.Order;

public class MediatorEmployee extends MediatorPerson {

    private Order.Status status;

    public MediatorEmployee(Mediator mediator, Order.Status status) {
        super(mediator);
        this.status = status;
    }

    @Override
    public void updateStatus() {

    }
}

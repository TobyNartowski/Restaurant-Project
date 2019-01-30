package restaurant.misc;

import restaurant.model.Order;

public abstract class MediatorPerson {

    protected Order.Status status;

    protected Mediator mediator;

    public MediatorPerson(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract Order.Status updateStatus();
}

package restaurant.misc;

public abstract class MediatorPerson {

    protected Mediator mediator;

    public MediatorPerson(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void updateStatus();
}

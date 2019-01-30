package restaurant.misc;

import restaurant.model.Order;

import java.util.HashSet;

public class Mediator {
    
    private HashSet<MediatorPerson> persons = new HashSet<>();

    public void register(MediatorPerson person) {
        persons.add(person);
    }

    public void createStatus() {
        for (MediatorPerson receiver : persons) {
            receiver.updateStatus();
        }
    }
}

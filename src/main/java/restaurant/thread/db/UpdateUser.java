package restaurant.thread.db;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import restaurant.controller.DraggableWindow;
import restaurant.database.AddressRepository;
import restaurant.database.ClientRepository;
import restaurant.exception.SessionNotSet;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Session;
import restaurant.model.Address;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

import java.math.BigInteger;

public class UpdateUser implements Runnable {

    private Pane pane;
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String street;
    private String number;

    public UpdateUser(Pane pane, String firstName, String lastName, String phone, String city, String street, String number) {
        this.pane = pane;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    @Override
    public void run() {
        ClientRepository clientRepository = ContextWrapper.getContext().getBean(ClientRepository.class);
        AddressRepository addressRepository = ContextWrapper.getContext().getBean(AddressRepository.class);

        try {
            Long addressId;
            Address clientAddress = Session.getClient().getAddress();
            Address address = new Address(city, street, number);

            if (clientAddress.getCity().equals(address.getCity()) && clientAddress.getStreet().equals(address.getStreet())
                    && clientAddress.getNumber().equals(address.getNumber())) {
                Worker.newTask(new LoadPane(pane, "/fxml/dashboard.fxml"));
                return;
            }

            if ((addressId = addressRepository.findId(city, street, number)) == null) {
                addressRepository.save(address);
               addressId = address.getId();
            }
            try {
                clientRepository.updateClient(lastName, firstName, Long.parseLong(phone),
                        BigInteger.valueOf(addressId), Session.getClient().getId());
            } catch (Exception e) {} finally {
                Session.setSession(clientRepository.getOne(Session.getClient().getId()));
            }
            Worker.newTask(new LoadPane(pane, "/fxml/dashboard.fxml"));
        } catch (SessionNotSet sessionNotSet) {
            throw new IllegalStateException();
        } catch (NumberFormatException e) {
            Platform.runLater(() -> DraggableWindow.generateAlert(pane, "Niepoprawy numer telefonu!"));
        }
    }
}

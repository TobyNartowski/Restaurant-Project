package restaurant.thread.db;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import restaurant.controller.DraggableWindow;
import restaurant.database.AddressRepository;
import restaurant.database.ClientRepository;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Session;
import restaurant.model.Address;
import restaurant.model.Client;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;
import restaurant.thread.fx.LoadWindow;

public class AddUserDetails implements Runnable {

    private Pane pane;
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String street;
    private String number;

    public AddUserDetails(Pane pane, String firstName, String lastName, String phone, String city, String street, String number) {
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
        Client client = Session.getClient();
        try {
            client.setPhoneNumber(Long.parseLong(phone));
        } catch (NumberFormatException e) {
            Platform.runLater(() -> DraggableWindow.generateAlert(pane, "Niepoprawy numer telefonu!"));
            return;
        }
        client.setName(firstName);
        client.setLastName(lastName);

        Address address = new Address(city, street, number);
        addressRepository.save(address);

        client.setAddress(address);
        clientRepository.save(client);
        Worker.newTask(new LoadWindow(pane, "Restauracja", "/fxml/dashboard.fxml", 1280, 720));
    }
}

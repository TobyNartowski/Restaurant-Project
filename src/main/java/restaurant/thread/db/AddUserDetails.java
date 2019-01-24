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
import restaurant.model.Client;
import restaurant.thread.Worker;
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

        try {
            Client client = Session.getClient();
            client.setPhoneNumber(Long.parseLong(phone));
            client.setName(firstName);
            client.setLastName(lastName);

            Address address = new Address(city, street, number);
            addressRepository.save(address);

            client.setAddress(address);
            clientRepository.save(client);
            Worker.newTask(new LoadWindow(pane, "Restauracja", "/fxml/dashboard.fxml",
                    DraggableWindow.LARGE_WINDOW_WIDTH, DraggableWindow.LARGE_WINDOW_HEIGHT));
        } catch (NumberFormatException e) {
            Platform.runLater(() -> DraggableWindow.generateAlert(pane, "Niepoprawy numer telefonu!"));
        } catch (SessionNotSet ex) {
            Worker.newTask(new LoadWindow(pane, "Restauracja", "/fxml/start.fxml",
                    DraggableWindow.SMALL_WINDOW_WIDTH, DraggableWindow.SMALL_WINDOW_HEIGHT));
            ex.printStackTrace();
        }
    }
}

package restaurant.thread.db;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import restaurant.controller.DraggableWindow;
import restaurant.database.ClientRepository;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Session;
import restaurant.model.Client;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;

public class RegisterUser implements Runnable {

    private Pane pane;
    private String login;
    private String password;

    public RegisterUser(Pane pane, String login, String password) {
        this.pane = pane;
        this.login = login;
        this.password = password;
    }

    @Override
    public void run() {
        ClientRepository clientRepository = ContextWrapper.getContext().getBean(ClientRepository.class);
        if (!clientRepository.exists(login)) {
            Client client = new Client(login, password);
            Session.setSession(client);
            Worker.newTask(new LoadPane(pane, "/fxml/add_client_details.fxml"));
        } else {
            Platform.runLater(() -> DraggableWindow.generateAlert(pane, "Użytkownik o podanym loginie już istnieje!"));
        }
    }
}

package restaurant.thread.db;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import restaurant.controller.DraggableWindow;
import restaurant.database.ClientRepository;
import restaurant.misc.ContextWrapper;
import restaurant.misc.Password;
import restaurant.misc.Session;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadWindow;

public class LoginUser implements Runnable {

    private Pane pane;
    private String login;
    private String password;

    public LoginUser(Pane pane, String login, String password) {
        this.pane = pane;
        this.login = login;
        this.password = password;
    }

    @Override
    public void run() {
        ClientRepository clientRepository = ContextWrapper.getContext().getBean(ClientRepository.class);
        if (clientRepository.auth(login, Password.hash(password))) {
            Session.setSession(clientRepository.getClientByLogin(login));
            Worker.newTask(new LoadWindow(pane, "Restauracja",
                    "/fxml/dashboard.fxml", DraggableWindow.LARGE_WINDOW_WIDTH, DraggableWindow.LARGE_WINDOW_HEIGHT));
        } else {
            Platform.runLater(() -> DraggableWindow.generateAlert(pane, "Użytkownik o podanej kombinacji nie istnieje!"));
        }
    }
}

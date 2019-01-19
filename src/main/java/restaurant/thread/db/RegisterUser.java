package restaurant.thread.db;

import javafx.scene.layout.Pane;

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
        System.out.println("RegisterUser()");
        // TODO: register here
    }
}

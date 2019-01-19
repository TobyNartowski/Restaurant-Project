package restaurant.thread.db;

import javafx.scene.layout.Pane;

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
        System.out.println("LoginUser()");
        // TODO: login here
        //Worker.newTask(new LoadWindow(pane, "Restauracja", "/fxml/dashboard.fxml", 1280, 720));
    }
}

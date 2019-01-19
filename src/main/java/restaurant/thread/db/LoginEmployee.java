package restaurant.thread.db;

import javafx.scene.layout.Pane;

public class LoginEmployee implements Runnable {

    private Pane pane;
    private String id;
    private String password;

    public LoginEmployee(Pane pane, String id, String password) {
        this.pane = pane;
        this.id = id;
        this.password = password;
    }

    @Override
    public void run() {
        System.out.println("LoginEmployee()");
        // TODO: login employee here
    }
}

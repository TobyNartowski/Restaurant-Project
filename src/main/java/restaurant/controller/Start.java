package restaurant.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import org.hibernate.jdbc.Work;
import restaurant.thread.Worker;
import restaurant.thread.fx.LoadPane;
import restaurant.thread.fx.LoadWindow;

import java.net.URL;
import java.util.ResourceBundle;
public class Start implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private void onExitClick() {
        System.exit(0);
    }

    @FXML
    private void onLoginClick() {
        // TODO: Login logic here
        Worker.newTask(new LoadWindow(pane, "Restauracja", "/fxml/dashboard.fxml", 1280, 720));
    }

    @FXML
    private void onRegisterClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/register.fxml"));
    }

    @FXML
    private void onEmployeeClick() {
        Worker.newTask(new LoadPane(pane, "/fxml/employee.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

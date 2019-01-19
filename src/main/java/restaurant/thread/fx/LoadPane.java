package restaurant.thread.fx;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoadPane implements Runnable {

    private static boolean alreadyLoading = false;
    private Pane pane;
    private String fxmlDirectory;

    public LoadPane(Pane pane, String fxmlDirectory) {
        this.pane = pane;
        this.fxmlDirectory = fxmlDirectory;
    }

    @Override
    public void run() {
        if (!alreadyLoading) {
            alreadyLoading = true;

            try {
                Pane newPane = FXMLLoader.load(getClass().getResource(fxmlDirectory));
                Platform.runLater(() -> pane.getChildren().setAll(newPane));
            } catch (IOException e) {
                e.printStackTrace();
            }

           alreadyLoading = false;
        }
    }
}

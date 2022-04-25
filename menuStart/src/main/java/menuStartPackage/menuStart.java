package menuStartPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class menuStart extends Application {

    public static musicPlayer musicPlayerInstance = new musicPlayer();
    Parent root;
    public void initializeMusic() {
        musicPlayerInstance.start();
    }

    @Override
    public void start(Stage stage) throws IOException {
        initializeMusic();
        root = FXMLLoader.load(getClass().getResource("FXMLControllers/menuStart.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
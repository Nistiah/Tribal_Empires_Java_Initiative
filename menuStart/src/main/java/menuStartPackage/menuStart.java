package menuStartPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class menuStart extends Application {

    static Parent root;
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        menuStartController music = new menuStartController();
        music.initializeMusic();
        root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
package menuStartPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import menuStartPackage.FXMLControllers.MenuStartController;
import menuStartPackage.player.Player;

import java.io.IOException;

import static menuStartPackage.FXMLControllers.MainBoardController.playerList;

//import static menuStartPackage.FXMLControllers.MainBoardController.playerList;


public class StartUp extends Application {

    public static MusicPlayer musicPlayerInstance = new MusicPlayer();
    Parent root;
    public void initializeMusic() {
        musicPlayerInstance.start();
    }
    public Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        initializeMusic();
        root = FXMLLoader.load(getClass().getResource("FXMLControllers/menuStart.fxml"));
        scene = new Scene(root);
        stage.getIcons().add(new Image("/icon.png"));
        scene.setOnKeyPressed(MenuStartController::space);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
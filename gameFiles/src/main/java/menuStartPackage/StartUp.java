package menuStartPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import menuStartPackage.FXMLControllers.MainBoardController;
import menuStartPackage.FXMLControllers.MapBuilderController;
import menuStartPackage.FXMLControllers.MenuStartController;
import menuStartPackage.player.Player;

import java.io.IOException;
import java.nio.MappedByteBuffer;

import static menuStartPackage.FXMLControllers.MainBoardController.playerList;

//import static menuStartPackage.FXMLControllers.MainBoardController.playerList;


public class StartUp extends Application {

    public static MusicPlayer musicPlayerInstance = new MusicPlayer();
    Parent root;
    public void initializeMusic() {
        musicPlayerInstance.start();
    }

    @Override
    public void start(Stage stage) throws IOException {
        initializeMusic();
        root = FXMLLoader.load(getClass().getResource("FXMLControllers/menuStart.fxml"));
        Player player1 = new Player("jajco1");
        Player player2 = new Player("jajco2");
        Player player3 = new Player("jajco3");
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        Scene scene = new Scene(root);

        scene.setOnKeyPressed(MainBoardController::zoom);

        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
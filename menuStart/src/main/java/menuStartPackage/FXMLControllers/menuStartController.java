package menuStartPackage.FXMLControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import static menuStartPackage.menuStart.musicPlayerInstance;

public class menuStartController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void startNewGame(ActionEvent event) {

        try {
            root = FXMLLoader.load(getClass().getResource("mainBoard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        musicPlayerInstance.menu = false;
        musicPlayerInstance.stopMusic();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    void settings(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    void credits(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("credits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    void quit(ActionEvent event) {
        musicPlayerInstance.killthread = true;
        musicPlayerInstance.stopMusic();     //for interupt within
        Platform.exit();
    }
}
package menuStartPackage.FXMLControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

import static menuStartPackage.startUp.musicPlayerInstance;


public class settingsController {

    @FXML
    private Button volumeUPButton;

    @FXML
    private Button volumeDOWNbutton;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void backToMainMenu(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML
    void volumeDOWN(ActionEvent event) {
        musicPlayerInstance.setVolume(-0.1);
        volumeDOWNbutton.setText("Decrease music volume " +(int)(100*musicPlayerInstance.volume)+"%");
        volumeUPButton.setText("Increase music volume " +(int)(100*musicPlayerInstance.volume)+"%");
    }

    @FXML
    void volumeUP(ActionEvent event) {
        musicPlayerInstance.setVolume(0.1);
        volumeDOWNbutton.setText("Decrease music volume " +(int)(100*musicPlayerInstance.volume)+"%");
        volumeUPButton.setText("Increase music volume " +(int)(100*musicPlayerInstance.volume)+"%");
    }
}

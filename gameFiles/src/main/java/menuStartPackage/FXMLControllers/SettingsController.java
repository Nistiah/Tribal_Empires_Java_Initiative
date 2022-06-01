package menuStartPackage.FXMLControllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Slider;
import javafx.stage.Stage;

import static menuStartPackage.StartUp.musicPlayerInstance;

public class SettingsController implements Initializable {
    @FXML
    private Slider volumeSlider;
    private Stage  stage;
    private Scene  scene;
    private Parent root;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        volumeSlider.setOnMouseDragged(event -> sliderVolumeChange());
        volumeSlider.setOnDragDone(event -> sliderVolumeChange());
        volumeSlider.setOnMouseDragReleased(event -> sliderVolumeChange());

    }

    private void sliderVolumeChange() {
        musicPlayerInstance.setVolumeAbsolute(volumeSlider.getValue());
        volumeSlider.setSnapToTicks(true);
        System.out.println(volumeSlider.getValue());
    }

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
    void mapBuilder(ActionEvent event) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mapBuilder.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        musicPlayerInstance.menu = false;
        musicPlayerInstance.stopMusic();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyPressed(MapBuilderController::zoom);
    }
}

package menuStartPackage.FXMLControllers;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.application.Platform;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.stage.Stage;

import static menuStartPackage.StartUp.musicPlayerInstance;

public class MenuStartController implements Initializable {
    static String        clipName             = "Antares_Ascendance_Presents.mp4";
    static boolean       initializedIntroFlag = false;
    private static Stage stage;
    static Media         media;
    static MediaPlayer   mediaPlayer;
    static File          file;
    @FXML
    private MediaView    intro = new MediaView();
    private Parent       root;
    @FXML
    private BorderPane   settingsBoard;

    private void addStatusListener(MediaPlayer mp) {
        ReadOnlyObjectProperty<MediaPlayer.Status> statusProperty = mp.statusProperty();

        statusProperty.addListener((v, o, n) -> playerStatusChanged(v, o, n));
    }

    @FXML
    void credits(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("credits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if (initializedIntroFlag) {
            intro.setDisable(true);
            intro.setVisible(false);

            return;
        }

        file        = new File(clipName);
        media       = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        intro.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
        addStatusListener(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
                                        @Override
                                        public void run() {
                                            mediaPlayer.stop();
                                        }
                                    });
    }

    private void playerStatusChanged(ObservableValue<? extends MediaPlayer.Status> observable,
                                     MediaPlayer.Status oldValue, MediaPlayer.Status newValue) {
        if ((oldValue == MediaPlayer.Status.PLAYING) && (newValue == MediaPlayer.Status.STOPPED)) {
            initializedIntroFlag = true;
            intro.setDisable(true);
            intro.setVisible(false);
        }
    }

    @FXML
    void quit(ActionEvent event) {
        musicPlayerInstance.stopMusic();    // for interupt within
        Platform.exit();
    }

    @FXML
    void settings(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    public static void space(KeyEvent event) {
        switch (event.getCode()) {
        case S :
            initializedIntroFlag = true;
            mediaPlayer.stop();

            break;
        }
    }

    @FXML
    void startNewGame(ActionEvent event) {
        if (!initializedIntroFlag) {
            return;
        }

        try {
            root = FXMLLoader.load(getClass().getResource("mainBoard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        musicPlayerInstance.menu = false;
        musicPlayerInstance.stopMusic();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);

    }
}

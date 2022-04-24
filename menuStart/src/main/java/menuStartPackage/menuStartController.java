package menuStartPackage;

import hexagons.src.main.java.com.prettybyte.hexagons.Hexagon;
import hexagons.src.main.java.com.prettybyte.hexagons.HexagonMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.IOException;


public class menuStartController {
    //Hexagonal
    @FXML
    private GridPane gridPane;
    @FXML public AnchorPane anchorBoard;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Color colorPick=Color.WHITE;
    static musicPlayer musicPlayerInstance = new musicPlayer();
    public void initializeMusic(){
       musicPlayerInstance.start();
    }
    @FXML
    public ColorPicker colorPicker = new ColorPicker();
    @FXML
    void onHelloHexClick(MouseEvent event) {
        Polygon temp = (Polygon) event.getPickResult().getIntersectedNode();
        temp.setFill(colorPick);
    }
    @FXML
    void but4(ActionEvent event) {
        colorPick=colorPicker.getValue();
    }
    @FXML
    void credits(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("credits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    @FXML
    void backToMainMenu(ActionEvent event) {

        try {
            root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    @FXML
    void backToMainMenuFromBoard(ActionEvent event) {
        musicPlayerInstance.exit=true;
        musicPlayerInstance.menu=false;
        musicPlayerInstance.stopMusic();
        try {
            root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    @FXML
    void quit(ActionEvent event) {
        musicPlayerInstance.killthread=true;
        musicPlayerInstance.stopMusic();     //for interupt within
        Platform.exit();
    }
    @FXML
    void settings(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.setFullScreen(true);
        stage.show();
    }
    @FXML
    void startNewGame(ActionEvent event) {
        musicPlayerInstance.menu=false;
        musicPlayerInstance.stopMusic();
        try {
            root = FXMLLoader.load(getClass().getResource("mainBoard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    @FXML
    private TextField textField;

    @FXML
    void addHex(ActionEvent event1) {
        HexagonMap map = new HexagonMap(20);
        //map.setRenderCoordinates(true);
        int nibyzero=1, niby30 = 47;
        for (int i = 1; i < 36; i++) {
            if(i%2==0) {
                nibyzero--;
                niby30--;
            }
            for (int j = nibyzero; j < niby30; j++) {
//                if(i==1&&j==46)continue;
//                if(i==35&&j==28)continue;
                if(i%2==1&&j==niby30-1)continue;

                Hexagon temphex = new Hexagon(j,i);
                temphex.setOnMouseClicked(MouseEvent ->{
                    textField.setText(temphex.getQ() +":"+temphex.getR());
                    temphex.setFill(colorPick);
                });
                temphex.setOnMouseMoved(MouseEvent ->{
                    textField.setText(temphex.getQ() +":"+temphex.getR());
                    temphex.setFill(colorPick);
                });
                if(i%2==0) {
                    temphex.setFill(Color.PINK);
                }
                if(j%2==0) {
                    temphex.setFill(Color.YELLOW);
                }
                if(i%2==0&&j%2==0) {
                    temphex.setFill(Color.GOLD);
                }
                map.addHexagon(temphex);
            }

            System.out.println(nibyzero);
        }
        Group tempgrup = new Group();
        map.render(tempgrup);
        anchorBoard.getChildren().add(tempgrup);
    }
    @FXML
    private Button volumeUPButton;

    @FXML
    private Button volumeDOWNbutton;

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
package menuStartPackage.FXMLControllers;

import hexagons.src.main.java.com.prettybyte.hexagons.Hexagon;
import hexagons.src.main.java.com.prettybyte.hexagons.HexagonMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import menuStartPackage.Prowincje.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Vector;

import static menuStartPackage.StartUp.musicPlayerInstance;


//progamowanie reaktywne - zmiana zmiennej -> zdarzenie

//ownerId 0 -nikt


public class MapBuilderController {


    private Parent root;
    @FXML
    public Button generateHexagonMap;
    @FXML
    public Button buyButton;
    @FXML
    private TextField current;
    @FXML
    public AnchorPane anchorBoard;
    @FXML
    private TextField textField;
    @FXML
    public ColorPicker colorPicker = new ColorPicker();

    private int playerId = 0;

    private HexagonMap map;

    private Province province;
    private Image image = null;
    PrintWriter writer;

    @FXML
    private void saveMap(){
        try {
            writer = new PrintWriter("map_1.txt");
        } catch (FileNotFoundException e) {
            System.out.println("brak pliku");
        }

        int jSetter = 1, jLimiter = 60;
        for (int i = 1; i < 60; i++) {
            if (i % 2 == 0) {jSetter--; jLimiter--;}
            for (int j=jSetter; j < jLimiter; j++) {
                if (i % 2 == 1 && j == jLimiter - 1) continue;
                //System.out.print(i+" "+j+" "+map.getHexagon(j,i).getProvince().getType()+"\n");
                writer.print(j+" "+i+" "+map.getHexagon(j, i).getProvince().getType()+"\n");

            }
        }
        writer.close();
    }




    @FXML
    void addHex(ActionEvent event1) {
        map = new HexagonMap(40);
        map.setRenderCoordinates(true);
        int jSetter = 1, jLimiter = 60;
        for (int i = 1; i < 60; i++) {
            if (i % 2 == 0) {jSetter--; jLimiter--;}
            for (int j=jSetter; j < jLimiter; j++) {
                if (i % 2 == 1 && j == jLimiter - 1) continue;
               // System.out.println(i+" "+j);

                Hexagon temphex = new Hexagon(j, i);
                temphex.setFill(Color.WHITE);
                Province temp = new Province();
                temp.setCoordinates(j, i);
                temphex.setProvince(temp);

                temphex.setOnMouseClicked(MouseEvent -> {
                    temphex.setProvince(province);
                    try {
                        image = new Image(getClass().getResource("province.tutajpath").toURI().toString());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    ImagePattern imgPat = new ImagePattern(image);
                    temphex.setFill(imgPat);

                });
                temphex.setOnMouseMoved(MouseEvent -> {
                    current.setText(temphex.getQ()+":"+temphex.getR()+" "+"owner:"+playerId+" "+temphex.getProvince().getType());

                });


                map.addHexagon(temphex);
            }
        }
        Group tempgrup = new Group();
        map.render(tempgrup);
        anchorBoard.getChildren().add(tempgrup);
        generateHexagonMap.setVisible(false);
        scrollPane.pannableProperty().set(true);
    }




    @FXML
    void backToMainMenuFromBoard(ActionEvent event) {
        musicPlayerInstance.exit = true;
        musicPlayerInstance.menu = false;
        musicPlayerInstance.stopMusic();
        try {
            root = FXMLLoader.load(getClass().getResource("exitStats.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }


    @FXML
    private ScrollPane scrollPane;

    @FXML
    void CityClick(ActionEvent event) {
        province = new City();
    }

    @FXML
    void CoastClick(ActionEvent event) {
        province = new Coast();

    }

    @FXML
    void DesertFlatClick(ActionEvent event) {
        province = new DesertFlat();
    }

    @FXML
    void DesertWyzClick(ActionEvent event) {
        province = new DesertWyzyny();
    }

    @FXML
    void ForestFlatClick(ActionEvent event) {
        province = new ForestFlat();
    }

    @FXML
    void ForestWyzClick(ActionEvent event) {
        province = new ForestWyzyny();
    }

    @FXML
    void MountainsClick(ActionEvent event) {
        province = new Mountains();
    }

    @FXML
    void RiversideClick(ActionEvent event) {
        province = new RiversideArea();
    }

    @FXML
    void TrawaFlatClick(ActionEvent event) {
        province = new TrawaFlat();
    }

    @FXML
    void TrawaWyzClick(ActionEvent event) {
        province = new TrawaWyzyny();
    }


    @FXML
    void player0(ActionEvent event) {
        playerId=0;
    }

    @FXML
    void player1(ActionEvent event) {
        playerId=1;
    }

    @FXML
    void player2(ActionEvent event) {
        playerId=2;
    }

    @FXML
    void player3(ActionEvent event) {
        playerId=3;
    }




}

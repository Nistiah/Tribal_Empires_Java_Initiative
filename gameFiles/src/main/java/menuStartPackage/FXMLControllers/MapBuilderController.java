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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Scanner;
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
    Scanner scanner;

    @FXML
    private void saveMap(){
        try {
            writer = new PrintWriter("map_1.txt");
        } catch (FileNotFoundException e) {
            System.out.println("brak pliku");
        }

        int jSetter = 1, jLimiter = 30;
        for (int i = 1; i < 30; i++) {
            if (i % 2 == 0) {jSetter--; jLimiter--;}
            for (int j=jSetter; j < jLimiter; j++) {
                if (i % 2 == 1 && j == jLimiter - 1) continue;
                //System.out.print(i+" "+j+" "+map.getHexagon(j,i).getProvince().getType()+"\n");
                writer.print(j+" "+i+" "+map.getHexagon(j,i).getProvince().ownerId+" "+map.getHexagon(j, i).getProvince().getType()+"\n");

            }
        }
        writer.close();
    }




    @FXML
    void addHex(ActionEvent event1) {
        map = new HexagonMap(40);
        map.setRenderCoordinates(false);
        int jSetter = 1, jLimiter = 30;
        for (int i = 1; i <30; i++) {
            if (i % 2 == 0) {jSetter--; jLimiter--;}
            for (int j=jSetter; j < jLimiter; j++) {
                if (i % 2 == 1 && j == jLimiter - 1) continue;
               // System.out.println(i+" "+j);

                Hexagon temphex = new Hexagon(j, i);
                temphex.setFill(Color.WHITE);
                Province temp = new TrawaFlat();
                temp.setCoordinates(j, i);
                temphex.setProvince(temp);
                temphex.setOnMouseClicked(MouseEvent -> {
                    temphex.setProvince(province);
                    try {
                        image = new Image(getClass().getResource(province.iconPath()).toURI().toString());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                    ImagePattern imgPat = new ImagePattern(image);
                    temphex.getProvince().ownerId=playerId;
                    temphex.setFill(imgPat);
                    temphex.borderColor(color);


                });
                temphex.setOnMouseMoved(MouseEvent -> {
                    current.setText(temphex.getQ()+":"+temphex.getR()+" "+"owner:"+temphex.getProvince().ownerId+" "+temphex.getProvince().getType());

                });


                map.addHexagon(temphex);
            }
        }
        Group tempgrup = new Group();
        map.render(tempgrup);
        anchorBoard.getChildren().add(tempgrup);
        //generateHexagonMap.setVisible(false);
        scrollPane.pannableProperty().set(true);

    }

    private Province provinceBuilder(String name){
        switch (name){
            case "City":
                return new City();
            case "Coast":
                return  new Coast();
            case "DesertFlat":
                return  new DesertFlat();
            case "DesertWyzyny":
                return new DesertWyzyny();
            case "ForestFlat":
                return  new ForestFlat();
            case "ForestWyzyny":
                return  new ForestWyzyny();
            case "Mountains":
                return new Mountains();
            case "RiversideArea":
                return  new RiversideArea();
            case "Sea":
                return  new Sea();
            case "TrawaFlat":
                return  new TrawaFlat();
            case "TrawaWyzyny":
                return  new TrawaWyzyny();
            default:
                return  new TrawaFlat();
        }
    }

    @FXML
    void load(ActionEvent event) {
        map = new HexagonMap(40);
        map.setRenderCoordinates(false);
        File file = new File("map_1.txt");

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i,j,owner;
        String tempProvince;

        while(scanner.hasNext()){

            j=scanner.nextInt();
            i=scanner.nextInt();
            owner=scanner.nextInt();
            tempProvince=scanner.next();
//            System.out.println(j+" "+i+" "+owner);
            Hexagon temphex = new Hexagon(j, i);
            temphex.setFill(Color.WHITE);
            Province temp = provinceBuilder(tempProvince);
            temp.setType(tempProvince);
            temp.ownerId=owner;
            temp.setCoordinates(j, i);
            temphex.setProvince(temp);
            try {
                image = new Image(getClass().getResource(temp.iconPath()).toURI().toString());
            } catch (URISyntaxException e) {
                System.out.println("pattern null");
            }
            ImagePattern imgPat2 = new ImagePattern(image);
            temphex.setFill(imgPat2);
            switch (owner) {
                case 0:
                    playerId = 0;
                    color = Color.BLACK;
                    break;
                case 1:
                    playerId = 1;
                    color = Color.AQUAMARINE;
                    break;
                case 2:
                    playerId = 2;
                    color = Color.YELLOW;
                    break;
                case 3:
                    playerId = 3;
                    color = Color.RED;
                    break;
            }


            temphex.getProvince().ownerId=playerId;
            temphex.setStrokeWidth(3);
            temphex.borderColor(color);


            temphex.setOnMouseClicked(MouseEvent -> {
                temphex.setProvince(province);

                try {
                    image = new Image(getClass().getResource(province.iconPath()).toURI().toString());
                } catch (URISyntaxException e) {
                    System.out.println("dawddaw");
                }

                ImagePattern imgPat = new ImagePattern(image);
                temphex.getProvince().ownerId=playerId;   //co tu sie odpierdala, jak klikam inne, zmieniaja sie dla poprzednich
                temphex.setFill(imgPat);
                temphex.borderColor(color);


            });
            temphex.setOnMouseMoved(MouseEvent -> {
                try {
                    current.setText(temphex.getQ() + ":" + temphex.getR() + " " + "owner:" + temphex.getProvince().ownerId + " " + temphex.getProvince().getType());
                }catch (NullPointerException e){
                    System.out.println("jajco" + temphex.getR()+" "+temphex.getQ());
                }

            });
            map.addHexagon(temphex);

        }

        Group tempgrup = new Group();
        map.setPadding(10,10);
        map.render(tempgrup);
        anchorBoard.getChildren().add(tempgrup);
        generateHexagonMap.setVisible(true);
        scrollPane.pannableProperty().set(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


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
    void SeaClick(ActionEvent event) {
        province = new Sea();
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
    Color color = Color.BLACK;

    @FXML
    void player0(ActionEvent event) {
        playerId=0;
        color = Color.BLACK;
    }

    @FXML
    void player1(ActionEvent event) {
        playerId=1;
        color = Color.AQUAMARINE;
    }

    @FXML
    void player2(ActionEvent event) {
        playerId=2;
        color = Color.YELLOW;
    }

    @FXML
    void player3(ActionEvent event) {
        playerId=3;
        color = Color.RED;
    }
    boolean coordinates = false;





}

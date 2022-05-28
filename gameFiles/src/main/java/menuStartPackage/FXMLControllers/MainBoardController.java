package menuStartPackage.FXMLControllers;

import hexagons.src.main.java.com.prettybyte.hexagons.Hexagon;
import hexagons.src.main.java.com.prettybyte.hexagons.HexagonMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import menuStartPackage.Prowincje.*;
import menuStartPackage.player.Player;
import menuStartPackage.player.TourCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Vector;

import static menuStartPackage.StartUp.musicPlayerInstance;

//import static menuStartPackage.StartUp.musicPlayerInstance;

//progamowanie reaktywne - zmiana zmiennej -> zdarzenie

//ownerId 0 -nikt


public class MainBoardController {

    private Stage stage;
    private Parent root;
    @FXML
    public Button generateHexagonMap;
    @FXML
    public Button buyButton;

    @FXML
    public AnchorPane anchorBoard;
    @FXML
    private TextField textField;
    @FXML
    public ColorPicker colorPicker = new ColorPicker();
    private Color color;

    int playerId;

    @FXML
    private TextField turnField = new TextField("dupa");

    @FXML
    private TextField fractionField = new TextField("duadudawu");

    @FXML
    private TextField goldField = new TextField("goldField");

    @FXML
    private TextField beliefField = new TextField("beliefField");

    @FXML
    private TextField bronzeField = new TextField("bronzeField");

    @FXML
    private TextField dyesField = new TextField("dyesField");

    @FXML
    private TextField recoursesField = new TextField("recoursesField");

    @FXML
    private TextField horsesField = new TextField("horsesField");

    @FXML
    private TextField ironField = new TextField("ironField");

    @FXML
    public AnchorPane provinceLowerPanel;

    @FXML
    public AnchorPane provinceUpperPanel;

    @FXML
    void nextPlayerButton(ActionEvent event){
        turnField.setText("Tura: "+tourCounter.getTour());
        playerId++;
        if(playerId==playerList.size()+1){
            playerId=1;
            tourCounter.incrementTour();
        }
        currentPlayer=playerList.get(playerId-1);
        fractionField.setText("Gracz:"+currentPlayer.name +" "+playerId);
        goldField.setText("" + currentPlayer.getGold());
        beliefField.setText("" + currentPlayer.getFaith());
        bronzeField.setText("" + currentPlayer.getBronze());
        recoursesField.setText("" + currentPlayer.getBuildingResources());
        horsesField.setText("" + currentPlayer.getHorses());
        ironField.setText("" + currentPlayer.getIron());
        dyesField.setText("" + currentPlayer.getDyes());
        if(buyInitialised){
            buyClicked();
        }
    }

    private boolean buyingMode=false;
    private boolean buyInitialised = false;
    private int ownerId =1;


    static public Vector<Player> playerList = new Vector<>();

    Player currentPlayer;

    TourCounter tourCounter = new TourCounter();

    private static HexagonMap map;

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

    Group tempgrup;

    @FXML
    void scrollup(ActionEvent event) {


        System.out.println("gowno");
//        anchorBoard.translateZProperty().set(anchorBoard.getTranslateZ()*0.5);
        map.sizeDown();
//        tempgrup.translateZProperty().set(tempgrup.getTranslateZ()*0.9);

//        tempgrup.translateYProperty().set(tempgrup.getTranslateY()*0.9);
//        tempgrup.translateXProperty().set(tempgrup.getTranslateX()*0.9);
    }

    Image image;
    @FXML
    void addhex(ActionEvent event) {
        map = new HexagonMap(40);
        map.setRenderCoordinates(false);
        File file = new File("map_1.txt");

        Scanner scanner = null;
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
            Hexagon temphex = new Hexagon(j, i);
            temphex.setFill(Color.WHITE);
            Province temp = provinceBuilder(tempProvince);
            temp.setType(tempProvince);
            temp.ownerId=owner;
            temp.setCoordinates(j, i);
            temphex.setProvince(temp);
            temphex.setStrokeWidth(3);
            try {
                image = new Image(getClass().getResource(temp.iconPath()).toURI().toString());
            } catch (URISyntaxException e) {
                System.out.println("pattern null");
            }
            ImagePattern imgPat2 = new ImagePattern(image);
            temphex.setFill(imgPat2);

            switch (temp.ownerId) {
                case 0:
                    color = Color.BLACK;
                    temphex.borderColor(color);
                    break;
                case 1:
                    color = Color.AQUAMARINE;
                    temphex.borderColor(color);
                    break;
                case 2:
                    color = Color.YELLOW;
                    temphex.borderColor(color);
                    break;
                case 3:
                    color = Color.RED;
                    temphex.borderColor(color);
                    break;
            }




            temphex.setOnMouseClicked(MouseEvent -> {
//                    visibility=!visibility;
//                    textField.setVisible(visibility);
                final int[] przesuniecie = {10,60};
                provinceLowerPanel.getChildren().clear();
                provinceUpperPanel.getChildren().clear();
                //provinceType.setText("Typ prowincji: " + temphex.getProvince().getType());
                Text provinceType2 = new Text("Typ prowincji: " + temphex.getProvince().getType());
                provinceType2.setTranslateY(30);
                provinceType2.setTranslateX(30);
                provinceType2.setFill(Paint.valueOf("GREEN"));
                provinceType2.setFont(Font.font("Berlin Sans FB",24));
                provinceUpperPanel.getChildren().add(provinceType2);
                temphex.getProvince().possibleBuildings.forEach(building -> {
                    Button b3 = new Button(building);
                    b3.setTranslateY(przesuniecie[0]);
                    przesuniecie[0] += 60;
                    provinceLowerPanel.getChildren().add(b3);
                });
                temphex.getProvince().resources.forEach(resource -> {
                    Text resourceText = new Text(resource);
                    resourceText.setTranslateY(przesuniecie[1]);
                    resourceText.setFont(Font.font("Berlin Sans FB",20));
                    przesuniecie[1] += 20;
                    provinceUpperPanel.getChildren().add(resourceText);
                });
//                    textField.setText(temphex.getQ() + ":" + temphex.getR());
                if(buyingMode) {
                    buyField(temphex);
                }
            });
            temphex.setOnMouseMoved(MouseEvent -> {
                textField.setText(temphex.getQ() + ":" + temphex.getR() + "  i:"+temphex.getProvince().i+" j:"+temphex.getProvince().j + "owner: "+ temphex.getProvince().ownerId);
            });
            map.addHexagon(temphex);

        }
        tempgrup = new Group();
        map.render(tempgrup);
        anchorBoard.getChildren().add(tempgrup);
        generateHexagonMap.setVisible(false);
        scrollPane.pannableProperty().set(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int jSetter = 1, jLimiter = 30;
        for (int i2 = 1; i2 <30; i2++) {
            if (i2 % 2 == 0) {jSetter--; jLimiter--;}
            for (int j2=jSetter; j2 < jLimiter; j2++) {
                if (i2 % 2 == 1 && j2 == jLimiter - 1) continue;

                switch (map.getHexagon(j2,i2).getProvince().ownerId) {
                    case 0:
                        color = Color.BLACK;
                        map.getHexagon(j2,i2).borderColor(color);
                        break;
                    case 1:
                        color = Color.AQUAMARINE;
                        map.getHexagon(j2,i2).borderColor(color);
                        break;
                    case 2:
                        color = Color.YELLOW;
                        map.getHexagon(j2,i2).borderColor(color);
                        break;
                    case 3:
                        color = Color.RED;
                        map.getHexagon(j2,i2).borderColor(color);
                        break;
                }

            }
        }


    }

    public static void zoom(KeyEvent event){
        switch(event.getCode()){
            case SUBTRACT:
                map.sizeDown();
                break;
            case ADD:
                map.sizeUp();
                break;

        }
    }



    //panel do uruchomienia kupowania hexów dla miasta
    @FXML
    void buyClicked() {
        buyingMode=!buyingMode;
        if(buyingMode) {
            buyButton.setText("WYJDZ "+buyingMode);
        }
        else {
            buyInitialised=false;
            cityCoordinatesLock = false;
            buyButton.setText("KUP POLE "+buyingMode);
            int jSetter = 1, jLimiter = 30;
            for (int i = 1; i <30; i++) {
                if (i % 2 == 0) {jSetter--; jLimiter--;}
                for (int j=jSetter; j < jLimiter; j++) {
                    if (i % 2 == 1 && j == jLimiter - 1) continue;
                    if(map.getHexagon(j,i).getBorderColor()==Color.PINK){
                        map.getHexagon(j,i).borderColor(Color.BLACK);
                    }
                }
            }

        }
    }





    boolean cityCoordinatesLock = false;
    private int initialisedI;
    private int initialisedJ;


    //podswietla na rozowo te heksy ktore sa mozliwe do kupna, daje te grafike cos tam na te kupione
    private void buyField(Hexagon tempname) {

//        System.out.println("i:" + tempname.getProvince().i + " j:" + tempname.getProvince().j);
//        System.out.print("q:" + tempname.getQ() + " r: " + tempname.getR() + " buyinit" + buyInitialised);
        int i = tempname.getQ();
        int j = tempname.getR();
//        System.out.println(" " + map.getHexagon(i, j).getProvince().ownerId);


        if (!buyInitialised && tempname.getProvince().ownerId != playerId) {
            buyClicked();
            return;
        }
        if (!cityCoordinatesLock) {
            cityCoordinatesLock = true;
            buyInitialised = true;
            initialisedI = i;
            initialisedJ = j;

            for (int tempI = initialisedI - 3; tempI < initialisedI + 4; tempI++) {
                for (int tempJ = initialisedJ - 3; tempJ < initialisedJ + 4; tempJ++) {
//                    System.out.println("tq:" + tempI + "  tr:" + tempJ + " ownId:" + ownerId);
                    if (map.getHexagon(tempI, tempJ - 1).getProvince().ownerId == ownerId || map.getHexagon(tempI, tempJ + 1).getProvince().ownerId == ownerId
                            || map.getHexagon(tempI + 1, tempJ - 1).getProvince().ownerId == ownerId || map.getHexagon(tempI - 1, tempJ + 1).getProvince().ownerId == ownerId
                            || map.getHexagon(tempI + 1, tempJ).getProvince().ownerId == ownerId || map.getHexagon(tempI - 1, tempJ).getProvince().ownerId == ownerId) {

                        if (map.getHexagon(tempI, tempJ).getProvince().ownerId != ownerId) {
                            map.getHexagon(tempI, tempJ).borderColor(Color.PINK);
//                            System.out.println("SUKCES");
                        }
                    }
                }
            }
            return;
        } else { //click poza zasiegiem
            if ((Math.abs(initialisedI - i) > 3 ||
                    Math.abs(initialisedJ - j) > 3) ||
                    (i < initialisedI && j < initialisedJ &&
                            ((Math.abs(initialisedI - i) + Math.abs(initialisedJ - j)) == 4 || (Math.abs(initialisedI - i) + Math.abs(initialisedJ - j)) == 5)
                            || (i > initialisedI && j > initialisedJ &&
                            ((Math.abs(initialisedI - i) + Math.abs(initialisedJ - j)) == 4 || (Math.abs(initialisedI - i) + Math.abs(initialisedJ - j)) == 5)
                            || (j == initialisedJ && Math.abs(initialisedI - i) == 3)
                            || (i == initialisedI && Math.abs(initialisedJ - j) == 3)
                            || (Math.abs(initialisedI - i) + Math.abs(initialisedJ - j)) == 6))) {
                return;
            }
        }

        map.getHexagon(i, j).getProvince().ownerId = ownerId;
        switch (map.getHexagon(i, j).getProvince().ownerId) {
            case 0:
                color = Color.BLACK;
                break;
            case 1:
                color = Color.AQUAMARINE;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 3:
                color = Color.RED;
                break;
        }



        map.getHexagon(i, j).borderColor(color);

        for (int tempI = initialisedI - 3; tempI < initialisedI + 4; tempI++) {
            for (int tempJ = initialisedJ - 3; tempJ < initialisedJ + 4; tempJ++) {
                //System.out.println("tq:" + tq + "  tr:" + tr);
                if (map.getHexagon(tempI, tempJ - 1).getProvince().ownerId == ownerId || map.getHexagon(tempI, tempJ + 1).getProvince().ownerId == ownerId
                        || map.getHexagon(tempI + 1, tempJ - 1).getProvince().ownerId == ownerId || map.getHexagon(tempI - 1, tempJ + 1).getProvince().ownerId == ownerId
                        || map.getHexagon(tempI + 1, tempJ).getProvince().ownerId == ownerId || map.getHexagon(tempI - 1, tempJ).getProvince().ownerId == ownerId) {
                    if (map.getHexagon(tempI, tempJ).getProvince().ownerId==0) {
                        if ((Math.abs(initialisedI - tempI) > 3 || //patern mozliwych do kupna
                                Math.abs(initialisedJ - tempJ) > 3) ||
                                (tempI < initialisedI && tempJ < initialisedJ &&
                                        (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4
                                        || (tempI > initialisedI && tempJ > initialisedJ &&
                                        (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4
                                        || (tempJ == initialisedJ && Math.abs(initialisedI - tempI) == 3)
                                        || (tempI == initialisedI && Math.abs(initialisedJ - tempJ) == 3)
                                        || (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 6))) {
                            continue;
                        }
                        if (map.getHexagon(tempI, tempJ).getProvince().ownerId != ownerId)
                            map.getHexagon(tempI, tempJ).borderColor(Color.PINK);
//                        System.out.println("SUKCES");
                    }
                }
            }
        }
        Image image = null;
        try {
            image = new Image(getClass().getResource("city.png").toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ImagePattern imgPat = new ImagePattern(image);
        map.getHexagon(i, j).setFill(imgPat);

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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
    @FXML
    private ScrollPane scrollPane;


}
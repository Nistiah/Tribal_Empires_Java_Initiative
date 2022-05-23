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

import menuStartPackage.Prowincje.Province;
import menuStartPackage.player.Player;
import menuStartPackage.player.TourCounter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Vector;

import static menuStartPackage.StartUp.musicPlayerInstance;

//import static menuStartPackage.StartUp.musicPlayerInstance;

//progamowanie reaktywne - zmiana zmiennej -> zdarzenie

//ownerId 0 -nikt


public class MainBoardController {

    private Color colorPick = Color.WHITE;
    private Stage stage;
    private Scene scene;
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

    @FXML
    void but4(ActionEvent event) {
        colorPick = colorPicker.getValue();
    }

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
    void nextPlayerButton(ActionEvent event){
        turnField.setText("Tura: "+tourCounter.getTour());
        playerId++;
        if(playerId==playerList.size()){
            playerId=0;
            tourCounter.incrementTour();
        }
        currentPlayer=playerList.get(playerId);
        fractionField.setText("Gracz:"+currentPlayer.getClass().getName());
        goldField.setText("" + currentPlayer.getGold());
        beliefField.setText("" + currentPlayer.getFaith());
        bronzeField.setText("" + currentPlayer.getBronze());
        recoursesField.setText("" + currentPlayer.getBuildingResources());
        horsesField.setText("" + currentPlayer.getHorses());
        ironField.setText("" + currentPlayer.getIron());
        dyesField.setText("" + currentPlayer.getDyes());
    }

    private boolean buyingMode=false;
    private boolean buyInitialised = false;
    private int ownerId =1;


    static public Vector<Player> playerList = new Vector<>();

    Player currentPlayer;

    TourCounter tourCounter = new TourCounter();

    private HexagonMap map;

    boolean visibility;
    //funkcja generujaca siatke
    @FXML
    void addHex(ActionEvent event1) {
        map = new HexagonMap(40);
        map.setRenderCoordinates(true);
        int jSetter = 1, jLimiter = 60;
        for (int i = 1; i < 60; i++) {
            if (i % 2 == 0) {jSetter--; jLimiter--;}
            for (int j=jSetter; j < jLimiter; j++) {

                if (i % 2 == 1 && j == jLimiter - 1) continue;

                Hexagon temphex = new Hexagon(j, i);
                temphex.setFill(Color.WHITE);
                Province temp = new Province();
                temp.setCoordinates(j, i);
                temphex.setProvince(temp);

                if(j==5&&i==10){
                    temphex.setFill(Color.GOLD);
                    temphex.getProvince().ownerId=1;
                    System.out.println(temphex.getProvince().i+" "+temphex.getProvince().j + " " + temphex.getProvince().ownerId);
                    System.out.println(temphex.getQ()+" "+temphex.getR() + " " + temphex.getProvince().ownerId);

                }
                //temphex.getProvince().ownerId=7;


                temphex.setOnMouseClicked(MouseEvent -> {
//                    visibility=!visibility;
//                    textField.setVisible(visibility);

//                    textField.setText(temphex.getQ() + ":" + temphex.getR());
                    if(buyingMode) {
                        buyField(temphex);
                    }
                });
                temphex.setOnMouseMoved(MouseEvent -> {
                    textField.setText(temphex.getQ() + ":" + temphex.getR() + "  i:"+temphex.getProvince().i+" j:"+temphex.getProvince().j + "owner: "+ temphex.getProvince().ownerId);
                });
//                if (i % 2 == 0) {
//                    temphex.setFill(Color.PINK);
//                }
//                if (j % 2 == 0) {
//                    temphex.setFill(Color.YELLOW);
//                }
//                if (i % 2 == 0 && j % 2 == 0) {
//                    temphex.setFill(Color.GOLD);
//                }


                //temphex.setProvince(new City());
                //temphex.getProvince().setCoordinates(temphex.getQ(), temphex.getR());

                map.addHexagon(temphex);
            }

            //System.out.println(jSetter);
        }
        Group tempgrup = new Group();
        map.render(tempgrup);
        anchorBoard.getChildren().add(tempgrup);
        generateHexagonMap.setVisible(false);
        scrollPane.pannableProperty().set(true);
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
        if (!buyInitialised && tempname.getProvince().ownerId != ownerId) {
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
                            map.getHexagon(tempI, tempJ).setFill(Color.PINK);
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
                            map.getHexagon(tempI, tempJ).setBackgroundColor(Color.PINK);
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
package menuStartPackage.FXMLControllers;

import hexagons.src.main.java.com.prettybyte.hexagons.Hexagon;
import hexagons.src.main.java.com.prettybyte.hexagons.HexagonMap;
import hexagons.src.main.java.com.prettybyte.hexagons.NoHexagonFoundException;
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
import menuStartPackage.Prowincje.City;
import menuStartPackage.player.Player;
import menuStartPackage.player.TourCounter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Vector;

import static menuStartPackage.StartUp.musicPlayerInstance;

//progamowanie reaktywne - zmiana zmiennej -> zdarzenie


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
    void nextPlayerButton(ActionEvent event){
        turnField.setText("Tura: "+tourCounter.getTour());
        playerId++;
        if(playerId==playerList.size()){
            playerId=0;
            tourCounter.incrementTour();
        }
        currentPlayer=playerList.get(playerId);
        fractionField.setText("Gracz:"+currentPlayer.getClass().getName());

    }




    static public Vector<Player> playerList = new Vector<>();



    Player currentPlayer;

    TourCounter tourCounter = new TourCounter();

    private HexagonMap map;

    boolean visibility;
    //funkcja generujaca siatke
    @FXML
    void addHex(ActionEvent event1) {
        map = new HexagonMap(50);
        map.setRenderCoordinates(true);
        int nibyzero = 31, niby30 = 90;
        for (int i = 1; i < 60; i++) {
            if (i % 2 == 0) {
                nibyzero--;
                niby30--;
            }
            for (int j = nibyzero; j < niby30; j++) {
                if (i % 2 == 1 && j == niby30 - 1) continue;

                Hexagon temphex = new Hexagon(j-30, i);
                temphex.setFill(Color.WHITE);
                province temp = new province();
                mechanics[j][i]=temp;


                if(j==35&&i==10){
                    temphex.setFill(Color.GOLD);
                    mechanics[j][i].owner=ownerid;

                }



                temphex.setOnMouseClicked(MouseEvent -> {
                    visibility=!visibility;
                    textField.setVisible(visibility);

//                    textField.setText(temphex.getQ() + ":" + temphex.getR());
                    if(buyingMode) {
                        buyField(temphex);
                    }
                });
//                temphex.setOnMouseMoved(MouseEvent -> {
//                    textField.setText(temphex.getQ() + ":" + temphex.getR());
//                    temphex.setFill(colorPick);
//                });
//                if (i % 2 == 0) {
//                    temphex.setFill(Color.PINK);
//                }
//                if (j % 2 == 0) {
//                    temphex.setFill(Color.YELLOW);
//                }
//                if (i % 2 == 0 && j % 2 == 0) {
//                    temphex.setFill(Color.GOLD);
//                }


                temphex.setProvince(new City());
                temphex.getProvince().setCoordinates(temphex.getQ(), temphex.getR());

                map.addHexagon(temphex);
            }

            System.out.println(nibyzero);
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
            buyButton.setText("WYJDZ");
        }
        else {
            buyInitialised=false;
            buyButton.setText("KUP POLE");
        }
    }


    private boolean buyingMode=false;
    private boolean buyInitialised = false;
    private int ownerid =1;




    private class province {

        int owner = 0;



    }
    private province[][] mechanics = new province[90][90];
    private int initialisedQ;
    private int initialisedR;


    //podswietla na rozowo te heksy ktore sa mozliwe do kupna, daje te grafike cos tam na te kupione
    private void buyField(Hexagon tempname) {
        System.out.println("i:" + tempname.getProvince().i +" j: "+ tempname.getProvince().j);
        System.out.println("q:" + tempname.getQ() +" r: "+ tempname.getR());

        int q = tempname.getQ() + 30;
        int r = tempname.getR();
        if(!buyInitialised &&mechanics[q][r].owner!=ownerid){
            buyClicked();
            return;
        }
        //System.out.println("q:" + q + "  r:" + r);
        if (!buyInitialised) {
            for (int tq = q - 3; tq < q + 4; tq++) {
                for (int tr = r - 3; tr < r + 4; tr++) {
                    //System.out.println("tq:" + tq + "  tr:" + tr);
                    if (
                            mechanics[tq][tr - 1].owner == ownerid ||
                                    mechanics[tq][tr + 1].owner == ownerid ||
                                    mechanics[tq + 1][tr - 1].owner == ownerid ||
                                    mechanics[tq - 1][tr + 1].owner == ownerid ||
                                    mechanics[tq + 1][tr].owner == ownerid ||
                                    mechanics[tq - 1][tr].owner == ownerid) {
                        try {
                            map.getHexagon(tq - 30, tr).setFill(Color.PINK);
                            System.out.println("SUKCES");
                        } catch (NoHexagonFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            buyInitialised = true;
            initialisedQ = tempname.getQ();
            initialisedR = tempname.getR();
        } else {
            if ((Math.abs(initialisedQ - tempname.getQ()) > 3 ||
                    Math.abs(initialisedR - tempname.getR()) > 3) ||
                    (tempname.getQ() < initialisedQ && tempname.getR() < initialisedR &&
                            (Math.abs(initialisedQ - tempname.getQ()) + Math.abs(initialisedR - tempname.getR())) == 4
                            || (tempname.getQ() > initialisedQ && tempname.getR() > initialisedR &&
                            (Math.abs(initialisedQ - tempname.getQ()) + Math.abs(initialisedR - tempname.getR())) == 4
                            || (tempname.getR() == initialisedR && Math.abs(initialisedQ - tempname.getQ()) == 3)
                            || (tempname.getQ() == initialisedQ && Math.abs(initialisedR - tempname.getR()) == 3)
                            || (Math.abs(initialisedQ - tempname.getQ()) + Math.abs(initialisedR - tempname.getR())) == 6))) {
                return;
            }
            if(mechanics[q][r].owner==ownerid){return;}

            Image image = null;
            try {
                image = new Image(getClass().getResource("city.png").toURI().toString());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            ImagePattern imgPat = new ImagePattern(image);
            try {
                map.getHexagon(q - 30, r).setFill(imgPat);

            } catch (NoHexagonFoundException e) {
                e.printStackTrace();
            }
            mechanics[q][r].owner=ownerid;
            for (int tq = initialisedQ + 30 - 3; tq < initialisedQ + 30 + 4; tq++) {
                for (int tr = initialisedR - 3; tr < initialisedR + 4; tr++) {
                    //System.out.println("tq:" + tq + "  tr:" + tr);
                    if (
                            mechanics[tq][tr - 1].owner == ownerid ||
                                    mechanics[tq][tr + 1].owner == ownerid ||
                                    mechanics[tq + 1][tr - 1].owner == ownerid ||
                                    mechanics[tq - 1][tr + 1].owner == ownerid ||
                                    mechanics[tq + 1][tr].owner == ownerid ||
                                    mechanics[tq - 1][tr].owner == ownerid) {
                        try {
                            if (map.getHexagon(tq - 30, tr).getFill() != Color.GOLD) {

                                if (((Math.abs(initialisedQ+30 - tq) > 3 ||
                                        Math.abs(initialisedR - tr) > 3) ||
                                        (tq < initialisedQ+30 && tr < initialisedR &&
                                                (Math.abs(initialisedQ +30- tq) + Math.abs(initialisedR - tr)) == 4
                                                || (tq > initialisedQ +30&& tr > initialisedR &&
                                                (Math.abs(initialisedQ +30- tq) + Math.abs(initialisedR - tr)) == 4
                                                || (tr == initialisedR && Math.abs(initialisedQ +30- tq) == 3)
                                                || (tq == initialisedQ+30 && Math.abs(initialisedR - tr) == 3)
                                                || (Math.abs(initialisedQ +30- tq) + Math.abs(initialisedR - tr)) == 6)
                                        ))
                                ) {continue;}
                                    if(mechanics[tq][tr].owner==0)
                                        map.getHexagon(tq - 30, tr).setBackgroundColor(Color.PINK);
                                    System.out.println("SUKCES");
                            }
                        } catch (NoHexagonFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
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

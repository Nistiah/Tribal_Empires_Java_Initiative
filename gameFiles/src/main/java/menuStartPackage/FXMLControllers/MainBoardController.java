package menuStartPackage.FXMLControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import javafx.stage.Stage;

import static javafx.scene.paint.Color.rgb;

import hexagons.src.main.java.com.prettybyte.hexagons.Hexagon;
import hexagons.src.main.java.com.prettybyte.hexagons.HexagonMap;

import menuStartPackage.Prowincje.*;

import menuStartPackage.player.Player;
import menuStartPackage.player.TourCounter;

import static hexagons.src.main.java.com.prettybyte.hexagons.Hexagon.hexBorderWidth;
import static hexagons.src.main.java.com.prettybyte.hexagons.HexagonMap.nullHex;

import static menuStartPackage.FXMLControllers.StatsController.addPlayer;

import static menuStartPackage.FXMLControllers.StatsController.playerStats;

import static menuStartPackage.StartUp.musicPlayerInstance;

//progamowanie reaktywne - zmiana zmiennej -> zdarzenie
//ownerId 0 -nikt
public class MainBoardController implements Initializable {
    static public Vector<Player> playerList = new Vector<>();
    private static HexagonMap    map;
    private final String         font                = "Manjaro";
    int                          playerId            = 1;
    @FXML
    private TextField            turnField           = new TextField("dupa");
    @FXML
    private TextFlow             descriptionField    = new TextFlow();
    @FXML
    private TextField            goldField           = new TextField("goldField");
    @FXML
    private TextField            beliefField         = new TextField("beliefField");
    @FXML
    private TextField            bronzeField         = new TextField("bronzeField");
    @FXML
    private TextField            dyesField           = new TextField("dyesField");
    @FXML
    private TextField            recoursesField      = new TextField("recoursesField");
    @FXML
    private TextField            horsesField         = new TextField("horsesField");
    @FXML
    private TextField            ironField           = new TextField("ironField");
    private boolean              buyingMode          = false;
    private boolean              buyInitialised      = false;
    private int                  ownerId             = 1;
    private final TourCounter    tourCounter         = new TourCounter();
    boolean                      cityCoordinatesLock = false;
    private Parent               root;
    @FXML
    public Button                generateHexagonMap;
    @FXML
    public AnchorPane            anchorBoard;
    @FXML
    private TextField            textField;
    private Color                color;
    @FXML
    public AnchorPane            provinceLowerPanel;
    @FXML
    public AnchorPane            provinceUpperPanel;
    @FXML
    private Rectangle            avatar;
    @FXML
    private TextFlow             descriptionTextFlow;
    @FXML
    private TextFlow             bronzeTextFlow;
    @FXML
    private TextFlow             dyesTextFlow;
    @FXML
    private TextFlow             faithTextFlow;
    @FXML
    private TextFlow             goldTextFlow;
    @FXML
    private TextFlow             horseTextFlow;
    @FXML
    private TextFlow             ironTextFlow;
    @FXML
    private TextFlow             woodTextFlow;
    private Player               currentPlayer;
    private Image                image;
    private Image                image2;
    private int                  initialisedI;
    private int                  initialisedJ;
    @FXML
    private ScrollPane           scrollPane;
    @FXML
    private AnchorPane           mainAnchorPane;

    @FXML
    void bronzeEntered() {
        bronzeTextFlow.setVisible(true);

        Text baseProduction = new Text("Base bronze production " + Player.baseBronzeProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREY);
        bronzeTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getBronze() <= 0) {
                continue;
            }

            Text text = new Text(city.getName() + " dyes production " + city.getBronze() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            bronzeTextFlow.getChildren().add(text);
        }
    }

    @FXML
    void bronzeExited() {
        bronzeTextFlow.getChildren().clear();
        bronzeTextFlow.setVisible(false);
    }

    @FXML
    void descriptionEntered() {
        descriptionTextFlow.setVisible(true);

        Text description = new Text(currentPlayer.getFraction().getDescription());

        description.setFont(Font.font(font, 18));
        description.setFill(Color.GREY);
        descriptionTextFlow.getChildren().add(description);
    }

    @FXML
    void descriptionExited() {
        descriptionTextFlow.getChildren().clear();
        descriptionTextFlow.setVisible(false);
    }

    @FXML
    void dyesEntered() {
        dyesTextFlow.setVisible(true);

        Text baseProduction = new Text("Base dyes production " + Player.baseDyesProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREY);
        dyesTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getDyes() <= 0) {
                continue;
            }

            Text text = new Text(city.getName() + " dyes production " + city.getDyes() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            dyesTextFlow.getChildren().add(text);
        }
    }

    @FXML
    void dyesExited() {
        dyesTextFlow.getChildren().clear();
        dyesTextFlow.setVisible(false);
    }

    @FXML
    void faithEntered() {
        faithTextFlow.setVisible(true);

        Text baseProduction = new Text("Base dyes production " + Player.baseBeliefProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREY);
        faithTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getBelief() <= 0) {
                continue;
            }

            Text text = new Text(city.getName() + " faith production " + city.getBelief() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            faithTextFlow.getChildren().add(text);
        }
    }

    @FXML
    void faithExited() {
        faithTextFlow.getChildren().clear();
        faithTextFlow.setVisible(false);
    }

    @FXML
    void goldEntered() {
        goldTextFlow.setVisible(true);

        Text baseProduction = new Text("Base gold production " + Player.baseGoldProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREEN);
        goldTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getGold() <= 0) {
                continue;
            }

            Text text = new Text(city.getName() + " gold production " + city.getGold() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            goldTextFlow.getChildren().add(text);
        }
    }

    @FXML
    void goldExited() {
        goldTextFlow.getChildren().clear();
        goldTextFlow.setVisible(false);
    }

    @FXML
    void horseEntered() {
        horseTextFlow.setVisible(true);

        Text baseProduction = new Text("Base dyes production " + Player.baseHorsesProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREY);
        horseTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getHorses() <= 0) {
                continue;
            }

            Text text = new Text("City " + city.getName() + "horse production" + city.getHorses() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            horseTextFlow.getChildren().add(text);
        }
    }

    @FXML
    void horseExited() {
        horseTextFlow.getChildren().clear();
        horseTextFlow.setVisible(false);
    }

    @FXML
    void ironEntered() {
        ironTextFlow.setVisible(true);

        Text baseProduction = new Text("Base dyes production " + Player.baseIronProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREY);
        ironTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getIron() <= 0) {
                continue;
            }

            Text text = new Text(city.getName() + " dyes production " + city.getIron() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            ironTextFlow.getChildren().add(text);
        }
    }

    @FXML
    void ironExited() {
        ironTextFlow.getChildren().clear();
        ironTextFlow.setVisible(false);
    }

    @FXML
    void nextPlayerButton() throws URISyntaxException {
        descriptionField.getChildren().clear();
        map.setNormalZoom();
        scrollPane.layout();
        provinceLowerPanel.getChildren().clear();
        provinceUpperPanel.getChildren().clear();
        turnField.setText("Turn: " + tourCounter.getTour());
        currentPlayer = playerList.get(playerId - 1);

        if (tourCounter.getTour() == 0) {    // /stats pane regulator
            PlayerData tmp = new PlayerData(currentPlayer.getName());

            tmp.addInfo(currentPlayer.getGold(), currentPlayer.getNumberOfProvinces());
            addPlayer(tmp);
            System.out.println(playerId + " " + currentPlayer.getName());
        } else {
            playerStats.get(playerId - 1).addInfo(currentPlayer.getGold(), currentPlayer.getNumberOfProvinces());
        }

        playerId++;

        if (playerId == playerList.size() + 1) {
            playerId = 1;
            tourCounter.incrementTour();

            for (Player player : playerList) {
                player.resourcesTourIncrease();
            }
        }

        switch (playerId) {
            case 1 :
                image2 = new Image(Objects.requireNonNull(getClass().getResource("avatar1.png")).toURI().toString());
                scrollPane.setVvalue(0.5975);
                scrollPane.setHvalue(0);

                break;

            case 2 :
                image2 = new Image(Objects.requireNonNull(getClass().getResource("avatar2.png")).toURI().toString());
                scrollPane.setVvalue(0);
                scrollPane.setHvalue(0);

                break;

            case 3 :
                image2 = new Image(Objects.requireNonNull(getClass().getResource("avatar3.png")).toURI().toString());
                scrollPane.setVvalue(0.4621);
                scrollPane.setHvalue(0.3900);

                break;
        }

        ImagePattern imgPat2 = new ImagePattern(image2);

        avatar.setFill(imgPat2);
        currentPlayer = playerList.get(playerId - 1);
        ownerId       = playerId;

        Text temp = new Text(currentPlayer.getName() + "\n" + currentPlayer.getFraction().getKing());

        temp.setFont(Font.font(font, 18));
        temp.setFill(Color.PINK);
        descriptionField.getChildren().add(temp);
        goldField.setText("" + currentPlayer.getGold());
        beliefField.setText("" + currentPlayer.getFaith());
        bronzeField.setText("" + currentPlayer.getBronze());
        recoursesField.setText("" + currentPlayer.getBuildingResources());
        horsesField.setText("" + currentPlayer.getHorses());
        ironField.setText("" + currentPlayer.getIron());
        dyesField.setText("" + currentPlayer.getDyes());

        Text goldField1      = new Text("Złoto: " + currentPlayer.getGold());
        Text beliefField1    = new Text("Wiara: " + currentPlayer.getFaith());
        Text bronzeField1    = new Text("Brąz: " + currentPlayer.getBronze());
        Text recoursesField1 = new Text("Surowce: " + currentPlayer.getBuildingResources());
        Text horsesField1    = new Text("Konie: " + currentPlayer.getHorses());
        Text ironField1      = new Text("Żelazo: " + currentPlayer.getIron());
        Text dyesField1      = new Text("Barwniki: " + currentPlayer.getDyes());

        goldField1.setFont(Font.font(font, 20));
        beliefField1.setFont(Font.font(font, 20));
        bronzeField1.setFont(Font.font(font, 20));
        recoursesField1.setFont(Font.font(font, 20));
        horsesField1.setFont(Font.font(font, 20));
        ironField1.setFont(Font.font(font, 20));
        dyesField1.setFont(Font.font(font, 20));
        goldField1.setTranslateY(10);
        beliefField1.setTranslateY(30);
        bronzeField1.setTranslateY(50);
        recoursesField1.setTranslateY(70);
        horsesField1.setTranslateY(90);
        ironField1.setTranslateY(110);
        dyesField1.setTranslateY(130);
        provinceUpperPanel.getChildren().add(goldField1);
        provinceUpperPanel.getChildren().add(beliefField1);
        provinceUpperPanel.getChildren().add(bronzeField1);
        provinceUpperPanel.getChildren().add(recoursesField1);
        provinceUpperPanel.getChildren().add(horsesField1);
        provinceUpperPanel.getChildren().add(ironField1);
        provinceUpperPanel.getChildren().add(dyesField1);

        if (buyInitialised) {
            buyClicked();
            cityCoordinatesLock=false;
        }
        fullMapBorderCleaning();
    }

    private Province provinceBuilder(String name) {
        switch (name) {
            case "City" :
                return new City();

            case "Coast" :
                return new Coast();

            case "DesertFlat" :
                return new DesertFlat();

            case "DesertWyzyny" :
                return new DesertWyzyny();

            case "ForestFlat" :
                return new ForestFlat();

            case "ForestWyzyny" :
                return new ForestWyzyny();

            case "Mountains" :
                return new Mountains();

            case "RiversideArea" :
                return new RiversideArea();

            case "Sea" :
                return new Sea();

            case "TrawaFlat" :
                return new TrawaFlat();

            case "TrawaWyzyny" :
                return new TrawaWyzyny();

            default :
                return new TrawaFlat();
        }
    }

    @FXML
    void woodEntered() {
        woodTextFlow.setVisible(true);

        Text baseProduction = new Text("Base building resources production " + Player.baseBuildingResourcesProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREEN);
        woodTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getWood() <= 0) {
                continue;
            }

            Text text = new Text(city.getName() + " building resources production " + city.getIron() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            woodTextFlow.getChildren().add(text);
        }
    }

    @FXML
    void woodExited() {
        woodTextFlow.getChildren().clear();
        woodTextFlow.setVisible(false);
    }

    public class PlayerData {
        String          name;
        Vector<Integer> goldPerTour;
        Vector<Integer> numberOfProvincesPerTour;

        PlayerData(String tmp) {
            this.name                = tmp;
            goldPerTour              = new Vector<>();
            numberOfProvincesPerTour = new Vector<>();
        }

        public void addInfo(int gold, int numberOfProvinces) {
            goldPerTour.add(gold);
            numberOfProvincesPerTour.add(numberOfProvinces);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Player player1 = new Player("Egypt");
        Player player2 = new Player("Hittites");
        Player player3 = new Player("Assyria");
        playerList.add(player1);
        player1.id=1;
        playerList.add(player2);
        player2.id=2;
        playerList.add(player3);
        player3.id=3;
        scrollPane.setVvalue(0.5975);
        scrollPane.setHvalue(0);

        currentPlayer = playerList.get(1);

        descriptionTextFlow.setTextAlignment(TextAlignment.CENTER);
        goldTextFlow.setTextAlignment(TextAlignment.CENTER);
        faithTextFlow.setTextAlignment(TextAlignment.CENTER);
        woodTextFlow.setTextAlignment(TextAlignment.CENTER);
        bronzeTextFlow.setTextAlignment(TextAlignment.CENTER);
        ironTextFlow.setTextAlignment(TextAlignment.CENTER);
        horseTextFlow.setTextAlignment(TextAlignment.CENTER);
        dyesTextFlow.setTextAlignment(TextAlignment.CENTER);

        mainAnchorPane.setOnKeyPressed(MainBoardController::zoom);

        try {
            image2 = new Image(Objects.requireNonNull(getClass().getResource("avatar1.png")).toURI().toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ImagePattern imgPat2 = new ImagePattern(image2);
        avatar.setFill(imgPat2);

        generateHexagonMap.setVisible(false);

        playerId=1;
        currentPlayer=playerList.get(0);
        Text temp2 = new Text(playerList.get(0).getName() +"\n" + playerList.get(playerId - 1).getFraction().getKing());
        temp2.setFont(Font.font(font,18));
        temp2.setFill(Color.PINK);
        descriptionField.getChildren().add(temp2);
        descriptionField.setLineSpacing(20);

        goldField.setText("" + playerList.get(playerId).getGold());
        beliefField.setText("" + playerList.get(playerId).getFaith());
        bronzeField.setText("" + playerList.get(playerId).getBronze());
        recoursesField.setText("" + playerList.get(playerId).getBuildingResources());
        horsesField.setText("" + playerList.get(playerId).getHorses());
        ironField.setText("" + playerList.get(playerId).getIron());
        dyesField.setText("" + playerList.get(playerId).getDyes());

        turnField.setText("Turn: "+tourCounter.getTour());
        nullHex.setProvince(new Province());
        nullHex.getProvince().setOwnerId(-1);   //wazne dla granic mapy przy malowaniu jej do map buildera
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

        while(true){
            assert scanner != null;
            if (!scanner.hasNext()) break;

            j=scanner.nextInt();
            i=scanner.nextInt();
            owner=scanner.nextInt();
            tempProvince=scanner.next();
            Hexagon temphex = new Hexagon(j, i);

            Province temp = provinceBuilder(tempProvince);
            temp.setType(tempProvince);
            temp.setOwnerId(owner);
            temp.setCoordinates(j, i);
            if(tempProvince.equals("City")){
                playerList.get(owner-1).createNewCity((City)temp);
            }
            temphex.setProvince(temp);
            temphex.setStrokeWidth(hexBorderWidth);
            try {
                image = new Image(Objects.requireNonNull(getClass().getResource(temp.iconPath())).toURI().toString());
            } catch (URISyntaxException e) {
                System.out.println("pattern null");
            }
            ImagePattern imgPat3 = new ImagePattern(image);
            temphex.setFill(imgPat3);


            temphex.setOnMouseClicked(MouseEvent -> hexClick(temphex, temp));
            temphex.setOnMouseMoved(MouseEvent -> textField.setText(temphex.getQ() + ":" + temphex.getR() + " V:" + scrollPane.getVvalue()+ " H"+scrollPane.getHvalue() + "owner:" + temphex.getProvince().getOwnerId()));
            map.addHexagon(temphex);

        }
        Group tempgrup = new Group();
        map.render(tempgrup);
        anchorBoard.getChildren().add(tempgrup);
        generateHexagonMap.setVisible(false);
        scrollPane.pannableProperty().set(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        int jSetter = 1, jLimiter = 46;
        for (int i2 = 1; i2 <35; i2++) {
            if (i2 % 2 == 0) {jSetter--; jLimiter--;}
            for (int j2=jSetter; j2 < jLimiter; j2++) {
                if (i2 % 2 == 1 && j2 == jLimiter - 1) continue;

                switch (map.getHexagon(j2,i2).getProvince().getOwnerId()) {
                    case 0:
                        color = rgb(2,0,36,1);
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
        map.setRenderCoordinates(true);

    }
    void hexClick(Hexagon temphex, Province temp){
        provinceLowerPanel.getChildren().clear();
        provinceUpperPanel.getChildren().clear();

        Button colonize = new Button("colonize");
        Button by = new Button("kup se pole");

        by.setOnMouseClicked(e -> {
            buyClicked();
            buyField(temphex);
        });

        by.setTranslateY(400);
        by.setTranslateX(80);


        if(buyingMode)provinceLowerPanel.getChildren().add(by);
        if(colonizeInitialised)provinceLowerPanel.getChildren().add(by);

        if(playerId == temp.getOwnerId() && !buyingMode){
            if(Objects.equals(temphex.getProvince().getType(), "City"))
            {
                provinceUpperPanel.getChildren().add(colonize);
                provinceLowerPanel.getChildren().add(by);
            }
            colonize.setOnMouseClicked(e -> colonize(temphex.getQ(), temphex.getR()));


            Text provinceType2 = new Text("Typ prowincji: " + temphex.getProvince().getType());
            provinceType2.setTranslateY(30);
            provinceType2.setTranslateX(5);
            provinceType2.setFill(Paint.valueOf("GREEN"));
            provinceType2.setFont(Font.font(font,24));
            provinceLowerPanel.getChildren().add(provinceType2);

            int belief = temphex.getProvince().getBelief();
            int wood = temphex.getProvince().getWood();
            int gold = temphex.getProvince().getGold();
            int food = temphex.getProvince().getFood();
            String provBelief = belief>0 ? "Wiara +" + belief + "\n" : "";
            String provWood = wood>0 ? "Drewno +" + wood + "\n" : "";
            String provGold = gold>0 ? "Złoto +" + gold + "\n" : "";
            String provFood = food>0 ? "Jedzenie +" + food + "\n" : "";
            if(belief > 0 || wood > 0 || gold > 0 || food > 0)
            {
                Text production = new Text("Produkcja: \n");
                production.setTranslateY(60);
                production.setTranslateX(5);
                production.setFont(Font.font(font,24));
                provinceLowerPanel.getChildren().add(production);
                temphex.getProvince().setBaseProduction(temphex.getProvince().getType());
            }
            Text provinceProduction = new Text(provBelief  + provWood + provGold + provFood);
            provinceProduction.setTranslateY(80);
            provinceProduction.setTranslateX(40);
            provinceProduction.setFont(Font.font(font,20));
            provinceLowerPanel.getChildren().add(provinceProduction);
            final int[] resourcesOffset = {(int) provinceProduction.getTranslateX() + (int) provinceProduction.getLayoutBounds().getHeight() + 30};
            temphex.getProvince().getResources().forEach(resource -> {
                Text resourceText = new Text(resource);
                resourceText.setTranslateY(resourcesOffset[0]);
                resourceText.setFont(Font.font(font,20));
                resourcesOffset[0] += 20;
                provinceLowerPanel.getChildren().add(resourceText);
            });
            final int[] buttonOffset = { resourcesOffset[0] + 10};
            temphex.getProvince().getPossibleBuildings().forEach(building -> {

                Button b3 = new Button(building);
                b3.setTranslateY(buttonOffset[0]);
                b3.setPrefWidth(250);
                b3.setTranslateX(20);
                buttonOffset[0] += 60;
                provinceLowerPanel.getChildren().add(b3);
            });
            provinceLowerPanel.setPrefHeight(resourcesOffset[0] + buttonOffset[0] + 200);
        }
        if(buyInitialised) {
            buyField(temphex);
        }
        if(colonizeInitialised){
            colonize(temphex.getQ(), temphex.getR());
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

    @FXML
    void backToMainMenuFromBoard(ActionEvent event) {
        musicPlayerInstance.exit = true;
        musicPlayerInstance.menu = false;
        musicPlayerInstance.stopMusic();

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("exitStats.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    boolean colonizeInitialised = false;

    int iColonize, jColonize;

    private void colonize(int iFrom, int jFrom) {
        if(buyInitialised){buyClicked();}

        if(!colonizeInitialised) {
            System.out.println("niecolo " + iFrom + " " + jFrom);
            for (int tempI = iFrom - 6; tempI < iFrom + 7; tempI++) {
                for (int tempJ = jFrom - 6; tempJ < jFrom + 7; tempJ++) {
                    if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                        if (((Math.abs(iFrom - tempI) > 5) || (Math.abs(jFrom - tempJ) > 5)) || (((tempI < iFrom) && (tempJ < jFrom) && (Math.abs(iFrom - tempI) + Math.abs(jFrom - tempJ)) > 5) || (((tempI > iFrom) && (tempJ > jFrom) && (Math.abs(iFrom - tempI) + Math.abs(jFrom - tempJ)) > 5) || ((tempJ == jFrom) && (Math.abs(iFrom - tempI) > 5)) || ((tempI == iFrom) && (Math.abs(jFrom - tempJ) > 5))))) {
                            continue;
                        }
                        if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                            map.getHexagon(tempI, tempJ).borderColor(Color.PINK);
                        }
                    }
                }
            }
            iColonize=iFrom;
            jColonize=jFrom;
            colonizeInitialised=true;
        }else{
            System.out.println("takcolo " + iFrom + " " + jFrom);
            if(iFrom!=iColonize&&jFrom!=jColonize) {
                if (!Objects.equals(map.getHexagon(iFrom, jFrom).getProvince().getType(), "Sea") && !Objects.equals(map.getHexagon(iFrom, jFrom).getProvince().getType(), "Mountains")) {
                    if (map.getHexagon(iFrom, jFrom).getBorderColor() == Color.PINK) {

                        City temp = new City();
                        temp.setOwnerId(currentPlayer.id);
                        map.getHexagon(iFrom, jFrom).setProvince(temp);
                        currentPlayer.createNewCity(temp);
                        try {
                            image = new Image(Objects.requireNonNull(getClass().getResource(temp.iconPath())).toURI().toString());
                        } catch (URISyntaxException e) {
                            System.out.println("pattern null");
                        }
                        ImagePattern imgPat3 = new ImagePattern(image);
                        map.getHexagon(iFrom, jFrom).setFill(imgPat3);
                        map.getHexagon(iFrom, jFrom).setOnMouseClicked(MouseEvent -> hexClick(map.getHexagon(iFrom, jFrom), temp));
                    }
                }
            }
            fullMapBorderCleaning();
            colonizeInitialised = false;
        }
    }


    // panel do uruchomienia kupowania hexów dla miasta
    void buyClicked() {
        buyingMode = !buyingMode;

        if (!buyingMode){
            buyInitialised      = false;
//            cityCoordinatesLock = false;
//            fullMapBorderCleaning();
//            colonize(initialisedI,initialisedJ);
//            colonize(initialisedI,initialisedJ);
            initialisedI = -11;
            initialisedJ = -11;
        }
    }

    void fullMapBorderCleaning(){
        int jSetter = 1, jLimiter = 46;
        for (int i2 = 1; i2 <35; i2++) {
            if (i2 % 2 == 0) {jSetter--; jLimiter--;}
            for (int j2=jSetter; j2 < jLimiter; j2++) {
                if (i2 % 2 == 1 && j2 == jLimiter - 1) continue;

                switch (map.getHexagon(j2,i2).getProvince().getOwnerId()) {
                    case 0:
                        color = rgb(2,0,36,1);
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




    private void buyField(Hexagon tempname) {
        System.out.println("otwarcie" + cityCoordinatesLock);
        if (colonizeInitialised) {
            colonizeInitialised=false;
            fullMapBorderCleaning();
        }




        int i = tempname.getQ();
        int j = tempname.getR();

        if(buyInitialised&&(map.getHexagon(i,j).getBorderColor()!=Color.PINK)){
            fullMapBorderCleaning();
            buyClicked();
            System.out.println("jeden");
            return;
        }


        if (!buyInitialised && (tempname.getProvince().getOwnerId() != playerId)) {
            buyClicked();
            fullMapBorderCleaning();
            System.out.println("dwa");
            return;
        }

        if (!cityCoordinatesLock&& Objects.equals(map.getHexagon(i, j).getProvince().getType(), "City")) {
            cityCoordinatesLock = true;
            buyInitialised      = true;
            initialisedI        = i;
            initialisedJ        = j;

            for (int tempI = initialisedI - 3; tempI < initialisedI + 4; tempI++) {
                for (int tempJ = initialisedJ - 3; tempJ < initialisedJ + 4; tempJ++) {
                    if ((map.getHexagon(tempI, tempJ - 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI, tempJ + 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI + 1, tempJ - 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI - 1, tempJ + 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI + 1, tempJ).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI - 1, tempJ).getProvince().getOwnerId() == ownerId)) {
                        if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                            if (((Math.abs(initialisedI - tempI) > 3) || (Math.abs(initialisedJ - tempJ) > 3)) || (((tempI < initialisedI) && (tempJ < initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || (((tempI > initialisedI) && (tempJ > initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || ((tempJ == initialisedJ) && (Math.abs(initialisedI - tempI) == 3)) || ((tempI == initialisedI) && (Math.abs(initialisedJ - tempJ) == 3)) || (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 6))) {
                                continue;
                            }
                            if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                                map.getHexagon(tempI, tempJ).borderColor(Color.PINK);
                            }
                        }
                    }
                }
            }
            System.out.println("trzy" + cityCoordinatesLock);
            return;
        }else{


        }

        if (map.getHexagon(i, j).getBorderColor() != Color.PINK) {
            System.out.println("cztery");
            cityCoordinatesLock = false;
            buyingMode=false;
            fullMapBorderCleaning();
            return;
        }

        map.getHexagon(i, j).getProvince().setOwnerId(ownerId);

        switch (map.getHexagon(i, j).getProvince().getOwnerId()) {
        case 0 :
            color = rgb(2, 0, 36, 1);

            break;

        case 1 :
            color = Color.AQUAMARINE;

            break;

        case 2 :
            color = Color.YELLOW;

            break;

        case 3 :
            color = Color.RED;

            break;
        }

        map.getHexagon(i, j).borderColor(color);

        try {
            image = new Image(Objects.requireNonNull(getClass().getResource(map.getHexagon(i, j).getProvince().iconPath())).toURI().toString());

            ImagePattern imgPat3 = new ImagePattern(image);

            map.getHexagon(i, j).setFill(imgPat3);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        map.getHexagon(i, j).borderColor(color);    // /TODO: tutaj

        for (int tempI = initialisedI - 3; tempI < initialisedI + 4; tempI++) {
            for (int tempJ = initialisedJ - 3; tempJ < initialisedJ + 4; tempJ++) {
                if ((map.getHexagon(tempI, tempJ - 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI, tempJ + 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI + 1, tempJ - 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI - 1, tempJ + 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI + 1, tempJ).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI - 1, tempJ).getProvince().getOwnerId() == ownerId)) {
                    if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                        if (((Math.abs(initialisedI - tempI) > 3) || (Math.abs(initialisedJ - tempJ) > 3)) || (((tempI < initialisedI) && (tempJ < initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || (((tempI > initialisedI) && (tempJ > initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || ((tempJ == initialisedJ) && (Math.abs(initialisedI - tempI) == 3)) || ((tempI == initialisedI) && (Math.abs(initialisedJ - tempJ) == 3)) || (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 6))) {
                            continue;
                        }
                        if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                            map.getHexagon(tempI, tempJ).borderColor(Color.PINK);
                        }
                    }
                }
            }
        }
        System.out.println("piec");
    }

}

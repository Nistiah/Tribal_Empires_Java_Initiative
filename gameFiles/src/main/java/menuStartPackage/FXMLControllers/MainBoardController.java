package menuStartPackage.FXMLControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;

import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    public static Vector<Player> playerList = new Vector<>();
    private static HexagonMap    map;
    private final String         font                = "Manjaro";
    public Slider soundSlider;
    public Slider volumeSlider;

    private final String gameButtonSound = "gameButtonSound.wav";
    private final String hexagonSound = "hexagonSound.wav";
    private final String settingsButtonSound = "settingsButtonSound.wav";
    private final String nextPlayerButtonSound = "nextPlayerButtonSound.wav";


    int                          playerId            = 1;
    @FXML
    private TextField            turnField           = new TextField("next turn");
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
    public  TextFlow             provinceType;
    @FXML
    public  HBox                 panelHbox;
    @FXML
    public  GridPane             mainPanel;

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

        Text baseProduction = new Text("Base faith production " + Player.baseBeliefProduction + "\n");

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

        soundPlayerPlaySound(nextPlayerButtonSound);

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

    private Province provinceBuilder(String name, int owner) {
        switch (name) {
            case "City" :
                return new City(owner);

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
    Player player1 = new Player("Egypt");
    Player player2 = new Player("Hittites");
    Player player3 = new Player("Assyria");

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        playerList.add(player1);
        player1.id=1;
        playerList.add(player2);
        player2.id=2;
        playerList.add(player3);
        player3.id=3;
        scrollPane.setVvalue(0.5975);
        scrollPane.setHvalue(0);

        mainAnchorPane.setOnKeyPressed(this::shortcuts);

        currentPlayer = playerList.get(1);

        descriptionTextFlow.setTextAlignment(TextAlignment.CENTER);
        goldTextFlow.setTextAlignment(TextAlignment.CENTER);
        faithTextFlow.setTextAlignment(TextAlignment.CENTER);
        woodTextFlow.setTextAlignment(TextAlignment.CENTER);
        bronzeTextFlow.setTextAlignment(TextAlignment.CENTER);
        ironTextFlow.setTextAlignment(TextAlignment.CENTER);
        horseTextFlow.setTextAlignment(TextAlignment.CENTER);
        dyesTextFlow.setTextAlignment(TextAlignment.CENTER);

        nextSong.setText("next song ->");


        volumeSlider.setOnMouseDragged(event -> sliderVolumeChange());
        volumeSlider.setOnDragDone(event -> sliderVolumeChange());
        volumeSlider.setOnMouseDragReleased(event -> sliderVolumeChange());
        
        soundSlider.setOnMouseDragged(event -> soundVolumeChange());
        soundSlider.setOnDragDone(event -> soundVolumeChange());
        soundSlider.setOnMouseDragReleased(event -> soundVolumeChange());
        
        

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

            Province temp = provinceBuilder(tempProvince, owner);
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

    private double soundVolume = 0.5;  //initial sound volume
    private double musicVolume = 0.5;

    private void soundVolumeChange() {
        soundVolume=soundSlider.getValue()/100;
    }

    private void sliderVolumeChange() {
        musicVolume=volumeSlider.getValue()/100;
        musicPlayerInstance.setVolumeAbsolute(musicVolume);
    }

    @FXML
    AnchorPane settingsPane;

    static Media media;
    static MediaPlayer   mediaPlayer;
    static File          file;

    boolean visible = false;
    @FXML
    void openSettings() {
        soundPlayerPlaySound(gameButtonSound);

        visible=!visible;
        settingsPane.setVisible(visible);
    }

    void hexClick(Hexagon temphex, Province temp){


        provinceLowerPanel.getChildren().clear();
        provinceUpperPanel.getChildren().clear();
        provinceType.getChildren().clear();

        Button colonize = new Button("colonize");
        Button by = new Button("kup se pole with gold");
        Button fieldWithFaith = new Button("kup se pole with faith");

        by.setOnMouseClicked(e -> {
            buyClicked();
            buyField(temphex);
        });

        colonize.setPrefWidth(200);
        colonize.setTranslateY(75);
        colonize.setTranslateX(20);
        colonize.getStyleClass().add("colonizeButton");

        by.setPrefWidth(205);
        by.setTranslateX(20);
        by.setTranslateY(135);
        by.getStyleClass().add("colonizeButton");

        fieldWithFaith.setPrefWidth(205);
        fieldWithFaith.setTranslateX(20);
        fieldWithFaith.setTranslateY(195);
        fieldWithFaith.getStyleClass().add("colonizeButton");

        Button unit1 = new Button("recruit unit 1");
        Button unit2 = new Button("recruit unit 2");
        Button unit3 = new Button("recruit unit 3");

        unit1.setPrefWidth(205);
        unit1.setTranslateX(20);
        unit1.setTranslateY(255);
        unit1.getStyleClass().add("colonizeButton");

        unit2.setPrefWidth(205);
        unit2.setTranslateX(20);
        unit2.setTranslateY(315);
        unit2.getStyleClass().add("colonizeButton");

        unit3.setPrefWidth(205);
        unit3.setTranslateX(20);
        unit3.setTranslateY(375);
        unit3.getStyleClass().add("colonizeButton");


        if(buyingMode)provinceUpperPanel.getChildren().add(by);
        if(colonizeInitialised)provinceUpperPanel.getChildren().add(by);

        if(playerId != temp.getOwnerId() && !buyingMode)
        {
            if(Objects.equals(temphex.getProvince().getType(), "City")) {
                //System.out.println("city = " + playerList.get(3).getCityList());
                String provNameEnemy = "enemy city " + temp.getOwnerId();
                Text provinceTypeTxt = new Text(provNameEnemy);
                provinceTypeTxt.setFill(Paint.valueOf("GRAY"));
                provinceTypeTxt.setFont(Font.font(font,24));
                provinceType.getChildren().add(provinceTypeTxt);
                provinceType.setTextAlignment(TextAlignment.CENTER);
                provinceUpperPanel.getChildren().add(provinceType);

                Button war = new Button("War");
                war.setPrefWidth(150);
                war.setTranslateY(75);
                //war.getStyleClass().add("colonizeButton");
                provinceUpperPanel.getChildren().add(war);

                Button peace = new Button("Peace");
                peace.setPrefWidth(145);
                peace.setTranslateX(150);
                peace.setTranslateY(75);
                //war.getStyleClass().add("colonizeButton");
                provinceUpperPanel.getChildren().add(peace);
            }
        }

        if(playerId == temp.getOwnerId() && !buyingMode){

            soundPlayerPlaySound(hexagonSound);

            if(Objects.equals(temphex.getProvince().getType(), "City"))
            {
                provinceUpperPanel.getChildren().add(colonize);
                provinceUpperPanel.getChildren().add(by);
                provinceUpperPanel.getChildren().add(fieldWithFaith);
                provinceUpperPanel.getChildren().add(unit1);
                provinceUpperPanel.getChildren().add(unit2);
                provinceUpperPanel.getChildren().add(unit3);
            }
            colonize.setOnMouseClicked(e -> colonize(temphex.getQ(), temphex.getR()));

            String provName = temphex.getProvince().getType();
            switch (provName)
            {
                case "City":
                    City city = (City)temp;
                    provName = city.getName();
                    break;
                case "Coast": provName = "Coast";
                    break;
                case "DesertFlat": provName = "Desert";
                    break;
                case "DesertWyzyny": provName = "Desert hills";
                    break;
                case "ForestFlat": provName = "Forest";
                    break;
                case "ForestWyzyny": provName = "Forest hills";
                    break;
                case "Mountains": provName = "Mountains";
                    break;
                case "RiversideArea": provName = "Riverside";
                    break;
                case "Sea": provName = "Sea";
                    break;
                case "TrawaFlat": provName = "Grass";
                    break;
                case "TrawaWyzyny": provName = "Grass hills";
                    break;
                default: provName = "";
            }
            Text provinceType2 = new Text(provName);
            provinceType.getChildren().add(provinceType2);
            provinceType.setTextAlignment(TextAlignment.CENTER);
            provinceType2.setFill(Paint.valueOf("GRAY"));
            provinceType2.setFont(Font.font(font,24));
            provinceUpperPanel.getChildren().add(provinceType);



            int belief = temphex.getProvince().getBelief();
            int wood = temphex.getProvince().getWood();
            int gold = temphex.getProvince().getGold();
            int food = temphex.getProvince().getFood();

            Text textOnProd = new Text();
            //GOLD
            TextField goldProduction = new TextField(String.valueOf(gold));
            goldProduction.getStyleClass().add("provincePanelGold");
            goldProduction.setTranslateY(40);
            goldProduction.setTranslateX(50);
            goldProduction.setFont(Font.font(font,16));
            goldProduction.setPrefWidth(50);
            goldProduction.setEditable(false);

            goldProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if(goldProduction.contains(x,y)){
                    sb.append("gold");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 35);
                textOnProd.setY(y + 20);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            goldProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            //FOOD
            TextField foodProduction = new TextField(String.valueOf(food));
            foodProduction.getStyleClass().add("provincePanelFood");
            foodProduction.setTranslateY(40);
            foodProduction.setTranslateX(100);
            foodProduction.setFont(Font.font(font,16));
            foodProduction.setPrefWidth(50);
            foodProduction.setEditable(false);

            foodProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if(goldProduction.contains(x,y)){
                    sb.append("food");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 85);
                textOnProd.setY(y + 20);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            foodProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            //WOOD
            TextField woodProduction = new TextField(String.valueOf(wood));
            woodProduction.getStyleClass().add("provincePanelWood");
            woodProduction.setTranslateY(40);
            woodProduction.setTranslateX(150);
            woodProduction.setFont(Font.font(font,16));
            woodProduction.setPrefWidth(50);
            woodProduction.setEditable(false);

            woodProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if(goldProduction.contains(x,y)){
                    sb.append("wood");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 135);
                textOnProd.setY(y + 20);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            woodProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            //BELIEF
            TextField beliefProduction = new TextField(String.valueOf(belief));
            beliefProduction.getStyleClass().add("provincePanelFaith");
            beliefProduction.setTranslateY(40);
            beliefProduction.setTranslateX(200);
            beliefProduction.setFont(Font.font(font,16));
            beliefProduction.setPrefWidth(50);
            beliefProduction.setEditable(false);

            beliefProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if(goldProduction.contains(x,y)){
                    sb.append("faith");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 185);
                textOnProd.setY(y + 20);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            beliefProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            provinceUpperPanel.getChildren().add(goldProduction);
            provinceUpperPanel.getChildren().add(foodProduction);
            provinceUpperPanel.getChildren().add(woodProduction);
            provinceUpperPanel.getChildren().add(beliefProduction);

            final int[] resourcesOffset = {(int) beliefProduction.getLayoutBounds().getHeight() + 70};
            //possible resources
            panelHbox.getChildren().clear();
            temphex.getProvince().getResources().forEach(resource -> {
                TextField resourceText = new TextField("  ");

                resourceText.setFont(Font.font(font,16));

                switch (resource)
                {
                    case "ryby":
                        resourceText.getStyleClass().add("possResryby");
                        break;
                    case "barwniki":
                        resourceText.getStyleClass().add("possResbarwniki");
                        break;
                    case "brąz":
                        resourceText.getStyleClass().add("possResbraz");
                        break;
                    case "żelazo":
                        resourceText.getStyleClass().add("possReszelazo");
                        break;
                    case "złoto":
                        resourceText.getStyleClass().add("possReszloto");
                        break;
                    case "bydło":
                        resourceText.getStyleClass().add("possResbydlo");
                        break;
                    case "konie":
                        resourceText.getStyleClass().add("possReskonie");
                        break;
                    case "drewno":
                        resourceText.getStyleClass().add("possResdrewno");
                        break;
                    case "dziczyzna":
                        resourceText.getStyleClass().add("possResdziczyzna");
                        break;
                    case "bursztyn":
                        resourceText.getStyleClass().add("possResbursztyn");
                        break;
                    case "owoce morza":
                        resourceText.getStyleClass().add("possResowocemorza");
                        break;
                }
                //resourceText.getStyleClass().add("possRes"+resource);
                //System.out.println("possRes"+resource);
                //resourcesOffset[0] += 20;
                //panelHbox.getChildren().add(resourceText);
            });
            if(temphex.getProvince().getResources().size() > 0)provinceLowerPanel.getChildren().add(panelHbox);
            final int[] buttonOffset = { 10 };
            temphex.getProvince().getBaseBuildings().forEach(baseBuilding -> {
                Button baseBuildingButton = new Button(baseBuilding);
                baseBuildingButton.setId(baseBuilding);
                baseBuildingButton.getStyleClass().add("baseBuilding");
                baseBuildingButton.setTranslateY(buttonOffset[0]);
                baseBuildingButton.setPrefWidth(250);
                baseBuildingButton.setPrefHeight(100);
                baseBuildingButton.setTranslateX(20);
                buttonOffset[0] += 120;
                temphex.getProvince().builtBuildings.forEach(builtBuilding -> {
                    if(Objects.equals(baseBuilding, builtBuilding)){
                        baseBuildingButton.getStyleClass().add("builtBaseBuilding");
                    }
//                    builtBuilding = builtBuilding.replaceAll("\\s+","");
//                    if(!temphex.getProvince().builtBuildings.contains(builtBuilding)){
//                        Building bld = new Building();
//                        bld.setBaseProduction(builtBuilding);
//                        temphex.getProvince().build.add(bld);
//                    }

                });
                System.out.println("Build: " + temphex.getProvince().build);
                System.out.println("Built blds: " + temphex.getProvince().builtBuildings);
                baseBuildingButton.setOnMouseClicked(e -> {
                    //System.out.println(e.getSource() + "" + temphex.getQ() + "" + temphex.getR());
                    if(!temphex.getProvince().builtBuildings.contains(baseBuilding))temphex.getProvince().builtBuildings.add(baseBuilding);

                    baseBuildingButton.getStyleClass().add("builtBaseBuilding");
                    if(temphex.getProvince().builtBuildings == null) System.out.println("LISTA NULL");
                    if(temphex.getProvince().builtBuildings.isEmpty()) System.out.println("LISTA PUSTA");
                    //System.out.println(temphex.getProvince().build);
                    List<String> tempList = new ArrayList<>(temphex.getProvince().getPossibleBuildings());
                    tempList.remove(baseBuilding);
                    //temphex.getProvince().setPossibleBuildings(tempList);
                    //System.out.println(temphex.getProvince().getPossibleBuildings());
                });

                provinceLowerPanel.getChildren().add(baseBuildingButton);
            });

            //possible buildings
            temphex.getProvince().getPossibleBuildings().forEach(building -> {
                Button possibleBuildingButton = new Button(building);
                possibleBuildingButton.setId(building);
                possibleBuildingButton.getStyleClass().add("building");
                temphex.getProvince().builtBuildings.forEach(builtBuilding -> {
                    if(Objects.equals(building, builtBuilding)){
                        possibleBuildingButton.getStyleClass().add("builtBuilding");
                    }
                });
                possibleBuildingButton.setTranslateY(buttonOffset[0]);
                possibleBuildingButton.setPrefWidth(250);
                possibleBuildingButton.setPrefHeight(100);
                possibleBuildingButton.setTranslateX(20);
                buttonOffset[0] += 120;
                possibleBuildingButton.setOnMouseClicked(e -> {
                    //System.out.println(e.getSource() + "" + temphex.getQ() + "" + temphex.getR());
                    if(!temphex.getProvince().builtBuildings.contains(building))temphex.getProvince().builtBuildings.add(building);
                    possibleBuildingButton.getStyleClass().add("builtBuilding");
                    if(temphex.getProvince().builtBuildings == null) System.out.println("LISTA NULL");
                    if(temphex.getProvince().builtBuildings.isEmpty()) System.out.println("LISTA PUSTA");
                    //System.out.println(temphex.getProvince().builtBuildings);
                    List<String> tempList = new ArrayList<>(temphex.getProvince().getPossibleBuildings());
                    tempList.remove(building);
                    //temphex.getProvince().setPossibleBuildings(tempList);
                    //System.out.println(temphex.getProvince().getPossibleBuildings());
                });
                provinceLowerPanel.getChildren().add(possibleBuildingButton);
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

    

    private void shortcuts(KeyEvent event) {
//        System.out.println(event.getCode());
        switch(event.getCode()){
            case SUBTRACT:
                map.sizeDown();
                break;
            case ADD:
                map.sizeUp();
                break;
            case ESCAPE:
                this.openSettings();
                break;
            case N:
                try {
                    this.nextPlayerButton();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                break;


        }
    }


    @FXML
    private TextField nextSong;
    @FXML
    void nextSong() {
        musicPlayerInstance.stopMusic();
    }



    @FXML
    void backToMainMenuFromBoard(MouseEvent event) {
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

    boolean soundPlayed = false;


    void soundPlayerPlaySound(String filename) {
        if(!soundPlayed) {
            soundPlayed=true;
            file = new File(filename);
            media = new Media(file.toURI().toString());
            mediaPlayer = null;
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(soundVolume);
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(()->{soundPlayed=false;});

        }
    }

    private void colonize(int iFrom, int jFrom) {

        soundPlayerPlaySound(gameButtonSound);

        if(buyInitialised){
            buyClicked();
            cityCoordinatesLock=false;
        }

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
            System.out.println("takcolo " + iFrom + " vs " + iColonize+"|||" + jFrom+" vs "+jColonize);
            if(iFrom!=iColonize||jFrom!=jColonize) {
                System.out.println("pass");
                if (!Objects.equals(map.getHexagon(iFrom, jFrom).getProvince().getType(), "Sea") && !Objects.equals(map.getHexagon(iFrom, jFrom).getProvince().getType(), "Mountains")) {
                    System.out.println("pass");
                    if (map.getHexagon(iFrom, jFrom).getBorderColor() == Color.PINK) {
                        System.out.println("pass");

                        City temp = new City(currentPlayer.id);
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

        soundPlayerPlaySound(gameButtonSound);

//        System.out.println("otwarcie" + cityCoordinatesLock);
        if (colonizeInitialised) {
            colonizeInitialised=false;
            fullMapBorderCleaning();
        }




        int i = tempname.getQ();
        int j = tempname.getR();

        if(buyInitialised&&(map.getHexagon(i,j).getBorderColor()!=Color.PINK)){
            fullMapBorderCleaning();
            buyClicked();
//            System.out.println("jeden");
            return;
        }


        if (!buyInitialised && (tempname.getProvince().getOwnerId() != playerId)) {
            buyClicked();
            fullMapBorderCleaning();
//            System.out.println("dwa");
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
                            if (((Math.abs(initialisedI - tempI) > 3) || (Math.abs(initialisedJ - tempJ) > 3)) || (((tempI < initialisedI) && (tempJ < initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || (((tempI > initialisedI) && (tempJ > initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || ((tempJ == initialisedJ) && (Math.abs(initialisedI - tempI) == 3)) || ((tempI == initialisedI) && (Math.abs(initialisedJ - tempJ) == 3)) || (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 6|| (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 7))) {
                                continue;
                            }
                            if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                                map.getHexagon(tempI, tempJ).borderColor(Color.PINK);
                            }
                        }
                    }
                }
            }
//            System.out.println("trzy" + cityCoordinatesLock);
            return;
        }else{


        }

        if (map.getHexagon(i, j).getBorderColor() != Color.PINK) {
//            System.out.println("cztery");
            cityCoordinatesLock = false;
            buyingMode=false;
            fullMapBorderCleaning();
            return;
        }

        map.getHexagon(i, j).getProvince().setOwnerId(ownerId);
        City temoCity = (City)map.getHexagon(initialisedI, initialisedJ).getProvince();
        temoCity.assignProvince(map.getHexagon(i, j).getProvince());
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
//        System.out.println("piec");
    }

}

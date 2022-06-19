package menuStartPackage.FXMLControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.geometry.Pos;
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

import menuStartPackage.Jednostki.*;

import menuStartPackage.Budynki.*;
import menuStartPackage.Jednostki.Army;
import menuStartPackage.Jednostki.ArmyUnit;
import menuStartPackage.Jednostki.Chariots;
import menuStartPackage.Jednostki.Infantry;
import menuStartPackage.Jednostki.Archer;

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
    private static HexagonMap map;
    private final String font = "Manjaro";
    public Slider soundSlider;
    public Slider volumeSlider;

    private final String gameButtonSound = "gameButtonSound.wav";
    private final String hexagonSound = "hexagonSound.wav";
    private final String settingsButtonSound = "settingsButtonSound.wav";
    private final String nextPlayerButtonSound = "nextPlayerButtonSound.wav";
    @FXML TextFlow siegeDescription;


    int playerId = 1;
    @FXML
    private TextField turnField = new TextField("next turn");
    @FXML
    private TextFlow descriptionField = new TextFlow();
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
    private boolean buyingMode = false;
    private boolean buyInitialised = false;
    private int ownerId = 1;
    private final static int PROVINCE_COST = 20;
    private final TourCounter tourCounter = new TourCounter();
    boolean cityCoordinatesLock = false;
    private Parent root;
    @FXML
    public Button generateHexagonMap;
    @FXML
    public AnchorPane anchorBoard;
    @FXML
    private TextField textField;
    private Color color;
    @FXML
    public AnchorPane provinceLowerPanel;
    @FXML
    public AnchorPane provinceUpperPanel;
    @FXML
    private Rectangle avatar;
    @FXML
    private TextFlow descriptionTextFlow;
    @FXML
    private TextFlow bronzeTextFlow;
    @FXML
    private TextFlow dyesTextFlow;
    @FXML
    private TextFlow faithTextFlow;
    @FXML
    private TextFlow goldTextFlow;
    @FXML
    private TextFlow horseTextFlow;
    @FXML
    private TextFlow ironTextFlow;
    @FXML
    private TextFlow woodTextFlow;
    private Player currentPlayer;
    private Image image;
    private Image image2;
    private int initialisedI;
    private int initialisedJ;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    public TextFlow provinceType;
    @FXML
    public HBox panelHbox;
    @FXML
    public GridPane mainPanel;
    @FXML
    private TextFlow popPanel;
    @FXML
    private GridPane shortcuts;

    @FXML
    void shortcutsEntered() {
        shortcuts.setVisible(true);
    }

    @FXML
    void shortcutsExited() {
        shortcuts.setVisible(false);
    }


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

            Text text = new Text(city.getName() + " bronze production " + city.getBronze() + "\n");

            text.setFont(Font.font(font, 18));
            text.setFill(Color.GREEN);
            bronzeTextFlow.getChildren().add(text);
        }
    }

    void popPanelEntered(City city) {
        popPanel.getChildren().clear();

        double currentPopGrowth = BigDecimal.valueOf(city.currentPopGrowth).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
        double currentPopGrowthCost = BigDecimal.valueOf(city.popGrowthCost * city.popGrowthCostMultiplier).setScale(2, RoundingMode.HALF_DOWN).doubleValue();

        Text cityPop = new Text("City population " + city.getPopulation() + "\nFood needed for population to grow " + currentPopGrowth + "/" + currentPopGrowthCost);
        Text bonus = new Text("");
        Text populationLimit = new Text("\nPopulation limit " + city.getPopulationLimit());
        Text foodProductionNotNumber = new Text("\nFood production ");
        Text foodProduction = new Text("" + city.getFoodBeforePop());
        Text foodConsumptionNotNumber = new Text("\nFood consumption by population ");
        Text foodConsumption = new Text("" + city.getPopulation());
        Text netGainNotNumber = new Text("\nFood net gain ");
        Text netGain = new Text("" + city.getFood());

        if (city.getOwnerId() == 1) {
            bonus.setText("\nfertile soils - new population cost -10%");
            bonus.setFont(Font.font(font, 18));
            bonus.setFill(Color.GREEN);
            bonus.setTextAlignment(TextAlignment.CENTER);

        }

        cityPop.setFont(Font.font(font, 18));
        cityPop.setFill(Color.GREY);
        cityPop.setTextAlignment(TextAlignment.CENTER);

        populationLimit.setFont(Font.font(font, 18));
        populationLimit.setFill(Color.GREY);
        populationLimit.setTextAlignment(TextAlignment.CENTER);

        foodProductionNotNumber.setFont(Font.font(font, 18));
        foodProductionNotNumber.setFill(Color.GREY);
        foodProductionNotNumber.setTextAlignment(TextAlignment.CENTER);

        foodProduction.setFont(Font.font(font, 18));
        foodProduction.setFill(Color.GREEN);
        foodProduction.setTextAlignment(TextAlignment.CENTER);

        foodConsumptionNotNumber.setFont(Font.font(font, 18));
        foodConsumptionNotNumber.setFill(Color.GREY);
        foodConsumptionNotNumber.setTextAlignment(TextAlignment.CENTER);

        foodConsumption.setFont(Font.font(font, 18));
        foodConsumption.setFill(Color.RED);
        foodConsumption.setTextAlignment(TextAlignment.CENTER);

        netGainNotNumber.setFont(Font.font(font, 18));
        netGainNotNumber.setFill(Color.GREY);
        netGainNotNumber.setTextAlignment(TextAlignment.CENTER);

        netGain.setFont(Font.font(font, 18));
        if (city.getFood() == 0) {
            netGain.setFill(Color.GREY);
        } else {
            netGain.setFill(Color.GREEN);
        }
        netGain.setTextAlignment(TextAlignment.CENTER);


        popPanel.getChildren().addAll(cityPop, populationLimit, bonus, foodProductionNotNumber, foodProduction, foodConsumptionNotNumber, foodConsumption, netGainNotNumber, netGain);
        popPanel.setTextAlignment(TextAlignment.CENTER);
        popPanel.setVisible(true);

    }

    void popPanelExited() {
        popPanel.getChildren().clear();
        popPanel.setVisible(false);

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
        Text bonus = new Text();
        //TODO:Hettyci boonus implementacja mechaniki
        if (currentPlayer.id == 1) {
            bonus.setText("\n\nFACTION BONUS\nferile soils - new population cost -10%\n");
            bonus.setFont(Font.font(font, 18));
            bonus.setFill(Color.GREEN);
            bonus.setTextAlignment(TextAlignment.CENTER);
        } else if (currentPlayer.id == 3) {
            bonus.setText("\n\nFACTION BONUS\nfirst scripture - new tile cost -20%\n");
            bonus.setFont(Font.font(font, 18));
            bonus.setFill(Color.GREEN);
            bonus.setTextAlignment(TextAlignment.CENTER);
        }
        description.setFont(Font.font(font, 18));
        description.setFill(Color.GREY);
        descriptionTextFlow.getChildren().addAll(description, bonus);
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

            Text text = new Text(city.getName() + " gold production " + (int) city.getGold() + "\n");

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

        Text baseProduction = new Text("Base horses production " + Player.baseHorsesProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREY);
        horseTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getHorses() <= 0) {
                continue;
            }

            Text text = new Text("City " + city.getName() + "horses production" + (int) city.getHorses() + "\n");

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

        Text baseProduction = new Text("Base iron production " + Player.baseIronProduction + "\n");

        baseProduction.setFont(Font.font(font, 18));
        baseProduction.setFill(Color.GREY);
        ironTextFlow.getChildren().add(baseProduction);

        for (City city : currentPlayer.getCityList()) {
            if (city.getIron() <= 0) {
                continue;
            }

            Text text = new Text(city.getName() + " iron production " + city.getIron() + "\n");

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

    @FXML AnchorPane victory;
    @FXML TextField  victoryTextField;
    boolean victoryAchieved = false;

    void victoryAchieved(){
        int maxPop=0;
        String name = null;
        for(Player player: playerList){
            if(player.getPopulation()>maxPop){
                maxPop=player.getPopulation();
                name=player.getName();
            }
        }
        if(maxPop>=100){
            victoryAchieved=true;
            victoryTextField.setText(name + " Victory by population!");
            victory.setVisible(true);
        }
    }

    @FXML
    void nextPlayerButton() throws URISyntaxException {

        soundPlayerPlaySound(nextPlayerButtonSound);


        buyingMode = false;
        buyInitialised = false;
        cityCoordinatesLock = false;

        descriptionField.getChildren().clear();
        map.setNormalZoom();
        scrollPane.layout();
        provinceLowerPanel.getChildren().clear();
        provinceUpperPanel.getChildren().clear();
        currentPlayer = playerList.get(playerId - 1);

        if (tourCounter.getTour() == 0) {    // /stats pane regulator
            PlayerData tmp = new PlayerData(currentPlayer.getName());
            tmp.addInfo((int) currentPlayer.getGold(), currentPlayer.getNumberOfProvinces(), currentPlayer.getPopulation());
            addPlayer(tmp);
        } else {
            if (!currentPlayer.playerDead) {
                playerStats.get(playerId - 1).addInfo((int) currentPlayer.getGold(), currentPlayer.getNumberOfProvinces(), currentPlayer.getPopulation());
            } else {
                playerStats.get(playerId - 1).addInfo((int) 0, 0, 0);
            }
            victoryAchieved();
        }

        playerId++;

        if (playerId == playerList.size() + 1) {
            tourCounter.incrementTour();
            turnField.setText("Turn: " + tourCounter.getTour());
            playerId = 1;
            for (Player player : playerList) {
                player.resourcesTourIncrease();

            }
        }

        switch (playerId) {
            case 1:
                image2 = new Image(Objects.requireNonNull(getClass().getResource("avatar1.png")).toURI().toString());
                scrollPane.setVvalue(0.5975);
                scrollPane.setHvalue(0);

                break;

            case 2:
                image2 = new Image(Objects.requireNonNull(getClass().getResource("avatar2.png")).toURI().toString());
                scrollPane.setVvalue(0);
                scrollPane.setHvalue(0);

                break;

            case 3:
                image2 = new Image(Objects.requireNonNull(getClass().getResource("avatar3.png")).toURI().toString());
                scrollPane.setVvalue(0.4621);
                scrollPane.setHvalue(0.3900);

                break;
        }

        ImagePattern imgPat2 = new ImagePattern(image2);

        avatar.setFill(imgPat2);
        currentPlayer = playerList.get(playerId - 1);
        ownerId = playerId;

        Text temp = new Text(currentPlayer.getName() + "\n" + currentPlayer.getFraction().getKing());

        temp.setFont(Font.font(font, 18));
        temp.setFill(Color.PINK);
        descriptionField.getChildren().add(temp);
        goldField.setText("" + (int) currentPlayer.getGold());
        beliefField.setText("" + (int) currentPlayer.getFaith());
        bronzeField.setText("" + currentPlayer.getBronze());
        recoursesField.setText("" + currentPlayer.getBuildingResources());
        horsesField.setText("" + currentPlayer.getHorses());
        ironField.setText("" + currentPlayer.getIron());
        dyesField.setText("" + currentPlayer.getDyes());


        if (buyInitialised) {
            buyClicked();
            cityCoordinatesLock = false;
        }
        fullMapBorderCleaning();
        currentPlayer.checkIfPlayerDead();
        if (currentPlayer.playerDead) {
            nextPlayerButton();
        }

    }

    private Province provinceBuilder(String name, int owner) {
        switch (name) {
            case "City":
                return new City(owner);

            case "Coast":
                return new Coast();

            case "DesertFlat":
                return new DesertFlat();

            case "DesertWyzyny":
                return new DesertWyzyny();

            case "ForestFlat":
                return new ForestFlat();

            case "ForestWyzyny":
                return new ForestWyzyny();

            case "Mountains":
                return new Mountains();

            case "RiversideArea":
                return new RiversideArea();

            case "Sea":
                return new Sea();

            case "TrawaFlat":
                return new TrawaFlat();

            case "TrawaWyzyny":
                return new TrawaWyzyny();

            default:
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
        String name;
        Vector<Integer> goldPerTour;
        Vector<Integer> numberOfProvincesPerTour;
        Vector<Integer> numberOfPopsPerTour;

        PlayerData(String tmp) {
            this.name = tmp;
            goldPerTour = new Vector<>();
            numberOfProvincesPerTour = new Vector<>();
            numberOfPopsPerTour = new Vector<>();
        }

        public void addInfo(int gold, int numberOfProvinces, int pops) {
            goldPerTour.add(gold);
            numberOfProvincesPerTour.add(numberOfProvinces);
            numberOfPopsPerTour.add(pops);

        }
    }

    Player player1 = new Player("Egypt");
    Player player2 = new Player("Hittites");
    Player player3 = new Player("Assyria");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Text text = new Text("Siege result is decided following those rules\n\n\n" +
                "Main factor is strength of both sides\n" +
                "Attackers strength is calculated as follows: (combined army hp) * 2 \n" +
                "Defenders strength is calculated as follows: (combined army hp + 50) * 2 \n\n" +
                "Every initial value is multiplied by strength as percentage of initial strength\n\n" +
                "Every turn \nThe attackers strength is multiplied by 95% and defenders strength is multiplied by 90%\n" +
                "The attackers mean damage is subtracted from defenders mean defence\n" +
                "The defenders mean damage is subtracted from attackers mean defence\n" +
                "Then the difference is multiplied by rng factor for current turn\n" +
                "If the difference is greater than 0 the damage is applied to the other side\n\n\n" +
                "For the defenders to win the attackers strength must be lower than 25%\n" +
                "For the attackers to win the defenders strength must be lower than 20%");

        text.setFont(Font.font(font, 16));
        text.setFill(Color.GREY);


        siegeDescription.getChildren().add(text);
        siegeDescription.setTextAlignment(TextAlignment.CENTER);

        siegeName.setOnMouseEntered(event -> {
            siegeDescription.setVisible(true);
        });

        siegeName.setOnMouseExited(event -> {
            siegeDescription.setVisible(false);
        });



        playerList.add(player1);
        player1.id = 1;
        playerList.add(player2);
        player2.id = 2;
        playerList.add(player3);
        player3.id = 3;
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

        playerId = 1;
        currentPlayer = playerList.get(0);
        Text temp2 = new Text(playerList.get(0).getName() + "\n" + playerList.get(playerId - 1).getFraction().getKing());
        temp2.setFont(Font.font(font, 18));
        temp2.setFill(Color.PINK);
        descriptionField.getChildren().add(temp2);
        descriptionField.setLineSpacing(20);

        goldField.setText("" + (int) playerList.get(playerId).getGold());
        beliefField.setText("" + (int) playerList.get(playerId).getFaith());
        bronzeField.setText("" + playerList.get(playerId).getBronze());
        recoursesField.setText("" + playerList.get(playerId).getBuildingResources());
        horsesField.setText("" + playerList.get(playerId).getHorses());
        ironField.setText("" + playerList.get(playerId).getIron());
        dyesField.setText("" + playerList.get(playerId).getDyes());

        turnField.setText("Turn: " + tourCounter.getTour());
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
        int i, j, owner;
        String tempProvince;

        while (true) {
            assert scanner != null;
            if (!scanner.hasNext()) break;

            j = scanner.nextInt();
            i = scanner.nextInt();
            owner = scanner.nextInt();
            tempProvince = scanner.next();
            Hexagon temphex = new Hexagon(j, i);

            Province temp = provinceBuilder(tempProvince, owner);
            temp.setType(tempProvince);
            temp.setOwnerId(owner);
            temp.setCoordinates(j, i);
            if (tempProvince.equals("City")) {
                playerList.get(owner - 1).createNewCity((City) temp);

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
            temphex.setOnMouseMoved(MouseEvent -> textField.setText(temphex.getQ() + ":" + temphex.getR() + " V:" + scrollPane.getVvalue() + " H" + scrollPane.getHvalue() + "owner:" + temphex.getProvince().getOwnerId()));
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
        for (int i2 = 1; i2 < 35; i2++) {
            if (i2 % 2 == 0) {
                jSetter--;
                jLimiter--;
            }
            for (int j2 = jSetter; j2 < jLimiter; j2++) {
                if (i2 % 2 == 1 && j2 == jLimiter - 1) continue;

                switch (map.getHexagon(j2, i2).getProvince().getOwnerId()) {
                    case 0:
                        color = rgb(2, 0, 36, 1);
                        map.getHexagon(j2, i2).borderColor(color);
                        break;
                    case 1:
                        color = Color.AQUAMARINE;
                        map.getHexagon(j2, i2).borderColor(color);
                        break;
                    case 2:
                        color = Color.YELLOW;
                        map.getHexagon(j2, i2).borderColor(color);
                        break;
                    case 3:
                        color = Color.RED;
                        map.getHexagon(j2, i2).borderColor(color);
                        break;
                }
            }
        }
        map.setRenderCoordinates(true);

    }

    private double soundVolume = 0.5;  //initial sound volume
    private double musicVolume = 0.5;

    private void soundVolumeChange() {
        soundVolume = soundSlider.getValue() / 100;
    }

    private void sliderVolumeChange() {
        musicVolume = volumeSlider.getValue() / 100;
        musicPlayerInstance.setVolumeAbsolute(musicVolume);
    }

    @FXML
    AnchorPane settingsPane;

    static Media media;
    static MediaPlayer mediaPlayer;
    static File file;

    boolean visible = false;
    boolean goldVSfaithBuy = false; //false gold, true faith


    @FXML
    void openSettings() {
        soundPlayerPlaySound(gameButtonSound);

        visible = !visible;
        settingsPane.setVisible(visible);
    }

    @FXML
    AnchorPane siegePane;
    @FXML
    TextField siegeName;
    @FXML
    TextFlow siegeAtack;
    @FXML
    TextFlow siegeDefence;
    @FXML
    TextField defendersCasualties;
    @FXML
    TextField rng;
    @FXML
    TextField attackersCasualties;
    @FXML
    TextFlow attackersFlow;
    @FXML
    TextFlow rngFlow;
    @FXML
    TextFlow defendersFlow;

    @FXML
    void siegeExit() {
        siegePane.setVisible(false);
    }

    void war(){

    }

    void peace(){

    }

    void sendToSiege(){

    }

    void hexClick(Hexagon temphex, Province temp) {


        provinceLowerPanel.getChildren().clear();
        provinceUpperPanel.getChildren().clear();
        provinceType.getChildren().clear();

        Button colonize = new Button("build a new city");
        Button buyProvinceGold = new Button("buy a province with gold"); //cost 20
        Button buyProvinceFaith = new Button("buy a province with faith"); //cost 20

        buyProvinceGold.setOnMouseClicked(e -> {
            buyClicked();
            goldVSfaithBuy = false;
            buyField(temphex);
        });
        buyProvinceFaith.setOnMouseClicked(e -> {
            buyClicked();
            goldVSfaithBuy = true;
            buyField(temphex);
        });

        colonize.setPrefWidth(200);
        colonize.setTranslateY(95);
        colonize.setTranslateX(20);
        colonize.getStyleClass().add("colonizeButton");

        buyProvinceGold.setPrefWidth(205);
        buyProvinceGold.setTranslateX(20);
        buyProvinceGold.setTranslateY(155);
        buyProvinceGold.getStyleClass().add("colonizeButton");

        buyProvinceFaith.setPrefWidth(205);
        buyProvinceFaith.setTranslateX(20);
        buyProvinceFaith.setTranslateY(215);
        buyProvinceFaith.getStyleClass().add("colonizeButton");

        Button armies = new Button("Armies");

        armies.setPrefWidth(205);
        armies.setTranslateX(20);
        armies.setTranslateY(275);
        armies.getStyleClass().add("colonizeButton");


        if (buyingMode) provinceUpperPanel.getChildren().add(buyProvinceGold);
        if (colonizeInitialised) provinceUpperPanel.getChildren().add(buyProvinceGold);

        int resourcesHeight = 40;

        if (playerId != temp.getOwnerId() && !buyingMode) {
            if (Objects.equals(temphex.getProvince().getType(), "City")) {

                City c1 = (City)temp;
                armies.setOnMouseClicked(e -> {
                    armiesClicked(c1);
                });


                String provNameEnemy = "";
                City city = (City) temp;
                provNameEnemy = city.getName();
                Text provinceTypeTxt = new Text(provNameEnemy);
                provinceTypeTxt.setFill(Paint.valueOf("GRAY"));
                provinceTypeTxt.setFont(Font.font(font, 24));
                provinceType.getChildren().add(provinceTypeTxt);
                provinceType.setTextAlignment(TextAlignment.CENTER);
                provinceUpperPanel.getChildren().add(provinceType);

                Button war = new Button("War");
                war.setPrefWidth(150);
                war.setTranslateY(75);
                //war.getStyleClass().add("colonizeButton");
                provinceUpperPanel.getChildren().add(war);
                war.setOnMouseClicked(e->war());

                Button peace = new Button("Peace");
                peace.setPrefWidth(145);
                peace.setTranslateX(150);
                peace.setTranslateY(75);
                //war.getStyleClass().add("colonizeButton");
                provinceUpperPanel.getChildren().add(peace);
                peace.setOnMouseClicked(e->peace());

                //TODO: siege button
                Button siege = new Button("Siege");
                siege.setPrefWidth(299);
                siege.setTranslateX(0);
                siege.setTranslateY(150);
                siege.setOnMouseClicked(e -> {
                    City tempCity = (City) temphex.getProvince();
                    if (tempCity.siege == null) {
                        return;
                    }
                    siegePane.setVisible(true);
                    attackersFlow.getChildren().clear();
                    defendersFlow.getChildren().clear();
                    rngFlow.getChildren().clear();

                    siegeAtack.getChildren().clear();
                    siegeDefence.getChildren().clear();


//                    siegeName.clear();
                    siegeName.setText("Siege of " + tempCity.getName());
                    Text atackForces = new Text("Besieging Army\n");
                    atackForces.setFont(Font.font(font, 30));
                    atackForces.setFill(Color.GREY);
                    siegeAtack.getChildren().add(atackForces);
                    siegeAtack.setTextAlignment(TextAlignment.CENTER);

                    Text atackersForcesStrenght = new Text("Strength " + tempCity.siege.getAtackStrength() + "%\n\n");
                    atackersForcesStrenght.setFont(Font.font(font, 30));
                    atackersForcesStrenght.setFill(Color.GREY);
                    siegeAtack.getChildren().add(atackersForcesStrenght);
                    siegeAtack.setTextAlignment(TextAlignment.CENTER);

                    for (ArmyUnit unit : tempCity.siege.atackingArmy.getUnits()) {
                        Text unitText = new Text(unit.getNameWithLvl() + "\n");
                        unitText.setFont(Font.font(font, 18));
                        unitText.setFill(Color.GREY);
                        siegeAtack.getChildren().add(unitText);
                    }

                    Text defenceForces = new Text("Defenders Army\n");
                    defenceForces.setFont(Font.font(font, 30));
                    defenceForces.setFill(Color.GREY);
                    siegeDefence.getChildren().add(defenceForces);
                    siegeDefence.setTextAlignment(TextAlignment.CENTER);

                    Text defenceForcesStrenght = new Text("Strength " + tempCity.siege.getDefenseStrength() + "%\n\n");
                    defenceForcesStrenght.setFont(Font.font(font, 30));
                    defenceForcesStrenght.setFill(Color.GREY);
                    siegeDefence.getChildren().add(defenceForcesStrenght);
                    siegeDefence.setTextAlignment(TextAlignment.CENTER);

                    for (ArmyUnit unit : tempCity.siege.defendingArmy.getUnits()) {
                        Text unitText = new Text(unit.getNameWithLvl() + "\n");
                        unitText.setFont(Font.font(font, 18));
                        unitText.setFill(Color.GREY);
                        siegeDefence.getChildren().add(unitText);
                    }
//                    @FXML TextField defendersCasualties;
//                    @FXML TextField rng;
//                    @FXML TextField attackersCasualties;
//                    @FXML TextFlow  attackersFlow;
//                    @FXML TextFlow  rngFlow;
//                    @FXML TextFlow  defendersFlow;

                    defendersCasualties.setText("Defenders casualties " + tempCity.siege.defCasualties);
                    attackersCasualties.setText("Attackers casualties " + tempCity.siege.atkCasualties);
                    rng.setText("" + tempCity.siege.getLastRng());
                    rng.setAlignment(Pos.CENTER);

                    Text rngDescription = new Text("Defenders Damage Modifier -  Roll  -  Attackers Damage Modifier\n\n");
                    Text zero = new Text("200%  -  0  -  0%  \n");
                    Text one = new Text("160%  -  1  -  0%  \n");
                    Text two = new Text("120%  -  2  -  0%  \n");
                    Text three = new Text("100%  -  3  -  0%  \n");
                    Text four = new Text("100%  -  4  -  40% \n");
                    Text five = new Text("100%  -  5  -  80% \n");
                    Text six = new Text("90%   -  6  -  120%\n");
                    Text seven = new Text("80%   -  7  -  180%\n");
                    Text eight = new Text("70%   -  8  -  240%\n");
                    Text nine = new Text("60%   -  9  -  320%\n");
                    Text ten = new Text("50%  -  10  -  400%\n");

                    rngDescription.setFont(Font.font(font, 18));
                    rngDescription.setFill(Color.GREY);
                    zero.setFont(Font.font(font, 18));
                    zero.setFill(Color.GREY);
                    one.setFont(Font.font(font, 18));
                    one.setFill(Color.GREY);
                    two.setFont(Font.font(font, 18));
                    two.setFill(Color.GREY);
                    three.setFont(Font.font(font, 18));
                    three.setFill(Color.GREY);
                    four.setFont(Font.font(font, 18));
                    four.setFill(Color.GREY);
                    five.setFont(Font.font(font, 18));
                    five.setFill(Color.GREY);
                    six.setFont(Font.font(font, 18));
                    six.setFill(Color.GREY);
                    seven.setFont(Font.font(font, 18));
                    seven.setFill(Color.GREY);
                    eight.setFont(Font.font(font, 18));
                    eight.setFill(Color.GREY);
                    nine.setFont(Font.font(font, 18));
                    nine.setFill(Color.GREY);
                    ten.setFont(Font.font(font, 18));
                    ten.setFill(Color.GREY);

                    switch (tempCity.siege.lastRng) {
                        case 0:
                            zero.setFill(Color.WHITE);
                            break;
                        case 1:
                            one.setFill(Color.WHITE);
                            break;
                        case 2:
                            two.setFill(Color.WHITE);
                            break;
                        case 3:
                            three.setFill(Color.WHITE);
                            break;
                        case 4:
                            four.setFill(Color.WHITE);
                            break;
                        case 5:
                            five.setFill(Color.WHITE);
                            break;
                        case 6:
                            six.setFill(Color.WHITE);
                            break;
                        case 7:
                            seven.setFill(Color.WHITE);
                            break;
                        case 8:
                            eight.setFill(Color.WHITE);
                            break;
                        case 9:
                            nine.setFill(Color.WHITE);
                            break;
                        case 10:
                            ten.setFill(Color.WHITE);
                            break;
                    }


                    rngFlow.getChildren().addAll(rngDescription, zero, one, two, three, four, five, six, seven, eight, nine, ten);
                    rngFlow.setTextAlignment(TextAlignment.CENTER);

                    Text defendersFlowDescription = new Text("Defenders Stats\n\n " +
                            "Initial Strength " + tempCity.siege.getDefenceStrenghtInitial() + "\n" +
                            "Initial Far Damage " + tempCity.siege.getDefenseFarDamageInitial() + "\n" +
                            "Initial Far Defence" + tempCity.siege.getDefenseFarDefenceInitial() + "\n" +
                            "Initial Close Damage" + tempCity.siege.getDefenseCloseDamageInitial() + "\n" +
                            "Initial Close Defence" + tempCity.siege.getDefenseCloseDefenceInitial() + "\n" +

                            "\nMean Strength " + tempCity.siege.getDefenceStrenghtMean() + "\n" +
                            "Final Far Damage" + tempCity.siege.getDefenseFarDamage() + "\n" +
                            "Final Far Defence" + tempCity.siege.getDefenceFarDefence() + "\n" +
                            "Final Close Damage" + tempCity.siege.getDefenseCloseDamage() + "\n" +
                            "Final Close Defence" + tempCity.siege.getDefenceCloseDefence() + "\n"
                    );
                    defendersFlowDescription.setFont(Font.font(font, 18));
                    defendersFlowDescription.setFill(Color.GREY);
                    defendersFlow.getChildren().add(defendersFlowDescription);


                    Text attackersFlowDescription = new Text("Attackers Stats\n\n" +
                            "Initial Strength " + tempCity.siege.getAttackStrenghtInitial() + "\n" +
                            "Initial Far Damage " + tempCity.siege.getAtackFarDamageInitial() + "\n" +
                            "Initial Far Defence" + tempCity.siege.getAtackFarDefenceInitial() + "\n" +
                            "Initial Close Damage" + tempCity.siege.getAtackCloseDamageInitial() + "\n" +
                            "Initial Close Defence" + tempCity.siege.getAtackCloseDefenceInitial() + "\n" +

                            "\nMean Strength " + tempCity.siege.getAtackStrenghtMean() + "\n" +
                            "Final Far Damage" + tempCity.siege.getAtackFarDamage() + "\n" +
                            "Final Far Defence" + tempCity.siege.getAtackFarDefence() + "\n" +
                            "Final Close Damage" + tempCity.siege.getAtackCloseDamage() + "\n" +
                            "Final Close Defence" + tempCity.siege.getAtackCloseDefence() + "\n"
                    );
                    attackersFlowDescription.setFont(Font.font(font, 18));
                    attackersFlowDescription.setFill(Color.GREY);
                    attackersFlow.getChildren().add(attackersFlowDescription);

                    attackersFlow.setTextAlignment(TextAlignment.CENTER);
                    defendersFlow.setTextAlignment(TextAlignment.CENTER);
                    rngFlow.setTextAlignment(TextAlignment.CENTER);

                    attackersCasualties.setOnMouseEntered(e1 -> {
                        attackersFlow.setVisible(true);
                    });
                    attackersCasualties.setOnMouseExited(e1 -> {
                        attackersFlow.setVisible(false);
                    });
                    defendersCasualties.setOnMouseEntered(e1 -> {
                        defendersFlow.setVisible(true);
                    });
                    defendersCasualties.setOnMouseExited(e1 -> {
                        defendersFlow.setVisible(false);
                    });
                    rng.setOnMouseEntered(e1 -> {
                        rngFlow.setVisible(true);
                    });
                    rng.setOnMouseExited(e1 -> {
                        rngFlow.setVisible(false);
                    });


                });
                //war.getStyleClass().add("colonizeButton");
                provinceUpperPanel.getChildren().add(siege);
            }
        }

        if (playerId == temp.getOwnerId() && !buyingMode) {

            soundPlayerPlaySound(hexagonSound);

            if (Objects.equals(temphex.getProvince().getType(), "City")) {
                City c1 = (City) temp;
                armies.setOnMouseClicked(e -> {
                    armiesClicked(c1);
                });
                resourcesHeight = 60;
                provinceUpperPanel.getChildren().add(colonize);
                provinceUpperPanel.getChildren().add(buyProvinceGold);
                provinceUpperPanel.getChildren().add(buyProvinceFaith);
                provinceUpperPanel.getChildren().add(armies);
            }
            colonize.setOnMouseClicked(e -> colonize(temphex.getQ(), temphex.getR()));

            String provName = temphex.getProvince().getType();
            switch (provName) {
                case "City":
                    City city = (City) temp;
                    provName = city.getName();
                    break;
                case "Coast":
                    provName = "Coast";
                    break;
                case "DesertFlat":
                    provName = "Desert";
                    break;
                case "DesertWyzyny":
                    provName = "Desert hills";
                    break;
                case "ForestFlat":
                    provName = "Forest";
                    break;
                case "ForestWyzyny":
                    provName = "Forest hills";
                    break;
                case "Mountains":
                    provName = "Mountains";
                    break;
                case "RiversideArea":
                    provName = "Riverside";
                    break;
                case "Sea":
                    provName = "Sea";
                    break;
                case "TrawaFlat":
                    provName = "Grass";
                    break;
                case "TrawaWyzyny":
                    provName = "Grass hills";
                    break;
                default:
                    provName = "";
            }
            String foodInCity = "food";
            Text provinceType2 = new Text(provName);
            provinceType.getChildren().add(provinceType2);
            provinceType.setTextAlignment(TextAlignment.CENTER);
            provinceType2.setFill(Paint.valueOf("GRAY"));
            provinceType2.setFont(Font.font(font, 24));
            provinceUpperPanel.getChildren().add(provinceType);


            int belief = (int) temphex.getProvince().getBelief();
            int wood = temphex.getProvince().getWood();
            int gold = (int) temphex.getProvince().getGold();
            int food = temphex.getProvince().getFood();
            int pop = temphex.getProvince().getPop();

            Text textOnProd = new Text();
            //GOLD
            TextField goldProduction = new TextField(String.valueOf(gold));
            goldProduction.getStyleClass().add("provincePanelGold");
            goldProduction.setTranslateY(resourcesHeight);
            goldProduction.setTranslateX(50);
            goldProduction.setFont(Font.font(font, 16));
            goldProduction.setPrefWidth(50);
            goldProduction.setEditable(false);

            goldProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if (goldProduction.contains(x, y)) {
                    sb.append("gold");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 35);
                textOnProd.setY(y + 40);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            goldProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            //FOOD

            TextField foodProduction = new TextField(String.valueOf(food));
            foodProduction.getStyleClass().add("provincePanelFood");
            foodProduction.setTranslateY(resourcesHeight);
            foodProduction.setTranslateX(100);
            foodProduction.setFont(Font.font(font, 16));
            foodProduction.setPrefWidth(50);
            foodProduction.setEditable(false);

            String finalFoodInCity = foodInCity;

            foodProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();

                if (goldProduction.contains(x, y)) {
                    sb.append(finalFoodInCity);
                }

                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 85);
                textOnProd.setY(y + 40);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            foodProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            //BUILDING RESOURCES
            TextField woodProduction = new TextField(String.valueOf(wood));
            woodProduction.getStyleClass().add("provincePanelWood");
            woodProduction.setTranslateY(resourcesHeight);
            woodProduction.setTranslateX(150);
            woodProduction.setFont(Font.font(font, 16));
            woodProduction.setPrefWidth(50);
            woodProduction.setEditable(false);

            woodProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if (goldProduction.contains(x, y)) {
                    sb.append("building resources");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 135);
                textOnProd.setY(y + 40);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            woodProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            //BELIEF
            TextField beliefProduction = new TextField(String.valueOf(belief));
            beliefProduction.getStyleClass().add("provincePanelFaith");
            beliefProduction.setTranslateY(resourcesHeight);
            beliefProduction.setTranslateX(200);
            beliefProduction.setFont(Font.font(font, 16));
            beliefProduction.setPrefWidth(50);
            beliefProduction.setEditable(false);

            beliefProduction.setOnMouseMoved(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if (goldProduction.contains(x, y)) {
                    sb.append("faith");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 185);
                textOnProd.setY(y + 40);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            beliefProduction.setOnMouseExited(e -> provinceUpperPanel.getChildren().remove(textOnProd));

            //POPULATION


            TextField population = new TextField(String.valueOf(pop));///
            population.getStyleClass().add("provincePanelPopulation");
            population.setTranslateY(35);
            population.setTranslateX(125);
            population.setFont(Font.font(font, 16));
            population.setPrefWidth(50);
            population.setEditable(false);
            population.setAlignment(Pos.BOTTOM_RIGHT);

            population.setOnMouseMoved(e -> {
                City tempCity = (City) temphex.getProvince();
                popPanelEntered(tempCity);

                provinceUpperPanel.getChildren().remove(textOnProd);
                double x = e.getX();
                double y = e.getY();
                StringBuilder sb = new StringBuilder();
                if (goldProduction.contains(x, y)) {
                    sb.append("population");
                }
                textOnProd.setText(sb.toString());
                textOnProd.setX(x + 125);
                textOnProd.setY(y + 20);
                textOnProd.setFill(Paint.valueOf("GRAY"));
                provinceUpperPanel.getChildren().add(textOnProd);
            });
            population.setOnMouseExited(e -> {
                provinceUpperPanel.getChildren().remove(textOnProd);
                popPanelExited();
            });

            provinceUpperPanel.getChildren().add(goldProduction);
            provinceUpperPanel.getChildren().add(foodProduction);
            provinceUpperPanel.getChildren().add(woodProduction);
            provinceUpperPanel.getChildren().add(beliefProduction);
            if (Objects.equals(temphex.getProvince().getType(), "City"))
                provinceUpperPanel.getChildren().add(population);


            final int[] resourcesOffset = {(int) beliefProduction.getLayoutBounds().getHeight() + 70};
            //possible resources
            panelHbox.getChildren().clear();
            temphex.getProvince().getResources().forEach(resource -> {
                TextField resourceText = new TextField("  ");

                resourceText.setFont(Font.font(font, 16));

                switch (resource) {
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
            if (temphex.getProvince().getResources().size() > 0) provinceLowerPanel.getChildren().add(panelHbox);
            final int[] buttonOffset = {10};
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
                    if (Objects.equals(baseBuilding, builtBuilding)) {
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
                System.out.println("Vector: " + temphex.getProvince().builtBuildingsVector);
                baseBuildingButton.setOnMouseClicked(e -> {
                    //System.out.println(e.getSource() + "" + temphex.getQ() + "" + temphex.getR());
                    String buildingNoSpacesTemp = baseBuilding.replaceAll("\\s+","");
                    if(Objects.equals(buildingNoSpacesTemp, "ResidentialDistrict") && temphex.getProvince().builtBuildings.contains(baseBuilding)){
                        City tempCity = (City) temphex.getProvince();
                        tempCity.setPopulationLimit(tempCity.getPopulationLimit() + 5);
                    }
                    else if (!temphex.getProvince().builtBuildings.contains(baseBuilding)){
                        temphex.getProvince().builtBuildings.add(baseBuilding);
                        String buildingNoSpaces = baseBuilding.replaceAll("\\s+","");
                        if(Objects.equals(buildingNoSpaces, "ResidentialDistrict")){
                            City tempCity = (City) temphex.getProvince();
                            tempCity.setPopulationLimit(tempCity.getPopulationLimit() + 5);
                        }
                        buyBuilding(temphex.getProvince(), buildingNoSpaces);
                    }


                    baseBuildingButton.getStyleClass().add("builtBaseBuilding");
                    if (temphex.getProvince().builtBuildings == null) System.out.println("LISTA NULL");
                    if (temphex.getProvince().builtBuildings.isEmpty()) System.out.println("LISTA PUSTA");
                    //System.out.println(temphex.getProvince().build);
                    List<String> tempList = new ArrayList<>(temphex.getProvince().getPossibleBuildings());
                    tempList.remove(baseBuilding);
                    hexClick(temphex, temp);
                });
                if(Objects.equals(temphex.getProvince().getType(), "RiversideArea")){
                    if(!temphex.getProvince().builtBuildings.contains("Irrigation System")){
                        buttonOffset[0] = 10;
                        return;
                    }
                }


                provinceLowerPanel.getChildren().add(baseBuildingButton);
            });

            //possible buildings
            temphex.getProvince().getPossibleBuildings().forEach(building -> {
                Button possibleBuildingButton = new Button(building);
                possibleBuildingButton.setId(building);
                possibleBuildingButton.getStyleClass().add("building");
                temphex.getProvince().builtBuildings.forEach(builtBuilding -> {
                    if (Objects.equals(building, builtBuilding)) {
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
                    if (!temphex.getProvince().builtBuildings.contains(building)){
                        temphex.getProvince().builtBuildings.add(building);
                        String buildingNoSpaces = building.replaceAll("\\s+","");
                        if(buildingNoSpaces.equals("CatchingBoars")) buildingNoSpaces = "CatchingBoar";
                        buyBuilding(temphex.getProvince(), buildingNoSpaces);
                    }

                    possibleBuildingButton.getStyleClass().add("builtBuilding");
                    if (temphex.getProvince().builtBuildings == null) System.out.println("LISTA NULL");
                    if (temphex.getProvince().builtBuildings.isEmpty()) System.out.println("LISTA PUSTA");
                    //System.out.println(temphex.getProvince().builtBuildings);
                    List<String> tempList = new ArrayList<>(temphex.getProvince().getPossibleBuildings());
                    tempList.remove(building);
                    hexClick(temphex, temp);
                });

                provinceLowerPanel.getChildren().add(possibleBuildingButton);
            });


            provinceLowerPanel.setPrefHeight(resourcesOffset[0] + buttonOffset[0] + 200);
        }
        if (buyInitialised) {


            buyField(temphex);
        }
        if (colonizeInitialised) {
            colonize(temphex.getQ(), temphex.getR());
        }


    }

    void buyBuilding(Province province, String building){
        //province.builtBuildings.add(building);
        switch (building) {
            case "AmberCollector":
                province.builtBuildingsVector.add(new AmberCollector());
                break;
            case "Barracks":
                province.builtBuildingsVector.add(new Barracks());
                break;
            case "BronzeMine":
                province.builtBuildingsVector.add(new BronzeMine());
                break;
            case "Caravan":
                province.builtBuildingsVector.add(new Caravan());
                break;
            case "CatchingBoar":
                province.builtBuildingsVector.add(new CatchingBoar());
                break;
            case "CowBreeding":
                province.builtBuildingsVector.add(new CowBreeding());
                break;
            case "Deforestation":
                province.builtBuildingsVector.add(new Deforestation());
                break;
            case "Farm":
                province.builtBuildingsVector.add(new Farm());
                break;
            case "Fishermen":
                province.builtBuildingsVector.add(new Fishermen());
                break;
            case "GoldMine":
                province.builtBuildingsVector.add(new GoldMine());
                break;
            case "HorseBreeding":
                province.builtBuildingsVector.add(new HorseBreeding());
                break;
            case "Hunter":
                province.builtBuildingsVector.add(new Hunter());
                break;
            case "IronMine":
                province.builtBuildingsVector.add(new IronMine());
                break;
            case "IrrigationSystem":
                province.builtBuildingsVector.add(new IrrigationSystem());
                break;
            case "Lighthouse":
                province.builtBuildingsVector.add(new Lighthouse());
                break;
            case "Market":
                province.builtBuildingsVector.add(new Market());
                break;
            case "PigBreeding":
                province.builtBuildingsVector.add(new PigBreeding());
                break;
            case "Pyramid":
                province.builtBuildingsVector.add(new Pyramid());
                break;
            case "ResidentialDistrict":
                province.builtBuildingsVector.add(new ResidentialDistrict());
                break;
            case "RiversideFarm":
                province.builtBuildingsVector.add(new RiversideFarm());
                break;
            case "Sawmill":
                province.builtBuildingsVector.add(new Sawmill());
                break;
            case "ScarletFishermen":
                province.builtBuildingsVector.add(new ScarletFishermen());
                break;
            case "SeaFoodCollector":
                province.builtBuildingsVector.add(new SeaFoodCollector());
                break;
            case "Temple":
                province.builtBuildingsVector.add(new Temple());
                break;
            case "Warehouse":
                province.builtBuildingsVector.add(new Warehouse());
                break;
        }
        province.setBuildingsProduction();
    }

    void armiesClicked(City city)
    {
        provinceLowerPanel.getChildren().clear();
        final int[] unitY = {0};
        city.army.forEach(army -> {
            Button a = new Button(army.getName());
            a.setTranslateY(unitY[0]);
            a.setPrefWidth(299);
            unitY[0] += 60;
            a.setOnMouseClicked(e2 -> {
                singleArmyClicked(army);
            });
            provinceLowerPanel.getChildren().add(a);
        });
        Button newArmy = new Button("Add army");
        newArmy.setTranslateY(unitY[0]);
        newArmy.setPrefWidth(299);
        newArmy.setOnMouseClicked(e1 -> {
            Army addNewArmy = new Army();
            city.addArmy(addNewArmy);

            provinceLowerPanel.getChildren().clear();
            unitY[0] = 0;
            city.army.forEach(army -> {
                Button a = new Button(army.getName());
                a.setTranslateY(unitY[0]);
                a.setPrefWidth(299);
                a.setOnMouseClicked(e2 -> {
                    provinceLowerPanel.getChildren().clear();
                    singleArmyClicked(army);
                });
                unitY[0] += 60;
                newArmy.setTranslateY(unitY[0]);
                provinceLowerPanel.getChildren().add(a);
                provinceLowerPanel.getChildren().remove(newArmy);
                provinceLowerPanel.getChildren().add(newArmy);
            });
        });
        if(!provinceLowerPanel.getChildren().contains(newArmy))provinceLowerPanel.getChildren().add(newArmy);
    }

    void singleArmyClicked(Army army)
    {
        provinceLowerPanel.getChildren().clear();

        TextField armyName = new TextField("" + army.getName());
        armyName.setTranslateY(5);
        armyName.setPrefWidth(299);
        armyName.getStyleClass().add("lvlText");
        provinceLowerPanel.getChildren().add(armyName);

        //ARCHERS AMOUNT
        TextField archersAmount = new TextField("" + army.getArchersAmount());
        archersAmount.getStyleClass().add("armyArcher");
        archersAmount.setTranslateY(35);
        archersAmount.setTranslateX(55);
        archersAmount.setFont(Font.font(font, 16));
        archersAmount.setPrefWidth(50);
        archersAmount.setEditable(false);
        archersAmount.setAlignment(Pos.BOTTOM_RIGHT);

        //CHARIOTS AMOUNT
        TextField chariotsAmount = new TextField("" + army.getChariotsAmount());
        chariotsAmount.getStyleClass().add("armyChariot");
        chariotsAmount.setTranslateY(35);
        chariotsAmount.setTranslateX(105);
        chariotsAmount.setFont(Font.font(font, 16));
        chariotsAmount.setPrefWidth(80);
        chariotsAmount.setEditable(false);
        chariotsAmount.setAlignment(Pos.BOTTOM_RIGHT);

        //WARRIORS AMOUNT
        TextField warriorsAmount = new TextField("" + army.getWarriorsAmount());
        warriorsAmount.getStyleClass().add("armyWarrior");
        warriorsAmount.setTranslateY(35);
        warriorsAmount.setTranslateX(185);
        warriorsAmount.setFont(Font.font(font, 16));
        warriorsAmount.setPrefWidth(50);
        warriorsAmount.setEditable(false);
        warriorsAmount.setAlignment(Pos.BOTTOM_RIGHT);

        Button recruitArchers = new Button("Recruit Archers");
        recruitArchers.setTranslateY(75);
        recruitArchers.setPrefWidth(299);
        recruitArchers.setOnMouseClicked(e3 -> {
            army.addUnit(new Archer());
            provinceLowerPanel.getChildren().clear();
            singleArmyClicked(army);
        });

        Button recruitChariots = new Button("Recruit Chariots");
        recruitChariots.setTranslateY(135);
        recruitChariots.setPrefWidth(299);
        recruitChariots.setOnMouseClicked(e3 -> {
            army.addUnit(new Chariots());
            provinceLowerPanel.getChildren().clear();
            singleArmyClicked(army);
        });

        Button recruitInfantry = new Button("Recruit Infantry");
        recruitInfantry.setTranslateY(195);
        recruitInfantry.setPrefWidth(299);
        recruitInfantry.setOnMouseClicked(e3 -> {
            army.addUnit(new Infantry());
            provinceLowerPanel.getChildren().clear();
            singleArmyClicked(army);
        });

        Button upgradeArchers = new Button("Upgrade Archers");
        upgradeArchers.setTranslateY(255);
        upgradeArchers.setPrefWidth(299);
        upgradeArchers.setOnMouseClicked(e3 -> {
            upgradeUnitClicked(army, "Archer");
        });
        final int[] isArchers = {0};
        army.getUnits().forEach(unit -> {
            if (Objects.equals(unit.getName(), "Archer")) isArchers[0] = 1;
        });
        upgradeArchers.setDisable(isArchers[0] != 1);

        Button upgradeChariots = new Button("Upgrade Chariots");
        upgradeChariots.setTranslateY(315);
        upgradeChariots.setPrefWidth(299);
        upgradeChariots.setOnMouseClicked(e3 -> {
            upgradeUnitClicked(army, "Chariots");
        });
        final int[] isChariots = {0};
        army.getUnits().forEach(unit -> {
            if (Objects.equals(unit.getName(), "Chariots")) isChariots[0] = 1;
        });
        upgradeChariots.setDisable(isChariots[0] != 1);

        Button upgradeInfantry = new Button("Upgrade Infantry");
        upgradeInfantry.setTranslateY(375);
        upgradeInfantry.setPrefWidth(299);
        upgradeInfantry.setOnMouseClicked(e3 -> {

            upgradeUnitClicked(army, "Infantry");
        });
        final int[] isInfantry = {0};
        army.getUnits().forEach(unit -> {
            if (Objects.equals(unit.getName(), "Infantry")) isInfantry[0] = 1;
        });
        upgradeInfantry.setDisable(isInfantry[0] != 1);


        Button sentArmyToSiege = new Button("Sent army to siege");
        sentArmyToSiege.setTranslateY(435);
        sentArmyToSiege.setPrefWidth(299);
        sentArmyToSiege.setOnMouseClicked(e3 -> {
            provinceLowerPanel.getChildren().clear();
            singleArmyClicked(army);
        });
        sentArmyToSiege.setDisable(army.getUnits().size() == 0);

        provinceLowerPanel.getChildren().add(archersAmount);
        provinceLowerPanel.getChildren().add(chariotsAmount);
        provinceLowerPanel.getChildren().add(warriorsAmount);

        provinceLowerPanel.getChildren().add(recruitArchers);
        provinceLowerPanel.getChildren().add(recruitChariots);
        provinceLowerPanel.getChildren().add(recruitInfantry);

        provinceLowerPanel.getChildren().add(upgradeArchers);
        provinceLowerPanel.getChildren().add(upgradeChariots);
        provinceLowerPanel.getChildren().add(upgradeInfantry);

        provinceLowerPanel.getChildren().add(sentArmyToSiege);

    }

    public void upgradeUnitClicked(Army army, String unitName)
    {
        provinceLowerPanel.getChildren().clear();

        int lvl0Units = 0;
        int lvl1Units = 0;
        int lvl2Units = 0;
        int lvl3Units = 0;

        final int[] lvls = {0,0,0,0};

        army.getUnits().forEach(u -> {
            if(Objects.equals(u.getName(), unitName))
            {
                if(u.getLvl() == 0)lvls[0]+=1;
                if(u.getLvl() == 1)lvls[1]+=1;
                if(u.getLvl() == 2)lvls[2]+=1;
                if(u.getLvl() == 3)lvls[3]+=1;
            }
        });
        System.out.println(Arrays.toString(lvls));

        TextField lvl0Label = new TextField("LVL 1");
        lvl0Label.setTranslateY(10);
        lvl0Label.setTranslateX(30);
        lvl0Label.setPrefWidth(60);
        lvl0Label.setEditable(false);
        lvl0Label.setAlignment(Pos.CENTER);
        lvl0Label.getStyleClass().add("lvlText");

        TextField lvl0 = new TextField("" + lvls[0]);
        lvl0.setTranslateY(30);
        lvl0.setTranslateX(30);
        lvl0.setPrefWidth(60);
        lvl0.setEditable(false);
        lvl0.setAlignment(Pos.CENTER);
        lvl0.getStyleClass().add("lvl");

        TextField lvl1Label = new TextField("LVL 2");
        lvl1Label.setTranslateY(10);
        lvl1Label.setTranslateX(90);
        lvl1Label.setPrefWidth(60);
        lvl1Label.setEditable(false);
        lvl1Label.setAlignment(Pos.CENTER);
        lvl1Label.getStyleClass().add("lvlText");

        TextField lvl1 = new TextField("" + lvls[1]);
        lvl1.setTranslateY(30);
        lvl1.setTranslateX(90);
        lvl1.setPrefWidth(60);
        lvl1.setEditable(false);
        lvl1.setAlignment(Pos.CENTER);
        lvl1.getStyleClass().add("lvl");

        TextField lvl2Label = new TextField("LVL 3");
        lvl2Label.setTranslateY(10);
        lvl2Label.setTranslateX(150);
        lvl2Label.setPrefWidth(60);
        lvl2Label.setEditable(false);
        lvl2Label.setAlignment(Pos.CENTER);
        lvl2Label.getStyleClass().add("lvlText");

        TextField lvl2 = new TextField("" + lvls[2]);
        lvl2.setTranslateY(30);
        lvl2.setTranslateX(150);
        lvl2.setPrefWidth(60);
        lvl2.setEditable(false);
        lvl2.setAlignment(Pos.CENTER);
        lvl2.getStyleClass().add("lvl");

        TextField lvl3Label = new TextField("LVL 4");
        lvl3Label.setTranslateY(10);
        lvl3Label.setTranslateX(210);
        lvl3Label.setPrefWidth(60);
        lvl3Label.setEditable(false);
        lvl3Label.setAlignment(Pos.CENTER);
        lvl3Label.getStyleClass().add("lvlText");

        TextField lvl3 = new TextField("" + lvls[3]);
        lvl3.setTranslateY(30);
        lvl3.setTranslateX(210);
        lvl3.setPrefWidth(60);
        lvl3.setEditable(false);
        lvl3.setAlignment(Pos.CENTER);
        lvl3.getStyleClass().add("lvl");






        provinceLowerPanel.getChildren().add(lvl0Label);
        provinceLowerPanel.getChildren().add(lvl1Label);
        provinceLowerPanel.getChildren().add(lvl2Label);
        provinceLowerPanel.getChildren().add(lvl3Label);

        provinceLowerPanel.getChildren().add(lvl0);
        provinceLowerPanel.getChildren().add(lvl1);
        provinceLowerPanel.getChildren().add(lvl2);
        provinceLowerPanel.getChildren().add(lvl3);

        Button upgradeToLvl1 = new Button("Upgrade to lvl 2");
        upgradeToLvl1.setTranslateY(80);
        upgradeToLvl1.setPrefWidth(299);
        upgradeToLvl1.setOnMouseClicked(e3 -> {
            for (int i = 0; i < army.getUnits().size(); i++) {
                ArmyUnit item = army.getUnits().get(i);
                if (Objects.equals(item.getName(), unitName)) {
                    if (item.getLvl() == 0) {
                        item.setLvl(1);
                        break;
                    }
                    System.out.println("NO MORE UNITS TO UPGRADE TO LVL 1!");
                }

            }
            upgradeUnitClicked(army, unitName);
        });

        Button upgradeToLvl2 = new Button("Upgrade to lvl 3");
        upgradeToLvl2.setTranslateY(140);
        upgradeToLvl2.setPrefWidth(299);
        upgradeToLvl2.setOnMouseClicked(e3 -> {
            for (int i = 0; i < army.getUnits().size(); i++) {
                ArmyUnit item = army.getUnits().get(i);
                if (Objects.equals(item.getName(), unitName)) {
                    if (item.getLvl() == 1) {
                        item.setLvl(2);
                        break;
                    }
                    System.out.println("NO MORE UNITS TO UPGRADE TO LVL 2!");
                }

            }
            upgradeUnitClicked(army, unitName);
        });

        Button upgradeToLvl3 = new Button("Upgrade to lvl 4");
        upgradeToLvl3.setTranslateY(200);
        upgradeToLvl3.setPrefWidth(299);
        upgradeToLvl3.setOnMouseClicked(e3 -> {
            for (int i = 0; i < army.getUnits().size(); i++) {
                ArmyUnit item = army.getUnits().get(i);
                if (Objects.equals(item.getName(), unitName)) {
                    if (item.getLvl() == 2) {
                        item.setLvl(3);
                        break;
                    }
                    System.out.println("NO MORE UNITS TO UPGRADE TO LVL 3!");
                }

            }
            upgradeUnitClicked(army, unitName);
        });

        Button back = new Button("Back");
        back.setTranslateY(260);
        back.setPrefWidth(299);
        back.setOnMouseClicked(e -> {
            singleArmyClicked(army);
        });

        provinceLowerPanel.getChildren().add(upgradeToLvl1);
        provinceLowerPanel.getChildren().add(upgradeToLvl2);
        provinceLowerPanel.getChildren().add(upgradeToLvl3);
        provinceLowerPanel.getChildren().add(back);
    }


        private void shortcuts (KeyEvent event){
            if(!victoryAchieved) {
                switch (event.getCode()) {
                    case SUBTRACT:
                    case UNDERSCORE:
                    case MINUS:
                        map.sizeDown();
                        break;
                    case ADD:
                    case EQUALS:
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
                    case X:
                        currentPlayer.setFaith(500);
                        currentPlayer.setGold(500);
                        currentPlayer.setBuildingResources(500);
                        currentPlayer.setBronze(500);
                        currentPlayer.setIron(500);
                        currentPlayer.setHorses(500);
                        currentPlayer.setDyes(500);
                        break;
                    case M:
                        nextSong();
                        break;
                }
            }
        }


        @FXML
        private TextField nextSong;
        @FXML
        void nextSong () {
            musicPlayerInstance.stopMusic();
        }


        @FXML
        void backToMainMenuFromBoard (MouseEvent event){
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


        void soundPlayerPlaySound (String filename){
            if (!soundPlayed) {
                soundPlayed = true;
                file = new File(filename);
                media = new Media(file.toURI().toString());
//                mediaPlayer = null;
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(soundVolume);
                mediaPlayer.play();
                mediaPlayer.setOnEndOfMedia(() -> {
                    soundPlayed = false;
                });

            }
        }

        private void colonize ( int iFrom, int jFrom){

            soundPlayerPlaySound(gameButtonSound);

            if (buyInitialised) {
                buyClicked();
                cityCoordinatesLock = false;
            }

            if (!colonizeInitialised) {
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
                iColonize = iFrom;
                jColonize = jFrom;
                colonizeInitialised = true;
            } else {
                if (iFrom != iColonize || jFrom != jColonize) {

                    if (!Objects.equals(map.getHexagon(iFrom, jFrom).getProvince().getType(), "Sea") && !Objects.equals(map.getHexagon(iFrom, jFrom).getProvince().getType(), "Mountains")) {

                        if (map.getHexagon(iFrom, jFrom).getBorderColor() == Color.PINK) {


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
        void buyClicked () {
            buyingMode = !buyingMode;

            if (!buyingMode) {
                buyInitialised = false;
//            cityCoordinatesLock = false;
//            fullMapBorderCleaning();
//            colonize(initialisedI,initialisedJ);
//            colonize(initialisedI,initialisedJ);
                initialisedI = -11;
                initialisedJ = -11;
            }
        }

        void fullMapBorderCleaning () {
            int jSetter = 1, jLimiter = 46;
            for (int i2 = 1; i2 < 35; i2++) {
                if (i2 % 2 == 0) {
                    jSetter--;
                    jLimiter--;
                }
                for (int j2 = jSetter; j2 < jLimiter; j2++) {
                    if (i2 % 2 == 1 && j2 == jLimiter - 1) continue;

                    switch (map.getHexagon(j2, i2).getProvince().getOwnerId()) {
                        case 0:
                            color = rgb(2, 0, 36, 1);
                            map.getHexagon(j2, i2).borderColor(color);
                            break;
                        case 1:
                            color = Color.AQUAMARINE;
                            map.getHexagon(j2, i2).borderColor(color);
                            break;
                        case 2:
                            color = Color.YELLOW;
                            map.getHexagon(j2, i2).borderColor(color);
                            break;
                        case 3:
                            color = Color.RED;
                            map.getHexagon(j2, i2).borderColor(color);
                            break;
                    }
                }
            }
        }


        private void buyField (Hexagon tempname){
            soundPlayerPlaySound(gameButtonSound);

            if (colonizeInitialised) {
                colonizeInitialised = false;
                fullMapBorderCleaning();
            }

            int i = tempname.getQ();
            int j = tempname.getR();

            if (buyInitialised && (map.getHexagon(i, j).getBorderColor() != Color.PINK)) {
                fullMapBorderCleaning();
                buyClicked();
                return;
            }

            if (!buyInitialised && (tempname.getProvince().getOwnerId() != playerId)) {
                buyClicked();
                fullMapBorderCleaning();
                return;
            }

            if (!cityCoordinatesLock && Objects.equals(map.getHexagon(i, j).getProvince().getType(), "City")) {
                cityCoordinatesLock = true;
                buyInitialised = true;
                initialisedI = i;
                initialisedJ = j;

                for (int tempI = initialisedI - 3; tempI < initialisedI + 4; tempI++) {
                    for (int tempJ = initialisedJ - 3; tempJ < initialisedJ + 4; tempJ++) {
                        if ((map.getHexagon(tempI, tempJ - 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI, tempJ + 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI + 1, tempJ - 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI - 1, tempJ + 1).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI + 1, tempJ).getProvince().getOwnerId() == ownerId) || (map.getHexagon(tempI - 1, tempJ).getProvince().getOwnerId() == ownerId)) {
                            if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                                if (((Math.abs(initialisedI - tempI) > 3) || (Math.abs(initialisedJ - tempJ) > 3)) || (((tempI < initialisedI) && (tempJ < initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || (((tempI > initialisedI) && (tempJ > initialisedJ) && (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 4) || ((tempJ == initialisedJ) && (Math.abs(initialisedI - tempI) == 3)) || ((tempI == initialisedI) && (Math.abs(initialisedJ - tempJ) == 3)) || (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 6 || (Math.abs(initialisedI - tempI) + Math.abs(initialisedJ - tempJ)) == 7))) {
                                    continue;
                                }
                                if (map.getHexagon(tempI, tempJ).getProvince().getOwnerId() == 0) {
                                    map.getHexagon(tempI, tempJ).borderColor(Color.PINK);
                                }
                            }
                        }
                    }
                }
                return;
            }
            double provinceCostMultiplier = 1;
            if (currentPlayer.id == 3) {
                provinceCostMultiplier = 0.8;
            }

            if (!goldVSfaithBuy) {
                if (currentPlayer.getGold() >= PROVINCE_COST * provinceCostMultiplier) {
                    currentPlayer.setGold(currentPlayer.getGold() - PROVINCE_COST * provinceCostMultiplier);
                    goldField.setText("" + (int) currentPlayer.getGold());
                } else {
                    buyingMode = false;
                    buyInitialised = false;
                    cityCoordinatesLock = false;
                    fullMapBorderCleaning();
                    return;
                }
            } else {
                if (currentPlayer.getFaith() >= PROVINCE_COST * provinceCostMultiplier) {
                    currentPlayer.setFaith(currentPlayer.getFaith() - PROVINCE_COST * provinceCostMultiplier);
                    beliefField.setText("" + (int) currentPlayer.getFaith());
                } else {
                    buyingMode = false;
                    buyInitialised = false;
                    cityCoordinatesLock = false;
                    fullMapBorderCleaning();
                    return;
                }
            }
            if (map.getHexagon(i, j).getBorderColor() != Color.PINK) {
                cityCoordinatesLock = false;
                buyingMode = false;
                fullMapBorderCleaning();
                return;
            }

            map.getHexagon(i, j).getProvince().setOwnerId(ownerId);
            City temoCity = (City) map.getHexagon(initialisedI, initialisedJ).getProvince();
            temoCity.assignProvince(map.getHexagon(i, j).getProvince());
            switch (map.getHexagon(i, j).getProvince().getOwnerId()) {
                case 0:
                    color = rgb(2, 0, 36, 1);

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
        }
    }

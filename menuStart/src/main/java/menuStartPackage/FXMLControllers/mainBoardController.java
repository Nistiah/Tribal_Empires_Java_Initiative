package menuStartPackage.FXMLControllers;

import hexagons.src.main.java.com.prettybyte.hexagons.Hexagon;
import hexagons.src.main.java.com.prettybyte.hexagons.HexagonMap;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

import static menuStartPackage.menuStart.musicPlayerInstance;


public class mainBoardController {

    private Color colorPick = Color.WHITE;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public Button generateHexagonMap;
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

    @FXML
    void addHex(ActionEvent event1) {
        HexagonMap map = new HexagonMap(40);
        //map.setRenderCoordinates(true);
        int nibyzero = 1, niby30 = 47;
        for (int i = 1; i < 36; i++) {
            if (i % 2 == 0) {
                nibyzero--;
                niby30--;
            }
            for (int j = nibyzero; j < niby30; j++) {
                if (i % 2 == 1 && j == niby30 - 1) continue;

                Hexagon temphex = new Hexagon(j, i);
                temphex.setOnMouseClicked(MouseEvent -> {
                    textField.setText(temphex.getQ() + ":" + temphex.getR());
                    temphex.setFill(colorPick);
                });
                temphex.setOnMouseMoved(MouseEvent -> {
                    textField.setText(temphex.getQ() + ":" + temphex.getR());
                    temphex.setFill(colorPick);
                });
                if (i % 2 == 0) {
                    temphex.setFill(Color.PINK);
                }
                if (j % 2 == 0) {
                    temphex.setFill(Color.YELLOW);
                }
                if (i % 2 == 0 && j % 2 == 0) {
                    temphex.setFill(Color.GOLD);
                }
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
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    @FXML
    private ScrollPane scrollPane;




    @FXML
    void mapMover(KeyEvent event) {


    }

}

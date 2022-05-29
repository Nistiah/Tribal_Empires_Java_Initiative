package menuStartPackage.FXMLControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;
//import static menuStartPackage.FXMLControllers.MainBoardController.


public class StatsController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void backToMainMenuFromStats(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
    }
    public static Vector<MainBoardController.PlayerData> playerStats = new Vector<>();

    public static void addPlayer(MainBoardController.PlayerData tmp){
        playerStats.add(tmp);
    }

    @FXML
    private LineChart<String, Integer> chart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        Integer i=0;
        for (Integer element:playerStats.get(0).goldPerTour) {
            series1.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }
        i=0;
        for (Integer element:playerStats.get(1).goldPerTour) {
            series2.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }
        i=0;
        for (Integer element:playerStats.get(2).goldPerTour) {
            series3.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }

        chart.getData().addAll(series1,series2,series3);

    }
}

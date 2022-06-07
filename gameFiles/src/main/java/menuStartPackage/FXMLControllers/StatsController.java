package menuStartPackage.FXMLControllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.Vector;

//import static menuStartPackage.FXMLControllers.MainBoardController.

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

import static menuStartPackage.FXMLControllers.MainBoardController.playerList;

public class StatsController implements Initializable {
    public static Vector<MainBoardController.PlayerData> playerStats = new Vector<>();
    private Stage                                        stage;
    private Scene                                        scene;
    private Parent                                       root;
    private XYChart.Series series1= new XYChart.Series();
    private XYChart.Series series2= new XYChart.Series();
    private XYChart.Series series3= new XYChart.Series();
    private XYChart.Series series4= new XYChart.Series();
    private XYChart.Series series5= new XYChart.Series();
    private XYChart.Series series6= new XYChart.Series();
    @FXML
    private LineChart<String, Integer>                   chart1;
    @FXML
    private LineChart<String, Integer>                   chart2;

    public static void addPlayer(MainBoardController.PlayerData tmp) {
        playerStats.add(tmp);
    }

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            playerStats.get(0);
            playerStats.get(1);
            playerStats.get(2);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }


        Integer        i       = 0;
        series1.setName(playerList.get(0).getName());
        series2.setName(playerList.get(1).getName());
        series3.setName(playerList.get(2).getName());
        series4.setName(playerList.get(0).getName());
        series5.setName(playerList.get(1).getName());
        series6.setName(playerList.get(2).getName());

        for (Integer element : playerStats.get(0).goldPerTour) {
            series1.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }

        i = 0;
        for (Integer element : playerStats.get(0).numberOfProvincesPerTour) {
            series4.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }

        i = 0;

        for (Integer element : playerStats.get(1).goldPerTour) {
            series2.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }

        i = 0;
        for (Integer element : playerStats.get(1).numberOfProvincesPerTour) {
            series5.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }

        i = 0;

        for (Integer element : playerStats.get(2).goldPerTour) {
            series3.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }

        i = 0;

        for (Integer element : playerStats.get(2).numberOfProvincesPerTour) {
            series6.getData().add(new XYChart.Data<String, Integer>(i.toString(), element));
            i++;
        }




        chart2.setTitle("Cities per Turn");
        chart1.setTitle("Gold Per Turn");

        chart1.getData().addAll(series1, series2, series3);
        chart2.getData().addAll(series4, series5, series6);
    }


    @FXML
    void citiesPerTurn() {
        chart1.setVisible(false);
        chart2.setVisible(true);


    }

    @FXML
    void goldPerTurn() {
        chart1.setVisible(true);
        chart2.setVisible(false);
    }


}

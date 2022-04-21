package antiquity.antiquity_congregate;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static antiquity.antiquity_congregate.HelloApplication.screenBounds;

public class HelloController {
    private Color colorboard[] = {Color.YELLOW, Color.BROWN, Color.GREEN};
    private int colorPointer = 0;
 //   private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private static final int TILE_SIZE = 30;
    private static final int W = (int) screenBounds.getWidth()-100;
    private static final int H = (int) screenBounds.getHeight()-100;
    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;
    public Polygon polygon[][];
    public Polygon polygon01;
    private Color colorPick=Color.WHITE;
    public Button but1 = new Button("żółty");
    public Button but2 = new Button("brazowy");
    public Button but3 = new Button("zielony");
    public SplitPane rightBar = new SplitPane();

    @FXML
    public ColorPicker colorPicker = new ColorPicker();


    @FXML
    protected void but1() {
        colorPointer=0;
        System.out.println("żółty");
    }
    @FXML
    protected void but2() {
        colorPointer=1;
        System.out.println("brazowy");
    }
    @FXML
    protected void but3() {
        colorPointer=2;
        System.out.println("zielony");
    }

    @FXML
    void but4(ActionEvent event) {
        colorPick=colorPicker.getValue();

    }

    @FXML
    void onHelloHexClick(MouseEvent event) {
        Polygon temp = (Polygon) event.getPickResult().getIntersectedNode();
        temp.setFill(colorPick);

    }




//    public Button ColorChange(int localColorPointer, String name){
//        Button button = new Button(name);
//        button.setAlignment(Pos.TOP_LEFT);
//        button.setPrefWidth(200);
//        button.applyCss();
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//                colorPointer = localColorPointer;
//            }
//        });
//        return button;
//    }



//    private class Tile extends StackPane {
//        private int i, j;
//        private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
//        private Text text = new Text();
        //        private Polygon hex = new Polygon().getPoints().addAll(new Double[]{
//                1,1,
//                2,1,
//                2*(3.0/2.0),2*Math.sqrt(3)/2.0,
//                2,2*Math.sqrt(3),
//                1,2*Math.sqrt(3),
//                1-(1/2.0),2*Math.sqrt(3)/2.0
//        });
//        public Tile(int i, int j) {
//            this.i = i;
//            this.j = j;
//            border.setFill(Color.WHITE);
//            border.setStroke(Color.DARKCYAN);
//            text.setFont(Font.font(18));
//            text.setVisible(true);
//            getChildren().addAll(border, text);
//            setTranslateX(i * TILE_SIZE);
//            setTranslateY(j * TILE_SIZE);
//            setOnMouseClicked(e -> open());
//        }
//        public void open() {
//            //border.setFill(colorboard[colorPointer]);
//        }
//    }
//    private Parent createBoard() {
//        Pane root = new Pane();
//        root.setPrefSize(W, H);
//        for (int y = 0; y < Y_TILES; y++) {
//            for (int x = 0; x < X_TILES; x++) {
//                Tile tile = new Tile(x, y);
//                grid[x][y] = tile;
//                root.getChildren().add(tile);
//            }
//        }
//        return root;
//    }
}
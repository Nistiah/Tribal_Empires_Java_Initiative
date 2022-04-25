module menuStart {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.logging;


    opens menuStartPackage to javafx.fxml;
    exports menuStartPackage;
    exports menuStartPackage.FXMLControllers;
    opens menuStartPackage.FXMLControllers to javafx.fxml;
}
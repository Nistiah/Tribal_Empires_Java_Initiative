module gameFiles {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.logging;


    opens menuStartPackage to javafx.fxml;
    exports menuStartPackage;
    exports menuStartPackage.FXMLControllers;
    exports menuStartPackage.Prowincje;
    exports menuStartPackage.Jednostki;
    opens menuStartPackage.FXMLControllers to javafx.fxml;
}
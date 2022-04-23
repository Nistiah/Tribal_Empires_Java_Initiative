module menuStart {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires mixite.core.jvm;


    opens menuStartPackage to javafx.fxml;
    exports menuStartPackage;
}
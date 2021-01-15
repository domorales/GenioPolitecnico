module com.espol.geniopolitecnico {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;
    requires javafx.graphicsEmpty;

    opens com.espol.geniopolitecnico to javafx.fxml;
    exports com.espol.geniopolitecnico;
    opens com.espol.controller to javafx.fxml;
    exports com.espol.controller;
    exports com.espol.model;
}

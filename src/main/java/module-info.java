module aqil.atomicbomber {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires java.sql;
    requires mysql.connector.java;


    opens aqil.atomicbomber to javafx.fxml;
    exports aqil.atomicbomber;
    exports aqil.atomicbomber.view;
    opens aqil.atomicbomber.view to javafx.fxml;
    exports aqil.atomicbomber.model;
    opens aqil.atomicbomber.model to javafx.fxml;
}
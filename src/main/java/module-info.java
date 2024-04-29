module aqil.atomicbomber {
    requires javafx.controls;
    requires javafx.fxml;


    opens aqil.atomicbomber to javafx.fxml;
    exports aqil.atomicbomber;
}
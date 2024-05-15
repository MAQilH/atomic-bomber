package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import javafx.stage.Stage;

public class MenuLoader {
    public static void setMenu(Menu menu) {
        Stage stage = App.getInstance().getStage();
        stage.setScene(menu.getScene());
        stage.centerOnScreen();
    }
}

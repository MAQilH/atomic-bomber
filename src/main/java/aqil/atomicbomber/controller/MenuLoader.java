package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import javafx.stage.Stage;

public class MenuLoader {
    private static Menu currentMenu = null;
    public static void setMenu(Menu menu) {
        Stage stage = App.getInstance().getStage();
        menu.getMenuController().reload(currentMenu);
        currentMenu = menu;
        stage.setScene(menu.getScene());
        stage.centerOnScreen();
    }
}

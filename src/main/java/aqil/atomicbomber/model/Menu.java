package aqil.atomicbomber.model;

import aqil.atomicbomber.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public enum Menu {
    PAUSE_MENU("pause-menu.fxml"),
    LOGIN_MENU("login-menu.fxml"),
    SIGNUP_MENU("signup-menu.fxml"),
    MAIN_MENU("main-menu.fxml"),
    SETTING_MENU("setting-menu.fxml"),
    SCOREBOARD_MENU("scoreboard-menu.fxml");

    private final String address;
    private Pane root;
    private Scene scene = null;

    Menu(String address){
        this.address = address;
    }

    public static void loader(){
        for(Menu menu : Menu.values()) {
            try {
                menu.root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/aqil/atomicbomber/FXML/" + menu.address)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Pane getRoot() {
        return root;
    }

    public Scene getScene() {
        if(scene == null){
            scene = new Scene(root);
        }
        return scene;
    }

}

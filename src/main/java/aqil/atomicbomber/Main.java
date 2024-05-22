package aqil.atomicbomber;

import aqil.atomicbomber.controller.Database;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.User;
import aqil.atomicbomber.view.GameLauncher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.TRANSPARENT);
        Menu.loader();
        App.getInstance().setStage(stage);
        MenuLoader.setMenu(Menu.LOGIN_MENU);
        initializeStage(stage);
        stage.show();
    }

    public void initializeStage(Stage stage){
        Image image = new Image(getClass().getResource("/Images/game-icon.png").toExternalForm());
        stage.getIcons().add(image);
        stage.setTitle("Atomic Bomber");
    }

    public static void main(String[] args) {
        launch();
    }
}
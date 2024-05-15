package aqil.atomicbomber;

import aqil.atomicbomber.controller.Database;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.User;
import aqil.atomicbomber.view.GameLauncher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Menu.loader();
        App.getInstance().setStage(stage);
        MenuLoader.setMenu(Menu.MAIN_MENU);
        stage.show();
//        GameLauncher gameLauncher = new GameLauncher();
//        App.getInstance().setGameLauncher(gameLauncher);
//        App.getInstance().setStage(stage);
//        gameLauncher.start(stage);
//
//        Database database = Database.getInstance();
//
//        User user = new User(0, "aqil", "1234");
//        System.out.println(database.getUserWithUsername("aqil").getId());
//
//        URL url = HelloMenu.class.getResource("/FXML/Hello.fxml");
//        URL url = Main.class.getResource("/aqil/atomicbomber/FXML/pause-menu.fxml");
//        Menu.loader();
//        Pane pane = Menu.PAUSE_MENU.getRoot();
//        Scene scene = new Scene(pane);

    }



    public static void main(String[] args) {
        launch();
    }
}
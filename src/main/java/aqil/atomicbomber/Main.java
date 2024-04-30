package aqil.atomicbomber;

import aqil.atomicbomber.model.App;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        App.getInstance().setStage(stage);

        URL url = Main.class.getResource("/aqil/atomicbomber/FXML/avatar-menu.fxml");

        assert url != null;
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setScene(scene);

        stage.setTitle("Atomic Bomber");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
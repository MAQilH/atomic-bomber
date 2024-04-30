package aqil.atomicbomber.view;

import aqil.atomicbomber.Main;
import aqil.atomicbomber.model.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public void click(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Pane pane =  FXMLLoader.load(Main.class.getResource("/aqil/atomicbomber/by-view.fxml"));
        stage.setScene(new Scene(pane));
        stage.show();
    }
}

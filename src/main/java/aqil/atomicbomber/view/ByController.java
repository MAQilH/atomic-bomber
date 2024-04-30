package aqil.atomicbomber.view;

import aqil.atomicbomber.Main;
import aqil.atomicbomber.model.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ByController {

    public void click(ActionEvent actionEvent) throws IOException {
        Stage stage = App.getInstance().getStage();
        Pane pane =  FXMLLoader.load(Main.class.getResource("/aqil/atomicbomber/hello-view.fxml"));
        stage.setScene(new Scene(pane));
    }
}

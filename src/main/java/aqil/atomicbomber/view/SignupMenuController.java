package aqil.atomicbomber.view;

import aqil.atomicbomber.model.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupMenuController implements Initializable {
    public Label username_error_lbl;
    public Label password_error_lbl;
    public VBox username_count;
    public VBox password_count;
    public ImageView avatar_img;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_count.getChildren().remove(username_error_lbl);
        password_count.getChildren().remove(password_error_lbl);

        avatar_img.setOnDragOver(event -> {
            if (event.getGestureSource() != avatar_img && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        avatar_img.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                File file = db.getFiles().get(0);
                Image image = new Image(file.toURI().toString());
                avatar_img.setImage(image);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void onLogin(ActionEvent event) {

    }
}

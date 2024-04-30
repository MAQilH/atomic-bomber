package aqil.atomicbomber.view;

import aqil.atomicbomber.model.App;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginMenuController implements Initializable {

    public Label username_error_lbl;
    public Label password_error_lbl;
    public VBox username_count;
    public VBox password_count;
    public Button guest_btn;
    public Button signup_btn;
    public Button login_btn;
    public PasswordField password_field;
    public TextField username_field;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        password_count.getChildren().remove(password_error_lbl);
        username_count.getChildren().remove(username_error_lbl);


    }

    private void onSignup(){
//        Stage stage = App.getInstance().getStage();
//
//        String Main;
//        Pane pane = FXMLLoader.load(new URL(Main));
//        stage.setScene();
    }
}

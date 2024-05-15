package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.Database;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    public Hyperlink login_btn;
    public TextField username_field;
    public PasswordField password_field;
    public PasswordField repeat_password_field;
    public Button signup_btn;

    private void resetError(){
        username_count.getChildren().remove(username_error_lbl);
        password_count.getChildren().remove(password_error_lbl);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetError();
        initializeAvatarImageView();

        login_btn.setOnMouseClicked(e -> onLogin());
        signup_btn.setOnMouseClicked(e -> onSignup());
    }

    private boolean signupValidation(String username, String password, String repeatPassword){
        boolean isValid = true;
        if(username.isEmpty()){
            username_error_lbl.setText("Username is required");
            username_count.getChildren().add(username_error_lbl);
            isValid = false;
        }
        if(password.isEmpty()){
            password_error_lbl.setText("Password is required");
            password_count.getChildren().add(password_error_lbl);
            isValid = false;
        }
        if(!password.equals(repeatPassword)){
            password_error_lbl.setText("Password does not match");
            password_count.getChildren().add(password_error_lbl);
            isValid = false;
        }

        if(!isValid) return false;

        Database database = Database.getInstance();
        if(database.getUserWithUsername(username) != null){
            username_error_lbl.setText("Username already exists");
            username_count.getChildren().add(username_error_lbl);
            isValid = false;
        }

        return isValid;
    }

    private void onSignup() {
        String username = username_field.getText();
        String password = password_field.getText();
        String repeatPassword = repeat_password_field.getText();

        resetError();

        if(signupValidation(username, password, repeatPassword)){
            Database database = Database.getInstance();
            database.saveUser(username, password);
            MenuLoader.setMenu(Menu.LOGIN_MENU);
        }
    }

    private void initializeAvatarImageView(){
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

    public void onLogin() {
        MenuLoader.setMenu(Menu.LOGIN_MENU);
        login_btn.setVisited(false);
    }
}

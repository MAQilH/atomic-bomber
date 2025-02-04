package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.Database;
import aqil.atomicbomber.controller.LoginController;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.User;
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

public class LoginMenuController extends MenuController implements Initializable {

    public Label username_error_lbl;
    public Label password_error_lbl;
    public VBox username_count;
    public VBox password_count;
    public Button guest_btn;
    public Button signup_btn;
    public Button login_btn;
    public PasswordField password_field;
    public TextField username_field;

    private LoginController loginController = new LoginController();


    private void reset() {
        password_count.getChildren().remove(password_error_lbl);
        username_count.getChildren().remove(username_error_lbl);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();

        signup_btn.setOnMouseClicked(e -> onSignup());
        login_btn.setOnMouseClicked(e -> onLogin());
        guest_btn.setOnMouseClicked(e -> onGuest());
    }

    private void onSignup() {
        MenuLoader.setMenu(Menu.SIGNUP_MENU);
    }

    private boolean loginValidation(String username, String password) {
        boolean isValid = true;
        if (username.isEmpty()) {
            username_error_lbl.setText("Username is empty!");
            username_count.getChildren().add(username_error_lbl);
            isValid = false;
        }
        if (password.isEmpty()) {
            password_error_lbl.setText("Password is empty!");
            password_count.getChildren().add(password_error_lbl);
            isValid = false;
        }
        if (!isValid) return false;

        User user = loginController.getUser(username);
        if (user == null) {
            username_error_lbl.setText("Username not found!");
            username_count.getChildren().add(username_error_lbl);
            return false;
        }
        if (!user.getPassword().equals(password)) {
            password_error_lbl.setText("Password is incorrect!");
            password_count.getChildren().add(password_error_lbl);
            return false;
        }
        return true;
    }

    private void onLogin() {
        String username = username_field.getText();
        String password = password_field.getText();
        reset();
        if (loginValidation(username, password)) {
            loginController.enterUser(loginController.getUser(username));
        }
    }

    private void onGuest() {
        loginController.enterUser(new User(0, "Guest", ""));
    }

    @Override
    public void reload(Menu prevMenu) {
        username_field.setText("");
        password_field.setText("");
    }
}

package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.Database;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.controller.ProfileController;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.User;
import aqil.atomicbomber.utils.FileSaver;
import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenuController extends MenuController implements Initializable {

    public ImageView avatar_btn;
    public TextField username_field;
    public Label username_field_error;
    public Button save_btn;
    public PasswordField password_field;
    public Button logout_btn;
    public Button delete_account_btn;
    public VBox username_count;
    public ImageView back_btn;
    private final ProfileController profileController = new ProfileController();


    public void removeError() {
        username_field_error.setText("");
        username_count.getChildren().remove(username_field_error);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save_btn.setOnMouseClicked(e -> onSave());
        logout_btn.setOnMouseClicked(e -> onLogout());
        delete_account_btn.setOnMouseClicked(e -> onDeleteAccount());
        avatar_btn.setOnMouseClicked(e -> onAvatar());
        back_btn.setOnMouseClicked(e -> onBack());
    }

    private void onBack() {
        MenuLoader.setMenu(Menu.MAIN_MENU);
    }

    private void onAvatar() {
        MenuLoader.setMenu(Menu.AVATAR_MENU);
        AvatarMenuController avatarMenuController = (AvatarMenuController) Menu.AVATAR_MENU.getMenuController();
        ChangeListener<String> listener = (observable, oldValue, newValue) -> {
            if (newValue != null) {
                avatar_btn.setImage(new Image(newValue));
            }
        };
        avatarMenuController.getSelectedImagePath().addListener(listener);
    }

    private void onDeleteAccount() {
        Database.getInstance().deleteUser(App.getInstance().getUser().getId());
        onLogout();
    }

    private void onLogout() {
        App.getInstance().setUser(null);
        MenuLoader.setMenu(Menu.LOGIN_MENU);
    }

    private boolean saveValidation() {
        boolean isValid = true;
        if (!username_field.getText().isEmpty()) {
            User user = Database.getInstance().getUserWithUsername(username_field.getText());
            if (user != null) {
                isValid = false;
                username_field_error.setText("Username already exists");
                username_count.getChildren().add(username_field_error);
            }
        }
        return isValid;
    }

    private void onSave() {
        removeError();
        if (saveValidation()) {
            profileController.saveUser(username_field.getText(), password_field.getText(), avatar_btn.getImage());
            onBack();
        }
    }

    @Override
    public void reload(Menu prevMenu) {
        removeError();
        if (App.getInstance().getUser() == null || App.getInstance().getUser().getId() == 0) {
            save_btn.setDisable(true);
            delete_account_btn.setDisable(true);
            password_field.setDisable(true);
            username_field.setDisable(true);
            avatar_btn.setDisable(true);
        } else {
            save_btn.setDisable(false);
            delete_account_btn.setDisable(false);
            password_field.setDisable(false);
            username_field.setDisable(false);
            avatar_btn.setDisable(false);
        }

        avatar_btn.setImage(App.getInstance().getUser().getAvatar());
    }
}

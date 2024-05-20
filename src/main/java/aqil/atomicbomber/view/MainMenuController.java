package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.MainController;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends MenuController implements Initializable {
    public VBox info_count;
    public ImageView avatar_img;
    public Label username_lbl;
    public Button saved_game_btn;
    public Button profile_btn;
    public Button score_board_btn;
    public Button exit_btn;
    public Button setting_btn;
    public Button new_game_btn;

    private MainController mainController = new MainController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new_game_btn.setOnMouseClicked(e -> onNewGame());
        saved_game_btn.setOnMouseClicked(e -> onSavedGame());
        profile_btn.setOnMouseClicked(e -> onProfile());
        exit_btn.setOnMouseClicked(e -> onExit());
        setting_btn.setOnMouseClicked(e -> onSetting());
        score_board_btn.setOnMouseClicked(e -> onScoreBoard());
    }

    private void onScoreBoard() {
        MenuLoader.setMenu(Menu.SCOREBOARD_MENU);
    }

    private void onSetting() {
        MenuLoader.setMenu(Menu.SETTING_MENU);
    }

    private void onExit() {
        App.getInstance().getStage().close();
    }

    private void onProfile() {
        MenuLoader.setMenu(Menu.PROFILE_MENU);
    }

    private void onSavedGame() {
        mainController.reloadGame();
    }

    private void onNewGame() {
        mainController.startNewGame();
    }

    @Override
    public void reload(Menu prevMenu) {
        username_lbl.setText(App.getInstance().getUser().getUsername());
        avatar_img.setImage(App.getInstance().getUser().getAvatar());
    }
}

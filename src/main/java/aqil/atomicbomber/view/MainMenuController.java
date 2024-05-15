package aqil.atomicbomber.view;

import aqil.atomicbomber.model.App;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    public VBox info_count;
    public ImageView avatar_img;
    public Label username_lbl;
    public Button saved_game_btn;
    public Button profile_btn;
    public Button score_board_btn;
    public Button exit_btn;
    public Button setting_btn;
    public Button new_game_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new_game_btn.setOnMouseClicked(e -> onNewGame());
        saved_game_btn.setOnMouseClicked(e -> onSavedGame());

    }

    private void onSavedGame() {
        GameLauncher gameLauncher = new GameLauncher();
        App.getInstance().setGameLauncher(gameLauncher);
        gameLauncher.reloadGame();
        System.out.println("Saved game loaded");
    }

    private void onNewGame() {
        GameLauncher gameLauncher = new GameLauncher();
        App.getInstance().setGameLauncher(gameLauncher);
        gameLauncher.start();
    }
}

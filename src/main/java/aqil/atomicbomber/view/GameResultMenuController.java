package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.Database;
import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.GameResult;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GameResultMenuController extends MenuController implements Initializable {

    public Label game_status_lbl;
    public Label wave_lbl;
    public Label killing_lbl;
    public Label accuracy_lbl;
    public Button save_btn;

    private GameResult gameResult;

    @Override
    public void reload(Menu prevMenu) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        save_btn.setOnMouseClicked(e -> onSave());
    }

    private void onSave() {
        Database.getInstance().saveGameResult(gameResult);
        MenuLoader.setMenu(Menu.MAIN_MENU);
    }

    public void updateMenu(GameResult gameResult) {
        this.gameResult = gameResult;

        if(gameResult.getWave() == Game.WAVE_NUMBER){
            game_status_lbl.setText("WIN");
            game_status_lbl.getStyleClass().remove("lose_lbl");
            game_status_lbl.getStyleClass().add("win_lbl");
        } else {
            game_status_lbl.setText("LOSE");
            game_status_lbl.getStyleClass().add("lose_lbl");
            game_status_lbl.getStyleClass().remove("win_lbl");
        }

        wave_lbl.setText(String.valueOf(gameResult.getWave()));
        killing_lbl.setText(String.valueOf(gameResult.getKills()));
        accuracy_lbl.setText(String.format("%.2f", gameResult.getAccurate()));
    }
}

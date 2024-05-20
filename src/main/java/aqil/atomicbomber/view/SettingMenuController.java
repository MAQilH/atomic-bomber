package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Difficulty;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.view.MenuController;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingMenuController extends MenuController implements Initializable {

    public ImageView back_btn;
    public RadioButton easy_toggle;
    public RadioButton normal_toggle;
    public RadioButton hard_toggle;
    public ImageView volume_btn;
    public RadioButton white_back_toggle;

    private ArrayList<RadioButton> difficulty_toggles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficulty_toggles = new ArrayList<>();
        difficulty_toggles.add(easy_toggle);
        difficulty_toggles.add(normal_toggle);
        difficulty_toggles.add(hard_toggle);

        easy_toggle.setSelected(true);

        easy_toggle.setOnMouseClicked(e -> onDifficulty(easy_toggle, Difficulty.EASY));
        normal_toggle.setOnMouseClicked(e -> onDifficulty(normal_toggle, Difficulty.NORMAL));
        hard_toggle.setOnMouseClicked(e -> onDifficulty(hard_toggle, Difficulty.HARD));

        back_btn.setOnMouseClicked(e -> onBack());
        volume_btn.setOnMouseClicked(e -> onVolume());
        white_back_toggle.setOnMouseClicked(e -> onWhiteAndBlack());
    }

    private void onWhiteAndBlack() {
        App.getInstance().getSetting().setBlackAndWhite(!App.getInstance().getSetting().isBlackAndWhite());
    }

    private void onDifficulty(RadioButton radioButton, Difficulty difficulty) {
        for (RadioButton difficultyToggle : difficulty_toggles) {
            difficultyToggle.setSelected(difficultyToggle == radioButton);
        }
        App.getInstance().getSetting().setDifficulty(difficulty);
    }

    private void onVolume() {
        Image image;
        if (App.getInstance().getSetting().isMuted())
            image = new javafx.scene.image.Image(getClass().getResource("/Images/unmute.png").toExternalForm());
        else image = new Image(getClass().getResource("/Images/mute.png").toExternalForm());
        volume_btn.setImage(image);
        App.getInstance().getSetting().setMuted(!App.getInstance().getSetting().isMuted());
    }

    private void onBack() {
        MenuLoader.setMenu(Menu.MAIN_MENU);
    }

    @Override
    public void reload(Menu prevMenu) {

    }
}

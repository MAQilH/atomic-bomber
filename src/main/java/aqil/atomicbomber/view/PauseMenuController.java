package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.MusicTrack;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PauseMenuController implements Initializable {

    public VBox menu_count;
    public Button resume_btn;
    public Button save_and_exit_btn;
    public Button exit_btn;
    public ImageView volume_btn;
    public MenuButton music_menu;

    private final Map<MenuItem, MusicTrack> menuItemToTrackMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menu_count.setOnKeyPressed((keyEvent -> {
            KeyCode code = keyEvent.getCode();
            switch (code){
                case ESCAPE:
                    App.getInstance().getGameLauncher().resumeGame();
                    break;
            }
        }));

        exit_btn.setOnMouseClicked(e -> onExit());
        save_and_exit_btn.setOnMouseClicked(e -> onSave());
        volume_btn.setOnMouseClicked(e -> onVolume());

        initializeMusicMenu();
    }

    private void initializeMusicMenu(){
        MusicTrack[] tracks = MusicTrack.values();
        for (int i = 0; i < music_menu.getItems().size(); i++) {
            MenuItem item = music_menu.getItems().get(i);
            MusicTrack track = tracks[i % tracks.length];
            menuItemToTrackMap.put(item, track);

            item.setOnAction(e -> {
                MusicTrack selectedTrack = menuItemToTrackMap.get(item);
                App.getInstance().getGameLauncher().setMusic(selectedTrack);
            });
        }
    }

    private void onSave() {
        App.getInstance().getGameLauncher().saveGame();
    }

    private void onExit(){
        MenuLoader.setMenu(Menu.MAIN_MENU);
    }

    private void onVolume(){
        Image image;
        if(App.getInstance().isMuted())
            image = new Image(getClass().getResource("/Images/unmute.png").toExternalForm());
        else image = new Image(getClass().getResource("/Images/mute.png").toExternalForm());
        volume_btn.setImage(image);
        App.getInstance().setMuted(!App.getInstance().isMuted());
    }
}

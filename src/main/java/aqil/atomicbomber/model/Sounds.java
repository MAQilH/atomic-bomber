package aqil.atomicbomber.model;

import javafx.beans.value.ChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public enum Sounds {
    BOMB_EXPLOSION("nuclear-bomb-sound.mp3"),
    LAY_LAY("lay-lay-lay.mp3");

    final Media sound;

    Sounds(String audioName) {
        String audioFilePath = "/Audios/" + audioName;
        sound = new Media(getClass().getResource(audioFilePath).toExternalForm());
    }

    public void play() {
        if (!App.getInstance().getSetting().isMuted()) {
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

            ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> {
                if (newValue) {
                    mediaPlayer.stop();
                } else {
                    mediaPlayer.play();
                }
            };
            App.getInstance().getSetting().isMutedProperty().addListener(listener);

            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.stop();
                App.getInstance().getSetting().isMutedProperty().removeListener(listener);
            });
        }
    }
}

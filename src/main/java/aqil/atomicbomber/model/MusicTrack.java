package aqil.atomicbomber.model;

import javafx.beans.value.ChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public enum MusicTrack {
    TRACK1("lay-lay-lay.mp3"),
    TRACK2("khanom-bebakhshid.mp3"),
    TRACK3("reimagined.mp3");
    final Media sound;

    MusicTrack(String audioName){
        String audioFilePath = "/Audios/" + audioName;
        sound = new Media(getClass().getResource(audioFilePath).toExternalForm());
    }

    private MediaPlayer mediaPlayer = null;
    public void play(){
        if(!App.getInstance().isMuted()){
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(-1);
            mediaPlayer.play();

            ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> {
                if(newValue){
                    mediaPlayer.pause();
                }else{
                    mediaPlayer.play();
                }
            };
            App.getInstance().isMutedProperty().addListener(listener);

            mediaPlayer.setOnStopped(() -> {
                App.getInstance().isMutedProperty().removeListener(listener);
            });
        }
    }

    public void stop(){
        if(mediaPlayer != null)
            mediaPlayer.stop();
    }
}

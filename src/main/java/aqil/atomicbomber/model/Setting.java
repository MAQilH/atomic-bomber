package aqil.atomicbomber.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Setting {
    private Difficulty difficulty;
    private BooleanProperty isMuted;
    private boolean isBlackAndWhite;
    private MusicTrack musicTrack;

    public Setting(){
        isMuted = new SimpleBooleanProperty(this, "mute", true);
        isBlackAndWhite = false;
        difficulty = Difficulty.EASY;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isMuted() {
        return isMuted.get();
    }

    public BooleanProperty isMutedProperty() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted.set(muted);
    }

    public boolean isBlackAndWhite() {
        return isBlackAndWhite;
    }

    public void setBlackAndWhite(boolean blackAndWhite) {
        isBlackAndWhite = blackAndWhite;
    }

    public MusicTrack getMusicTrack() {
        return musicTrack;
    }

    public void setMusicTrack(MusicTrack musicTrack) {
        this.musicTrack = musicTrack;
    }
}

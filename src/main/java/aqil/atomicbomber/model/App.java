package aqil.atomicbomber.model;

import aqil.atomicbomber.view.GameLauncher;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;

public class App {
    private Stage stage;
    private GameLauncher gameLauncher;
    private User user;
    private final BooleanProperty isMuted;
    private static App app = null;

    private App(){
        isMuted = new SimpleBooleanProperty(this, "isMuted", true);
    }
    public synchronized static App getInstance(){
        if(app == null)
            app = new App();
        return app;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setGameLauncher(GameLauncher gameLauncher) {
        this.gameLauncher = gameLauncher;
    }

    public GameLauncher getGameLauncher() {
        return gameLauncher;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean isMuted() {
        return isMuted.get();
    }

    public void setMuted(Boolean muted) {
        isMuted.set(muted);
    }

    public BooleanProperty isMutedProperty() {
        return isMuted;
    }
}

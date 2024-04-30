package aqil.atomicbomber.model;

import javafx.stage.Stage;

public class App {
    private Stage stage;
    private static App app = null;

    private App(){}
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
}

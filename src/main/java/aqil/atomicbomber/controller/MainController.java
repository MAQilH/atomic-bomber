package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.view.GameLauncher;

public class MainController {

    public void startNewGame(){
        GameLauncher gameLauncher = new GameLauncher();
        App.getInstance().setGameLauncher(gameLauncher);
        gameLauncher.start();
    }

    public void reloadGame(){
        GameLauncher gameLauncher = new GameLauncher();
        App.getInstance().setGameLauncher(gameLauncher);
        gameLauncher.reloadGame();
    }
}

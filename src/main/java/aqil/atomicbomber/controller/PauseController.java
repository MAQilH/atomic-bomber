package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;

public class PauseController {
    public void resumeGame(){
        App.getInstance().getGameLauncher().resumeGame(Menu.PAUSE_MENU.getRoot());
    }

    public void saveGame(){
        App.getInstance().getGameLauncher().saveGame();
    }

    public void exitGame(){
        App.getInstance().getGameLauncher().stopMusic();
        MenuLoader.setMenu(Menu.MAIN_MENU);
    }
}

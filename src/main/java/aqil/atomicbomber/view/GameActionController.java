package aqil.atomicbomber.view;

import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.game.Game;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class GameActionController extends Rectangle {
    public GameActionController(Game game, GameLauncher gameLauncher) {
        setOnKeyPressed((keyEvent -> {
            KeyCode code = keyEvent.getCode();
            switch (code) {
                case W:
                    game.getWarplane().rotateUp();
                    break;
                case S:
                    game.getWarplane().rotateDown();
                    break;
                case D:
                    game.getWarplane().increaseSpeed();
                    break;
                case A:
                    game.getWarplane().decreaseSpeed();
                    break;
                case SPACE:
                    game.getWarplane().dropNormalBomb();
                    break;
                case R:
                    game.getWarplane().dropNuclearBomb();
                    break;
                case C:
                    game.getWarplane().dropClusterBomb();
                    break;
                case TAB:
                    gameLauncher.freezeGame();
                    break;
                case ESCAPE:
                    gameLauncher.pauseGame(Menu.PAUSE_MENU.getRoot());
                    break;
                case P:
                    gameLauncher.nextWave();
                    break;
                case G:
                    gameLauncher.getNuclearBomb();
                    break;
                case CONTROL:
                    gameLauncher.getClusterBomb();
                    break;
                case T:
                    gameLauncher.addTank();
                    break;
                case H:
                    gameLauncher.resetHP();
                    break;
            }
        }));
    }
}

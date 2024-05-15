package aqil.atomicbomber.model.game.bombs;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class NormalBomb extends Bomb{
    public static double WIDTH = 40, HEIGHT = 30, RADIUS = 150;
    public NormalBomb(Game game){
        super(WIDTH, HEIGHT, RADIUS, game, "normal-bomb", 6);
        setFill(new ImagePattern(new Image(Warplane.class.getResource("/Images/normal-bomb.png").toExternalForm())));
    }
}

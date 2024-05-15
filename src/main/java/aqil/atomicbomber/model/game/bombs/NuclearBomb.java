package aqil.atomicbomber.model.game.bombs;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class NuclearBomb extends Bomb{
    public static double WIDTH = 60, HEIGHT = 50, RADIUS = 300;
    public NuclearBomb(Game game){
        super(WIDTH, HEIGHT, RADIUS, game, "nuclear-bomb", 8);
        setFill(new ImagePattern(new Image(Warplane.class.getResource("/Images/nuclear-bomb.png").toExternalForm())));
    }
}

package aqil.atomicbomber.model.game.bombs;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class ClusterBomb extends Bomb{
    public static double WIDTH = 50, HEIGHT = 50, RADIUS = 150;
    public ClusterBomb(Game game){
        super(HEIGHT, WIDTH, RADIUS, game, "bomb2", 5);
        setFill(new ImagePattern(new Image(Warplane.class.getResource("/Images/cluster-bomb.png").toExternalForm())));
    }
}

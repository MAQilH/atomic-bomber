package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.utils.Random;
import javafx.scene.shape.Rectangle;

public class Tree extends Obstacle {
    public static final int HEIGHT = 70, WIDTH = 55;

    public Tree(Game game) {
        super(WIDTH, HEIGHT, "trees/tree" + Random.randomInt(1, 3) + ".png", game, 0, 0);
    }

    @Override
    public void start() {
        setX(Random.randomDouble(0, (int) (Game.WIDTH - WIDTH)));
        setY(Game.GROUND - HEIGHT);
        game.addObstacle(this);
    }

    @Override
    public void onExplosion() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}

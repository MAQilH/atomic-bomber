package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.bonus.ClusterBombBonus;
import aqil.atomicbomber.model.game.bonus.NuclearBombBonus;
import aqil.atomicbomber.utils.Random;
import javafx.scene.shape.Rectangle;

public class Building extends Obstacle {

    public static final int HEIGHT = 120, WIDTH = 100;
    public Building(Game game) {
        super(WIDTH, HEIGHT, "building.png", game, 1, 0);
    }

    @Override
    public void start() {
        setX(Random.randomDouble(0, (int) (Game.WIDTH - WIDTH)));
        setY(Game.GROUND - HEIGHT);
        game.addObstacle(this);
    }

    @Override
    public void onExplosion() {
        NuclearBombBonus nuclearBombBonus = new NuclearBombBonus(game, this);
        nuclearBombBonus.start();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}

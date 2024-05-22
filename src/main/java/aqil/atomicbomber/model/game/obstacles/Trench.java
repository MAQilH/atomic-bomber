package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.bonus.ClusterBombBonus;
import aqil.atomicbomber.utils.Random;

public class Trench extends Obstacle {
    public static final int HEIGHT = 60, WIDTH = 70;

    public Trench(Game game) {
        super(WIDTH, HEIGHT, "trench.png", game, 2, 10);
    }

    @Override
    public void start() {
        setX(Random.randomDouble(0, (int) (Game.WIDTH - WIDTH)));
        setY(Game.GROUND - HEIGHT);
        game.addObstacle(this);
    }

    @Override
    public void onExplosion() {
        ClusterBombBonus clusterBombBonus = new ClusterBombBonus(game, this);
        clusterBombBonus.start();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}

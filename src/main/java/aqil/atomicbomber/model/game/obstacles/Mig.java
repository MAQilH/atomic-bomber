package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.game.Game;

public class Mig extends Fighter{
    public static final int WIDTH = 120, HEIGHT = 80;

    public Mig(Game game) {
        super(WIDTH, HEIGHT, "mig.png", game, 1000, 100);
    }

    @Override
    public void start() {

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

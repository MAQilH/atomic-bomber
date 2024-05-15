package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.utils.Random;
import aqil.atomicbomber.view.animation.ObstacleBurningAnimation;
import aqil.atomicbomber.view.animation.TankMoveAnimation;
import javafx.util.Pair;

public class Tank extends Fighter {
    public static double HEIGHT = 60, WIDTH = 90;
    public Tank(Game game){
        super(WIDTH, HEIGHT, "tank.png", game, 6, 100);
    }

    @Override
    public void start() {
        setX(Random.randomDouble(0, (int) (Game.WIDTH - WIDTH)));
        setY(Game.GROUND - HEIGHT);
        game.addObstacle(this);

        speed += Random.randomDouble(-0.7, 0.7);
        if(Random.bern(0.5)){
            speed = -speed;
            setScaleX(-1);
        }
        startMove();
    }


    TankMoveAnimation tankMoveAnimation;
    void startMove(){
        tankMoveAnimation = new TankMoveAnimation(this);
        tankMoveAnimation.play();
        game.addAnimation(tankMoveAnimation);
    }

    @Override
    public void onExplosion() {
        tankMoveAnimation.stop();
        game.removeAnimation(tankMoveAnimation);
    }

    @Override
    public void pause() {
        tankMoveAnimation.pause();
    }

    @Override
    public void resume() {
        tankMoveAnimation.play();
    }
}

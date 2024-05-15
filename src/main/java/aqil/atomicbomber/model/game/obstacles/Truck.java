package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.utils.Random;
import aqil.atomicbomber.view.animation.TankMoveAnimation;
import aqil.atomicbomber.view.animation.TruckMoveAnimation;
import javafx.scene.shape.Rectangle;

public class Truck extends Obstacle {

    public static final int HEIGHT = 60, WIDTH = 90;

    private double speed = 2;

    public Truck(Game game) {
        super(WIDTH, HEIGHT, "truck.png", game, 4, 15);
    }

    @Override
    public void start() {
        setX(Random.randomDouble(0, (int) (Game.WIDTH - WIDTH)));
        setY(Game.GROUND - HEIGHT);
        game.addObstacle(this);

        speed += Random.randomDouble(-0.5, 0.5);
        if(Random.bern(0.5)){
            speed = -speed;
            setScaleX(-1);
        }
        startMove();
    }

    @Override
    public void onExplosion() {
        truckMoveAnimation.stop();
        game.removeAnimation(truckMoveAnimation);
    }


    TruckMoveAnimation truckMoveAnimation;
    void startMove(){
        truckMoveAnimation = new TruckMoveAnimation(this);
        truckMoveAnimation.play();

        game.addAnimation(truckMoveAnimation);
    }

    @Override
    public void pause() {
        truckMoveAnimation.pause();
    }

    @Override
    public void resume() {
        truckMoveAnimation.play();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

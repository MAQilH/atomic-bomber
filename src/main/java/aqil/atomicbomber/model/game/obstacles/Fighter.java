package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.game.Bullet;
import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.bombs.Bomb;
import aqil.atomicbomber.model.game.bombs.ClusterBomb;
import javafx.application.Platform;
import javafx.util.Pair;

import java.util.Timer;
import java.util.TimerTask;

public abstract class  Fighter extends Obstacle{
    protected double radius = 500;
    protected double speed = 3;
    protected boolean canHit = true;
    public Fighter(double WIDTH, double HEIGHT, String imageName, Game game, int killingValue, int freezeValue) {
        super(WIDTH, HEIGHT, imageName, game, killingValue, freezeValue);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean warplaneInRange(){
        double theta = -getAngleWithWarplane();
        if(speed < 0) theta = Math.PI - theta;
        return getDistFromWarplane() < radius && theta < Math.PI/3 && theta > Math.PI/6;
    }

    public void bang() {
        if(canHit && warplaneInRange()){
            Bullet bullet = new Bullet(game, this);
            bullet.start();
            System.out.println(canHit);
            canHit = false;
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    canHit = true;
                    timer.cancel();
                }
            };
            timer.schedule(task, 500, 500);
        }
    }
}

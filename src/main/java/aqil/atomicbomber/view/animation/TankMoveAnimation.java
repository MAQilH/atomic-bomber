package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.obstacles.Tank;
import javafx.animation.Transition;

import java.io.Serializable;

public class TankMoveAnimation extends Transition implements Serializable {
    private Tank tank;
    public TankMoveAnimation(Tank tank){
        this.tank = tank;
        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(500));
    }
    @Override
    protected void interpolate(double v) {
        double x = tank.getX();
        x += tank.getSpeed();
        if(x >= Game.WIDTH) x = -tank.getWidth();
        else if(x < -tank.getWidth()) x = Game.WIDTH;
        tank.setX(x);
        tank.bang();
    }
}

package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.obstacles.Truck;
import javafx.animation.Transition;

import java.io.Serializable;

public class TruckMoveAnimation extends Transition implements Serializable {
    private final Truck truck;

    public TruckMoveAnimation(Truck truck) {
        this.truck = truck;
        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(5000));
    }

    @Override
    protected void interpolate(double v) {
        double x = truck.getX();
        x += truck.getSpeed();
        if (x >= Game.WIDTH) x = -truck.getWidth();
        else if (x < -truck.getWidth()) x = Game.WIDTH;
        truck.setX(x);
    }
}

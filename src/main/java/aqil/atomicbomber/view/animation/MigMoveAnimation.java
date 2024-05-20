package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.obstacles.Mig;
import javafx.animation.Transition;
import javafx.util.Duration;

public class MigMoveAnimation extends Transition {

    private final Mig mig;
    private final int DURATION = 5000;

    public MigMoveAnimation(Mig mig){
        this.mig = mig;
        setCycleCount(-1);
        setCycleDuration(Duration.millis(DURATION));
    }

    @Override
    protected void interpolate(double v) {
        double x = mig.getX() + mig.getSpeed();
        mig.setX(x);
        if(x < -mig.getWidth() || x > Game.WIDTH)
            stop();
        mig.bang();
    }
}

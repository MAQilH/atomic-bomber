package aqil.atomicbomber.view.animation;

import javafx.animation.Transition;

public class BulletExplosionAnimation extends Transition {

    public BulletExplosionAnimation() {
        setCycleCount(1);
        setCycleDuration(javafx.util.Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        stop();
    }
}

package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.model.game.bonus.Bonus;
import javafx.animation.Transition;

import java.io.Serializable;

public class BonusMoveAnimation extends Transition implements Serializable {

    private final Bonus bonus;
    private final double DURATION = 5000;

    private final double orgX;

    public BonusMoveAnimation(Bonus bonus) {
        this.bonus = bonus;
        orgX = bonus.getX();

        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(DURATION));
    }

    @Override
    protected void interpolate(double v) {
        Warplane warplane = bonus.getGame().getWarplane();

        double y = bonus.getY() - bonus.getSpeed();
        double x = orgX + Math.sin(y / 10) * 50;

        bonus.setRotate(bonus.getRotate() + Math.sin(y / 10) / 2);

        bonus.setX(x);
        bonus.setY(y);

        if (bonus.getBoundsInParent().intersects(warplane.getBoundsInParent())) {
            stop();
            bonus.gain();
            bonus.remove();
        } else if (y < -Bonus.HEIGHT) {
            stop();
            bonus.remove();
        }
    }
}

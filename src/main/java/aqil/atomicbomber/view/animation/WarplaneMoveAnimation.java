package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.io.Serializable;

public class WarplaneMoveAnimation extends Transition implements Serializable {

    public static final int DURATION = 50000;
    Warplane warplane;
    public WarplaneMoveAnimation(Warplane warplane){
        this.warplane = warplane;
        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(DURATION));
    }
    @Override
    protected void interpolate(double v) {
        double y = warplane.getY() + warplane.getSpeed()*Math.sin(Math.toRadians(warplane.getRotate()));
        double x = warplane.getX() + warplane.getSpeed()*Math.cos(Math.toRadians(warplane.getRotate()));
        if(x > Game.WIDTH) x = -Warplane.WIDTH;
        else if(x < -Warplane.WIDTH) x = Game.WIDTH;
        if(y < 10) {
            y = 10;
            flattenWarplane();
        }
        if(y + warplane.getHeight() > Game.GROUND){
            warplane.setCurrentHp(0);
            stop();
        }
        warplane.setY(y);
        warplane.setX(x);
    }

    void flattenWarplane(){
        Duration duration = Duration.millis(50);
        RotateTransition rotateTransition = new RotateTransition(duration, warplane);
        double dest = 0;
        if(Math.abs(warplane.getRotate() - 180) < 90) dest = 180;
        else if(warplane.getRotate() > 180) dest = 360;
        rotateTransition.setByAngle(dest - warplane.getRotate());
        rotateTransition.play();
    }

}

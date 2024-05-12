package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

public class WarplaneMoveAnimation extends Transition {

    private final int duration = 5000;
    Warplane warplane;
    public WarplaneMoveAnimation(Warplane warplane){
        this.warplane = warplane;
        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(duration));
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
        warplane.setY(y);
        warplane.setX(x);
        System.out.println(warplane.getRotate());
    }

    void flattenWarplane(){
        Duration duration = Duration.millis(50);
        RotateTransition rotateTransition = new RotateTransition(duration, warplane);
        double dest = 0;
        System.out.println(warplane.getRotate());
        if(Math.abs(warplane.getRotate() - 180) < 90) dest = 180;
        else if(warplane.getRotate() > 180) dest = 360;
        rotateTransition.setByAngle(dest - warplane.getRotate());
        rotateTransition.play();
    }

}

package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.model.game.bombs.Bomb;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.util.Duration;

public class BombMoveAnimation extends Transition {
    private final int duration = 5000;
    private Bomb bomb;
    private RotateTransition rotateTransition;
    public BombMoveAnimation(Bomb bomb){
        this.bomb = bomb;
        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(duration));

        addRotateAnimation();
    }

    void addRotateAnimation(){
        Duration duration = Duration.millis(1500);
        rotateTransition = new RotateTransition(duration, bomb);
        rotateTransition.setByAngle(90 - bomb.getRotate());
        rotateTransition.play();
    }

    @Override
    protected void interpolate(double v) {
        bomb.setSpeed(bomb.getSpeed() + bomb.getAcceleration());
        double y = bomb.getY() + bomb.getSpeed()*Math.sin(Math.toRadians(bomb.getRotate()));
        double x = bomb.getX() + bomb.getSpeed()*Math.cos(Math.toRadians(bomb.getRotate()));

        bomb.setY(y);
        bomb.setX(x);

        if(y + bomb.getHeight() > Game.GROUND){
            rotateTransition.stop();
            bomb.explosion();
            stop();
        }
    }
}

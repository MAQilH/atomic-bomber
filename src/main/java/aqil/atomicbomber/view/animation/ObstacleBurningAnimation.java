package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.obstacles.Obstacle;
import aqil.atomicbomber.model.game.obstacles.Tank;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.Serializable;

public class ObstacleBurningAnimation extends Transition implements Serializable {
    private Obstacle obstacle;
    private final int DURATION = 500;
    private String imageAddress;
    private int numberOfFrame;
    public ObstacleBurningAnimation(Obstacle obstacle, String imageAddress, int numberOfFrame){
        this.obstacle = obstacle;
        this.imageAddress = imageAddress;
        this.numberOfFrame = numberOfFrame;

        setCycleCount(1);
        setCycleDuration(javafx.util.Duration.millis(DURATION));
    }
    @Override
    protected void interpolate(double v) {
        int frameId = 0;
        for (int frameIndex = 1; frameIndex <= numberOfFrame; frameIndex++) {
            if((frameIndex - 1)*((double) 1 / numberOfFrame) <= v) frameId = frameIndex;
        }
        obstacle.setFill(new ImagePattern(new Image(Tank.class.getResource("/Images/" + imageAddress + "/frame" + frameId + ".png").toExternalForm())));
    }
}

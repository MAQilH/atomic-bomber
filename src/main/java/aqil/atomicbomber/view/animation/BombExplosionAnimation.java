package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.model.game.bombs.Bomb;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.io.Serializable;

public class BombExplosionAnimation extends Transition implements Serializable {
    private Bomb bomb;
    private String bombImagesAddress;
    private int frame;

    private final int DURATION = 700;

    double initWidth, initHeight, initCenter;

    public BombExplosionAnimation(Bomb bomb, String bombImagesAddress, int frame) {
        this.bomb = bomb;
        this.bombImagesAddress = bombImagesAddress;
        this.frame = frame;
        initHeight = bomb.getHeight();
        initWidth = bomb.getWidth();
        initCenter = bomb.getX() + initWidth / 2;

        setCycleCount(1);
        setCycleDuration(Duration.millis(DURATION));

    }

    @Override
    protected void interpolate(double v) {
        bomb.setRotate(0);
        int frameId = 0;
        for (int frameIndex = 1; frameIndex <= frame; frameIndex++) {
            if ((frameIndex - 1) * ((double) 1 / frame) <= v) frameId = frameIndex;
        }
        bomb.setFill(new ImagePattern(new Image(Warplane.class.getResource("/Images/" + bombImagesAddress + "/frame" + frameId + ".png").toExternalForm())));

        bomb.setHeight(initHeight + (bomb.getRadius() - initHeight) * v);
        bomb.setWidth(initWidth + (bomb.getRadius() - initWidth) * v);
        bomb.setY(Game.GROUND - bomb.getHeight());
        bomb.setX(initCenter - bomb.getWidth() / 2);

        if (v < 0.75) bomb.checkCollision();
    }
}

package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.utils.Random;
import aqil.atomicbomber.view.animation.ObstacleBurningAnimation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.Objects;

public abstract class Obstacle extends Rectangle implements Serializable {
    protected Game game;
    protected boolean isHit = false;
    protected int killingValue;
    protected int freezeValue;

    public Obstacle(double WIDTH, double HEIGHT, String imageName, Game game, int killingValue, int freezeValue) {
        super(WIDTH, HEIGHT);
        this.game = game;
        this.killingValue = killingValue;
        this.freezeValue = freezeValue;

        setFill(new ImagePattern(new Image(Objects.requireNonNull(Warplane.class.getResource("/Images/" + imageName)).toExternalForm())));
//        setFill();
    }

    public abstract void start();

    public abstract void onExplosion();

    public abstract void pause();

    public abstract void resume();

    ObstacleBurningAnimation burningAnimation;

    public void explosion() {
        if (isHit) return;
        isHit = true;
        onExplosion();
        showBurningAnimation();
        updateGameInfo();
    }

    private void updateGameInfo() {
        game.setKillingNumber(game.getKillingNumber() + killingValue);
        game.setFreezePercentage(game.getFreezePercentage() + freezeValue);
    }

    private void showBurningAnimation() {
        burningAnimation = new ObstacleBurningAnimation(this, "tank-burning", 8);
        game.addAnimation(burningAnimation);
        burningAnimation.play();
        burningAnimation.setOnFinished((event -> {
            remove();
        }));
    }

    public void remove() {
        game.removeObstacle(this);
        game.removeAnimation(burningAnimation);
        App.getInstance().getGameLauncher().checkWaveEnded();
    }

    public Pair<Double, Double> getCenterCoordinate() {
        return new Pair<>(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }

    public double getAngleWithWarplane() {
        Pair<Double, Double> warplanePosition = game.getWarplane().getCenterCoordinate();
        Pair<Double, Double> centerCoordinate = getCenterCoordinate();
        double x = warplanePosition.getKey() - centerCoordinate.getKey();
        double y = centerCoordinate.getValue() - warplanePosition.getValue();

        double tetha = -Math.atan(y / x);

        if (x < 0) {
            tetha -= Math.PI;
        }
        if (tetha > Math.PI) {
            tetha -= 2 * Math.PI;
        }
        if (tetha < -Math.PI) {
            tetha += 2 * Math.PI;
        }
        return tetha;
    }

    public double getDistFromWarplane() {
        Pair<Double, Double> warplanePosition = game.getWarplane().getCenterCoordinate();
        Pair<Double, Double> bulletPosition = getCenterCoordinate();
        return Math.sqrt(Math.pow(warplanePosition.getKey() - bulletPosition.getKey(), 2) + Math.pow(warplanePosition.getValue() - bulletPosition.getValue(), 2));
    }

}

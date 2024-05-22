package aqil.atomicbomber.model.game;

import aqil.atomicbomber.model.game.obstacles.Fighter;
import aqil.atomicbomber.model.game.obstacles.Tank;
import aqil.atomicbomber.view.animation.BulletExplosionAnimation;
import aqil.atomicbomber.view.animation.BulletMoveAnimation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

import java.awt.*;
import java.util.Objects;

public class Bullet extends Rectangle {
    public static final int HEIGHT = 5, WIDTH = 10;
    private double speed = 40;
    private Game game;
    private Fighter fighter;
    boolean isHit = false;

    private double radius = 30;

    public Bullet(Game game, Fighter fighter) {
        super(WIDTH, HEIGHT);
        this.game = game;
        this.fighter = fighter;

        setFill(new ImagePattern(new Image(Objects.requireNonNull(Warplane.class.getResource("/Images/bullet.png")).toExternalForm())));
    }

    public void start() {
        Pair<Double, Double> tankPosition = fighter.getCenterCoordinate();
        setX(tankPosition.getKey() - (double) WIDTH / 2);
        setY(tankPosition.getValue() - (double) HEIGHT / 2);
        setRotate(Math.toDegrees(fighter.getAngleWithWarplane()));
        game.addBullet(this);
        startMove();
    }

    BulletMoveAnimation bulletMoveAnimation;

    private void startMove() {
        bulletMoveAnimation = new BulletMoveAnimation(this, game, fighter);
        bulletMoveAnimation.play();
        game.addAnimation(bulletMoveAnimation);

        bulletMoveAnimation.setOnFinished((event -> {
            game.removeAnimation(bulletMoveAnimation);
        }));
    }

    BulletExplosionAnimation bulletExplosionAnimation;

    public void explosion() {
        if (isHit) return;
        isHit = true;
        game.getWarplane().setCurrentHp(game.getWarplane().getCurrentHp() - 20);
        bulletExplosionAnimation = new BulletExplosionAnimation();
        game.addAnimation(bulletExplosionAnimation);
        bulletExplosionAnimation.play();

        bulletExplosionAnimation.setOnFinished((event -> {
            System.out.println("end");
            remove();
        }));
        remove();
    }

    public void remove() {
        game.removeAnimation(bulletExplosionAnimation);
        game.removeAnimation(bulletMoveAnimation);
        game.removeBullet(this);
    }

    public Pair<Double, Double> getCenterCoordinate() {
        return new Pair<>(getX() + getWidth() / 2, getY() + getHeight() / 2);
    }

    public double getDistFromWarplane() {
        Pair<Double, Double> warplanePosition = game.getWarplane().getCenterCoordinate();
        Pair<Double, Double> bulletPosition = getCenterCoordinate();
        return Math.sqrt(Math.pow(warplanePosition.getKey() - bulletPosition.getKey(), 2) + Math.pow(warplanePosition.getValue() - bulletPosition.getValue(), 2));
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}

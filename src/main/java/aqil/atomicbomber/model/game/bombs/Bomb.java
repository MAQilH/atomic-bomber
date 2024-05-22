package aqil.atomicbomber.model.game.bombs;

import aqil.atomicbomber.model.Sounds;
import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.model.game.obstacles.Obstacle;
import aqil.atomicbomber.view.animation.BombExplosionAnimation;
import aqil.atomicbomber.view.animation.BombMoveAnimation;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public abstract class Bomb extends Rectangle implements Serializable {
    private final double radius;
    private double speed = 0;
    private double acceleration = 0.2;
    private final Game game;
    private final String bombImageAddress;
    private final int numberOfFrame;
    private boolean destroyObstacle = false;

    Bomb(double WIDTH, double HEIGHT, double radius, Game game, String bombImageAddress, int numberOfFrame) {
        super(WIDTH, HEIGHT);
        this.radius = radius;
        this.game = game;
        this.bombImageAddress = bombImageAddress;
        this.numberOfFrame = numberOfFrame;
    }

    private void initialize(Warplane warplane) {
        speed = warplane.getSpeed() / 2;
        this.setX(warplane.getX() + warplane.getWidth() / 2 - this.getWidth() / 2);
        this.setY(warplane.getY() + warplane.getWidth() / 2 - this.getWidth() / 2);
        double rotate = warplane.getRotate();
        if (rotate > 270) rotate = 0;
        else if (rotate > 180) rotate = 180;
        this.setRotate(rotate);
    }

    public void start() {
        game.setNumberOfPuttedBombs(game.getNumberOfPuttedBombs() + 1);
        initialize(game.getWarplane());
        game.addBomb(this);
        startMove();
    }

    private void startMove() {
        BombMoveAnimation bombMoveAnimation = new BombMoveAnimation(this);
        game.addAnimation(bombMoveAnimation);
        bombMoveAnimation.play();

        bombMoveAnimation.setOnFinished((event -> {
            game.removeAnimation(bombMoveAnimation);
        }));
    }

    public void explosion() {
        BombExplosionAnimation bombExplosionAnimation = new BombExplosionAnimation(this, bombImageAddress, numberOfFrame);
        game.addAnimation(bombExplosionAnimation);
        bombExplosionAnimation.play();

        Sounds.BOMB_EXPLOSION.play();

        bombExplosionAnimation.setOnFinished((event -> {
            game.removeAnimation(bombExplosionAnimation);
            remove();
        }));
    }

    public void remove() {
        if (destroyObstacle)
            game.setNumberOfSuccessfulBombs(game.getNumberOfSuccessfulBombs() + 1);
        game.removeBomb(this);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getRadius() {
        return radius;
    }

    public void checkCollision() {
        for (Node node : game.getObstacles().getChildren()) {
            Obstacle obstacle = (Obstacle) node;
            if (this.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                obstacle.explosion();
                destroyObstacle = true;
            }
        }
    }

}

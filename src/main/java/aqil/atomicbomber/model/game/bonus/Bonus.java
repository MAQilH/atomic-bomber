package aqil.atomicbomber.model.game.bonus;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.model.game.obstacles.Obstacle;
import aqil.atomicbomber.utils.Random;
import aqil.atomicbomber.view.animation.BonusMoveAnimation;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public abstract class Bonus extends Rectangle implements Serializable {
    public static final double WIDTH = 65, HEIGHT = 50;
    protected final Game game;
    protected double speed = 0.5;
    Obstacle org;
    public Bonus(Game game, Obstacle org, String imageName) {
        super(WIDTH, HEIGHT);
        this.game = game;
        this.org = org;

        setFill(new ImagePattern(new Image(Warplane.class.getResource("/Images/" + imageName).toExternalForm())));
    }
    public void start() {
        setX(org.getX() + org.getWidth() / 2 - WIDTH / 2);
        setY(org.getY() + org.getHeight() / 2 - HEIGHT / 2);
        speed += Random.randomDouble(-0.2, 0.2);

        game.addBonus(this);
        startMove();
    }

    BonusMoveAnimation bonusMoveAnimation;
    private void startMove() {
        bonusMoveAnimation = new BonusMoveAnimation(this);
        bonusMoveAnimation.play();

        game.addAnimation(bonusMoveAnimation);
        bonusMoveAnimation.setOnFinished((event -> {
            game.removeAnimation(bonusMoveAnimation);
        }));
    }

    public abstract void gain();

    public void remove() {
        game.removeBonus(this);
    }

    public Game getGame() {
        return game;
    }

    public double getSpeed() {
        return speed;
    }
}

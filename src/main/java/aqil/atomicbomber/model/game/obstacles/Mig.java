package aqil.atomicbomber.model.game.obstacles;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Difficulty;
import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.utils.Random;
import aqil.atomicbomber.view.animation.MigMoveAnimation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.security.Key;

public class Mig extends Fighter {
    public static final int WIDTH = 160, HEIGHT = 80;

    public Mig(Game game, Difficulty difficulty) {
        super(WIDTH, HEIGHT, "mig.png", game, 1000, 100, difficulty);
    }

    @Override
    public void start() {
        setX(-WIDTH);
        setY(Random.randomDouble(10, (double) (Game.GROUND - HEIGHT - 10) / 2));
        game.addObstacle(this);
        speed += Random.randomDouble(-0.7, 0.7);
        if (Random.bern(0.5)) {
            speed = -speed;
            setScaleX(-1);
            setX(Game.WIDTH);
        }

        int duration = Random.randomInt(2000, 4000);
        System.out.println(duration);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(duration), e -> {
                    startMove();
                })
        );
        timeline.setCycleCount(1);
        game.addTimeline(timeline);

        timeline.play();
        timeline.setOnFinished(e -> {
            game.removeTimeline(timeline);
        });
    }

    private MigMoveAnimation migMoveAnimation;

    private void startMove() {
        migMoveAnimation = new MigMoveAnimation(this);
        game.addAnimation(migMoveAnimation);
        migMoveAnimation.play();
    }

    @Override
    public void onExplosion() {
        migMoveAnimation.stop();
        game.removeAnimation(migMoveAnimation);
        App.getInstance().getGameLauncher().checkWaveEnded();
    }

    @Override
    public void pause() {
        migMoveAnimation.pause();
    }

    @Override
    public void resume() {
        migMoveAnimation.play();
    }

    @Override
    public boolean checkInRadios(double theta) {
        theta = Math.abs(theta);
        return theta < Math.PI / 3;
    }
}

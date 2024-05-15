package aqil.atomicbomber.model.game;

import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.game.bombs.Bomb;
import aqil.atomicbomber.model.game.bombs.ClusterBomb;
import aqil.atomicbomber.model.game.bombs.NormalBomb;
import aqil.atomicbomber.model.game.bombs.NuclearBomb;
import aqil.atomicbomber.view.animation.WarplaneMoveAnimation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Warplane extends Rectangle implements Serializable {
    public static double WIDTH = 120, HEIGHT = 80;
    public static double ROTATE_RATE = 10, SPEED_RATE = 0.5, ROTATE_DURATION = 75;
    public static double MAX_HP = 100;
    private DoubleProperty currentHp;
    private double speed = 4;
    private final Game game;

    public Warplane(Game game){
        super(WIDTH, HEIGHT);
        this.game = game;
        currentHp = new SimpleDoubleProperty(this, "currentHp", MAX_HP);

        initializeView();
        initializeMove();
        initializeRotate();
    }

    private void initializeView(){
        setFill(new ImagePattern(new Image(Warplane.class.getResource("/Images/warplane.png").toExternalForm())));
        setY((double) (Game.HEIGHT - Game.GROUND) /4 + (double) HEIGHT /2);
    }

    private RotateTransition rotateTransition;
    void initializeRotate(){
        Duration duration = Duration.millis(ROTATE_DURATION);
        rotateTransition = new RotateTransition(duration, this);
    }

    private WarplaneMoveAnimation warplaneMoveAnimation;
    void initializeMove(){
        warplaneMoveAnimation = new WarplaneMoveAnimation(this);
        game.addAnimation(warplaneMoveAnimation);
        warplaneMoveAnimation.play();

        warplaneMoveAnimation.setOnFinished((event -> {
            game.removeAnimation(warplaneMoveAnimation);
        }));
    }
    public void dropNormalBomb(){
        Bomb bomb = new NormalBomb(game);
        bomb.start();
    }

    public void dropClusterBomb(){
        if(game.getNumberOfClusterBombs() > 0)
            game.setNumberOfClusterBombs(game.getNumberOfClusterBombs() - 1);
        else return;

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int counter = 0;
            public void run() {
                Platform.runLater(() -> {
                    Bomb bomb = new ClusterBomb(game);
                    bomb.start();

                    counter++;
                    if (counter >= 5) {
                        timer.cancel();
                    }
                });
            }
        };

        timer.schedule(task, 0, 500);
    }

    public void dropNuclearBomb(){
        if(game.getNumberOfNuclearBombs() > 0)
            game.setNumberOfNuclearBombs(game.getNumberOfNuclearBombs() - 1);
        else return;

        Bomb bomb = new NuclearBomb(game);
        bomb.start();
    }

    public void rotateUp(){
        double rotateValue = -ROTATE_RATE;

        rotateTransition.setByAngle(rotateValue);
        if(getRotate() >= 360){
            setRotate(getRotate() - 360);
        }
        if(getRotate() <= 0){
            setRotate(getRotate() + 360);
        }
        rotateTransition.play();
    }

    public void rotateDown(){
        double rotateValue = ROTATE_RATE;

        rotateTransition.setByAngle(rotateValue);
        if(getRotate() >= 360){
            setRotate(getRotate() - 360);
        }
        if(getRotate() <= 0){
            setRotate(getRotate() + 360);
        }
        rotateTransition.play();
    }

    public void increaseSpeed(){
        speed = Math.min(SPEED_RATE*20, speed + SPEED_RATE);
    }

    public void decreaseSpeed(){
        speed = Math.max(SPEED_RATE*4, speed - SPEED_RATE);
    }

    public double getSpeed() {
        return speed;
    }

    public void setCurrentHp(double currentHp) {
        this.currentHp.set(currentHp);
    }

    public double getCurrentHp() {
        return currentHp.get();
    }

    public DoubleProperty currentHpProperty(){
        return currentHp;
    }

    public Pair<Double, Double> getCenterCoordinate(){
        return new Pair<Double, Double>(getX() + WIDTH/3.5, getY() + HEIGHT/2.);
    }
}

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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;

public class Warplane extends Rectangle {
    public static double WIDTH = 120, HEIGHT = 80, ROTATE_RATE = 10, SPEED_RATE = 0.5, ROTATE_DURATION = 100;
    Game game;
    private double speed = 4;

    public Warplane(Game game){
        super(WIDTH, HEIGHT);
        this.game = game;
        setFill(new ImagePattern(new Image(Warplane.class.getResource("/Images/warplane.png").toExternalForm())));
        setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.W)
                rotateUp();
            else if(event.getCode() == KeyCode.S)
                rotateDown();
            else if(event.getCode() == KeyCode.D)
                increaseSpeed();
            else if(event.getCode() == KeyCode.A)
                decreaseSpeed();
            else if(event.getCode() == KeyCode.SPACE)
                dropNormalBomb();
            else if(event.getCode() == KeyCode.C)
                dropClusterBomb();
            else if(event.getCode() == KeyCode.R)
                dropNuclearBomb();
        });

        setY((double) (Game.HEIGHT - Game.GROUND) /4 + (double) HEIGHT /2);

        WarplaneMoveAnimation warplaneMoveAnimation = new WarplaneMoveAnimation(this);
        warplaneMoveAnimation.play();

        initializeRotate();
    }

    private RotateTransition rotateTransition;
    void initializeRotate(){
        Duration duration = Duration.millis(ROTATE_DURATION);
        rotateTransition = new RotateTransition(duration, this);
    }

    private WarplaneMoveAnimation warplaneMoveAnimation;
    void initializeMove(){
        warplaneMoveAnimation = new WarplaneMoveAnimation(this);
        warplaneMoveAnimation.play();
    }


    void dropNormalBomb(){
        Bomb bomb = new NormalBomb(game);
        bomb.start();
    }

    void dropClusterBomb(){

//        final Timeline timeline = new Timeline();
//        timeline.setCycleCount(5);
//        timeline.setAutoReverse(true);
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
//                new KeyValue()));
//        timeline.play();

        Bomb bomb = new ClusterBomb(game);
        bomb.start();
    }

    void dropNuclearBomb(){
        Bomb bomb = new NuclearBomb(game);
        bomb.start();
    }

    void rotateUp(){
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

    void rotateDown(){
        System.out.println("down");
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

    void increaseSpeed(){
        speed = Math.min(SPEED_RATE*20, speed + SPEED_RATE);
    }

    void decreaseSpeed(){
        speed = Math.max(SPEED_RATE*4, speed - SPEED_RATE);
    }

    public double getSpeed() {
        return speed;
    }
}

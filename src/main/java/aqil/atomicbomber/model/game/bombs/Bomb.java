package aqil.atomicbomber.model.game.bombs;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.view.animation.BombExplosionAnimation;
import aqil.atomicbomber.view.animation.BombMoveAnimation;
import javafx.scene.shape.Rectangle;

public abstract class Bomb extends Rectangle {
    private final double radius;
    private double speed = 0;
    private double acceleration = 0.2;
    private final Game game;
    private final String bombImageAddress;
    private final int numberOfFrame;
    Bomb(double HEIGHT, double WIDTH, double radius, Game game, String bombImageAddress, int numberOfFrame){
        super(HEIGHT, WIDTH);
        this.radius = radius;
        this.game = game;
        this.bombImageAddress = bombImageAddress;
        this.numberOfFrame = numberOfFrame;
    }

    private void initialize(Warplane warplane){
        speed = warplane.getSpeed()/2;
        this.setX(warplane.getX() + warplane.getWidth() / 2 - this.getWidth() / 2);
        this.setY(warplane.getY() + warplane.getWidth() / 2 - this.getWidth() / 2);
        double rotate = warplane.getRotate();
        if(rotate > 270) rotate = 0;
        else if(rotate > 180) rotate = 180;
        this.setRotate(rotate);
    }

    public void start(){
        initialize(game.getWarplane());
        game.addBomb(this);
        startMove();
    }

    private void startMove(){
        BombMoveAnimation bombMoveAnimation = new BombMoveAnimation(this);
        bombMoveAnimation.play();
    }

    public void explosion(){
        BombExplosionAnimation bombExplosionAnimation = new BombExplosionAnimation(this, bombImageAddress, numberOfFrame);
        bombExplosionAnimation.play();
    }

    public void remove(){
        game.removeBomb(this);
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getAcceleration(){
        return acceleration;
    }

    public double getRadius() {
        return radius;
    }
}

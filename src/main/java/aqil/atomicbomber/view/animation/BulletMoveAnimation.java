package aqil.atomicbomber.view.animation;

import aqil.atomicbomber.model.game.Bullet;
import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.obstacles.Fighter;
import aqil.atomicbomber.model.game.obstacles.Obstacle;
import aqil.atomicbomber.model.game.obstacles.Tank;
import javafx.animation.Transition;

public class BulletMoveAnimation extends Transition {

    private Game game;
    private Bullet bullet;
    private Fighter fighter;

    public BulletMoveAnimation(Bullet bullet, Game game, Fighter fighter){
        this.bullet = bullet;
        this.game = game;
        this.fighter = fighter;
        setCycleCount(-1);
        setCycleDuration(javafx.util.Duration.millis(200));
    }

    @Override
    protected void interpolate(double v) {
        bullet.setX(bullet.getX() + bullet.getSpeed() * Math.cos(Math.toRadians(bullet.getRotate())));
        bullet.setY(bullet.getY() + bullet.getSpeed() * Math.sin(Math.toRadians(bullet.getRotate())));

        bullet.setSpeed(bullet.getSpeed() - bullet.getSpeed()/50);

        if(bullet.getX() < -bullet.getWidth() || bullet.getX() > Game.WIDTH || bullet.getY() < 0){
            bullet.remove();
            stop();
        }

        if(bullet.getDistFromWarplane() < bullet.getRadius() || bullet.getY() + bullet.getHeight() > Game.GROUND){
            bullet.explosion();
            stop();
        }
    }
}

package aqil.atomicbomber.model.game;

import aqil.atomicbomber.model.game.bombs.Bomb;
import aqil.atomicbomber.model.game.obstacles.Obstacle;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Game {
    Pane root;
    Group bombs = new Group();
    Group obstacles = new Group();
    public static int WIDTH = 1500, HEIGHT = 800;
    public static int GROUND = 725;

//    public static int WIDTH = 800, HEIGHT = 450;
//    public static int GROUND = 380;

    private Warplane warplane;
    private double accurate;
    private int killingNumber;

    public Game(Pane root){
        this.root = root;
        root.getChildren().addAll(bombs, obstacles);
        warplane = new Warplane(this);
        accurate = 0;
        killingNumber = 0;
    }

    public void addBomb(Bomb bomb){
        bombs.getChildren().add(bomb);
    }

    public void removeBomb(Bomb bomb){
        bombs.getChildren().remove(bomb);
    }

    public Warplane getWarplane() {
        return warplane;
    }

    public void setWarplane(Warplane warplane) {
        this.warplane = warplane;
    }

    public double getAccurate() {
        return accurate;
    }

    public void setAccurate(double accurate) {
        this.accurate = accurate;
    }

    public int getKillingNumber() {
        return killingNumber;
    }

    public void setKillingNumber(int killingNumber) {
        this.killingNumber = killingNumber;
    }

}

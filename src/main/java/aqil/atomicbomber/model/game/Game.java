package aqil.atomicbomber.model.game;

import aqil.atomicbomber.model.game.bombs.Bomb;
import aqil.atomicbomber.model.game.bonus.Bonus;
import aqil.atomicbomber.model.game.obstacles.Obstacle;
import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    public static int WIDTH = 1500, HEIGHT = 800;
    public static int GROUND = 725;
    private final transient Group bullets = new Group();
    private final transient Group bombs = new Group();
    private final transient Group obstacles = new Group();
    private final transient Group bonuses = new Group();
    private final ArrayList<Transition> animations = new ArrayList<>();
    private Warplane warplane;


    private int numberOfPuttedBombs = 0;
    private int numberOfSuccessfulBombs = 0;
    private DoubleProperty accurate;
    private IntegerProperty killingNumber;
    private IntegerProperty numberOfNuclearBombs;
    private IntegerProperty numberOfClusterBombs;
    private IntegerProperty freezePercentage;
    private IntegerProperty waveNumber;
    public Game(){
        accurate = new SimpleDoubleProperty(this, "accurate", 1);
        killingNumber = new SimpleIntegerProperty(this, "killingNumber", 0);
        numberOfNuclearBombs = new SimpleIntegerProperty(this, "numberOfNuclearBombs", 0);
        numberOfClusterBombs = new SimpleIntegerProperty(this, "numberOfClusterBombs", 0);
        freezePercentage = new SimpleIntegerProperty(this, "freezePercentage", 0);
        waveNumber = new SimpleIntegerProperty(this, "waveNumber", 1);

    }
    public int getNumberOfPuttedBombs() {
        return numberOfPuttedBombs;
    }

    public void setNumberOfPuttedBombs(int numberOfPuttedBombs) {
        this.numberOfPuttedBombs = numberOfPuttedBombs;
    }

    public int getNumberOfSuccessfulBombs() {
        return numberOfSuccessfulBombs;
    }

    public void setNumberOfSuccessfulBombs(int numberOfSuccessfulBombs) {
        this.numberOfSuccessfulBombs = numberOfSuccessfulBombs;
    }

    public void addAnimation(Transition animation){
        animations.add(animation);
    }

    public void removeAnimation(Transition animation){
        animations.remove(animation);
    }

    public void addBullet(Bullet bullet){
        bullets.getChildren().add(bullet);
    }

    public void removeBullet(Bullet bullet){
        bullets.getChildren().remove(bullet);
    }
    public void addBomb(Bomb bomb){
        bombs.getChildren().add(bomb);
    }

    public void removeBomb(Bomb bomb){
        bombs.getChildren().remove(bomb);
    }

    public void addObstacle(Obstacle obstacle){
        obstacles.getChildren().add(obstacle);
    }

    public void removeObstacle(Obstacle obstacle){
        obstacles.getChildren().remove(obstacle);
    }

    public void addBonus(Bonus bonus){
        bonuses.getChildren().add(bonus);
    }

    public void removeBonus(Bonus bonus){
        bonuses.getChildren().remove(bonus);
    }

    public Warplane getWarplane() {
        return warplane;
    }

    public void setWarplane(Warplane warplane) {
        this.warplane = warplane;
    }

    public double getAccurate() {
        return accurate.get();
    }

    public void setAccurate() {
        this.accurate.set((double) numberOfSuccessfulBombs/numberOfPuttedBombs);
    }

    public int getKillingNumber() {
        return killingNumber.get();
    }

    public void setKillingNumber(int killingNumber) {
        this.killingNumber.set(killingNumber);
    }

    public Group getObstacles(){
        return obstacles;
    }

    public int getNumberOfNuclearBombs() {
        return numberOfNuclearBombs.get();
    }

    public void setNumberOfNuclearBombs(int numberOfNuclearBombs) {
        this.numberOfNuclearBombs.set(numberOfNuclearBombs);
    }

    public int getNumberOfClusterBombs() {
        return numberOfClusterBombs.get();
    }

    public void setNumberOfClusterBombs(int numberOfClusterBombs) {
        this.numberOfClusterBombs.set(numberOfClusterBombs);
    }

    public int getFreezePercentage() {
        return freezePercentage.get();
    }

    public void setFreezePercentage(int freezePercentage) {
        this.freezePercentage.set(Math.min(100, freezePercentage));
    }

    public ArrayList<Transition> getAnimations() {
        return animations;
    }

    public int getWaveNumber() {
        return waveNumber.get();
    }

    public void setWaveNumber(int waveNumber){
        this.waveNumber.set(waveNumber);
    }

    public Group getBombs() {
        return bombs;
    }

    public Group getBonuses() {
        return bonuses;
    }

    public Group getBullets() {
        return bullets;
    }

    public IntegerProperty killingNumberProperty() {
        return killingNumber;
    }

    public IntegerProperty numberOfNuclearBombsProperty() {
        return numberOfNuclearBombs;
    }

    public IntegerProperty numberOfClusterBombsProperty() {
        return numberOfClusterBombs;
    }

    public IntegerProperty freezePercentageProperty() {
        return freezePercentage;
    }

    public IntegerProperty waveNumberProperty() {
        return waveNumber;
    }

    public DoubleProperty accurateProperty() {
        return accurate;
    }

}

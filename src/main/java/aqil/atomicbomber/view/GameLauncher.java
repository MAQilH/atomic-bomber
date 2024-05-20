package aqil.atomicbomber.view;

import aqil.atomicbomber.controller.MenuLoader;
import aqil.atomicbomber.model.App;
import aqil.atomicbomber.model.Menu;
import aqil.atomicbomber.model.MusicTrack;
import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.GameResult;
import aqil.atomicbomber.model.game.Warplane;
import aqil.atomicbomber.model.game.obstacles.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameLauncher {
    private AnchorPane gameRoot;
    private StackPane root;
    private Game game;
    private GameActionController actionController;

    public void start() {
        root = new StackPane();
        gameRoot = new AnchorPane();
        game = new Game();

        initializeGameObject(1);
        initializeGameRoot();
        initializeRoot();

        showScene();

        setMusic(MusicTrack.TRACK1);

    }
    public void setMusic(MusicTrack newMusicTrack) {
        stopMusic();
        App.getInstance().getSetting().setMusicTrack(newMusicTrack);
        App.getInstance().getSetting().getMusicTrack().play();
    }

    public void stopMusic(){
        MusicTrack musicTrack = App.getInstance().getSetting().getMusicTrack();
        if(musicTrack != null)
            musicTrack.stop();
    }

    public void reloadGame() {
        root = new StackPane();
        gameRoot = new AnchorPane();

        try {
            FileInputStream fin = new FileInputStream("game.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fin);
            game = (Game) objectIn.readObject();

            gameRoot.getChildren().add(game.getWarplane());
        } catch (Exception e) {
            System.err.println ("Unable to reload game data");
            MenuLoader.setMenu(Menu.MAIN_MENU);
        }

        initializeGameRoot();
        initializeRoot();
        showScene();
    }

    public void saveGame() {
        try {
            FileOutputStream out = new FileOutputStream("game.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            objectOut.writeObject(game);
            objectOut.close();

            MenuLoader.setMenu(Menu.MAIN_MENU);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showScene(){
        Stage stage = App.getInstance().getStage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();

        blackAndWhite();

        stage.show();
    }

    private void blackAndWhite() {
        if(App.getInstance().getSetting().isBlackAndWhite()){
            ColorAdjust colorAdjust = new ColorAdjust();
            System.out.println("Black and white");
            colorAdjust.setSaturation(-1);
            root.setEffect(colorAdjust);
        }
    }

    void initializeRoot(){
        setPaneSize(root);
        root.getChildren().add(gameRoot);
    }

    void initializeGameRoot(){
        setPaneSize(gameRoot);
        setBackground();
        addView();
        addGameController();
    }

    void addGameController(){
        actionController = new GameActionController(game, this);
        gameRoot.getChildren().add(actionController);
        actionController.setFocusTraversable(true);
    }

    void setPaneSize(Pane pane){
        pane.setMinHeight(Game.HEIGHT);
        pane.setMaxHeight(Game.HEIGHT);
        pane.setMinWidth(Game.WIDTH);
        pane.setMaxWidth(Game.WIDTH);
    }

    void setBackground(){
        Image image = new Image(GameLauncher.class.getResource("/Images/game-background2.jpg").toExternalForm(), Game.WIDTH, Game.HEIGHT, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
                );
        gameRoot.setBackground(new Background(backgroundImage));
    }

    void addView(){
        GameViewBuilder gameViewBuilder = new GameViewBuilder(gameRoot, game);
        gameViewBuilder.start();
    }

    void initializeGameObject(int wave){
        if(wave == 1){
            gameRoot.getChildren().add(game.getBullets());
            gameRoot.getChildren().add(game.getObstacles());
            addWarplane();
            gameRoot.getChildren().add(game.getBombs());
            gameRoot.getChildren().add(game.getWarplane());
            gameRoot.getChildren().add(game.getBonuses());
        }

        initBuilding(wave);
        initTrees(wave);
        initTrench(wave);
        initTank(wave);
        initTruck(wave);
        initMig(wave);
    }

    void addWarplane(){
        game.setWarplane(new Warplane(game));

        game.getWarplane().currentHpProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.doubleValue() <= 0){
                endGame(false);
            }
        });
    }

    void initTank(int wave){
        int tankNumber = 2 + wave;
        for (int tankIndex = 0; tankIndex < tankNumber; tankIndex++) {
            Tank tank = new Tank(game, App.getInstance().getSetting().getDifficulty());
            tank.start();
        }
    }

    void initTrees(int wave){
        int treeNumber = 3 + wave;
        for (int treeIndex = 0; treeIndex < treeNumber; treeIndex++) {
            Tree tree = new Tree(game);
            tree.start();
        }
    }

    void initBuilding(int wave){
        int treeNumber = 1 + wave;
        for (int buildingIndex = 0; buildingIndex < treeNumber; buildingIndex++) {
            Building building = new Building(game);
            building.start();
        }
    }

    void initTrench(int wave){
        int trenchNumber = 1 + wave;
        for (int trenchIndex = 0; trenchIndex < trenchNumber; trenchIndex++) {
            Trench trench = new Trench(game);
            trench.start();
        }
    }

    void initTruck(int wave){
        int truckNumber = 1 + wave;
        for (int truckIndex = 0; truckIndex < truckNumber; truckIndex++) {
            Truck truck = new Truck(game);
            truck.start();
        }
    }

    void initMig(int wave){
        if(wave == Game.WAVE_NUMBER){
            Mig mig = new Mig(game, App.getInstance().getSetting().getDifficulty());
            mig.start();

        }
    }

    public void freezeGame(){
        if(game.getFreezePercentage() < 100) return;
        game.setFreezePercentage(0);

        for(Node node: game.getObstacles().getChildren()){
            Obstacle obstacle = (Obstacle) node;
            obstacle.pause();
            obstacle.setOpacity(0.5);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                for(Node node: game.getObstacles().getChildren()){
                    Obstacle obstacle = (Obstacle) node;
                    obstacle.resume();
                    obstacle.setOpacity(1);
                }
                timer.cancel();
            }
        };
        timer.schedule(task, 5000, 5000);
    }

    public void pauseGame(Node node) {
        blurScene();

        for(Transition transition: game.getAnimations()){
            transition.pause();
        }
        for(Timeline timeline: game.getTimelines()){
            timeline.pause();
        }
        actionController.setDisable(true);

        node.setFocusTraversable(true);

        root.getChildren().add(node);
    }

    public void resumeGame(Node node) {
        for(Transition transition: game.getAnimations()){
            transition.play();
        }
        for(Timeline timeline: game.getTimelines()){
            timeline.play();
        }
        actionController.setDisable(false);

        root.getChildren().remove(node);

        unBlurScene();
    }

    public void blurScene(){
        GaussianBlur blur = new GaussianBlur(0);
        gameRoot.setEffect(blur);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(blur.radiusProperty(), 13);
        KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    public void unBlurScene(){
        GaussianBlur blur = new GaussianBlur(0);
        gameRoot.setEffect(blur);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(blur.radiusProperty(), 0);
        KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    public void nextWave() {
        for (Node child : game.getObstacles().getChildren()) {
            Obstacle obstacle = (Obstacle) child;
            obstacle.explosion();
        }
        game.setAccurate();
        int waveNumber = game.getWaveNumber();
        if(waveNumber == Game.WAVE_NUMBER){
            endGame(true);
            return;
        }
        waveNumber++;
        game.setWaveNumber(waveNumber);
        initializeGameObject(waveNumber);
    }

    public void endGame(boolean win){
        stopMusic();
        GameResult gameResult = new GameResult(
                App.getInstance().getUser().getId(),
                win ? Game.WAVE_NUMBER: game.getWaveNumber() - 1,
                game.getKillingNumber(),
                App.getInstance().getSetting().getDifficulty().getValue() * game.getKillingNumber(),
                game.getAccurate()
                );
        ((GameResultMenuController) Menu.GAME_RESULT_MENU.getMenuController()).updateMenu(gameResult);
        pauseGame(Menu.GAME_RESULT_MENU.getRoot());
    }

    public void getNuclearBomb() {
        game.setNumberOfNuclearBombs(game.getNumberOfNuclearBombs() + 1);
    }

    public void getClusterBomb() {
        game.setNumberOfClusterBombs(game.getNumberOfClusterBombs() + 1);
    }

    public void addTank() {
        Tank tank = new Tank(game, App.getInstance().getSetting().getDifficulty());
        tank.start();
    }

    public void resetHP() {
        game.getWarplane().setCurrentHp(Warplane.MAX_HP);
    }

    public void checkWaveEnded(){
        if(game.getObstacles().getChildren().isEmpty()){
            nextWave();
        }
    }
}

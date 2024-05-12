package aqil.atomicbomber.view;

import aqil.atomicbomber.model.game.Game;
import aqil.atomicbomber.model.game.Warplane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameLauncher {
    Pane root;
    Label accurateNum;
    Label killingNumber;
    Game game;

    public void start(Stage stage) {

        root = new Pane();
        game = new Game(root);
        initialize();
        stage.setScene(new Scene(root));
        stage.show();

        game.getWarplane().setFocusTraversable(true);
    }

    void initialize(){
        setPaneSize();
        setBackground();
        addWarplane();
        addInfoView();
    }

    void setPaneSize(){
        root.setMinHeight(Game.HEIGHT);
        root.setMaxHeight(Game.HEIGHT);
        root.setMinWidth(Game.WIDTH);
        root.setMaxWidth(Game.WIDTH);
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
        root.setBackground(new Background(backgroundImage));
    }

    void addInfoView(){
        VBox infoBox = new VBox();
        HBox killingNumCount = new HBox();
        HBox accurateCount = new HBox();
        Label killingLabel = new Label();
        Label accurateLabel = new Label();
        accurateNum = new Label();
        killingNumber = new Label();
        infoBox.getChildren().add(killingNumCount);
        infoBox.getChildren().add(accurateCount);
        killingNumCount.getChildren().addAll(killingLabel, killingNumber);
        accurateCount.getChildren().addAll(accurateLabel, accurateNum);
        root.getChildren().addAll(infoBox);
        accurateLabel.setText("Accurate: ");
        killingLabel.setText("Killing Number: ");
        accurateNum.setText("0");
        killingNumber.setText("0");
        game.setAccurate(0);
        game.setKillingNumber(0);
    }

    void addWarplane(){
        root.getChildren().add(game.getWarplane());
    }

}

package aqil.atomicbomber.view;

import aqil.atomicbomber.model.game.Game;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class GameViewBuilder {
    private AnchorPane root;
    private Game game;

    GameViewBuilder(AnchorPane root, Game game){
        this.root = root;
        this.game = game;
    }

    void start(){
        addStyle();
        addInfo();
        addFreezeProgressBar();
    }

    void addStyle(){
        root.getStylesheets().add(getClass().getResource("/aqil/atomicbomber/Style/game.css").toExternalForm());
    }

    void addInfo(){
        VBox info = new VBox();
        info.getStyleClass().add("info");

        addInfoLabel(info, "HP", game.getWarplane().currentHpProperty());
        addInfoLabel(info, "Accurate", game.accurateProperty());
        addInfoLabel(info, "Killing", game.killingNumberProperty());
        addInfoLabel(info, "Nuclear Bombs", game.numberOfNuclearBombsProperty());
        addInfoLabel(info, "Cluster Bombs", game.numberOfClusterBombsProperty());
        addInfoLabel(info, "Wave Number", game.waveNumberProperty());
        root.getChildren().add(info);
    }

    void addInfoLabel(Pane count, String label, Property<Number> prop){
        HBox infoCount = new HBox();
        infoCount.getStyleClass().add("info-count");

        Label infoLabel = new Label();
        Label infoNum = new Label();
        count.getChildren().add(infoCount);
        infoLabel.setText(label + ": ");
        infoNum.setText(String.valueOf(prop.getValue()));
        infoCount.getChildren().addAll(infoLabel, infoNum);

        prop.addListener((observable, oldValue, newValue) -> {
            infoNum.setText(String.valueOf(newValue));

            Timeline colorAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(infoNum.textFillProperty(), Color.web("#605868"))),
                    new KeyFrame(Duration.millis(500), new KeyValue(infoNum.textFillProperty(), Color.RED)),
                    new KeyFrame(Duration.millis(1000), new KeyValue(infoNum.textFillProperty(), Color.web("#605868")))
            );
            colorAnimation.setCycleCount(2);
            colorAnimation.setAutoReverse(true);
            colorAnimation.play();
        });
    }

    void addFreezeProgressBar(){
        ProgressBar progressBar = new ProgressBar();

        progressBar.getStyleClass().add("freeze-progress-bar");
        AnchorPane.setRightAnchor(progressBar, 5.0);
        AnchorPane.setTopAnchor(progressBar, 5.0);


        progressBar.setProgress((double) game.getFreezePercentage() /100);
        game.freezePercentageProperty().addListener((observable, oldValue, newValue) -> {
            progressBar.setProgress(newValue.doubleValue() / 100);
        });

        root.getChildren().add(progressBar);
    }
}

package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.User;
import aqil.atomicbomber.model.game.GameRecord;
import aqil.atomicbomber.model.game.GameResult;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ScoreController {

    public ObservableList<GameRecord> getScoreDate(int number) {
        ArrayList<GameResult> results = Database.getInstance().loadGameResultWithColumn("hardness", 10);
        ObservableList<GameRecord> data = FXCollections.observableArrayList();

        for (GameResult gameResult : results) {
            User user = Database.getInstance().getUserWithId(gameResult.getUserId());
            GameRecord record = new GameRecord(
                    gameResult.getWave(),
                    gameResult.getKills(),
                    gameResult.getHardness(),
                    gameResult.getAccurate(),
                    data.size() + 1,
                    user == null ? "Guest" : user.getUsername()
            );
            data.add(record);
        }
        return data;
    }
}

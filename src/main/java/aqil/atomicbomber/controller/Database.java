package aqil.atomicbomber.controller;

import aqil.atomicbomber.model.User;
import aqil.atomicbomber.model.game.GameResult;

import java.sql.*;
import java.util.ArrayList;

public class Database {
private static Database instance = null;

    private Database() {
        connect();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private Connection connection;

    void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/atomicbomber";
            String username = "root";
            String password = "13831383";
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Could not connect to the database: " + e.getMessage());
        }
    }

    public User getUserWithUsername(String usernameQ) {
        String sql = "SELECT * FROM USER WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usernameQ);
            pstmt.executeQuery();

            ResultSet res = pstmt.getResultSet();
            if (res.next()) {
                int id = res.getInt("id");
                String username = res.getString("username");
                String password = res.getString("password");
                return new User(id, username, password);
            } else return null;
        } catch (SQLException e) {
            System.out.println("Could not get user from the database: " + e.getMessage());
            return null;
        }
    }

    public User saveUser(String username, String password) {
        String sql = "INSERT INTO USER (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            pstmt.executeUpdate();

            System.out.println("User saved to the database!");
            return getUserWithUsername(username);
        } catch (SQLException e) {
            System.out.println("Could not save user to the database: " + e.getMessage());
            return null;
        }
    }

    public void saveGameResult(GameResult gameResult){
        String sql = "INSERT INTO GAME_RESULT (user_id, score, wave, kills, hardness, accurate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, gameResult.getUserId());
            pstmt.setDouble(2, gameResult.getScore());
            pstmt.setInt(3, gameResult.getWave());
            pstmt.setInt(4, gameResult.getKills());
            pstmt.setDouble(5, gameResult.getHardness());
            pstmt.setDouble(6, gameResult.getAccurate());

            pstmt.executeUpdate();

            System.out.println("Game result saved to the database!");
        } catch (SQLException e) {
            System.out.println("Could not save game result to the database: " + e.getMessage());
        }
    }

    public ArrayList<GameResult> loadGameResultWithColumn(String column, int numberOfLoad){
        String sql = "SELECT * FROM GAME_RESULT ORDER BY ? DESC LIMIT ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, column);
            pstmt.setInt(2, numberOfLoad);
            pstmt.executeQuery();

            ResultSet res = pstmt.getResultSet();
            ArrayList<GameResult> gameResults = new ArrayList<>();
            while (res.next()) {
                int userId = res.getInt("user_id");
                double score = res.getDouble("score");
                int wave = res.getInt("wave");
                int kills = res.getInt("kills");
                double hardness = res.getDouble("hardness");
                double accurate = res.getDouble("accurate");
                gameResults.add(new GameResult(userId, score, wave, kills, hardness, accurate));
            }
            return gameResults;
        } catch (SQLException e) {
            System.out.println("Could not load game result from the database: " + e.getMessage());
            return null;
        }
    }


    public void close() {
        try {
            connection.close();
            System.out.println("Connection to the database closed!");
        } catch (SQLException e) {
            System.out.println("Could not close the connection to the database: " + e.getMessage());
        }
    }
}

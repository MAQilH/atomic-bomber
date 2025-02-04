package aqil.atomicbomber.model.game;

public class GameResult {
    private final int userId;
    private final int wave;
    private final int kills;
    private final double hardness;
    private final double accurate;
    private String username;

    public GameResult(int userId, int wave, int kills, double hardness, double accurate) {
        this.userId = userId;
        this.wave = wave;
        this.kills = kills;
        this.hardness = hardness;
        this.accurate = accurate;
    }

    public int getUserId() {
        return userId;
    }

    public int getWave() {
        return wave;
    }

    public int getKills() {
        return kills;
    }

    public double getHardness() {
        return hardness;
    }

    public double getAccurate() {
        return accurate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package aqil.atomicbomber.model.game;

public class GameResult {
    private final int userId;
    private final double score;
    private final int wave;
    private final int kills;
    private final double hardness;
    private final double accurate;

    public GameResult(int userId, double score, int wave, int kills, double hardness, double accurate) {
        this.userId = userId;
        this.score = score;
        this.wave = wave;
        this.kills = kills;
        this.hardness = hardness;
        this.accurate = accurate;
    }

    public int getUserId() {
        return userId;
    }

    public double getScore() {
        return score;
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
}

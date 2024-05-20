package aqil.atomicbomber.model.game;

public class GameRecord {
    private final int wave;
    private final int kills;
    private final double hardness;
    private final double accurate;
    private final int rank;
    private final String username;

    public GameRecord(int wave, int kills, double hardness, double accurate, int rank, String username) {
        this.wave = wave;
        this.kills = kills;
        this.hardness = hardness;
        this.accurate = accurate;
        this.rank = rank;
        this.username = username;
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

    public int getRank() {
        return rank;
    }

    public String getUsername() {
        return username;
    }
}

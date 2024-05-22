package aqil.atomicbomber.model;

public enum Wave {
    WAVE1, WAVE2, WAVE3, WAVE_WIN;

    public Wave getNextWave() {
        int nextWave = this.ordinal() + 1;
        return Wave.values()[Math.min(nextWave, Wave.values().length - 1)];
    }

    public int getValue() {
        return this.ordinal();
    }
}

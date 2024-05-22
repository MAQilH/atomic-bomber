package aqil.atomicbomber.utils;

public class Random {
    public static double randomDouble(double min, double max) {
        return Math.random() * (max - min + 1) + min;
    }

    public static int randomInt(int min, int max) {
        return (int) randomDouble(min, max);
    }

    public static boolean bern(double p) {
        return randomDouble(0, 1) < p;
    }
}

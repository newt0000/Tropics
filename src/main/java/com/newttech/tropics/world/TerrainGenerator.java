package com.newttech.tropics.world;

public class TerrainGenerator {

    public static double getTerrainHeight(int x, int z) {

        double scale = 0.005;

        double noise = simpleNoise(x * scale, z * scale);

        // Island shaping (key tropical logic)
        double islandMask = islandFalloff(x, z);

        return noise * 12 * islandMask;
    }

    private static double islandFalloff(int x, int z) {

        double distance = Math.sqrt(x * x + z * z);

        double radius = 800;

        double value = 1.0 - (distance / radius);

        return clamp(value, 0, 1);
    }

    private static double simpleNoise(double x, double z) {
        return Math.sin(x) * Math.cos(z);
    }

    private static double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}
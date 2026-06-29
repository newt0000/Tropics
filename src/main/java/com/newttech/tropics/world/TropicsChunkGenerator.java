package com.newttech.tropics.world;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import java.util.Random;

public class TropicsChunkGenerator extends ChunkGenerator {
    private static WorldDecorator decorator;
    public static void setDecorator(WorldDecorator d) {
        decorator = d;
    }
    @Override
    public void generateNoise(World world, Random random, int chunkX, int chunkZ, ChunkData chunkData) {

        int baseHeight = 64;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                int worldX = chunkX * 16 + x;
                int worldZ = chunkZ * 16 + z;

                double height = TerrainGenerator.getTerrainHeight(worldX, worldZ);

                int finalHeight = (int) (baseHeight + height);

                // Ocean floor
                for (int y = 0; y < 50; y++) {
                    chunkData.setBlock(x, y, z, org.bukkit.Material.STONE);
                }

                // Water layer
                for (int y = 50; y < 62; y++) {
                    chunkData.setBlock(x, y, z, org.bukkit.Material.WATER);
                }

                // Terrain surface
                chunkData.setBlock(x, finalHeight, z, getSurfaceBlock(height));

                // Fill below surface
                for (int y = 51; y < finalHeight; y++) {
                    chunkData.setBlock(x, y, z, org.bukkit.Material.DIRT);
                }
            }
        }
        if (decorator != null) {
            decorator.decorateChunk(world, chunkX, chunkZ);
        }
    }

    private org.bukkit.Material getSurfaceBlock(double height) {
        if (height < -2) return org.bukkit.Material.SAND;
        if (height < 2) return org.bukkit.Material.SAND;
        return org.bukkit.Material.GRASS_BLOCK;
    }
}
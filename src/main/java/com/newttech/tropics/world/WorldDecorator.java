package com.newttech.tropics.world;

import com.newttech.tropics.world.structure.StructureManager;
import com.newttech.tropics.world.vegetation.PalmTreeManager;
import org.bukkit.World;

public class WorldDecorator {

    private final PalmTreeManager palmTreeManager;
    private final StructureManager structureManager;

    public WorldDecorator(PalmTreeManager palmTreeManager, StructureManager structureManager) {
        this.palmTreeManager = palmTreeManager;
        this.structureManager = structureManager;
    }

    public void decorateChunk(World world, int chunkX, int chunkZ) {

        int startX = chunkX * 16;
        int startZ = chunkZ * 16;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {

                int worldX = startX + x;
                int worldZ = startZ + z;

                palmTreeManager.trySpawnPalm(world, worldX, worldZ);
                structureManager.tryPlaceStructures(world, worldX, worldZ);
            }
        }

    }
}
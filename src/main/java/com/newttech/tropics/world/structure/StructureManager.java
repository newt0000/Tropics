package com.newttech.tropics.world.structure;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.Random;

public class StructureManager {

    private final Random random = new Random();

    public void tryPlaceStructures(World world, int x, int z) {

        if (random.nextDouble() > 0.02) return;

        Location loc = new Location(world, x, world.getHighestBlockYAt(x, z), z);

        placeBeachHut(world, loc);
    }

    private void placeBeachHut(World world, Location loc) {

        int baseY = loc.getBlockY();

        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {

                world.getBlockAt(loc.getBlockX() + x, baseY, loc.getBlockZ() + z)
                        .setType(org.bukkit.Material.OAK_PLANKS);
            }
        }

        for (int y = 1; y <= 3; y++) {
            world.getBlockAt(loc.getBlockX(), baseY + y, loc.getBlockZ())
                    .setType(org.bukkit.Material.OAK_LOG);
        }
    }
}
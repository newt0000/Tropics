package com.newttech.tropics.world.vegetation;

import com.newttech.tropics.Tropics;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class PalmTreeManager {

    private final Tropics plugin;
    private final Random random = new Random();

    public PalmTreeManager(Tropics plugin) {
        this.plugin = plugin;
    }

    public void trySpawnPalm(World world, int x, int z) {

        // Only spawn occasionally
        if (random.nextDouble() > 0.03) return;

        int y = world.getHighestBlockYAt(x, z);

        Location base = new Location(world, x, y, z);

        if (!isValidBeachSpot(world, base)) return;

        placePalm(world, base);
    }

    private boolean isValidBeachSpot(World world, Location loc) {

        Material ground = loc.clone().add(0, -1, 0).getBlock().getType();

        return ground == Material.SAND;
    }
    private void placeSimplePalm(World world, Location loc) {

        int height = 5 + random.nextInt(3);

        for (int i = 0; i < height; i++) {
            world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i, loc.getBlockZ())
                    .setType(org.bukkit.Material.OAK_LOG);
        }

        addLeaves(world, loc, height);
    }
    private void placeBentPalm(World world, Location loc) {

        int height = 6 + random.nextInt(4);

        int dx = random.nextBoolean() ? 1 : -1;

        for (int i = 0; i < height; i++) {
            world.getBlockAt(loc.getBlockX() + (i > 2 ? dx : 0),
                            loc.getBlockY() + i,
                            loc.getBlockZ())
                    .setType(org.bukkit.Material.OAK_LOG);
        }

        addLeaves(world, loc, height);
    }
    private void placeTallPalm(World world, Location loc) {

        int height = 8 + random.nextInt(6);

        for (int i = 0; i < height; i++) {
            world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i, loc.getBlockZ())
                    .setType(org.bukkit.Material.OAK_LOG);
        }

        addLeaves(world, loc, height);
    }
    private void addLeaves(World world, Location loc, int height) {

        int topY = loc.getBlockY() + height;

        for (int dx = -2; dx <= 2; dx++) {
            for (int dz = -2; dz <= 2; dz++) {

                if (Math.abs(dx) + Math.abs(dz) > 3) continue;

                world.getBlockAt(loc.getBlockX() + dx,
                                topY,
                                loc.getBlockZ() + dz)
                        .setType(org.bukkit.Material.JUNGLE_LEAVES);
            }
        }
    }
    private void placePalm(World world, Location loc) {

        int variant = random.nextInt(3);

        switch (variant) {

            case 0 -> placeSimplePalm(world, loc);
            case 1 -> placeBentPalm(world, loc);
            case 2 -> placeTallPalm(world, loc);
        }
    }
}
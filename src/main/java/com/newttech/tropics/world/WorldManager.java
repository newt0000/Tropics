package com.newttech.tropics.world;

import com.newttech.tropics.Tropics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

public class WorldManager {

    private final Tropics plugin;

    public static final String WORLD_NAME = "tropics_world";

    public WorldManager(Tropics plugin) {
        this.plugin = plugin;
    }

    public void createOrLoadWorld() {
        if (Bukkit.getWorld(WORLD_NAME) != null) return;

        plugin.getLogger().info("🌴 Creating Tropics world...");

        WorldCreator creator = new WorldCreator(WORLD_NAME);
        creator.generator(new TropicsChunkGenerator());

        World world = creator.createWorld();

        if (world != null) {
            world.setSpawnLocation(0, 100, 0);
            plugin.getLogger().info("🌊 Tropics world loaded successfully");
        }
    }
}
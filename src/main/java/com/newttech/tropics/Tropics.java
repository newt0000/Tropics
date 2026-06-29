package com.newttech.tropics;

import com.newttech.tropics.world.WorldDecorator;
import com.newttech.tropics.world.WorldManager;
import com.newttech.tropics.world.TropicsChunkGenerator;
import com.newttech.tropics.world.structure.StructureManager;
import com.newttech.tropics.world.vegetation.PalmTreeManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Tropics extends JavaPlugin {

    private static Tropics instance;

    private WorldManager worldManager;
    private PalmTreeManager palmTreeManager;
    private StructureManager structureManager;
    private WorldDecorator worldDecorator;

    @Override
    public void onEnable() {
        instance = this;

        palmTreeManager = new PalmTreeManager(this);
        worldDecorator = new WorldDecorator(palmTreeManager,structureManager);

        TropicsChunkGenerator.setDecorator(worldDecorator);

        worldManager = new WorldManager(this);
        worldManager.createOrLoadWorld();

        getLogger().info("🌴 Tropics fully enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("🌊 Tropics disabled");
    }

    public static Tropics getInstance() {
        return instance;
    }
}
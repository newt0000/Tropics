package com.newttech.tropics;

import org.bukkit.plugin.java.JavaPlugin;

public class Tropics extends JavaPlugin {

    private static Tropics instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("🌴 Tropics plugin enabled");

        // Phase 1 systems (we'll build next):
        // - World generator hook
        // - Palm tree system
        // - Structure manager
        // - Biome simulator
    }

    @Override
    public void onDisable() {
        getLogger().info("🌊 Tropics plugin disabled");
    }

    public static Tropics getInstance() {
        return instance;
    }
}
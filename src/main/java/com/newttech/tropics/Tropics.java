package com.newttech.tropics;

import com.newttech.tropics.world.WorldDecorator;
import com.newttech.tropics.world.WorldManager;
import com.newttech.tropics.world.TropicsChunkGenerator;
import com.newttech.tropics.world.structure.StructureManager;
import com.newttech.tropics.world.vegetation.PalmTreeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        Objects.requireNonNull(getCommand("tbuild")).setExecutor(new Tropics());

        getLogger().info("🌴 Tropics fully enabled");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(ChatColor.RED + "Players only.");
            return true;
        }
        sender.sendMessage(ChatColor.AQUA + "Tropics version: " +BuildInfo.VERSION);
        return true;
    };
    @Override
    public void onDisable() {
        getLogger().info("🌊 Tropics disabled");
    }

    public static Tropics getInstance() {
        return instance;
    }
}
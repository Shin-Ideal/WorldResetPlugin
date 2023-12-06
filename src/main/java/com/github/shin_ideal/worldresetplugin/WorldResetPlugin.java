package com.github.shin_ideal.worldresetplugin;

import com.github.shin_ideal.worldresetplugin.CommandExecutors.ResetWorldCommandExecutor;
import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class WorldResetPlugin extends JavaPlugin {

    private static WorldResetPlugin Instance;
    private MultiverseCore mv;
    private WorldManageUtil worldManage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enable");

        Instance = this;
        mv = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        worldManage = new WorldManageUtil();

        //Backup folder
        File backupFolder = new File(this.getDataFolder(), "backup");
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }

        //Commands
        getCommand("resetworld").setExecutor(new ResetWorldCommandExecutor());

        //Config
        saveDefaultConfig();
        Configuration config = getConfig();
        if (config.getBoolean("ResetOnBoot.enable")) {
            for (String world : config.getStringList("ResetOnBoot.world")) {
                worldManage.resetWorld(world);
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disable");
    }

    public static WorldResetPlugin getInstance() {
        return Instance;
    }

    public MultiverseCore getMultiverseCore() {
        return mv;
    }

    public WorldManageUtil getWorldManage() {
        return worldManage;
    }

    public boolean resetWorld(World world) {
        return resetWorld(world.getName());
    }

    public boolean resetWorld(String world) {
        return worldManage.resetWorld(world);
    }
}

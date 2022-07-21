package ASPlugin;

import org.bukkit.plugin.java.JavaPlugin;

import ASPlugin.commands.CopyItem;
import ASPlugin.commands.Flight;
import ASPlugin.listeners.PlayerJoin;
import ASPlugin.listeners.PlayerQuit;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("================================");
        getLogger().info(" AS Plugin - USUAL WORLD PLUGIN ");
        getLogger().info("  Version 1.0, Developer XiaoQ ");
        getLogger().info("================================");
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getCommand("flight").setExecutor(new Flight());
        getCommand("cp").setExecutor(new CopyItem());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

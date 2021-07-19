package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.Complaints;
import indrocraft.spigot.ecnomyranks.commands.SetRank;
import indrocraft.spigot.ecnomyranks.commands.Warn;
import indrocraft.spigot.ecnomyranks.events.Join;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.options().copyDefaults(true);
        saveConfig();

        //Commands
        getServer().getPluginCommand("Complaints").setExecutor(new Complaints(this));
        getServer().getPluginCommand("Warn").setExecutor(new Warn());
        getServer().getPluginCommand("SetRank").setExecutor(new SetRank());

        //Events
        getServer().getPluginManager().registerEvents(new Join(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.Complaints;
import indrocraft.spigot.ecnomyranks.commands.SetRank;
import indrocraft.spigot.ecnomyranks.commands.Warn;
import indrocraft.spigot.ecnomyranks.events.Join;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.options().copyDefaults(true);
        saveConfig();

        Map<Player, Rank> ranks = new HashMap<>();

        //Commands
        getServer().getPluginCommand("Complaints").setExecutor(new Complaints(this));
        getServer().getPluginCommand("Warn").setExecutor(new Warn());
        getServer().getPluginCommand("SetRank").setExecutor(new SetRank(this));

        //Events
        getServer().getPluginManager().registerEvents(new Join(this),this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

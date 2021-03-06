package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.*;

import indrocraft.spigot.ecnomyranks.commands.Complaints;
import indrocraft.spigot.ecnomyranks.commands.Economy;
import indrocraft.spigot.ecnomyranks.commands.SetRank;
import indrocraft.spigot.ecnomyranks.commands.Warn;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.events.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public final class Main extends JavaPlugin implements Listener {

    FileConfiguration config = getConfig();
    public MySQL SQL;
    public SQLgetter data;

    public  File configf;

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.options().copyDefaults(true);
        saveConfig();
        // makes second config file:
        configf = new File(getDataFolder(), "rank.yml");

        if(!configf.exists()){
            configf.getParentFile().mkdirs();
            saveResource("rank.yml", false);
        }
        config = new YamlConfiguration();

        try{
            config.load(configf);
        }catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }

        this.SQL = new MySQL();
        this.data = new SQLgetter(this);

        //Commands
        getServer().getPluginCommand("Complaints").setExecutor(new Complaints(this));
        getServer().getPluginCommand("Warn").setExecutor(new Warn(this));
        getServer().getPluginCommand("SetRank").setExecutor(new SetRank(this));
        getServer().getPluginCommand("Dev").setExecutor(new Dev(this));
        getServer().getPluginCommand("Convert").setExecutor(new Converter());
        getServer().getPluginCommand("serverinfo").setExecutor(new ServerInfo());
        getServer().getPluginCommand("Economy").setExecutor(new Economy(this));
        getServer().getPluginCommand("home").setExecutor(new Home(this));
        getServer().getPluginCommand("verify").setExecutor(new Verify(this));
        getServer().getPluginCommand("openinv").setExecutor(new OpenInv(this.data));

        //Commands Tab Autocomplete
        getCommand("SetRank").setTabCompleter(new SetRank(this));
        getCommand("Economy").setTabCompleter(new Economy(this));

        //Events
        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(this), this);
        getServer().getPluginManager().registerEvents(new RankEvents(this), this);

        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            Bukkit.getLogger().info(ChatColor.RED + "Database not connected!");
        }

        if (SQL.isConnected()) {
            Bukkit.getLogger().info(ChatColor.BLUE + "Database is connected!");
            data.createTable("playerinfo");
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SQL.disconnect();
    }
}
package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.*;
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.events.PlayerJoinLeave;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public final class Main extends JavaPlugin{

        FileConfiguration config = getConfig();
        public MySQL SQL;
        public SQLgetter data;

        public Map<Player, Rank> ranks;

        @Override
        public void onEnable() {
            // Plugin startup logic
            config.options().copyDefaults(true);
            saveConfig();
            this.SQL = new MySQL();
            this.data = new SQLgetter(this);

            Map<Player, Rank> ranks = new HashMap<>();

            //Commands
            getServer().getPluginCommand("Complaints").setExecutor(new Complaints(this));
            getServer().getPluginCommand("Warn").setExecutor(new Warn());
            getServer().getPluginCommand("SetRank").setExecutor(new SetRank(this));
            getServer().getPluginCommand("Dev").setExecutor(new Dev());
            getServer().getPluginCommand("Convert").setExecutor(new Converter());

            //Events
            getServer().getPluginManager().registerEvents(new PlayerJoinLeave(ranks,data), this);



            try {
                SQL.connect();
            } catch (ClassNotFoundException | SQLException e) {
                //e.printStackTrace();
                Bukkit.getLogger().info(ChatColor.RED +"Database not connected!");
            }

            if (SQL.isConnected()) {
                Bukkit.getLogger().info(ChatColor.BLUE + "Database is connected!");
                data.createTable();



            }

        }


        @Override
        public void onDisable() {
            // Plugin shutdown logic
            SQL.disconnect();
        }


    }

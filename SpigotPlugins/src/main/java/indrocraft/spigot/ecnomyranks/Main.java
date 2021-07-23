package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.Complaints;
import indrocraft.spigot.ecnomyranks.commands.Mail;
import indrocraft.spigot.ecnomyranks.commands.SetRank;
import indrocraft.spigot.ecnomyranks.commands.Warn;
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.events.Join;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import org.bukkit.Bukkit;
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

            Map<Player, Rank> ranks = new HashMap<>();

            //Commands
            getServer().getPluginCommand("Complaints").setExecutor(new Complaints(this));
            getServer().getPluginCommand("Warn").setExecutor(new Warn(this));
            getServer().getPluginCommand("SetRank").setExecutor(new SetRank(this));
            getServer().getPluginCommand("Mail").setExecutor(new Mail(this));


            //Events
            getServer().getPluginManager().registerEvents(new Join(this), this);

            this.SQL = new MySQL();
            this.data = new SQLgetter(this);

            try {
                SQL.connect();
            } catch (ClassNotFoundException | SQLException e) {
                //e.printStackTrace();
                Bukkit.getLogger().info("database not connected!");
            }

            if (SQL.isConnected()) {
                Bukkit.getLogger().info("database is connected!");
                data.createTable();



            }

        }


        @Override
        public void onDisable() {
            // Plugin shutdown logic
            SQL.disconnect();
        }


    }

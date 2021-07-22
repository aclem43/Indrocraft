package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.Complaints;
import indrocraft.spigot.ecnomyranks.commands.SetRank;
import indrocraft.spigot.ecnomyranks.commands.Warn;
<<<<<<< HEAD
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
=======
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin implements Listener {
>>>>>>> origin/feature/database

    FileConfiguration config = getConfig();
    public MySQL SQL;
    public SQLgetter data;

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

<<<<<<< HEAD
        //Events
        getServer().getPluginManager().registerEvents(new Join(this),this);
=======
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
            this.getServer().getPluginManager().registerEvents(this, this);
        }
>>>>>>> origin/feature/database
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SQL.disconnect();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        data.createPlayer(player);
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player player = (Player) event.getEntity().getKiller();
            data.setString(player.getUniqueId(), "bad_boy", "COMPLAINT");
            String msg = data.getString(player.getUniqueId(), "COMPLAINT");
            player.sendMessage(msg);
            data.addcolumn("feed", "VARCHAR(100)");
        }
    }

}

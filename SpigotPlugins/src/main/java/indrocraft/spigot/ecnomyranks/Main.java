package indrocraft.spigot.ecnomyranks;

import indrocraft.spigot.ecnomyranks.commands.*;

import indrocraft.spigot.ecnomyranks.commands.Complaints;
import indrocraft.spigot.ecnomyranks.commands.Economy;
import indrocraft.spigot.ecnomyranks.commands.SetRank;
import indrocraft.spigot.ecnomyranks.commands.Warn;
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.events.Inventories;
import indrocraft.spigot.ecnomyranks.events.PlayerJoinLeave;
import indrocraft.spigot.ecnomyranks.events.RankEvents;
import indrocraft.spigot.ecnomyranks.inventories.InitAH;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public final class Main extends JavaPlugin implements Listener {

    FileConfiguration config = getConfig();
    public MySQL SQL;
    public SQLgetter data;
    public PlayerJoinLeave onJoin;

    public Map<Player, Rank> ranks;


    public Main() {
        Map<Player, Rank> ranks = new HashMap<>();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        config.options().copyDefaults(true);
        saveConfig();

        this.SQL = new MySQL();
        this.data = new SQLgetter(this);

        //Commands
        getServer().getPluginCommand("Complaints").setExecutor(new Complaints(this));
        getServer().getPluginCommand("Warn").setExecutor(new Warn(this));
        getServer().getPluginCommand("SetRank").setExecutor(new SetRank(this));
        getServer().getPluginCommand("Dev").setExecutor(new Dev());
        getServer().getPluginCommand("Convert").setExecutor(new Converter());
        getServer().getPluginCommand("Economy").setExecutor(new Economy(this));
        getServer().getPluginCommand("auctionhouse").setExecutor(new AuctionHouse());
        getServer().getPluginCommand("home").setExecutor(new Home(this));
        getServer().getPluginCommand("openinv").setExecutor(new OpenInv(this.data));

        //Commands Tab Autocomplete
        getCommand("SetRank").setTabCompleter(new SetRank(this));
        getCommand("Economy").setTabCompleter(new Economy(this));
        //Events
        getServer().getPluginManager().registerEvents(new PlayerJoinLeave(ranks, data), this);
        getServer().getPluginManager().registerEvents(new RankEvents(data), this);
        getServer().getPluginManager().registerEvents(new Inventories(), this);

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

        data.createTable("auctionhouse");
        data.addcolumn("itemtype","VARCHAR(100)","auctionhouse");
        data.addcolumn("cost","INT","auctionhouse");
        data.addcolumn("amount","INT","auctionhouse");

        //databse+discord
        data.createTable("discord");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SQL.disconnect();
    }
/*
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("tesginsoef");
        data.createPlayer(player);
    }
    */
}
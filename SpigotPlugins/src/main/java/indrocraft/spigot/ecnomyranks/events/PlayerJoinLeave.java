package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeave implements Listener {

    private final Main main;
    public PlayerJoinLeave(Main main) {this.main = main;}

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        FileConfiguration config = Databasemanager.getFileConfig("config.yml");
        String serverName = config.getString("serverName");
        event.setJoinMessage(ChatColor.GREEN  + "Welcome " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " To " + ChatColor.BOLD + serverName + "!");
//                "\n" + ChatColor.GREEN + "Join the conversation on our discord server:\n" + ChatColor.BLUE + ChatColor.UNDERLINE + "https://discord.gg/XdKgd7mbHJ");
        //fills in the database with required columns
        main.data.createPlayer(player, "playerinfo");// ADD gremlin Rank to player
        main.data.setString(player.getUniqueId(), player.getName(), "NAME", "playerinfo");

        //adds columns for the player info table
        main.data.addcolumn("Rank", "VARCHAR(100)", "playerinfo");
        main.data.addcolumn("Bank", "INT(100)", "playerinfo");
        main.data.addcolumn("Wallet", "INT(100)", "playerinfo");
        main.data.addcolumn("ComplaintMessage", "VARCHAR(255)", "playerinfo");
        main.data.addcolumn("Count", "INT(100)", "playerinfo");
        main.data.addcolumn("god", "INT(100)", "playerinfo");
        //adds all the tp information and tables

        String databseName = config.getString("databseForTP");

        main.data.createTable(databseName);
        main.data.addcolumn("world", "VARCHAR(200)", databseName);
        main.data.addcolumn("x", "DOUBLE", databseName);
        main.data.addcolumn("y", "DOUBLE", databseName);
        main.data.addcolumn("z", "DOUBLE", databseName);
        main.data.addcolumn("pitch", "Float", databseName);
        main.data.addcolumn("yaw", "Float", databseName);

        main.data.addcolumn(databseName, "VARCHAR(1000)", "playerinfo");
        main.data.addcolumn(databseName + "num", "INT(255)", "playerinfo");

        //data.createPlayer(player, databseName);

        //setting columns to default values if not already
        String rank = main.data.getString(player.getUniqueId(), "Rank", "playerinfo");
        String Wallet = main.data.getString(player.getUniqueId(), "Wallet", "playerinfo");
        String Bank = main.data.getString(player.getUniqueId(), "Bank", "playerinfo");
        String count = main.data.getString(player.getUniqueId(), "count", "playerinfo");
        String complaint = main.data.getString(player.getUniqueId(), "ComplaintMessage", "playerinfo");
        String homeList = main.data.getString(player.getUniqueId(), databseName, "playerinfo");
        String homesNum = main.data.getString(player.getUniqueId(), databseName + "num", "playerinfo");
        String god = main.data.getString(player.getUniqueId(), "god", "playerinfo");
        if (rank == null) {
            main.data.setString(player.getUniqueId(), "none", "Rank", "playerinfo");
        }
        if (Wallet == null) {
            main.data.setInt(player.getUniqueId(), 0, "Wallet", "playerinfo");
        }
        if (Bank == null) {
            main.data.setInt(player.getUniqueId(), 0, "Bank", "playerinfo");
        }
        if (count == null) {
            main.data.setInt(player.getUniqueId(), 0, "count", "playerinfo");
        }
        if (complaint == null) {
            main.data.setString(player.getUniqueId(), "",    "ComplaintMessage", "playerinfo");
        }
        if (homeList == null) {
            main.data.setString(player.getUniqueId(), " ", databseName, "playerinfo");
        }
        if (homesNum == null) {
            main.data.setInt(player.getUniqueId(), 0, databseName + "num", "playerinfo");
        }
        if (god == null) {
            main.data.setInt(player.getUniqueId(), 0, "god", "playerinfo");
        }
        RankUtils.LoadRank(player, main.data);

        //databse+discord
        main.data.createTable("unverified");
        main.data.createPlayer(player, "unverified");

        main.data.createTable("discord");

        //auction house table

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.GREEN + "See you soon " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
        // use rank to add to database
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedLeaveEvent e) {
        Player p = (Player) e.getPlayer();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(ChatColor.YELLOW + p.getName() + ChatColor.GREEN + " went to bed sweet dreams!");
        }
    }
}

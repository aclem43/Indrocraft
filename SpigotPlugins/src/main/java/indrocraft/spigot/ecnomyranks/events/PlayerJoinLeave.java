package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class PlayerJoinLeave implements Listener {


    private SQLgetter data;


    public PlayerJoinLeave(Map<Player, Rank> ranks, SQLgetter data ) {
        this.data = data;
    }



    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GREEN  + "Welcome " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " To " + ChatColor.BOLD + "IndroCraft!");
//                "\n" + ChatColor.GREEN + "Join the conversation on our discord server:\n" + ChatColor.BLUE + ChatColor.UNDERLINE + "https://discord.gg/XdKgd7mbHJ");
        //fills in the database with required columns
        data.createPlayer(player, "playerinfo");// ADD gremlin Rank to player
        //data.createTable("tpinfo " + player.getUniqueId().toString());

        //adds columns for the player info table
        data.addcolumn("Rank", "VARCHAR(100)", "playerinfo");
        data.addcolumn("Bank", "INT(100)", "playerinfo");
        data.addcolumn("Wallet", "INT(100)", "playerinfo");
        data.addcolumn("ComplaintMessage", "VARCHAR(255)", "playerinfo");
        data.addcolumn("Count", "INT(100)", "playerinfo");
        //adds all the tp information and tables

        FileConfiguration config = Databasemanager.getFileConfig("config.yml");
        String databseName = config.getString("databseForTP");

        data.createTable(databseName);
        data.addcolumn("world", "VARCHAR(200)", databseName);
        data.addcolumn("x", "DOUBLE", databseName);
        data.addcolumn("y", "DOUBLE", databseName);
        data.addcolumn("z", "DOUBLE", databseName);
        data.addcolumn("pitch", "Float", databseName);
        data.addcolumn("yaw", "Float", databseName);

        data.addcolumn("homeList", "VARCHAR(1000)", "playerinfo");
        data.addcolumn("homeNum", "INT(255)", "playerinfo");

        //data.createPlayer(player, databseName);

        //setting columns to default values if not already
        String rank = data.getString(player.getUniqueId(), "Rank", "playerinfo");
        String Wallet = data.getString(player.getUniqueId(), "Wallet", "playerinfo");
        String Bank = data.getString(player.getUniqueId(), "Bank", "playerinfo");
        String count = data.getString(player.getUniqueId(), "count", "playerinfo");
        String complaint = data.getString(player.getUniqueId(), "ComplaintMessage", "playerinfo");
        String homeList = data.getString(player.getUniqueId(), "homeList", "playerinfo");
        String homesNum = data.getString(player.getUniqueId(), "homeNum", "playerinfo");
        if (rank == null) {
            data.setString(player.getUniqueId(), "NONE", "Rank", "playerinfo");
        }
        if (Wallet == null) {
            data.setInt(player.getUniqueId(), 0, "Wallet", "playerinfo");
        }
        if (Bank == null) {
            data.setInt(player.getUniqueId(), 0, "Bank", "playerinfo");
        }
        if (count == null) {
            data.setInt(player.getUniqueId(), 0, "count", "playerinfo");
        }
        if (complaint == null) {
            data.setString(player.getUniqueId(), "", "ComplaintMessage", "playerinfo");
        }
        if (homeList == null) {
            data.setString(player.getUniqueId(), " ", "homeList", "playerinfo");
        }
        if (homesNum == null) {
            data.setInt(player.getUniqueId(), 0, "homeNum", "playerinfo");
        }
        RankManager.LoadRank(player,data);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.GREEN + "See you soon " + player.getName() + "!");
        // use rank to add to database
    }
}

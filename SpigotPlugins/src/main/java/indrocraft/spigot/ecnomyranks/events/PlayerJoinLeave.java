package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class PlayerJoinLeave implements Listener {

    private Map<Player, Rank> ranks;
    private SQLgetter data;


    public PlayerJoinLeave(Map<Player, Rank> ranks, SQLgetter data ) {
        this.ranks = ranks;
        this.data = data;
    }



    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GREEN  + "Welcome " + player.getName() + " To " + ChatColor.BOLD + "IndroCraft!\n" + ChatColor.GREEN + "Join the conversation on our discord server:\n" + ChatColor.BLUE + ChatColor.UNDERLINE + "https://discord.gg/XdKgd7mbHJ");
        data.createPlayer(player); // ADD gremlin Rank to player
        data.addcolumn("Rank", "VARCHAR(100)");

        //setting columns to default values if not already
        String rank = data.getString(player.getUniqueId(), "Rank");
        String Wallet = data.getString(player.getUniqueId(), "Wallet");
        String Bank = data.getString(player.getUniqueId(), "Bank");
        String count = data.getString(player.getUniqueId(), "count");
        String complaint = data.getString(player.getUniqueId(), "ComplaintMessage");
        if (rank == null) {
            data.setString(player.getUniqueId(), "NONE", "Rank");
        }
        if (Wallet == null) {
            data.setInt(player.getUniqueId(), 0, "Wallet");
        }
        if (Bank == null) {
            data.setInt(player.getUniqueId(), 0, "Bank");
        }
        if (count == null) {
            data.setInt(player.getUniqueId(), 0, "count");
        }
        if (complaint == null) {
            data.setString(player.getUniqueId(), "", "ComplaintMessage");
        }
        RankManager.LoadRank(player,data);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Rank rank = RankManager.getRank(player,data);
        event.setQuitMessage(ChatColor.GREEN + "See you soon " + player.getName() + "!");
        // use rank to add to database
        data.setString(player.getUniqueId(), rank.toString(), "Rank");
    }
}

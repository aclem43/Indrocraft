package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.Main;
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


    private Map<Player, Rank> ranks ;
    private SQLgetter data;

    public PlayerJoinLeave(Map<Player, Rank> ranks, SQLgetter data ) {
        this.ranks = ranks;
        this.data = data;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RankManager.LoadRank(player,this.ranks);
        player.sendMessage(ChatColor.BLUE  + "Welcome To The Server! :)");
        this.data.createPlayer(player); // ADD gremlin Rank to player


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Rank rank = RankManager.getRank(player,this.ranks);
        // use rank to add to database
    }
}

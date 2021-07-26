package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {


    private final Main main;

    public Join(Main main) {
        this.main = main;
    }

    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        //RankManager.LoadRank(player,main.ranks);
        player.sendMessage(ChatColor.BLUE  + "Welcome To The Server! :)");
        main.data.createPlayer(player);
    }
}

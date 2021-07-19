package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        RankManager.initLoadRank(player);


        player.sendMessage(ChatColor.BLUE  + "Welcome To The Server! :)");



    }
}

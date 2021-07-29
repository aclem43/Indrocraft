package indrocraft.spigot.ecnomyranks.events;

import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;


public class RankEvents implements Listener {
    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent event){
        Player player = event.getPlayer();
        NamespacedKey key = event.getAdvancement().getKey();

        if(key.getNamespace().equals(NamespacedKey.MINECRAFT) && key.getKey().equals("end/kill_dragon")) {
            // Your code here
        }
    }


}


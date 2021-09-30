package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.RankUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class RankEvents implements Listener {
    private Main main;
    public RankEvents(Main main) {this.main = main;}

    @EventHandler
    public void advancementDoneEvent(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        NamespacedKey key = event.getAdvancement().getKey();
        int level = RankUtils.getLevel(player, main.data);
        int getGod = main.data.getInt(player.getUniqueId(), "god", "playerinfo");
        if (key.getNamespace().equals(NamespacedKey.MINECRAFT)) {
            if (key.getKey().equals("story/mine_diamond")) {
                if (level == 0) {

                }
            }
        }
    }
}

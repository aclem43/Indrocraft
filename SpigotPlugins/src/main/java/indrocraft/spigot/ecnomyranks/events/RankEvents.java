package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.RankUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class RankEvents implements Listener {
    private Main main;
    public RankEvents(Main main) {this.main = main;}

    @EventHandler
    public void advancementDoneEvent(PlayerAdvancementDoneEvent event) {
        FileConfiguration config = Databasemanager.getFileConfig("config.yml");
        if (config.getBoolean("autoRankUp")) {
            Player player = event.getPlayer();
            NamespacedKey key = event.getAdvancement().getKey();
            int level = RankUtils.getLevel(player, main.data);
            String advancement = config.getString("0.achievement");
            if (advancement.equalsIgnoreCase(key.getKey())) {
                RankUtils.setRank(player, main.data, config.getString("0.nextRank"));
            }
            int getGod = main.data.getInt(player.getUniqueId(), "god", "playerinfo");
        }
    }
}

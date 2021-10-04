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

import java.util.List;

public class RankEvents implements Listener {
    private Main main;
    public RankEvents(Main main) {this.main = main;}

    @EventHandler
    public void advancementDoneEvent(PlayerAdvancementDoneEvent event) {
        FileConfiguration config = Databasemanager.getFileConfig("rank.yml");
        Player player = event.getPlayer();
        if (config.getBoolean("autoRankUp")) {
            NamespacedKey key = event.getAdvancement().getKey();
            Integer level = RankUtils.getLevel(player, main.data);
            List<String> rankUpOrder = config.getStringList("rankUpOrder");
            if (key.getNamespace().equals(NamespacedKey.MINECRAFT)) {
                if (rankUpOrder.contains(key.getKey())) {
                    Integer advancement = rankUpOrder.indexOf(key.getKey());
                    if (key.getKey().equals(rankUpOrder.get(advancement))) {
                        if (level < advancement + 1) {
                            level++;

                            advancement++;
                            player.sendMessage(advancement.toString());
                            RankUtils.levelUp(player, main.data, advancement.toString());

                            player.sendMessage("leveled up\n" + advancement);
                        }
                    }
                }
            }
        }
    }
}

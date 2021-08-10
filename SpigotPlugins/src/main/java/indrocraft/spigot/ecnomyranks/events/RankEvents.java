package indrocraft.spigot.ecnomyranks.events;

import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;


public class RankEvents implements Listener {

    private SQLgetter data;


    public RankEvents(SQLgetter data ) {
        this.data = data;
    }
    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent event){
        Player player = event.getPlayer();
        Rank rank = RankManager.getRank(player,data);
        NamespacedKey key = event.getAdvancement().getKey();

        if(key.getNamespace().equals(NamespacedKey.MINECRAFT) && key.getKey().equals("story/mine_diamond")) {
            if (rank.getLevel()>0){
                return;
            }
            if ("".equals(rank.getStatus())) {
                RankManager.setRank(player, Rank.HERO, data);
            } else if ("$".equalsIgnoreCase(rank.getStatus())) {
                RankManager.setRank(player, Rank.HEROX, data);
            }

        }else if(key.getNamespace().equals(NamespacedKey.MINECRAFT) && key.getKey().equals("nether/obtain_ancient_debris")) {
            if (rank.getLevel()>1){
                return;
            }
            if ("".equals(rank.getStatus())) {
                RankManager.setRank(player, Rank.TITAN, data);
            } else if ("$".equalsIgnoreCase(rank.getStatus())) {
                RankManager.setRank(player, Rank.TITANX, data);
            }

        }else if(key.getNamespace().equals(NamespacedKey.MINECRAFT) && key.getKey().equals("end/kill_dragon")) {
            if (rank.getLevel()>2){
                return;
            }
            if ("".equals(rank.getStatus())) {
                RankManager.setRank(player, Rank.DGOD, data);
            } else if ("$".equalsIgnoreCase(rank.getStatus())) {
                RankManager.setRank(player, Rank.DGODX, data);
            }

        }else if(key.getNamespace().equals(NamespacedKey.MINECRAFT) && key.getKey().equals("nether/summon_wither")) {
            if (rank.getLevel()>3){
                return;
            }
            if ("".equals(rank.getStatus())) {
                RankManager.setRank(player, Rank.GOD, data);
            } else if ("$".equalsIgnoreCase(rank.getStatus())) {
                RankManager.setRank(player, Rank.GODX, data);
            }

        }


    }


}


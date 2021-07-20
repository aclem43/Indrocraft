package indrocraft.spigot.ecnomyranks.ranks;

import indrocraft.spigot.ecnomyranks.ranks.Rank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager.getFileConfig;

public class RankManager {

    public RankManager(){
        super();
    }

    public static void setRank(Player player, Rank rank,Map<Player, Rank> ranks) {
        map.put(player, rank);

   }

    public Rank getRank(Player player,Map<Player, Rank> ranks) {
        return ranks.get(player);
    }

    public static void LoadRank(Player player) {

        ranks.put(player, rank);


    }


}

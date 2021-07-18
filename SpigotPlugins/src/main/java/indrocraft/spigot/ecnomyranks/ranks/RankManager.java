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

    public static void setRank(Player player, Rank rank) {
        FileConfiguration configfile = getFileConfig("ranks.yml");
        configfile.set(player.getUniqueId() + ".rank",rank);
    }

    public Rank getRank(Player player) {
        FileConfiguration configfile = getFileConfig("ranks.yml");
        String rankString = configfile.getString(player.getUniqueId() + ".rank");
        Rank rank = (rankString == null) ? Rank.NONE : Rank.valueOf(rankString);

        return rank;
    }

    public static void initLoadRank(Player player) {
        FileConfiguration configfile = getFileConfig("ranks.yml");
        String rankString = configfile.getString(player.getUniqueId() + ".rank");
        Rank rank = (rankString == null) ? Rank.NONE : Rank.valueOf(rankString);
        player.setDisplayName(rank.getPrefix() + player.getName());
        player.setPlayerListName(rank.getPrefix() + player.getName());


    }


}

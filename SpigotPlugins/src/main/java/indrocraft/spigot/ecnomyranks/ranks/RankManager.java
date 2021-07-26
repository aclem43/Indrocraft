package indrocraft.spigot.ecnomyranks.ranks;

import indrocraft.spigot.ecnomyranks.ranks.Rank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager.getFileConfig;
import static indrocraft.spigot.ecnomyranks.ranks.Rank.NONE;
import static org.bukkit.Bukkit.getPlayer;

public class RankManager {

    public RankManager(){
        super();
    }

    public static void setRank(Player player, Rank rank,Map<Player, Rank> ranks) {
        player.setDisplayName(rank.getPrefix() + player.getName());
        player.setPlayerListName(rank.getPrefix() + player.getName());
        ranks.put(player, rank);

   }

   public static void saveRank(Player player,Map<Player, Rank> ranks){

        ranks.get(player);
    }


    public static Rank getRank(Player player, Map<Player, Rank> ranks) {
        return ranks.get(player);
    }

    public static void LoadRank(Player player,Map<Player, Rank> ranks) {

        player.setDisplayName(NONE.getPrefix() + player.getName());
        player.setPlayerListName(NONE.getPrefix() + player.getName());
        ranks.put(player, NONE);


    }
    public static void TestLoadRank(Player player,String rankString) {
        player.sendMessage("Rank Updating");
        if("".equalsIgnoreCase(rankString)){
            player.setDisplayName("TEST" + player.getName());
            player.setPlayerListName("TEST" + player.getName());
        }else if("GREMLIN".equalsIgnoreCase(rankString)){
            Rank rank = NONE;
            player.setDisplayName(rank.getPrefix() + player.getName());
            player.setPlayerListName(rank.getPrefix() + player.getName());
        }else if("HERO".equalsIgnoreCase(rankString)){
            Rank rank = Rank.HERO;
            player.setDisplayName(rank.getPrefix() + player.getName());
            player.setPlayerListName(rank.getPrefix() + player.getName());
        }else if(rankString=="DGOD"){
            Rank rank = Rank.DGOD;
            player.setDisplayName(rank.getPrefix() + player.getName());
            player.setPlayerListName(rank.getPrefix() + player.getName());
        }else if(rankString=="GOD"){
            Rank rank = Rank.GOD;
            player.setDisplayName(rank.getPrefix() + player.getName());
            player.setPlayerListName(rank.getPrefix() + player.getName());
        }else if(rankString=="TITAN"){
            Rank rank = Rank.TITAN;
            player.setDisplayName(rank.getPrefix() + player.getName());
            player.setPlayerListName(rank.getPrefix() + player.getName());
        }else if(rankString=="DEV"){
            Rank rank = Rank.DEV;
            player.setDisplayName(rank.getPrefix() + player.getName());
            player.setPlayerListName(rank.getPrefix() + player.getName());
        }
        player.sendMessage("Rank Updating");
        //ranks.put(player, rank);


    }


}

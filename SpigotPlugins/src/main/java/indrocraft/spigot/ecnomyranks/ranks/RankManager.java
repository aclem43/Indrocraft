package indrocraft.spigot.ecnomyranks.ranks;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager.getFileConfig;
import static indrocraft.spigot.ecnomyranks.ranks.Rank.*;
import static org.bukkit.Bukkit.getPlayer;
/*
Huon Todo -
loadrank: Gets Rank from data base and sets rank string to correct rank
setrank: Setsrank in database to new rank
getrank:returns Players rank




 */

public class RankManager {




    public static Rank codeGetRank(String code){
        if (code.equals("GRE")){
            return NONE;
        }else if (code.equals("HER")){
            return HERO;
        }else if (code.equals("DGD")){
            return DGOD;
        }else if (code.equals("GOD")){
            return GOD;
        }else if (code.equals("TIT")){
            return TITAN;
        }else if (code.equals("DEV")){
            return DEV;
        }else if (code.equals("DON")){
            return DONOR;
        }else {
            return NONE;
        }

    }
    public static void setRank(Player player, Rank rank, SQLgetter data) {
        player.setDisplayName(rank.getPrefix() + player.getName());
        player.setPlayerListName(rank.getPrefix() + player.getName());
        data.setString(player.getUniqueId(), rank.getCode(), "Rank" );
        //main.data.getString(player.getUniqueId(),"Rank");

   }



    public static Rank getRank(Player player,SQLgetter data) {
        String code = data.getString(player.getUniqueId(),"Rank");
        Rank rank = codeGetRank(code);
        return  rank;
    }


    public static void LoadRank(Player player,SQLgetter data) {


        Rank rank = getRank(player,data);
        player.setDisplayName(rank + player.getName());
        player.setPlayerListName(NONE.getPrefix() + player.getName());




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
            Rank rank = HERO;
            player.setDisplayName(rank.getPrefix() + player.getName());
            player.setPlayerListName(rank.getPrefix() + player.getName());
        }else if(rankString=="DGOD"){
            Rank rank = DGOD;
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

}
}

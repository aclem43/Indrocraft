package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getPlayer;

public class SetRank implements CommandExecutor {

    private final Main main;

    public SetRank(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // arg 0 = Target i.e Aclem43, arg 1 = rank name i.e Hero  -
        if (sender.isOp()){
            if(args[1]=="GREMLIN"){
                Rank rank = Rank.NONE;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if(args[1]=="HERO"){
                Rank rank = Rank.HERO;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if(args[1]=="DGOD"){
                Rank rank = Rank.DGOD;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if(args[1]=="GOD"){
                Rank rank = Rank.GOD;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if(args[1]=="TITAN"){
                Rank rank = Rank.TITAN;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if(args[1]=="DEV"){
                Rank rank = Rank.DEV;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }

        }

        return false;
    }
}

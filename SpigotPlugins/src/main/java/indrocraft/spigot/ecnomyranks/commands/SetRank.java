package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getPlayer;

public class SetRank implements TabExecutor {

    private final Main main;

    public SetRank(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // arg 0 = Target i.e Aclem43, arg 1 = rank name i.e Hero  -
        if (sender.isOp()){
            if("GREMLIN".equalsIgnoreCase(args[1])){
                Rank rank = Rank.NONE;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if("HERO".equalsIgnoreCase(args[1])){
                Rank rank = Rank.HERO;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if("DGOD".equalsIgnoreCase(args[1])){
                Rank rank = Rank.DGOD;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if("GOD".equalsIgnoreCase(args[1])){
                Rank rank = Rank.GOD;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if("TITAN".equalsIgnoreCase(args[1])){
                Rank rank = Rank.TITAN;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if("DEV".equalsIgnoreCase(args[1])){
                Rank rank = Rank.DEV;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }else if("DONOR".equalsIgnoreCase(args[1])){
                Rank rank = Rank.DONOR;
                RankManager.setRank(getPlayer(args[0]),rank,main.ranks);
            }

        }

        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args) {
        if(args.length == 2){
            List<String> arg1 = new ArrayList<>();
            arg1.add("GREMLIN");
            arg1.add("HERO");
            arg1.add("DGOD");
            arg1.add("GOD");
            arg1.add("TITAN");
            arg1.add("DEV");
            arg1.add("DONOR");
            return arg1;
        }

        return null;
    }
}

package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.ranks.RankUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;

public class SetRank implements TabExecutor {

    private final Main main;

    public SetRank(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // arg 0 = Target i.e Aclem43, arg 1 = rank,main.data name i.e Hero  -
        Player target = Bukkit.getPlayer(args[0]);

        if (sender.isOp()){
            if (label.equalsIgnoreCase("namecolour")) {
                RankUtils.setNameColour(target, main.data, args[1]);
            } else {
                RankUtils.setRank(target, main.data, args[1]);
            }
            RankUtils.LoadRank(target, main.data);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args) {
        if(args.length == 2){
            List<String> arg1 = new ArrayList<>();
            if (alias.equalsIgnoreCase("setrank")) {
                FileConfiguration config = Databasemanager.getFileConfig("rank.yml");
                for (String ranks : config.getConfigurationSection("ranks").getKeys(false))
                    arg1.add(ranks);
            } else {
                arg1.add("dark_red");
                arg1.add("red");
                arg1.add("gold");
                arg1.add("yellow");
                arg1.add("dark_green");
                arg1.add("green");
                arg1.add("aqua");
                arg1.add("dark_aqua");
                arg1.add("dark_blue");
                arg1.add("blue");
                arg1.add("light_purple");
                arg1.add("dark_purple");
                arg1.add("white");
                arg1.add("gray");
                arg1.add("dark_gray");
                arg1.add("black");
            }
            return arg1;
        }

        return null;
    }
}

package indrocraft.spigot.ecnomyranks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Converter implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String convertType = args[0];

            if("stacks".equalsIgnoreCase(convertType) || "s".equalsIgnoreCase(convertType)) {
                float num = Float.parseFloat(args[1]);
                num = num / 64;
                player.sendMessage("Number Of Stacks Is: "+ num);
            }else if ("blocks".equalsIgnoreCase(convertType) || "b".equalsIgnoreCase(convertType)) {
                float num = Float.parseFloat(args[1]);
                num = num * 64;
                player.sendMessage("Number Of Blocks Is: "+ num);
            }else{
                player.sendMessage("Error");
            }

        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0) {
            List<String> arg1 = new ArrayList<>();
            arg1.add("stacks");
            arg1.add("blocks");
        }
        return null;
    }
}
package indrocraft.spigot.ecnomyranks.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Converter implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String convertType = args[0];

            if("s".equals(convertType) ) {
                float num = Float.parseFloat(args[1]);
                num = num / 64;
                player.sendMessage("Number Of Stacks Is: "+ num);
            }else if ("b".equals(convertType)) {
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
}
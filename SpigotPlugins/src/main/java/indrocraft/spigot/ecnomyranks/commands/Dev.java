package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Dev implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender sender,Command command,String label, String[] args) {
        Player player = (Player) sender;

        if (player.isOp()) {
            if ("testRank".equalsIgnoreCase(args[0]))   {
                player.sendMessage("Starting");
                RankManager.TestLoadRank(player, args[1]);
                player.sendMessage("Finished");
            }else{
                player.sendMessage(args);
            }

            if ("world".equalsIgnoreCase(args[0])) {
                player.sendMessage(player.getWorld().toString());
            }
        }
        return true;
    }
}

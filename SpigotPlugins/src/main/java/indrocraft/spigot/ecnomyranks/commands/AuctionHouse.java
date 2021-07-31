package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.inventories.InitAH;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AuctionHouse implements CommandExecutor {


    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            InitAH gui = new InitAH();
            player.openInventory(gui.getInventory());
        }
        return true;
    }
}

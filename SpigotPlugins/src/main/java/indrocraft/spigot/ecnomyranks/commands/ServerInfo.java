package indrocraft.spigot.ecnomyranks.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player instanceof Player) {
            player.sendMessage(ChatColor.GREEN + "Server info!" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "\n------------------------------");
            player.sendMessage(ChatColor.DARK_AQUA + "Welcome to IndroCraft! We are a server made for everyone to enjoy if you have questions you can contact the developers through discord here: *link to the server*\n");
            player.sendMessage("" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "------------------------------\n");
        } else {
            sender.sendMessage("must be player!");
        }
        return true;
    }
}

package indrocraft.spigot.ecnomyranks.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatColor a = ChatColor.BOLD;
        ChatColor b = ChatColor.DARK_RED;
        ChatColor c = ChatColor.RED;
        ChatColor d = ChatColor.GOLD;
        ChatColor e = ChatColor.YELLOW;
        ChatColor j = ChatColor.GREEN;
        ChatColor f = ChatColor.DARK_GREEN;
        ChatColor g = ChatColor.AQUA;
        ChatColor h = ChatColor.DARK_AQUA;
        ChatColor i = ChatColor.BLUE;
        ChatColor z = ChatColor.RESET;

        Player player = (Player) sender;
        if (player instanceof Player) {
            if (args.length == 0) {
                player.sendMessage(e + "" + ChatColor.STRIKETHROUGH + "====================" + d + " Server Info! " + e + ChatColor.STRIKETHROUGH + "====================");
                player.sendMessage(f + "Welcome to " + a + j + "IndroCraft" + z + f + ", " + player.getName() + "!\n" + j + "We are a server made for everyone to enjoy! If you have \nquestions you can contact the developers through discord \nhere: " + f + ChatColor.ITALIC + "https://discord.gg/gH9nQSBXzq\n");
                player.sendMessage(j + "For more information use on of the following commands:");
                player.sendMessage(d + "/info server" + j + " - This will show you stats about the server and how its run!");
                player.sendMessage(d + "/info plugins" + j + " - This will show you all the plugins that are on the Indrocraft server!");
                player.sendMessage(d + "/info rules" + j + " - This will show you a list of the rules on the server!");
                player.sendMessage(e + "" + ChatColor.STRIKETHROUGH + "=====================================================");
                return true;
            }

            player.sendMessage("");
            player.sendMessage("" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "------------------------------\n");
        } else {
            sender.sendMessage("must be player!");
        }
        return true;
    }
}

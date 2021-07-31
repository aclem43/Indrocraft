package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warn implements CommandExecutor {

    private final Main main;

    public Warn(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("must be a player to warn a player");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You need to input a players name!");
            return true;
        }

        Player player = (Player) sender;
        try {
            Player target = Bukkit.getPlayer(args[0]);
        } catch (NullPointerException e) {
            player.sendMessage(ChatColor.RED + "You need to target a real player!");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (player == target) {
            player.sendMessage(ChatColor.RED + "Cannot target yourself!");
            return true;
        }
        try {
            main.data.createPlayer(target);
        } catch (NullPointerException e) {
            player.sendMessage(ChatColor.RED + "You need to input a players name!");
            return true;
        }

        player.sendMessage("You have warned: " + args[0]);
        target.sendMessage(ChatColor.RED + "You have been warned! Watch it!");
        main.data.setInt(target.getUniqueId(), main.data.getInt(target.getUniqueId(), "count") + 1, "count");
        return true;
    }
}

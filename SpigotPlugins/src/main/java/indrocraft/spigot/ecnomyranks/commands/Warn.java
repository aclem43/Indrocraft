package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.databasemanager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager.getFileConfig;

public class Warn implements CommandExecutor {
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
        Player traget = Bukkit.getPlayer(args[0]);

        if (!(traget instanceof Player)) {
            player.sendMessage(ChatColor.RED + "You need to target a real player!");
            return true;
        }

        player.sendMessage("You have warned: " + args[0]);

        String msg = player.getName() + " Warned: " + args[0] + "     " + args[0] + " UUID " + traget.getUniqueId();

        FileConfiguration configFile = getFileConfig();
        String locaction = configFile.getString("WarnSaveLocation");
        String fileName = locaction + "Warned " + args[0] + ".txt";
        FileManager.filewrite(fileName, msg);

        return true;
    }
}

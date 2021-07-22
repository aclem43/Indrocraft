package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.databasemanager.FileManager;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

        String msg = player.getName() + " Warned: " + args[0] + "\n" + args[0] + " UUID " + traget.getUniqueId() + "\n\n";

        FileConfiguration configFile = getFileConfig("config.yml");
        String locaction = configFile.getString("WarnSaveLocation");
        String fileName = locaction + "Warns" + ".txt";

        try {
            Files.write(Paths.get(fileName), msg.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            player.sendMessage("it no work");
            FileManager.filewrite(fileName, msg);
            //exception handling left as an exercise for the reader
        }

        return true;
    }
}

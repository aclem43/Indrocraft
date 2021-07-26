package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.FileManager;
import indrocraft.spigot.ecnomyranks.databasemanager.MySQL;
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

    private final Main main;

    public Warn(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        main.data.addcolumn("count", "INT(100)");

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
        main.data.createPlayer(traget);

        if (!(traget instanceof Player)) {
            player.sendMessage(ChatColor.RED + "You need to target a real player!");
            return true;
        }

        player.sendMessage("You have warned: " + args[0]);
        traget.sendMessage(ChatColor.RED + "You have been warned! Watch it!");
        main.data.setInt(traget.getUniqueId(), main.data.getInt(traget.getUniqueId(), "count") + 1, "count");
        return true;
    }
}

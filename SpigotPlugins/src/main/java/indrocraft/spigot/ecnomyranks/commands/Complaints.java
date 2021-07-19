package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager.getFileConfig;

public class Complaints implements CommandExecutor {
    //this is so that i can message console:
    private final Main main;

    public Complaints(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //checks if the console issued the command
        if (!(sender instanceof Player)) {
            main.getLogger().info("Must be a player to use this command!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "You must enter a players name then write your complaint!");
            return true;
        }

        //casts player
        Player player = (Player) sender;
        //puts the the args into a string
        String msg = "";
        for (int i = 1; i < args.length; i++) {
            msg += " " + args[i];
        }

        if (msg == "") {
            player.sendMessage(ChatColor.RED + "Please make sure that your command is: /complaints <PlayerName> <complaint>");
            return true;
        }

        Player targ = Bukkit.getPlayer(args[0]);
        if (!(targ instanceof Player)) {
            player.sendMessage(ChatColor.RED + "Please make sure that your command is: /complaints <PlayerName> <complaint>");
            return true;
        }

        //sends message to admin
        FileConfiguration configFile = getFileConfig("config.yml");
        String loc = configFile.getString("complaintssavelocation");
        Player admin1 = Bukkit.getPlayer("OMEN44");
        String loc2 = loc + "Complaint about " + args[0] + ".txt";
        FileManager.filewrite(loc2, player.getName() + " said: " + msg + "\nComplaints target is: " + args[0] + " " + targ.getUniqueId() + "\n");
        admin1.sendMessage("Sup, some one said dis: " + msg);

        return true;
    }
}

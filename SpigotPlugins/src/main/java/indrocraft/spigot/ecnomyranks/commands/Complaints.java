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
        //casts player
        Player player = (Player) sender;
        //puts the the args into a string
        String msg = "";
        for (int i = 0; i < args.length; i++) {
            msg += " " + args[i];
        }
        player.sendMessage("final message is: " + ChatColor.BLUE + "" + msg);
        //sends message to admin
        FileConfiguration configFile = getFileConfig();
        String loc = configFile.getString("complaintssavelocation");
        Player admin1 = Bukkit.getPlayer("OMEN44");
        String loc2 = loc + "complaint" + player.getName() + ".txt";
        FileManager.filewrite(loc2, msg);
        admin1.sendMessage("Sup, some one said dis: " + msg);

        return true;
    }
}

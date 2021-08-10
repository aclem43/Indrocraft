package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class Verify implements CommandExecutor {

    private final Main main;

    public Verify(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            main.data.createPlayer(player, "unverified");
            Random rand = new Random();
            int code = rand.nextInt(899999);
            Integer y = new Integer(code);
            main.data.setString(player.getUniqueId(), y.toString(), "NAME", "unverified");
            player.sendMessage(ChatColor.YELLOW + "Please go to discord and use the command '$verify " + y.toString() + "' to finish the process!");
            player.sendMessage(ChatColor.YELLOW + "If you run into any problems use /verify to restart the process or contact an admin!");
        }
        return true;
    }
}

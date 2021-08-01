package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
    private final Main main;

    public Home(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("*console teleports*");
        } else {
            Player player = (Player) sender;
            Location playerLoc = player.getLocation();
            Location loc = playerLoc.add(1, 10, 1);

            player.teleport(loc);
        }
        return false;
    }
}

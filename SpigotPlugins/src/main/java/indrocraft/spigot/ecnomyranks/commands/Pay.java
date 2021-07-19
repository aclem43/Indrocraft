package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pay implements CommandExecutor {
    //this is so that i can message console:
    private final Main main;

    public Pay(Main main) {
        this.main = main;
    }
    public boolean onCommand(CommandSender sender,Command command, String label,String[] args){

        if (!(sender instanceof Player)) {
            main.getLogger().info("Must be a player to use this command!");
            return true;
        }
        Player target = (Player) Bukkit.getServer().getPlayer(args[0]);

        //get Money Of Sender, Check If They Have Enough
        //If True update money of sender to have theirs minus the amount then update the money of the target
        //if false Send Message informing them of not having enough for the transaction

        return true;
    }
}

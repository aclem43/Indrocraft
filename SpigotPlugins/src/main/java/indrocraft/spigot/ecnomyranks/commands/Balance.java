package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Balance implements CommandExecutor {
    //this is so that i can message console:
    private final Main main;

    public Balance(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            main.getLogger().info("Must be a player to use this command!");
            return true;
        }
        //get Balance Of Wallet And Bank
        sender.sendMessage("Omen44 Needs to Finish Database");
        return true;
    }
}

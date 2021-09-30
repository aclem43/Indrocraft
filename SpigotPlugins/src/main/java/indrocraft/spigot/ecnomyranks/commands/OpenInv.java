package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenInv implements CommandExecutor {

    private SQLgetter data;

    public OpenInv(SQLgetter data) {
        this.data = data;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.isOp()){
            try {
                Player target = Bukkit.getPlayer(args[0]);
                player.openInventory(target.getInventory());
            } catch (Exception e) {
                player.sendMessage("did not specify a player");
                return true;
            }

        }

        return true;
    }



}

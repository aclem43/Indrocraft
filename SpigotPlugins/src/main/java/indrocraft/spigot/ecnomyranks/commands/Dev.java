package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.ranks.RankUtils;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;


public class Dev implements CommandExecutor {

    private final Main main;
    public Dev(Main main) {this.main = main;}

    @Override
    public boolean onCommand( CommandSender sender,Command command,String label, String[] args) {
        Player player = (Player) sender;

        if (player.isOp()) {
            if ("world".equalsIgnoreCase(args[0])) {
                // Assign 99.95 to double variable billAmt
                double billAmt = 99.95;
                System.out.println("billAmt :"+ billAmt);
                // Typecast billAmt
                // to convert double billAmt value to int
                // and assign it to int variable bill
                int bill = (int) billAmt;
                System.out.println(" Your generated bill amount is : $"+bill+". Thank You! ");
            }
            if ("load".equals(args[0])) {
                RankUtils.LoadRank(player, main.data);
            }
            if ("list".equalsIgnoreCase(args[0])) {
                FileConfiguration config = Databasemanager.getFileConfig("config.yml");
                for(String key : config.getConfigurationSection("ranks").getKeys(false))
                player.sendMessage(key);
                return true;
            }
        }
        return true;
    }
}

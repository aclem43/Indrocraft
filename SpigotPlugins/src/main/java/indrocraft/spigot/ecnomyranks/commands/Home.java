package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Home implements TabExecutor {
    private final Main main;

    public Home(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please put a home name after the command!");
            } else if ("sethome".equalsIgnoreCase(label)) {



                World world = Bukkit.getWorld("CraftWorld{name=world}");
                double x = player.getLocation().getX();
                double y = player.getLocation().getY();
                double z = player.getLocation().getZ();
                float pitch =player.getLocation().getPitch();
                float yaw = player.getLocation().getYaw();

                Location loc = new Location(world, x, y, z, pitch, yaw);

                main.data.createRow(player, player.getName() + args[0], "tpinfo");
                main.data.addcolumn("homeName", "VARCHAR(200)", "tpinfo");
                main.data.setString(player.getUniqueId(), args[0], "homeName", "tpinfo", player.getName() + args[0]);
                main.data.setString(player.getUniqueId(), player.getWorld().toString(), "world", "tpinfo", player.getName() + args[0]);
                main.data.setdouble(player.getUniqueId(), x, "x", "tpinfo", player.getName() + args[0]);
                main.data.setdouble(player.getUniqueId(), y, "y", "tpinfo", player.getName() + args[0]);
                main.data.setdouble(player.getUniqueId(), z, "z", "tpinfo", player.getName() + args[0]);
                main.data.setFloat(player.getUniqueId(), pitch, "pitch", "tpinfo", player.getName() + args[0]);
                main.data.setFloat(player.getUniqueId(), yaw, "yaw", "tpinfo", player.getName() + args[0]);

                String get = main.data.getString(player.getUniqueId(), "Homes", "playerinfo");
                main.data.setString(player.getUniqueId(), get + " " + args[0], "Homes", "playerinfo");


            } else if ("home".equalsIgnoreCase(label)){

                World world = player.getWorld();
                double x = main.data.getDouble(player.getUniqueId(), "x", "tpinfo", player.getName() + args[0]);
                double y = main.data.getDouble(player.getUniqueId(), "y", "tpinfo", player.getName() + args[0]);
                double z = main.data.getDouble(player.getUniqueId(), "z", "tpinfo", player.getName() + args[0]);
                float pitch = main.data.getFloat(player.getUniqueId(), "pitch", "tpinfo", player.getName() + args[0]);
                float yaw = main.data.getFloat(player.getUniqueId(), "yaw", "tpinfo", player.getName() + args[0]);

                Location location = new Location(world, x, y, z, yaw, pitch);
                player.teleport(location);

            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 0){
            List<String> arg1 = new ArrayList<>();
            Player player = (Player) sender;

            String string = main.data.getString(player.getUniqueId(), "Homes", "playerinfo");
            String[] list = string.split(" ");

            player.sendMessage(list);

            for (int i = 1; i < list.length; i++) {
                arg1.add(args[i]);
            }

            player.sendMessage(string.split(string));
            return arg1;
        }
        return null;
    }
}

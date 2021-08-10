package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.ranks.Rank;
import indrocraft.spigot.ecnomyranks.ranks.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Home implements CommandExecutor {
    private final Main main;

    public Home(Main main) {this.main = main;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatColor red = ChatColor.RED;
        FileConfiguration config = Databasemanager.getFileConfig("config.yml");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Rank pRank = RankManager.getRank(player, main.data);
            int getNumOfHomes = main.data.getInt(player.getUniqueId(), "homeNum", "playerinfo");
            int numOfHomes = 0;
            int playerLevel = pRank.getLevel();
            String status = pRank.getStatus();
            if ("$".equalsIgnoreCase(status)) {
                numOfHomes = 5;
            } else if (playerLevel == 0) {
                numOfHomes = 1;
            } else if (playerLevel == 1) {
                numOfHomes = 2;
            } else if (playerLevel == 2) {
                numOfHomes = 3;
            } else if (playerLevel == 3) {
                numOfHomes = 4;
            } else if (playerLevel == 4) {
                numOfHomes = 5;
            } else if (playerLevel == 5) {
                numOfHomes = 10;
            }
            //gets number of homes using: pRank.getLevel()
            if ("sethome".equalsIgnoreCase(label)) {
                if (getNumOfHomes < numOfHomes) {
                    if (args.length > 0) {
                        String homesList = main.data.getString(player.getUniqueId(), "homeList", "playerinfo");
                        if (!(homesList.contains(" " + args[0].toLowerCase() + " "))) {
                            player.sendMessage(ChatColor.BLUE + "Successfully set home: " + ChatColor.GREEN + args[0].toLowerCase());

                            String databaseName = config.getString("databseForTP");

                            main.data.setInt(player.getUniqueId(), getNumOfHomes + 1, "homeNum", "playerinfo");

                            String hList = main.data.getString(player.getUniqueId(), "homeList", "playerinfo");
                            main.data.setString(player.getUniqueId(), hList + args[0].toLowerCase() + " ", "homeList", "playerinfo");

                            String getWorld = player.getWorld().toString();
                            getWorld = getWorld.substring(0, getWorld.length() - 1);
                            String[] world = getWorld.split("\\=");

                            main.data.createRow(player, player.getUniqueId() + args[0].toLowerCase(), databaseName);
                            main.data.setString(player.getUniqueId(), world[1], "world", databaseName, player.getUniqueId() + args[0].toLowerCase());
                            main.data.setdouble(player.getUniqueId(), player.getLocation().getX(), "x", databaseName, player.getUniqueId() + args[0].toLowerCase());
                            main.data.setdouble(player.getUniqueId(), player.getLocation().getY(), "y", databaseName, player.getUniqueId() + args[0].toLowerCase());
                            main.data.setdouble(player.getUniqueId(), player.getLocation().getZ(), "z", databaseName, player.getUniqueId() + args[0].toLowerCase());
                            main.data.setFloat(player.getUniqueId(), player.getLocation().getYaw(), "yaw", databaseName, player.getUniqueId() + args[0].toLowerCase());
                            main.data.setFloat(player.getUniqueId(), player.getLocation().getPitch(), "pitch", databaseName, player.getUniqueId() + args[0].toLowerCase());
                        } else {
                            player.sendMessage(red + "You already have a home set with this name please delete it first!");
                            return true;
                        }
                    } else {
                        player.sendMessage(red + "Must have a home name after '/sethome'");
                        return true;
                    }
                } else {
                    player.sendMessage(red + "You have too many homes! You cannot make more until you delete some!");
                    return true;
                }
            } else if ("home".equalsIgnoreCase(label)) {
                if (args.length > 0) {
                    String databaseName = config.getString("databseForTP");
                    String test = main.data.getString(player, "NAME", databaseName, player.getUniqueId() + args[0].toLowerCase());

                    if ((player.getUniqueId() + args[0].toLowerCase()).equalsIgnoreCase(test)) {
                        String getWorld = main.data.getString(player, "world", databaseName, player.getUniqueId() + args[0].toLowerCase());
                        double x = main.data.getDouble(player.getUniqueId(), "x", databaseName, player.getUniqueId() + args[0].toLowerCase());
                        double y = main.data.getDouble(player.getUniqueId(), "y", databaseName, player.getUniqueId() + args[0].toLowerCase());
                        double z = main.data.getDouble(player.getUniqueId(), "z", databaseName, player.getUniqueId() + args[0].toLowerCase());
                        float yaw = main.data.getFloat(player.getUniqueId(), "yaw", databaseName, player.getUniqueId() + args[0].toLowerCase());
                        float pitch = main.data.getFloat(player.getUniqueId(), "pitch", databaseName, player.getUniqueId() + args[0].toLowerCase());

                        World world = Bukkit.getWorld(getWorld);
                        Location location = new Location(world, x, y, z, yaw, pitch);
                        player.sendMessage(ChatColor.YELLOW + "Teleporting to home " + ChatColor.GREEN + args[0].toLowerCase() + ChatColor.YELLOW + " now!");
                        player.teleport(location);
                    } else {
                        player.sendMessage(red + "The home: " + ChatColor.DARK_RED + args[0].toLowerCase() + red + " doesn't exist");
                        return true;
                    }
                } else {
                    player.sendMessage(red + "Must have a home name after '/home'");
                    return true;
                }
            } else if ("delhome".equalsIgnoreCase(label)) {
                if (args.length > 0) {
                    String databaseName = config.getString("databseForTP");
                    String test = main.data.getString(player, "NAME", databaseName, player.getUniqueId() + args[0].toLowerCase());

                    if ((player.getUniqueId() + args[0].toLowerCase()).equalsIgnoreCase(test)) {
                        player.sendMessage(ChatColor.BLUE + "Home " + args[0].toLowerCase() + " was successfully deleted!");
                        int currentNum = main.data.getInt(player.getUniqueId(), "homeNum", "playerinfo");
                        main.data.setInt(player.getUniqueId(), currentNum - 1, "homeNum", "playerinfo");
                        main.data.remove(player.getUniqueId(), databaseName, player.getUniqueId() + args[0].toLowerCase());
                        String hl = main.data.getString(player.getUniqueId(), "homeList", "playerinfo");
                        main.data.setString(player.getUniqueId(), hl.replace(" " + args[0].toLowerCase() + " ", " "), "homeList", "playerinfo");
                    } else {
                        player.sendMessage(red + "The home: " + ChatColor.DARK_RED + args[0].toLowerCase() + red + " needs to exist to delete it!");
                        return true;
                    }
                } else {
                    player.sendMessage(red + "Must have a home name after '/delhome'");
                    return true;
                }
            } else if ("homelist".equalsIgnoreCase(label)) {
                String string = main.data.getString(player.getUniqueId(), "homeList", "playerinfo");
                String[] list = string.split(" ");

                String homeList = "";

                for (int i = 1; i < list.length; i++) {
                    homeList = homeList + ChatColor.GREEN + list[i] + ChatColor.WHITE + ", ";
                }

                try {
                    player.sendMessage(ChatColor.BLUE + "You have " + ChatColor.GREEN + getNumOfHomes + "/" + numOfHomes + ChatColor.BLUE + " homes, they are:");
                    player.sendMessage(homeList.substring(0, homeList.length() - 2));
                } catch (StringIndexOutOfBoundsException e) {
                    player.sendMessage(red + "You have no homes set!");
                    return true;
                }
            }
        }
        return true;
    }
}
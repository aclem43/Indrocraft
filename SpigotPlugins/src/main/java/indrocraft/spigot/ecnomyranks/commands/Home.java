package indrocraft.spigot.ecnomyranks.commands;

import indrocraft.spigot.ecnomyranks.Main;
import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.ranks.RankUtils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Home implements TabExecutor {
    private final Main main;

    public Home(Main main) {this.main = main;}

    FileConfiguration config = Databasemanager.getFileConfig("config.yml");
    public String databaseName = config.getString("databseForTP");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatColor red = ChatColor.RED;
        Boolean homeEnabled = config.getBoolean("homes");

        if (homeEnabled) {

            if (sender instanceof Player) {
                Player player = (Player) sender;
                int getNumOfHomes = main.data.getInt(player.getUniqueId(), databaseName + "num", "playerinfo");
                int numOfHomes = 0;
                int playerLevel = RankUtils.getLevel(player, main.data);
                if (playerLevel == 0) {
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
                            String homesList = main.data.getString(player.getUniqueId(), databaseName, "playerinfo");
                            if (!(homesList.contains(" " + args[0].toLowerCase() + " "))) {
                                player.sendMessage(ChatColor.BLUE + "Successfully set home: " + ChatColor.GREEN + args[0].toLowerCase());

                                main.data.setInt(player.getUniqueId(), getNumOfHomes + 1, databaseName + "num", "playerinfo");

                                String hList = main.data.getString(player.getUniqueId(), databaseName, "playerinfo");
                                main.data.setString(player.getUniqueId(), hList + args[0].toLowerCase() + " ", databaseName, "playerinfo");

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

                            Chunk chunk = player.getWorld().getChunkAt(location);
                            player.getWorld().loadChunk(chunk);


                            player.sendMessage(ChatColor.BLUE + "Teleporting to home " + ChatColor.GREEN + args[0].toLowerCase() + ChatColor.BLUE + " now!");
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
                        String test = main.data.getString(player, "NAME", databaseName, player.getUniqueId() + args[0].toLowerCase());

                        if ((player.getUniqueId() + args[0].toLowerCase()).equalsIgnoreCase(test)) {
                            player.sendMessage(ChatColor.BLUE + "Home " + ChatColor.GREEN + args[0].toLowerCase() + ChatColor.BLUE + " was successfully deleted!");
                            int currentNum = main.data.getInt(player.getUniqueId(), databaseName + "num", "playerinfo");
                            main.data.setInt(player.getUniqueId(), currentNum - 1, databaseName + "num", "playerinfo");
                            main.data.remove(databaseName, player.getUniqueId() + args[0].toLowerCase());
                            String hl = main.data.getString(player.getUniqueId(), databaseName, "playerinfo");
                            main.data.setString(player.getUniqueId(), hl.replace(" " + args[0].toLowerCase() + " ", " "), databaseName, "playerinfo");
                        } else {
                            player.sendMessage(red + "The home: " + ChatColor.DARK_RED + args[0].toLowerCase() + red + " needs to exist to delete it!");
                            return true;
                        }
                    } else {
                        player.sendMessage(red + "Must have a home name after '/delhome'");
                        return true;
                    }
                } else if ("homelist".equalsIgnoreCase(label)) {
                    String string = main.data.getString(player.getUniqueId(), databaseName, "playerinfo");
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

        } else {
            Player player = (Player) sender;
            player.sendMessage("Please enable homes in the config file");
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1){
            if (s.equalsIgnoreCase("home") || s.equalsIgnoreCase("delhome")) {
                Player player = (Player) commandSender;
                String rawHomes = main.data.getString(player.getUniqueId(), databaseName, "playerinfo");
                String[] homes = rawHomes.split(" ");
                List<String> arg1 = new ArrayList<>();
                int len = homes.length - 1;
                while (len > 0) {
                    arg1.add(homes[len]);
                    len--;
                }
                return arg1;
            }
        }
        return null;
    }
}
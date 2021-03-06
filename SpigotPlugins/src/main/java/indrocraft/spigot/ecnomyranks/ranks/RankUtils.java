package indrocraft.spigot.ecnomyranks.ranks;

import indrocraft.spigot.ecnomyranks.databasemanager.Databasemanager;
import indrocraft.spigot.ecnomyranks.databasemanager.SQLgetter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class RankUtils {
    public static FileConfiguration config = Databasemanager.getFileConfig("rank.yml");

    public static void setRank(Player player, SQLgetter data, String newRank) {
        data.setString(player.getUniqueId(), newRank, "Rank", "playerinfo");
    }

    public static void setNameColour(Player player, SQLgetter data, String newColour) {
        data.setString(player.getUniqueId(), newColour, "nameColour", "playerinfo");
    }

    public static void levelUp(Player player, SQLgetter data, String newRank) {
        setRank(player, data, newRank);
        LoadRank(player, data);
    }

    public static void updateAdvancement(Player player, SQLgetter data, String level) {

        Integer test = data.getInt(player.getUniqueId(), "level" + level + "advances", "playerinfo");
        if (test == null) {
            data.setInt(player.getUniqueId(), 0, "level" + level + "advances", "playerinfo");
        } else {
            test++;
            data.setInt(player.getUniqueId(), test, "level" + level + "advances", "playerinfo");
        }
    }

    public static int getAdvancement(Player player, SQLgetter data, String level) {
        return data.getInt(player.getUniqueId(), "level" + level + "advances", "playerinfo");
    }

    public static void LoadRank(Player player, SQLgetter data) {
        String code = data.getString(player.getUniqueId(),"Rank", "playerinfo");
        String displayName = config.getString("ranks." + code + ".displayName");
        ChatColor colorA = getColour(1, player, data);
        ChatColor colorB = getColour(2, player, data);
        ChatColor name = getColour(3, player, data);
        player.setDisplayName(colorB + "[" + colorA + displayName + colorB + "] " + name + player.getName() + ChatColor.WHITE + "");
        player.setPlayerListName(colorB + "[" + colorA + displayName + colorB + "] " + name + player.getName() + ChatColor.WHITE + "");
    }

    public static int getLevel(Player player, SQLgetter data) {
        String code = data.getString(player.getUniqueId(),"Rank", "playerinfo");
        int level = Integer.parseInt(code);
        return level;
    }

    public static ChatColor getColour(int colourNum, Player player, SQLgetter data) {
        String code = data.getString(player.getUniqueId(),"Rank", "playerinfo");
        String color = null;
        if (colourNum == 1) {
            color = config.getString("ranks." + code + ".primaryColour");
        } else if (colourNum == 2) {
            color = config.getString("ranks." + code + ".secondaryColour");
        } else if (colourNum == 3) {
            color = data.getString(player.getUniqueId(), "nameColour", "playerinfo");
        }

        return readColour(color);
    }
    
    public static ChatColor readColour(String color) {
        if (color.equalsIgnoreCase("gray")) {
            return ChatColor.GRAY;
        } else if (color.equalsIgnoreCase("dark_gray")) {
            return ChatColor.DARK_GRAY;
        } else if (color.equalsIgnoreCase("black")) {
            return ChatColor.BLACK;
        } else if (color.equalsIgnoreCase("dark_red")) {
            return ChatColor.DARK_RED;
        } else if (color.equalsIgnoreCase("red")) {
            return ChatColor.RED;
        } else if (color.equalsIgnoreCase("gold")) {
            return ChatColor.GOLD;
        } else if (color.equalsIgnoreCase("yellow")) {
            return ChatColor.YELLOW;
        } else if (color.equalsIgnoreCase("dark_green")) {
            return ChatColor.DARK_GREEN;
        } else if (color.equalsIgnoreCase("green")) {
            return ChatColor.GREEN;
        } else if (color.equalsIgnoreCase("aqua")) {
            return ChatColor.AQUA;
        } else if (color.equalsIgnoreCase("dark_aqua")) {
            return ChatColor.DARK_AQUA;
        } else if (color.equalsIgnoreCase("dark_blue")) {
            return ChatColor.DARK_BLUE;
        } else if (color.equalsIgnoreCase("blue")) {
            return ChatColor.BLUE;
        } else if (color.equalsIgnoreCase("light_purple")) {
            return ChatColor.LIGHT_PURPLE;
        } else if (color.equalsIgnoreCase("dark_purple")) {
            return ChatColor.DARK_PURPLE;
        }
        return ChatColor.WHITE;
    }
}



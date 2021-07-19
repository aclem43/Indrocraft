package indrocraft.spigot.ecnomyranks.ranks;

import org.bukkit.ChatColor;

public enum Rank {
    NONE(ChatColor.RED +  "GREMLIN"),
    HERO(ChatColor.AQUA + "HERO"),
    DGOD(ChatColor.BLUE + "DEMIGOD"),
    GOD(ChatColor.LIGHT_PURPLE + "GOD"),
    TITAN(ChatColor.GOLD + "TITAN"),
    DEV(ChatColor.BLACK + "DEV");

    private final String prefix;

    Rank(String prefix) {
        this.prefix = prefix;
    }
    public String getPrefix() {
        return prefix;
    }
}
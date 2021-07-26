package indrocraft.spigot.ecnomyranks.ranks;

import org.bukkit.ChatColor;

public enum Rank {
    NONE(ChatColor.RED,   "GREMLIN"),
    HERO(ChatColor.AQUA, "HERO"),
    DGOD(ChatColor.BLUE, "DEMIGOD"),
    GOD(ChatColor.LIGHT_PURPLE,  "GOD"),
    TITAN(ChatColor.GREEN,  "TITAN"),
    DEV(ChatColor.BLACK,  "DEV"),
    DONOR(ChatColor.GOLD,  "$ DONOR $");

    private final String prefix;

    Rank(ChatColor color,String prefix) {

        this.prefix = color + prefix+ ChatColor.WHITE + " : ";
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean hasPrefix() {
        return prefix != null;
    }
}
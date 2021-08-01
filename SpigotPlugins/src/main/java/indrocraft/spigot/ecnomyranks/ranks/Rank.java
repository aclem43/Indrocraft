package indrocraft.spigot.ecnomyranks.ranks;

import org.bukkit.ChatColor;

public enum Rank {
    NONE(ChatColor.GRAY,   "GREMLIN","GRE"),
    HERO(ChatColor.GREEN, "HERO","HER"),
    DGOD(ChatColor.DARK_AQUA, "DEMIGOD","DGD"),
    GOD(ChatColor.DARK_BLUE,  "GOD","GOD"),
    TITAN(ChatColor.DARK_GREEN,  "TITAN","TIT"),
    DEV(ChatColor.DARK_PURPLE,  "DEV","DEV"),
    DONOR(ChatColor.GOLD,  "$ DONOR $","DON");

    private final String prefix;
    private final String code;

    Rank(ChatColor color,String prefix,String code) {

        this.prefix = color + prefix+ ChatColor.WHITE + " : ";
        this.code = code;
    }



    public String getPrefix() {
        return prefix;
    }
    public String getCode() {
        return code;
    }

    public boolean hasPrefix() {
        return prefix != null;
    }
}
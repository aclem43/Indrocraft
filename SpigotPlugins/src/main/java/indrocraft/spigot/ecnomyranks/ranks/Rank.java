package indrocraft.spigot.ecnomyranks.ranks;

import org.bukkit.ChatColor;

public enum Rank {
    NONE(ChatColor.RED,   "GREMLIN","GRE"),
    HERO(ChatColor.AQUA, "HERO","HER"),
    DGOD(ChatColor.BLUE, "DEMIGOD","DGD"),
    GOD(ChatColor.LIGHT_PURPLE,  "GOD","GOD"),
    TITAN(ChatColor.GREEN,  "TITAN","TIT"),
    DEV(ChatColor.BLACK,  "DEV","DEV"),
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
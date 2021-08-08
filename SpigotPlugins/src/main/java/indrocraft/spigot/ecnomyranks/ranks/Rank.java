package indrocraft.spigot.ecnomyranks.ranks;

import org.bukkit.ChatColor;

public enum Rank {
    NONE(ChatColor.GRAY, ChatColor.DARK_GRAY,   "GREMLIN","GRE",0),
    HERO(ChatColor.GREEN, ChatColor.DARK_GREEN, "HERO","HER",1),
    TITAN(ChatColor.AQUA, ChatColor.DARK_AQUA,  "TITAN","TIT",2),
    DGOD(ChatColor.BLUE, ChatColor.DARK_BLUE, "DEMIGOD","DGD",3),
    GOD(ChatColor.RED, ChatColor.DARK_RED,  "GOD","GOD",4),
    DONOR(ChatColor.YELLOW, ChatColor.GOLD,  "$","DON",4),
    DEV(ChatColor.LIGHT_PURPLE, ChatColor.DARK_PURPLE,  "DEV","DEV",5);

    private final String prefix;
    private final String code;
    private final Integer level;

    Rank(ChatColor colorA, ChatColor colorB, String prefix, String code, Integer level) {
        this.prefix = colorB + "[" + colorA + prefix + colorB + "] " + ChatColor.WHITE + "";
        this.code = code;
        this.level = level;
    }



    public String getPrefix() {
        return prefix;
    }
    public String getCode() {
        return code;
    }
    public Integer getLevel() {
        return level;
    }

    public boolean hasPrefix() {
        return prefix != null;
    }
}
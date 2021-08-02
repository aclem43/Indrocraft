package indrocraft.spigot.ecnomyranks.ranks;

import org.bukkit.ChatColor;

public enum Rank {
    NONE(ChatColor.GRAY,   "GREMLIN","GRE",0),
    HERO(ChatColor.GREEN, "HERO","HER",1),
    TITAN(ChatColor.DARK_GREEN,  "TITAN","TIT",2),
    DGOD(ChatColor.DARK_AQUA, "DEMIGOD","DGD",3),
    GOD(ChatColor.DARK_BLUE,  "GOD","GOD",4),
    DONOR(ChatColor.GOLD,  "$ DONOR $","DON",4),
    DEV(ChatColor.DARK_PURPLE,  "DEV","DEV",5);

    private final String prefix;
    private final String code;
    private final Integer level;

    Rank(ChatColor color,String prefix,String code,Integer level) {
        this.prefix = color + prefix+ ChatColor.WHITE + " : ";
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
package indrocraft.spigot.ecnomyranks.ranks;

import org.bukkit.ChatColor;

public enum Rank {
    NONE(ChatColor.GRAY, ChatColor.DARK_GRAY,   "GREMLIN","GRE",0),
    HERO(ChatColor.GREEN, ChatColor.DARK_GREEN, "HERO","HER",1),
    TITAN(ChatColor.AQUA, ChatColor.DARK_AQUA,  "TITAN","TIT",2),
    DGOD(ChatColor.BLUE, ChatColor.DARK_BLUE, "DEMIGOD","DGD",3),
    GOD(ChatColor.RED, ChatColor.DARK_RED,  "GOD","GOD",4),
    NONEX(ChatColor.GRAY, ChatColor.DARK_GRAY,   "GREMLIN","GR$",0, "$"),
    HEROX(ChatColor.GREEN, ChatColor.DARK_GREEN, "HERO","HE$",1, "$"),
    TITANX(ChatColor.AQUA, ChatColor.DARK_AQUA,  "TITAN","TI$",2, "$"),
    DGODX(ChatColor.BLUE, ChatColor.DARK_BLUE, "DEMIGOD","DG$",3, "$"),
    GODX(ChatColor.RED, ChatColor.DARK_RED,  "GOD","GO$",4, "$"),
    DONOR(ChatColor.YELLOW, ChatColor.GOLD,  "$","DON",4),
    DEV(ChatColor.LIGHT_PURPLE, ChatColor.DARK_PURPLE,  "DEV","DEV",5);


    private final String prefix;
    private final String code;
    private final Integer level;
    private final String status;

    Rank(ChatColor colorA, ChatColor colorB, String prefix, String code, Integer level, String status) {
            this.prefix = colorB + "[" + ChatColor.GOLD + status + colorA + prefix + ChatColor.GOLD + status + colorB + "] " + ChatColor.WHITE + "";
            this.code = code;
            this.level = level;
            this.status = status;
    }

    Rank(ChatColor colorA, ChatColor colorB, String prefix, String code, Integer level) {
            this.prefix = colorB + "[" + colorA + prefix + colorB + "] " + ChatColor.WHITE + "";
            this.code = code;
            this.level = level;
            this.status = "";
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
    public String getStatus() {
        return status;
    }

    public boolean hasPrefix() {
        return prefix != null;
    }
}
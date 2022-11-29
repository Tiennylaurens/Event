package com.tienny.event.RankManagment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum Rank {
    STAFF(ChatColor.DARK_RED + "[Staff] ", new String[]{"blockwars.warp"}),
    HELPER(ChatColor.AQUA + "[Helper] ", new String[]{}),
    PLAYER(ChatColor.GRAY + "[Player] ", new String[]{});

    private String display;
    private String[] permissions;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;
    }

    public String getDisplay() {
        return display;
    }
    public String[] getPermissions() {return permissions; }
}

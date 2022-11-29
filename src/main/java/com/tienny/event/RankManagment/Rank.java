package com.tienny.event.RankManagment;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum Rank {
    STAFF(ChatColor.DARK_RED + "[Staff]"),
    HELPER(ChatColor.AQUA + "[Helper]"),
    PLAYER(ChatColor.GRAY + "[Player]");

    private String display;

    Rank(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}

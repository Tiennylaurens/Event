package com.tienny.event.RankManagment;

import com.tienny.event.TeamManagment.TEAM;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public enum Rank {
    OWNER(ChatColor.GOLD + "[Owner]", new String[]{"team"}, TEAM.OWNER),
    STAFF(ChatColor.DARK_RED + "[Staff] ", new String[]{"team"}, TEAM.STAFF),
    HELPER(ChatColor.AQUA + "[Helper] ", new String[]{}, TEAM.STAFF),
    PLAYER(ChatColor.GRAY + "[Player] ", new String[]{}, TEAM.SPECTATOR);

    private String display;
    private String[] permissions;
    private TEAM team;

    Rank(String display, String[] permissions, TEAM team) {
        this.display = display;
        this.permissions = permissions;
        this.team = team;
    }

    public String getDisplay() { return display; }
    public String[] getPermissions() { return permissions; }
    public TEAM getTeam() { return team; }
}

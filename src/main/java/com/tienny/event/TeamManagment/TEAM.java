package com.tienny.event.TeamManagment;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public enum TEAM {
    BLUE(ChatColor.BLUE + "[Blue Bottomfraggers]", ChatColor.BLUE, true),
    GREEN(ChatColor.GREEN + "[Lime Leavers]", ChatColor.GREEN, true),
    PURPLE(ChatColor.DARK_PURPLE + "[Purple Prawns]", ChatColor.DARK_PURPLE, true),
    RED(ChatColor.RED + "[Red Roleplayers]", ChatColor.RED, true),
    SPECTATOR(ChatColor.GRAY + "[Spectator]", ChatColor.GRAY, false),
    STAFF(ChatColor.DARK_RED + "[Staff]", ChatColor.DARK_RED, false),
    OWNER(ChatColor.GOLD + "[Owner]", ChatColor.GOLD, false);

    private TeamClass teamClass;
    private String display;
    private ChatColor color;
    private boolean realTeam;

    TEAM(String display, ChatColor color, boolean realTeam) {
        teamClass = new TeamClass(this);
        this.display = display;
        this.color = color;
        this.realTeam = realTeam;
    }

    public List<TEAM> getGameTeams() {
        List<TEAM> returnList = new ArrayList<>();
        for (TEAM team : TEAM.values()) {
            if (team.isRealTeam()) {
                returnList.add(team);
            }
        }
        return returnList;
    }

    public TeamClass getTeamClass() {
        return teamClass;
    }
    public String getDisplay() {
        return display;
    }
    public ChatColor getColor() {
        return color;
    }
    public boolean isRealTeam() {
        return realTeam;
    }
}

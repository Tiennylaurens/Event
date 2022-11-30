package com.tienny.event.ScoreboardManagment;

import com.tienny.event.Main;
import com.tienny.event.TeamManagment.TEAM;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoardManager {

    private Main main;

    public ScoreBoardManager(Main main) {
        this.main = main;
    }

    public void setScoreBoard(Player player) { //Create a scoreboard for the player that stores the lobby nametags and teams
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (TEAM gameTeam : TEAM.values()) { //Set the teams and their prefixes
            Team team = player.getScoreboard().registerNewTeam(gameTeam.name());
            team.setPrefix(gameTeam.getDisplay() + " ");
            team.setColor(gameTeam.getColor());
        }

        for (Player target : Bukkit.getOnlinePlayers()) { //Add all online players into new scoreboard.
            player.getScoreboard().getTeam(main.getTeamManager().getTeam(target.getUniqueId()).name()).addEntry(target.getName());
        }

        Objective objective = player.getScoreboard().registerNewObjective("lobbyBoard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Event");


        Team indivPointsTeam = player.getScoreboard().registerNewTeam("indivPoints");
        indivPointsTeam.addEntry(ChatColor.AQUA.toString());
        indivPointsTeam.setPrefix(ChatColor.DARK_AQUA + "Individual Points: ");
        indivPointsTeam.setSuffix(ChatColor.GOLD + String.valueOf(main.getPointsManager().getIndivPoints(player.getUniqueId())));
        objective.getScore(ChatColor.AQUA.toString()).setScore(1);

        Team teamPointsTeam = player.getScoreboard().registerNewTeam("teamPoints");
        teamPointsTeam.addEntry(ChatColor.BLUE.toString());
        teamPointsTeam.setPrefix(ChatColor.DARK_AQUA + "Team points: ");
        teamPointsTeam.setSuffix(ChatColor.GOLD + String.valueOf(main.getPointsManager().getTeamPoints(player.getUniqueId())));
        objective.getScore(ChatColor.BLUE.toString()).setScore(2);

        Score space1 = objective.getScore("");
        space1.setScore(0);

        Score space2 = objective.getScore(" ");
        space2.setScore(3);

    }

    public void newPlayer(Player player) { //Update all existing scoreboards
        TEAM gameTeam = main.getTeamManager().getTeam(player.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(gameTeam.name()).addEntry(player.getName());
        }
    }

    public void removePlayer(Player player) { //Remove from all existing scoreboards
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

    public void resetScoreboards() { //Reset the scoreboard after resetting all teams
        for (Player player : Bukkit.getOnlinePlayers()) {
            removePlayer(player);
            newPlayer(player);
        }
    }

    public void updateScoreboards(Player player) {
        player.getScoreboard().getTeam("indivPoints").setSuffix(ChatColor.GOLD + String.valueOf(main.getPointsManager().getIndivPoints(player.getUniqueId())));
        player.getScoreboard().getTeam("teamPoints").setSuffix(ChatColor.GOLD + String.valueOf(main.getPointsManager().getTeamPoints(player.getUniqueId())));
    }
}

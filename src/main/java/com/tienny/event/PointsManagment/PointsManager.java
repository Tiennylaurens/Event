package com.tienny.event.PointsManagment;

import com.tienny.event.Main;
import com.tienny.event.TeamManagment.TEAM;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;


public class PointsManager {

    private Main main;

    private HashMap<UUID, Integer> indivPoints = new HashMap<>();
    private HashMap<TEAM, Integer> teamPoints = new HashMap<>();

    public PointsManager(Main main) {
        this.main = main;
    }

    public void addPoints(UUID uuid, int points) {
        if (main.getTeamManager().getTeam(uuid).isRealTeam()) {
            if (!indivPoints.containsKey(uuid)) {
                indivPoints.put(uuid, points);
            } else {
                indivPoints.replace(uuid, indivPoints.get(uuid) + points);
            }

            if (!teamPoints.containsKey(main.getTeamManager().getTeam(uuid))) {
                teamPoints.put(main.getTeamManager().getTeam(uuid), points);
            } else {
                teamPoints.replace(main.getTeamManager().getTeam(uuid), teamPoints.get(main.getTeamManager().getTeam(uuid)) + points);
            }
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            main.getScoreBoardManager().updateScoreboards(player);
        }
    }

    public HashMap<UUID, Integer> getIndivPointsMap() {
        return indivPoints;
    }

    public HashMap<TEAM, Integer> getTeamPointsMap() {
        return teamPoints;
    }

    public int getIndivPoints(UUID uuid) {
        if (indivPoints.containsKey(uuid)) {
            return indivPoints.get(uuid);
        }
        return 0;
    }

    public int getTeamPoints(UUID uuid) {
        if (teamPoints.containsKey(main.getTeamManager().getTeam(uuid))) {
            return teamPoints.get(main.getTeamManager().getTeam(uuid));
        }
        return 0;
    }
}

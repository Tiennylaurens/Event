package com.tienny.event.TeamManagment;

import com.tienny.event.Main;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TeamManager {

    private Main main;

    private HashMap<UUID, TEAM> teamHashMap = new HashMap<>();

    public TeamManager(Main main) {
        this.main = main;
    }

    public void addPlayer(UUID uuid, TEAM team) {

        if (!teamHashMap.containsKey(uuid)) {
            team.getTeamClass().addPlayer(uuid);
            teamHashMap.put(uuid, team);
        }

    }

    public void removePlayer(UUID uuid) {

        if (teamHashMap.containsKey(uuid)) {
            teamHashMap.get(uuid).getTeamClass().removePlayer(uuid);
            teamHashMap.remove(uuid);
        }


    }

    public void resetTeams() {
        for (UUID uuid : teamHashMap.keySet()) {
            removePlayer(uuid);
            setTeamBasedOnRank(uuid);
        }
    }

    public TEAM getTeam(UUID uuid) {
        if (teamHashMap.containsKey(uuid)) {
            return teamHashMap.get(uuid);
        }
        return null;
    }

    public boolean isPlayerInTeam(UUID uuid) {
        return teamHashMap.containsKey(uuid);
    }

    public void setTeamBasedOnRank(UUID uuid) {
         main.getRankManager().getRank(uuid).getTeam().getTeamClass().addPlayer(uuid);
         teamHashMap.put(uuid, main.getRankManager().getRank(uuid).getTeam());
    }

}

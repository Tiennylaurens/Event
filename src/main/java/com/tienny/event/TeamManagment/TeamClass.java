package com.tienny.event.TeamManagment;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamClass {

    private List<UUID> uuidList = new ArrayList<>();

    private TEAM team;

    public TeamClass(TEAM team) {
        this.team = team;
    }

    public void addPlayer(UUID uuid) {
        if (!uuidList.contains(uuid)) {
            uuidList.add(uuid);
        }
    }

    public void removePlayer(UUID uuid) {
        if (uuidList.contains(uuid)) {
            uuidList.remove(uuid);
        }
    }

    public void sendMessage(String message) { //Send a message to all online players on team
        for (UUID uuid : getUuidList()) {
            Bukkit.getPlayer(uuid).sendMessage(message);
        }
    }

    public void sendTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) { //Send a title to all online players on team
        for (UUID uuid : getUuidList()) {
            Bukkit.getPlayer(uuid).sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    public List<UUID> getUuidList() {
         List<UUID> returnList = new ArrayList<>();
         for (UUID uuid : uuidList) {
             if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
                 returnList.add(uuid);
             }
         }
         return returnList;
    }

    public TEAM getTeam() { return team; }
}

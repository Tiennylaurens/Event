package com.tienny.event.NameTagManagment;

import com.tienny.event.Main;
import com.tienny.event.RankManagment.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NameTagManager {

    private Main main;

    public NameTagManager(Main main) {
        this.main = main;
    }

    public void setNameTags(Player player) { //Create a scoreboard for the player that stores the lobby nametags and teams
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Rank rank : Rank.values()) { //Set the teams and their suffixes
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay());
        }

        for (Player target : Bukkit.getOnlinePlayers()) { //Add all online players into new scoreboard.
            player.getScoreboard().getTeam(main.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
        }
    }

    public void newTag(Player player) { //Update all existing scoreboards
        Rank rank = main.getRankManager().getRank(player.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }
    }

    public void removeTag(Player player) { //Remove from all existing scoreboards.
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }
}

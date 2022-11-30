package com.tienny.event.TeamManagment;

import com.tienny.event.Main;
import com.tienny.event.RankManagment.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

public class TeamListener implements Listener {

    private Main main;

    public TeamListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) { //Set the name tag of a player on join event

        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) { //Set rank to Player if a player has never played before
            main.getRankManager().setRank(player.getUniqueId(), Rank.PLAYER, true);
        }

        //Set all the perms for a player
        PermissionAttachment attachment;
        if (main.getRankManager().getPerms().containsKey(player.getUniqueId())) {
            attachment = main.getRankManager().getPerms().get(player.getUniqueId());
        } else {
            attachment = player.addAttachment(main);
            main.getRankManager().getPerms().put(player.getUniqueId(), attachment);
        }

        for (String perm : main.getRankManager().getRank(player.getUniqueId()).getPermissions()) {
            attachment.setPermission(perm, true);
        }

        //Add player to team and set their scoreboard
        if (!main.isEventRunning() || !main.getTeamManager().isPlayerInTeam(player.getUniqueId())) { //Add player to spectator team if they aren't in a team yet
            main.getTeamManager().setTeamBasedOnRank(player.getUniqueId());
        }

        main.getScoreBoardManager().setScoreBoard(player);
        main.getScoreBoardManager().newPlayer(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) { //Remove player on quit event

        Player player = event.getPlayer();
        main.getScoreBoardManager().removePlayer(player);
    }
}

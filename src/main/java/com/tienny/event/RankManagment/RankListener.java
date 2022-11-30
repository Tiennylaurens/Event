package com.tienny.event.RankManagment;

import com.tienny.event.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

public class RankListener implements Listener {

    private Main main;

    public RankListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) { //Set rank to Player if a player has never played before
            main.getRankManager().setRank(player.getUniqueId(), Rank.PLAYER, true);
        }

        //Set perms for a player that hasn't joined before
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
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true); //Cancel normal chat message

        Player player = event.getPlayer();

        Bukkit.broadcastMessage(main.getRankManager().getRank(player.getUniqueId()).getDisplay() + " " + player.getName() + ": " + ChatColor.WHITE + event.getMessage()); //Broadcast new message
    }
}

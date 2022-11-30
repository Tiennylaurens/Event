package com.tienny.event.Listeners;

import com.tienny.event.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Main main;

    public ChatListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true); //Cancel normal chat message

        Player player = event.getPlayer();

        Bukkit.broadcastMessage(main.getTeamManager().getTeam(player.getUniqueId()).getDisplay() + " " + player.getName() + ": " + ChatColor.WHITE + event.getMessage()); //Broadcast new message
    }
}

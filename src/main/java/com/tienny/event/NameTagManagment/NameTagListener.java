package com.tienny.event.NameTagManagment;

import com.tienny.event.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NameTagListener implements Listener {

    private Main main;

    public NameTagListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        main.getNameTagManager().setNameTags(player);
        main.getNameTagManager().newTag(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        main.getNameTagManager().removeTag(player);
    }
}

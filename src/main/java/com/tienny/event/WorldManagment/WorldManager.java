package com.tienny.event.WorldManagment;

import com.tienny.event.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldManager {

    public void changeWorld(Player player, Worlds worlds) {

        World oldWorld = player.getWorld();
        player.teleport(worlds.getWorld().getSpawnLocation());
        if (oldWorld.getPlayers().size() == 0) {
            Bukkit.unloadWorld(oldWorld, true);
        }
    }
}

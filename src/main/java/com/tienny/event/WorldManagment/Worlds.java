package com.tienny.event.WorldManagment;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

public enum Worlds {
     LOBBY("Lobby"),
     PARKOUR("Parkour");

     private String world;

     Worlds(String world) {
          this.world = world;
     }

     public World getWorld() {
          return Bukkit.createWorld(new WorldCreator(world));
     }
}

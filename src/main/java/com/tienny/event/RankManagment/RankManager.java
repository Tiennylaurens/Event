package com.tienny.event.RankManagment;

import com.tienny.event.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private Main main;

    private YamlConfiguration config;
    private File file;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager(Main main) {

        this.main = main;

        if (!main.getDataFolder().exists()) { //Create Folder if it doesn't exist yet
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "ranks.yml"); //Set File variable
        if (!file.exists()) { // Create ranksFile if it doesn't exist yet
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file); //Set Config variable
    }

    public void setRank(UUID uuid, Rank rank, boolean firstJoin) { //Set a player's rank
        if(Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid)) {
                attachment = perms.get(uuid);
            } else {
                attachment = player.addAttachment(main);
                perms.put(uuid, attachment);
            }

            for (String perm : getRank(uuid).getPermissions()) {
                if (player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }

            for (String perm : rank.getPermissions()) {
                attachment.setPermission(perm, true);
            }
        }
        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Bukkit.getOfflinePlayer(uuid).isOnline()) { //Update scoreboards if the player is online
            Player player = Bukkit.getPlayer(uuid);
            main.getNameTagManager().removeTag(player);
            main.getNameTagManager().newTag(player);
        }
    }

    public Rank getRank(UUID uuid) { //Get a player's rank
        return Rank.valueOf(config.getString(uuid.toString()));
    }

    public HashMap<UUID, PermissionAttachment> getPerms() { return perms; }
}

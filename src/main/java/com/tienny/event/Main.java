package com.tienny.event;

import com.tienny.event.EventInfoManagment.EventInfoManager;
import com.tienny.event.NameTagManagment.NameTagListener;
import com.tienny.event.NameTagManagment.NameTagManager;
import com.tienny.event.RankManagment.RankCommand;
import com.tienny.event.RankManagment.RankListener;
import com.tienny.event.RankManagment.RankManager;
import com.tienny.event.RankManagment.ReloadCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private RankManager rankManager;
    private EventInfoManager eventInfoManager;
    private NameTagManager nameTagManager;


    @Override
    public void onEnable() { // Plugin startup logic

        //Setup Managers
        rankManager = new RankManager(this);
        eventInfoManager = new EventInfoManager(this);
        nameTagManager = new NameTagManager(this);

        //Setup Listeners
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
        Bukkit.getPluginManager().registerEvents(new NameTagListener(this), this);

        //Setup Commands
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("reloadserver").setExecutor(new ReloadCommand());

        for (Player target : Bukkit.getOnlinePlayers()) {
            nameTagManager.setNameTags(target);
        }
        for (Player target : Bukkit.getOnlinePlayers()) {
            nameTagManager.newTag(target);
        }

        //Update EventInfo
        eventInfoManager.addReload();
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Server reloaded " + ChatColor.GRAY + "[#" + eventInfoManager.getReloads() + " Event: " + eventInfoManager.getVersion() + "].");
            }
        }, 20L);
    }

    @Override
    public void onDisable() { // Plugin shutdown logic

    }

    public RankManager getRankManager() { return rankManager; }
    public NameTagManager getNameTagManager() { return nameTagManager; }
}

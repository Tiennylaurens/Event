package com.tienny.event;

import com.tienny.event.EventInfoManagment.EventInfoManager;
import com.tienny.event.PointsManagment.PointsManager;
import com.tienny.event.RankManagment.Commands.RankCommandTab;
import com.tienny.event.TeamManagment.Commands.ResetTeamsCommand;
import com.tienny.event.TeamManagment.Commands.TeamCommand;
import com.tienny.event.TeamManagment.Commands.TeamCommandTab;
import com.tienny.event.TeamManagment.TeamListener;
import com.tienny.event.ScoreboardManagment.ScoreBoardManager;
import com.tienny.event.RankManagment.Commands.RankCommand;
import com.tienny.event.Listeners.ChatListener;
import com.tienny.event.RankManagment.RankManager;
import com.tienny.event.RankManagment.ReloadCommand;
import com.tienny.event.TeamManagment.TeamManager;
import com.tienny.event.WorldManagment.Commands.WarpCommand;
import com.tienny.event.WorldManagment.Commands.WarpCommandTab;
import com.tienny.event.WorldManagment.WorldManager;
import com.tienny.event.WorldManagment.Worlds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private RankManager rankManager;
    private EventInfoManager eventInfoManager;
    private ScoreBoardManager scoreBoardManager;
    private TeamManager teamManager;
    private PointsManager pointsManager;
    private WorldManager worldManager;

    private boolean eventRunning = false;


    @Override
    public void onEnable() { // Plugin startup logic

        //Setup Managers
        rankManager = new RankManager(this);
        eventInfoManager = new EventInfoManager(this);
        scoreBoardManager = new ScoreBoardManager(this);
        teamManager = new TeamManager(this);
        pointsManager = new PointsManager(this);
        worldManager = new WorldManager();

        //Setup Listeners
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new TeamListener(this), this);

        //Setup Commands
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("reloadserver").setExecutor(new ReloadCommand());
        getCommand("team").setExecutor(new TeamCommand(this));
        getCommand("resetteams").setExecutor(new ResetTeamsCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));

        //Setup TabCompleters
        getCommand("team").setTabCompleter(new TeamCommandTab());
        getCommand("rank").setTabCompleter(new RankCommandTab());
        getCommand("warp").setTabCompleter(new WarpCommandTab());



        //Update EventInfo
        eventInfoManager.addReload();
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Server reloaded " + ChatColor.GRAY + "[#" + eventInfoManager.getReloads() + " Event: " + eventInfoManager.getVersion() + "]");
            }
        }, 20L);
    }

    @Override
    public void onDisable() { // Plugin shutdown logic

    }

    public RankManager getRankManager() { return rankManager; }
    public ScoreBoardManager getScoreBoardManager() { return scoreBoardManager; }
    public TeamManager getTeamManager() { return teamManager; }
    public PointsManager getPointsManager() { return pointsManager; }
    public WorldManager getWorldManager() { return worldManager; }

    public boolean isEventRunning() { return eventRunning; }
}

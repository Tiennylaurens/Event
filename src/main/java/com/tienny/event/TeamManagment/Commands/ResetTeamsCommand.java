package com.tienny.event.TeamManagment.Commands;

import com.tienny.event.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetTeamsCommand implements CommandExecutor {

    private Main main;

    public ResetTeamsCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("team")) {
                main.getTeamManager().resetTeams();
            }

            main.getScoreBoardManager().resetScoreboards();
        }
        return false;
    }
}

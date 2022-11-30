package com.tienny.event.TeamManagment.Commands;

import com.tienny.event.Main;
import com.tienny.event.TeamManagment.TEAM;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TeamCommand implements CommandExecutor {

    private Main main;

    public TeamCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("team")) {
                if (args.length == 2) {
                        if (Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {

                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                            for (TEAM team : TEAM.values()) {
                                if (team.name().equalsIgnoreCase(args[1])) {
                                    main.getTeamManager().removePlayer(target.getUniqueId());
                                    main.getTeamManager().addPlayer(target.getUniqueId(), team);

                                    if (target.isOnline()) {
                                        Player targetPlayer = (Player) target;
                                        main.getScoreBoardManager().removePlayer(targetPlayer);
                                        main.getScoreBoardManager().newPlayer(targetPlayer);
                                    }

                                    if (target.getName().equals(player.getName())) {
                                        player.sendMessage(ChatColor.GREEN + "You changed your team to " + team.getDisplay() + ".");
                                        return false;
                                    }

                                    player.sendMessage(ChatColor.GREEN + "You changed " + target.getName() + "'s team to " + team.getDisplay() + ".");

                                    return false;

                                }
                            }
                            player.sendMessage(ChatColor.RED + "You did not specify a valid team! Options are Red, Green, Blue, Purple, Spectator & Staff");
                        } else {
                            player.sendMessage(ChatColor.RED + "This player doesn't exist!");
                        }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please use /team <player> <team>.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            }
        }
        return false;
    }
}

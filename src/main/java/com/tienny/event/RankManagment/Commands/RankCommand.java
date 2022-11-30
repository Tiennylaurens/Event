package com.tienny.event.RankManagment.Commands;

import com.tienny.event.Main;
import com.tienny.event.RankManagment.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    private Main main;

    public RankCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) { //Check if sender is OP
                if (args.length == 2) { //Check if correct amount of args is used
                    if (Bukkit.getOfflinePlayer(args[0]) != null) { //Check if the first arg is a player

                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        for (Rank rank : Rank.values()) { //Check if a correct rank is used
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                main.getRankManager().setRank(target.getUniqueId(), rank, false);

                                if (target.getUniqueId().equals(player.getUniqueId())) { //Check if target is sender
                                    player.sendMessage(ChatColor.GREEN + "You changed your rank to " + rank.getDisplay() + ".");
                                    return false;
                                }

                                player.sendMessage(ChatColor.GREEN + "You changed " + target.getName() + "'s rank to " + rank.getDisplay() + ".");

                                if (target.isOnline()) {
                                    target.getPlayer().sendMessage(ChatColor.GREEN + player.getName() + " set your rank to " + rank.getDisplay() + ".");
                                }

                                return false;
                            }
                        }
                        player.sendMessage(ChatColor.RED + "You did not specify a valid rank! Options are Owner, Staff, Helper & Player.");
                    } else {
                        player.sendMessage(ChatColor.RED + "This user doesn't exist!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please use /rank <player> <rank>.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must be OP to use this command!");
            }
        }
        return false;
    }
}

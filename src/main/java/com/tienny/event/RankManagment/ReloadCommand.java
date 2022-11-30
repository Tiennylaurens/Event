package com.tienny.event.RankManagment;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.isOp()) {
                Bukkit.reload();
            } else {
                sender.sendMessage(ChatColor.RED + "You need to be OP to use this command!");
            }
        }
        return false;
    }
}

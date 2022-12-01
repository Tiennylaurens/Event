package com.tienny.event.WorldManagment.Commands;

import com.tienny.event.Main;
import com.tienny.event.WorldManagment.Worlds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private Main main;

    public WarpCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("warp")) {
                if (args.length == 1) {
                    for (Worlds world : Worlds.values()) {
                        if (world.name().equalsIgnoreCase(args[0])) {
                            main.getWorldManager().changeWorld(player, world);
                            player.sendMessage(ChatColor.GREEN  + "You warped to " + ChatColor.BOLD +  world.name().toLowerCase());
                            return false;
                        }
                    }

                    player.sendMessage(ChatColor.RED + "Invalid world!");

                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please use /warp <world>");
                }

            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            }
        }
        return false;
    }
}

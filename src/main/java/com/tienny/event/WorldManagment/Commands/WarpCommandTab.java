package com.tienny.event.WorldManagment.Commands;

import com.tienny.event.TeamManagment.TEAM;
import com.tienny.event.WorldManagment.Worlds;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class WarpCommandTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> results = new ArrayList<>();

        for (Worlds worlds : Worlds.values()) {
            results.add(worlds.name().toLowerCase());
        }

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], results, new ArrayList<>());
        }

        return null;
    }
}

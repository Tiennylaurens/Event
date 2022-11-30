package com.tienny.event.TeamManagment.Commands;

import com.tienny.event.TeamManagment.TEAM;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class TeamCommandTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> results = new ArrayList<>();

        for (TEAM team : TEAM.values()) {
            results.add(team.name().toLowerCase());
        }

        if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], results, new ArrayList<>());
        }

        return null;
    }
}

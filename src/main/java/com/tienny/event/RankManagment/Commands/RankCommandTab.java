package com.tienny.event.RankManagment.Commands;

import com.tienny.event.RankManagment.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class RankCommandTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        ArrayList<String> results = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            results.add(rank.name().toLowerCase());
        }

        if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], results, new ArrayList<>());
        }

        return null;
    }


}

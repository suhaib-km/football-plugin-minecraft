/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package org.FBC.teams.commands;

import java.util.ArrayList;
import java.util.List;
import org.FBC.core.Main;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CmdLeaveTeam {
    public CmdLeaveTeam(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("leave")) {
            return;
        }
        if (!player.hasMetadata("team")) {
            teamMethods.sendMessage(player, "You are not in a team.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        String partyName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
        Team team = teamMethods.loadTeam(partyName);
        if (player.hasMetadata("isManager")) {
            teamMethods.disbandTeam(team);
            teamMethods.saveTeam(team);
            player.removeMetadata("team", (Plugin)teamMethods.getPlugin());
            player.removeMetadata("isManager", (Plugin)teamMethods.getPlugin());
            return;
        }
        player.removeMetadata("team", (Plugin)teamMethods.getPlugin());
        team.captain.remove(player.getName());
        team.roster.remove(player.getName());
        team.online.remove((Object)player);
        teamMethods.removePlayer(player.getName());
        team.Announcement((Object)ChatColor.GRAY + String.valueOf(player.getDisplayName()) + (Object)ChatColor.GRAY + " left the " + "team" + ".");
        teamMethods.sendMessage(player, "You left the team \"" + team.name + "\".");
        teamMethods.savePlayer(player);
        teamMethods.saveTeam(team);
    }
}


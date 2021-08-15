/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package org.FBC.teams.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import org.FBC.core.Main;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CmdCreateTeam {
    public CmdCreateTeam(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("create")) {
            return;
        }
        if (player.hasMetadata("team")) {
            teamMethods.sendMessage(player, "You are already in a team.");
            return;
        }
        if (args.length != 2) {
            teamMethods.sendMessage(player, "Usage: /team create <name>");
            return;
        }
        String teamName = args[1];
        if (teamName.length() > 15) {
            teamMethods.sendMessage(player, "This name is too long! (3-15 letters)");
            return;
        }
        if (teamName.length() < 3) {
            teamMethods.sendMessage(player, "This name is too short! (3-15 letters)");
            return;
        }
        if (teamMethods.loadTeam(teamName) != null) {
            teamMethods.sendMessage(player, "The team \"" + teamName + "\" already exists. Please choose a different name.");
            return;
        }
        Team team = new Team(teamName, teamMethods.getPlugin());
        team.manager = player.getName();
        team.online.add(player);
        player.setMetadata("team", (MetadataValue)new FixedMetadataValue((Plugin)teamMethods.getPlugin(), (Object)team.name));
        player.setMetadata("isManager", (MetadataValue)new FixedMetadataValue((Plugin)teamMethods.getPlugin(), (Object)true));
        teamMethods.getPlugin().onlineTeams.put(teamName, team);
        teamMethods.savePlayer(player);
        teamMethods.saveTeam(team);
        player.sendMessage((Object)ChatColor.DARK_GRAY + "(" + (Object)ChatColor.GOLD + "FBC" + (Object)ChatColor.DARK_GRAY + ") " + (Object)ChatColor.GRAY + "You created the team \"" + team.name + "\".");
        player.sendMessage((Object)ChatColor.GRAY + "Invite players with /team invite <player>");
        player.sendMessage((Object)ChatColor.GRAY + "Send a message to your team with /t <message>");
        teamMethods.getPlugin().getLogger().info("Created the chat team \"" + team.name + "\".");
    }
}


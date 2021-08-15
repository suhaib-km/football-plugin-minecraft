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
import java.util.List;
import org.FBC.core.Main;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CmdJoinTeam {
    public CmdJoinTeam(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("join")) {
            return;
        }
        if (!player.hasMetadata("partyInvitation")) {
            teamMethods.sendMessage(player, "No active team invitation.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        String partyName = ((MetadataValue)player.getMetadata("partyInvitation").get(0)).asString();
        Team team = teamMethods.loadTeam(partyName);
        if (team == null) {
            teamMethods.sendMessage(player, "No active team invitation.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        player.removeMetadata("partyInvitation", (Plugin)teamMethods.getPlugin());
        team.Announcement((Object)ChatColor.GRAY + String.valueOf(player.getDisplayName()) + (Object)ChatColor.GRAY + " joined the team.");
        team.roster.add(player.getName());
        team.online.add(player);
        player.setMetadata("team", (MetadataValue)new FixedMetadataValue((Plugin)teamMethods.getPlugin(), (Object)team.name));
        teamMethods.sendMessage(player, "You joined the team \"" + team.name + "\".");
        player.sendMessage((Object)ChatColor.GRAY + "Chat with /t <message>");
        teamMethods.savePlayer(player);
        teamMethods.saveTeam(team);
    }
}


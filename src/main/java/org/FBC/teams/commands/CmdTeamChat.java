/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.MetadataValue
 */
package org.FBC.teams.commands;

import java.util.List;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class CmdTeamChat {
    public CmdTeamChat(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (!player.hasMetadata("team")) {
            teamMethods.sendMessage(player, "You are not in a team.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        if (args.length == 0) {
            teamMethods.sendMessage(player, "Use /t <text> to send a message to your team.");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (String word : args) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(word);
        }
        String message = builder.toString();
        String partyName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
        Team team = teamMethods.loadTeam(partyName);
        team.PlayerMessage(player, message);
    }
}


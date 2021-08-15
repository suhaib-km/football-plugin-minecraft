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

import java.util.ArrayList;
import java.util.List;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class CmdGetMembers {
    public CmdGetMembers(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (args[0].equalsIgnoreCase("roster")) {
            if (!player.hasMetadata("team")) {
                teamMethods.sendMessage(player, "You are not in a party.");
                player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
                return;
            }
            String partyName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
            Team team = teamMethods.loadTeam(partyName);
            String sep = ", ";
            StringBuilder builder = new StringBuilder();
            for (String name : team.captain) {
                if (builder.length() > 0) {
                    builder.append(sep);
                }
                builder.append(name);
            }
            String captains = builder.toString();
            builder = new StringBuilder();
            for (String name : team.roster) {
                if (builder.length() > 0) {
                    builder.append(sep);
                }
                builder.append(name);
            }
            String members = builder.toString();
            player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)("&8&m--------------------&8(" + (Object)ChatColor.GOLD + "Team Roster" + "&8)&8&m--------------------")));
            player.sendMessage((Object)ChatColor.GOLD + "Manager" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + team.manager);
            player.sendMessage((Object)ChatColor.GOLD + "Captains" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + captains);
            player.sendMessage((Object)ChatColor.GOLD + "Roster" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + members);
            player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8&m-----------------------------------------------------"));
            return;
        }
    }
}


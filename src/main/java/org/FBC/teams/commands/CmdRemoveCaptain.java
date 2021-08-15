/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Server
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
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CmdRemoveCaptain {
    public CmdRemoveCaptain(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("demote")) {
            return;
        }
        if (!player.hasMetadata("team")) {
            teamMethods.sendMessage(player, "You are not in a team.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        if (!player.hasMetadata("isManager")) {
            teamMethods.sendMessage(player, "Only managers can demote other players.");
            return;
        }
        if (args.length != 2) {
            teamMethods.sendMessage(player, "Usage: /team demote <player>");
            return;
        }
        String playerName = args[1];
        OfflinePlayer demotedPlayer = teamMethods.getPlugin().getServer().getOfflinePlayer(playerName);
        String partyName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
        Team team = teamMethods.loadTeam(partyName);
        if (team.roster.contains(demotedPlayer.getName())) {
            teamMethods.sendMessage(player, "The player cannot be demoted more.");
            return;
        }
        if (team.manager == demotedPlayer.getName()) {
            teamMethods.sendMessage(player, "Managers cannot be demoted.");
            return;
        }
        if (!team.captain.contains(demotedPlayer.getName())) {
            teamMethods.sendMessage(player, "The player is not a member of your team.");
            return;
        }
        team.roster.add(demotedPlayer.getName());
        team.captain.remove(demotedPlayer.getName());
        team.Announcement(String.valueOf(demotedPlayer.getName()) + (Object)ChatColor.GRAY + " has been demoted from a captain.");
        Player onlinePlayer = teamMethods.getPlugin().getServer().getPlayer(playerName);
        if (onlinePlayer != null && onlinePlayer.isOnline()) {
            onlinePlayer.removeMetadata("isCaptain", (Plugin)teamMethods.getPlugin());
        }
    }
}


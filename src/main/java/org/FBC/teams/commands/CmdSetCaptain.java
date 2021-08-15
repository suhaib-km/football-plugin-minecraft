/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Server
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
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CmdSetCaptain {
    public CmdSetCaptain(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("captain")) {
            return;
        }
        if (!player.hasMetadata("team")) {
            teamMethods.sendMessage(player, "You are not in a team.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        if (!player.hasMetadata("isManager")) {
            teamMethods.sendMessage(player, "Only managers can promote other players.");
            return;
        }
        if (args.length != 2) {
            teamMethods.sendMessage(player, "Usage: /team captain <player>");
            return;
        }
        String playerName = args[1];
        OfflinePlayer promotedPlayer = teamMethods.getPlugin().getServer().getOfflinePlayer(playerName);
        String partyName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
        Team team = teamMethods.loadTeam(partyName);
        if (team.captain.contains(promotedPlayer.getName())) {
            teamMethods.sendMessage(player, "The player is already a captain.");
            return;
        }
        if (!team.roster.contains(promotedPlayer.getName())) {
            teamMethods.sendMessage(player, "The player is not a member of your team.");
            return;
        }
        team.roster.remove(promotedPlayer.getName());
        team.captain.add(promotedPlayer.getName());
        Player onlinePlayer = teamMethods.getPlugin().getServer().getPlayer(playerName);
        if (onlinePlayer != null && onlinePlayer.isOnline()) {
            onlinePlayer.setMetadata("isCaptain", (MetadataValue)new FixedMetadataValue((Plugin)teamMethods.getPlugin(), (Object)true));
        }
        teamMethods.saveTeam(team);
        team.Announcement((Object)ChatColor.GRAY + String.valueOf(promotedPlayer.getName()) + (Object)ChatColor.GRAY + " is now a captain of the team.");
    }
}


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

public class CmdKick {
    public CmdKick(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("kick")) {
            return;
        }
        if (!player.hasMetadata("team")) {
            teamMethods.sendMessage(player, "You are not in a team.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        if (!player.hasMetadata("isManager") && !player.hasMetadata("isCaptain")) {
            teamMethods.sendMessage(player, "Only team managers or captains can kick other players.");
            return;
        }
        if (args.length != 2) {
            teamMethods.sendMessage(player, "Usage: /team kick <player>");
            return;
        }
        String playerName = args[1];
        OfflinePlayer kickedPlayer = teamMethods.getPlugin().getServer().getOfflinePlayer(playerName);
        String partyName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
        Team team = teamMethods.loadTeam(partyName);
        if (team.captain.contains(kickedPlayer.getName())) {
            teamMethods.sendMessage(player, "You can't kick captains.");
            return;
        }
        if (team.manager == kickedPlayer.getName()) {
            teamMethods.sendMessage(player, "You can't kick managers.");
            return;
        }
        if (!team.roster.contains(kickedPlayer.getName())) {
            teamMethods.sendMessage(player, "The player is not a member of your team.");
            return;
        }
        team.roster.remove(kickedPlayer.getName());
        Player onlinePlayer = teamMethods.getPlugin().getServer().getPlayer(playerName);
        if (onlinePlayer != null) {
            onlinePlayer.removeMetadata("team", (Plugin)teamMethods.getPlugin());
            team.online.remove((Object)onlinePlayer);
            teamMethods.sendMessage(onlinePlayer, "You were kicked from the team \"" + team.name + "\".");
        }
        teamMethods.removePlayer(kickedPlayer.getName());
        teamMethods.saveTeam(team);
        team.Announcement(String.valueOf(String.valueOf(kickedPlayer.getName())) + " was kicked from the " + "team" + ".");
    }
}


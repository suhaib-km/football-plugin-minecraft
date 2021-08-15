/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Server
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package org.FBC.teams.commands;

import java.util.List;
import net.md_5.bungee.api.ChatColor;
import org.FBC.core.Main;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class CmdInvite {
    public CmdInvite(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length == 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("invite")) {
            return;
        }
        if (!player.hasMetadata("team")) {
            teamMethods.sendMessage(player, "You are not in a team.");
            player.sendMessage((Object)ChatColor.GRAY + "Create your own team with /team create <name>.");
            return;
        }
        if (!player.hasMetadata("isManager")) {
            teamMethods.sendMessage(player, "Only managers or captains can invite other players.");
            return;
        }
        if (args.length != 2) {
            teamMethods.sendMessage(player, "Usage: /team invite <player>");
            return;
        }
        String playerName = args[1];
        Player invitedPlayer = teamMethods.getPlugin().getServer().getPlayer(playerName);
        if (invitedPlayer == null || !invitedPlayer.isOnline()) {
            teamMethods.sendMessage(player, "You can only invite online players.");
            return;
        }
        if (invitedPlayer.hasMetadata("team")) {
            teamMethods.sendMessage(player, "The player is already in a team.");
            return;
        }
        String partyName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
        Team team = teamMethods.loadTeam(partyName);
        invitedPlayer.setMetadata("partyInvitation", (MetadataValue)new FixedMetadataValue((Plugin)teamMethods.getPlugin(), (Object)team.name));
        teamMethods.sendMessage(player, "You invited " + invitedPlayer.getName() + " to your team.");
        teamMethods.sendMessage(invitedPlayer, String.valueOf(String.valueOf(player.getName())) + " invited you to the team" + " \"" + team.name + "\".");
        teamMethods.sendNonPrefixMessage(invitedPlayer, (Object)ChatColor.GRAY + "To accept the invitation, type /team join");
    }
}


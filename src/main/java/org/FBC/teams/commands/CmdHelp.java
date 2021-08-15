/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package org.FBC.teams.commands;

import org.FBC.teams.TeamMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHelp {
    public CmdHelp(CommandSender sender, Player player, TeamMethods teamMethods, String[] args) {
        if (args.length != 0 && !args[0].equalsIgnoreCase("help")) {
            return;
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)("&8&m--------------&8(" + (Object)ChatColor.GOLD + "Teams Help" + "&8)&8&m--------------")));
        if (!player.hasMetadata("team")) {
            player.sendMessage((Object)ChatColor.GOLD + "/team join" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Accept a team invitation");
            player.sendMessage((Object)ChatColor.GOLD + "/team create <name>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Create a new team");
            return;
        }
        player.sendMessage((Object)ChatColor.GOLD + "/t <message>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Send a message to your team");
        player.sendMessage((Object)ChatColor.GOLD + "/team leave" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Leave your team");
        player.sendMessage((Object)ChatColor.GOLD + "/team roster" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Show your team roster");
        if (player.hasMetadata("isCaptain")) {
            player.sendMessage((Object)ChatColor.GOLD + "/team invite <player>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Invite a player to your team");
            player.sendMessage((Object)ChatColor.GOLD + "/team kick <player>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Kick a player from your team");
        }
        if (player.hasMetadata("isManager")) {
            player.sendMessage((Object)ChatColor.GOLD + "/team invite <player>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Invite a player to your team");
            player.sendMessage((Object)ChatColor.GOLD + "/team kick <player>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Kick a player from your team");
            player.sendMessage((Object)ChatColor.GOLD + "/team captain <player>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Add a captain to your team");
            player.sendMessage((Object)ChatColor.GOLD + "/team demote <player>" + (Object)ChatColor.DARK_GRAY + ": " + (Object)ChatColor.GRAY + "Demote a captain");
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)"&8&m----------------------------------------"));
    }
}


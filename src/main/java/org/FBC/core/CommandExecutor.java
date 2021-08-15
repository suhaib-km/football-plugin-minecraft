/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package org.FBC.core;

import org.FBC.ball.commands.CmdSpawnBall;
import org.FBC.teams.TeamMethods;
import org.FBC.teams.commands.CmdCreateTeam;
import org.FBC.teams.commands.CmdGetMembers;
import org.FBC.teams.commands.CmdHelp;
import org.FBC.teams.commands.CmdInvite;
import org.FBC.teams.commands.CmdJoinTeam;
import org.FBC.teams.commands.CmdKick;
import org.FBC.teams.commands.CmdLeaveTeam;
import org.FBC.teams.commands.CmdRemoveCaptain;
import org.FBC.teams.commands.CmdSetCaptain;
import org.FBC.teams.commands.CmdTeamChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandExecutor
implements org.bukkit.command.CommandExecutor {
    private TeamMethods teamMethods;
    private Plugin main;

    public CommandExecutor(TeamMethods teamMethods, Plugin main) {
        this.teamMethods = teamMethods;
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Player player = null;
        if (!(sender instanceof Player)) {
            return false;
        }
        player = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("t")) {
            new org.FBC.teams.commands.CmdTeamChat(sender, player, this.teamMethods, args);
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("team")) {
            new org.FBC.teams.commands.CmdHelp(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdJoinTeam(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdLeaveTeam(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdInvite(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdCreateTeam(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdSetCaptain(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdRemoveCaptain(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdKick(sender, player, this.teamMethods, args);
            new org.FBC.teams.commands.CmdGetMembers(sender, player, this.teamMethods, args);
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("fb")) {
            new org.FBC.ball.commands.CmdSpawnBall(player, args, this.main);
            return true;
        }
        return false;
    }
}


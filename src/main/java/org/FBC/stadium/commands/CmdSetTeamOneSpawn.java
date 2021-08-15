/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 */
package org.FBC.stadium.commands;

import org.FBC.Utils.LocationToString;
import org.FBC.core.Main;
import org.FBC.stadium.CheckStadiumExists;
import org.FBC.stadium.SetTeamOneSpawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CmdSetTeamOneSpawn {
    private Main plugin;

    public CmdSetTeamOneSpawn(Player player, String[] args, Main main) {
        this.plugin = main;
        if (args.length <= 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("stadium")) {
            return;
        }
        if (args.length <= 1) {
            return;
        }
        if (!args[1].equalsIgnoreCase("red")) {
            return;
        }
        String StadName = String.valueOf(args[2]).toUpperCase();
        boolean exists = new CheckStadiumExists().Check(StadName, this.plugin);
        if (!exists) {
            player.sendMessage("Sorry a stadium with that name does not exist.");
            return;
        }
        String loc = new LocationToString().execute(player.getLocation());
        new org.FBC.stadium.SetTeamOneSpawn(StadName, loc, this.plugin);
    }
}


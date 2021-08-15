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
import org.FBC.stadium.SetBallSpawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CmdSetBallSpawn {
    private Main plugin;

    public CmdSetBallSpawn(Player player, String[] args, Main main) {
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
        if (!args[1].equalsIgnoreCase("ball")) {
            return;
        }
        String StadName = String.valueOf(args[2]).toUpperCase();
        boolean exists = new CheckStadiumExists().Check(StadName, this.plugin);
        if (!exists) {
            player.sendMessage("Sorry a stadium with that name does not exist.");
            return;
        }
        String loc = new LocationToString().execute(player.getLocation());
        new org.FBC.stadium.SetBallSpawn(StadName, loc, this.plugin);
        player.sendMessage(String.format("The ball spawn for the stadium {0} has been set.", StadName));
    }
}


/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package org.FBC.stadium.commands;

import org.FBC.core.Main;
import org.FBC.stadium.CheckStadiumExists;
import org.FBC.stadium.StadiumCreate;
import org.bukkit.entity.Player;

public class CmdCreateStadium {
    private String type;
    Boolean ranked;

    public CmdCreateStadium(Player player, String[] args, Main p) {
        boolean exists;
        if (args.length <= 0) {
            return;
        }
        if (!args[0].equalsIgnoreCase("stadium")) {
            return;
        }
        if (args.length <= 1) {
            return;
        }
        if (!args[1].equalsIgnoreCase("create")) {
            return;
        }
        this.type = args[2];
        if (this.type != "unranked" && this.type != "ranked") {
            player.sendMessage("usage: /fb stadium create (ranked/unranked) (name)");
            return;
        }
        this.ranked = this.type == "ranked" ? Boolean.valueOf(true) : Boolean.valueOf(false);
        String StadName = String.valueOf(args[3]).toUpperCase();
        if (StadName.length() >= 13) {
            player.sendMessage("The stadium name cannot be more than 12 characters long");
        }
        if (exists = new CheckStadiumExists().Check(StadName, p)) {
            player.sendMessage("We're sorry but that arena already exists. Try a different name!");
        } else {
            new org.FBC.stadium.StadiumCreate(StadName, this.ranked, p);
            player.sendMessage(String.format("The stadium {0} has been created.", StadName));
        }
    }
}


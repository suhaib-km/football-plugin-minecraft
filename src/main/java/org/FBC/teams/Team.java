/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package org.FBC.teams;

import java.util.ArrayList;
import org.FBC.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team {
    public String name;
    public String tag;
    public String manager;
    public ArrayList<String> roster;
    public ArrayList<String> captain;
    public ArrayList<Player> online;

    public Team(String Name, Main p) {
        this.name = Name;
        this.tag = this.name.substring(0, 3);
        this.roster = new ArrayList();
        this.captain = new ArrayList();
        this.online = new ArrayList();
    }

    public void PlayerMessage(Player sender, String text) {
        String format = (Object)ChatColor.DARK_GRAY + "(" + (Object)ChatColor.YELLOW + "Team" + (Object)ChatColor.DARK_GRAY + ") " + (Object)ChatColor.YELLOW + sender.getDisplayName() + (Object)ChatColor.GRAY + ": " + (Object)ChatColor.YELLOW;
        for (Player player : this.online) {
            player.sendMessage(String.valueOf(format) + text);
        }
    }

    public void Announcement(String text) {
        for (Player player : this.online) {
            player.sendMessage((Object)ChatColor.DARK_GRAY + "(" + (Object)ChatColor.GOLD + "FBC" + (Object)ChatColor.DARK_GRAY + ") " + text);
        }
    }
}


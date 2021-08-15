/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Server
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package org.FBC.teams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import org.FBC.core.Main;
import org.FBC.teams.Team;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class TeamMethods {
    private Main plugin;
    private String prefixFormat = (Object)ChatColor.DARK_GRAY + "(" + (Object)ChatColor.GOLD + "FBC" + (Object)ChatColor.DARK_GRAY + ") ";

    public TeamMethods(Main p) {
        this.plugin = p;
    }

    public Main getPlugin() {
        return this.plugin;
    }

    public String getprefix() {
        return this.prefixFormat;
    }

    public void saveTeam(Team team) {
        ConfigurationSection teamSection = this.plugin.getConfig().getConfigurationSection("teams").createSection(team.name);
        teamSection.set("manager", (Object)team.manager);
        teamSection.set("captains", team.captain);
        teamSection.set("roster", team.roster);
        this.plugin.saveConfig();
        this.plugin.reloadConfig();
    }

    public Team getPlayerTeam(Player player) {
        String teamName = this.plugin.getConfig().getConfigurationSection("players").getString(player.getName());
        if (teamName != null) {
            return this.loadTeam(teamName);
        }
        return null;
    }

    public Team loadTeam(String name) {
        Team team = this.plugin.onlineTeams.get(name);
        if (team == null) {
            team = new Team(name, this.plugin);
            ConfigurationSection teamSection = this.plugin.getConfig().getConfigurationSection("teams." + name);
            if (teamSection == null || teamSection.getStringList("manager").size() == 0) {
                this.plugin.getLogger().warning(String.valueOf(name) + " has had an error being loaded. Contact lrg at your convenience tyvm.");
                return null;
            }
            team.manager = teamSection.getString("manager");
            team.captain = (ArrayList)teamSection.getStringList("captains");
            team.roster = (ArrayList)teamSection.getStringList("roster");
            ArrayList arrplayer = new ArrayList(this.plugin.getServer().getOnlinePlayers());
            int n = arrplayer.size();
            for (int n2 = 0; n2 < n; ++n2) {
                Player player = (Player)arrplayer.get(n2);
                if (!team.captain.contains(player.getName()) && !team.roster.contains(player.getName()) && team.manager != player.getName()) continue;
                team.online.add(player);
            }
        }
        return team;
    }

    public void disbandTeam(Team team) {
        team.Announcement((Object)ChatColor.GRAY + "This team has been disbanded because the manager left.");
        for (String playerName : team.roster) {
            this.removePlayer(playerName);
        }
        for (String playerName : team.captain) {
            this.removePlayer(playerName);
        }
        this.removePlayer(team.manager);
        for (Player player : team.online) {
            player.removeMetadata("team", (Plugin)this.plugin);
            player.removeMetadata("isManager", (Plugin)this.plugin);
            player.removeMetadata("isCaptain", (Plugin)this.plugin);
        }
        this.plugin.onlineTeams.remove(team.name);
        team.captain = null;
        team.roster = null;
        team.manager = null;
        this.plugin.saveConfig();
        this.plugin.getLogger().info("Disbanded the chat team \"" + team.name + "\".");
    }

    public void savePlayer(Player player) {
        ConfigurationSection playerSection = this.plugin.getConfig().getConfigurationSection("players");
        if (!player.hasMetadata("team")) {
            playerSection.set(player.getName(), null);
        } else {
            String teamName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
            playerSection.set(player.getName(), (Object)teamName);
        }
        this.plugin.saveConfig();
    }

    public void removePlayer(String playerName) {
        ConfigurationSection playerSection = this.plugin.getConfig().getConfigurationSection("players");
        playerSection.set(playerName, null);
        this.plugin.saveConfig();
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage((Object)ChatColor.DARK_GRAY + "(" + (Object)ChatColor.GOLD + "FBC" + (Object)ChatColor.DARK_GRAY + ") " + (Object)ChatColor.GRAY + message);
    }

    public void sendNonPrefixMessage(Player player, String message) {
        player.sendMessage(message);
    }
}


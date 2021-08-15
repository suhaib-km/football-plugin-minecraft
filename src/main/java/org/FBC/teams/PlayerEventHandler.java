/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package org.FBC.teams;

import java.util.ArrayList;
import java.util.List;
import org.FBC.core.Main;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class PlayerEventHandler
implements Listener {
    private Main plugin;
    private TeamMethods teamMethods;

    public PlayerEventHandler(Main plugin, TeamMethods teamMethods) {
        this.plugin = plugin;
        this.teamMethods = teamMethods;
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.getPlayer().sendMessage("test 1");
        Team team = this.teamMethods.getPlayerTeam(player);
        if (team == null) {
            player.removeMetadata("team", (Plugin)this.plugin);
            return;
        }
        event.getPlayer().sendMessage("test 2");
        player.setMetadata("team", (MetadataValue)new FixedMetadataValue((Plugin)this.plugin, (Object)team.name));
        if (team.captain.contains(player.getName())) {
            player.setMetadata("isCaptain", (MetadataValue)new FixedMetadataValue((Plugin)this.plugin, (Object)true));
        } else {
            player.removeMetadata("isCaptain", (Plugin)this.plugin);
        }
        event.getPlayer().sendMessage("test 3");
        if (team.manager == player.getName()) {
            player.setMetadata("isManager", (MetadataValue)new FixedMetadataValue((Plugin)this.plugin, (Object)true));
        } else {
            player.removeMetadata("isManager", (Plugin)this.plugin);
        }
        event.getPlayer().sendMessage("test 4");
        team.online.add(player);
        event.getPlayer().sendMessage("test 5");
    }

    @EventHandler
    void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasMetadata("team")) {
            String teamName = ((MetadataValue)player.getMetadata("team").get(0)).asString();
            Team team = this.teamMethods.loadTeam(teamName);
            team.online.remove((Object)player);
            player.removeMetadata("team", (Plugin)this.plugin);
            player.removeMetadata("isManager", (Plugin)this.plugin);
            player.removeMetadata("isCaptain", (Plugin)this.plugin);
        }
    }
}


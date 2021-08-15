/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Server
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.FileConfigurationOptions
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.scheduler.BukkitScheduler
 */
package org.FBC.core;

import java.util.HashMap;
import org.FBC.ball.BallHandler;
import org.FBC.ball.BallRunnable;
import org.FBC.ball.events.BallDespawn;
import org.FBC.ball.events.PickUpBall;
import org.FBC.ball.events.PlayerKick;
import org.FBC.core.CommandExecutor;
import org.FBC.teams.PlayerEventHandler;
import org.FBC.teams.Team;
import org.FBC.teams.TeamMethods;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main
extends JavaPlugin {
    public HashMap<String, Team> onlineTeams;
    private TeamMethods teamMethods;
    public static Main plugin;
    public BallHandler ballhandler = new BallHandler();

    public void onEnable() {
        plugin = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.onlineTeams = new HashMap();
        this.setupCommandExecutor();
        this.setupEvents();
        this.setupRunnables();
    }

    public void onDisable() {
    }

    public void setupCommandExecutor() {
        this.teamMethods = new TeamMethods(this);
        CommandExecutor commandExecutor = new CommandExecutor(this.teamMethods, (Plugin)this);
        this.getCommand("team").setExecutor((org.bukkit.command.CommandExecutor)commandExecutor);
        this.getCommand("t").setExecutor((org.bukkit.command.CommandExecutor)commandExecutor);
        this.getCommand("fb").setExecutor((org.bukkit.command.CommandExecutor)commandExecutor);
    }

    public void setupRunnables() {
        this.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new BallRunnable(), 1L, 1L);
    }

    public void setupEvents() {
        this.registerEvents((Plugin)this, new PlayerEventHandler(this, this.teamMethods));
        this.registerEvents((Plugin)this, new PickUpBall());
        this.registerEvents((Plugin)this, new BallDespawn());
        this.registerEvents((Plugin)this, new PlayerKick());
    }

    public /* varargs */ void registerEvents(Plugin plugin, Listener ... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }
}


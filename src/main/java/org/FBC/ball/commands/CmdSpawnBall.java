/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package org.FBC.ball.commands;

import org.FBC.ball.Ball;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CmdSpawnBall {
    public CmdSpawnBall(Player player, String[] args, Plugin plugin) {
        if (args.length < 1) {
            return;
        }
        if (args.length > 1) {
            player.sendMessage("Correct usage is ./fb spawnball");
            return;
        }
        if (!args[0].equalsIgnoreCase("spawnball")) {
            return;
        }
        String world = player.getLocation().getWorld().getName();
        String trainingworlds = plugin.getConfig().getString("trainingworld");
        if (trainingworlds == null) {
            player.sendMessage("No training world set.");
        }
        if (!(player.isOp() && trainingworlds == null || trainingworlds.contains(world))) {
            player.sendMessage("You are not in the training world.");
        }
        Location loc = player.getLocation();
        new Ball().spawnBall(loc);
        player.sendMessage("Ball spawned at your location.");
    }
}


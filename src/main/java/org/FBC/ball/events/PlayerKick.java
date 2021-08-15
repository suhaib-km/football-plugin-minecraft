/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerAnimationEvent
 */
package org.FBC.ball.events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.FBC.ball.Ball;
import org.FBC.ball.BallHandler;
import org.FBC.core.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

public class PlayerKick
implements Listener {
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerAnimation(PlayerAnimationEvent event) {
        Player player = event.getPlayer();
        List ent = player.getNearbyEntities(1.0, 1.0, 1.0);
        for (Entity entity : ent) {
            if (!(entity instanceof Item)) continue;
            UUID id = entity.getUniqueId();
            if (!Main.plugin.ballhandler.getBallIDs().contains(id)) continue;
            Main.plugin.ballhandler.getItemBall().get(id).doKick(player);
            return;
        }
    }
}


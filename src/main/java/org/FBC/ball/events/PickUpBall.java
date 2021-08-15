/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Item
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerPickupItemEvent
 */
package org.FBC.ball.events;

import java.util.HashSet;
import java.util.UUID;
import org.FBC.ball.BallHandler;
import org.FBC.core.Main;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickUpBall
implements Listener {
    @EventHandler
    public void onArenaPlayerPickupItem(PlayerPickupItemEvent event) {
        Item item = event.getItem();
        UUID itemID = item.getUniqueId();
        if (Main.plugin.ballhandler.getItemBalls() != null && Main.plugin.ballhandler.getBallIDs().contains(itemID)) {
            if (event.isCancelled()) {
                return;
            }
            event.setCancelled(true);
        }
    }
}


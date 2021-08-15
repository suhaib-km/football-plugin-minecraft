/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Item
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.ItemDespawnEvent
 */
package org.FBC.ball.events;

import java.util.HashSet;
import org.FBC.ball.BallHandler;
import org.FBC.core.Main;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;

public class BallDespawn
implements Listener {
    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        BallHandler BH = Main.plugin.ballhandler;
        if (!BH.getItemBalls().isEmpty()) {
            Item item = event.getEntity();
            if (BH.getItemBalls().contains((Object)item)) {
                event.setCancelled(true);
            }
        }
    }
}


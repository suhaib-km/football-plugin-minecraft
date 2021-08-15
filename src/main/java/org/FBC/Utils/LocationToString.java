/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 */
package org.FBC.Utils;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationToString {
    public String execute(Location loc) {
        String world = loc.getWorld().getName();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        double yaw = loc.getYaw();
        double pitch = loc.getPitch();
        return String.format("{0},{1},{2},{3},{4},{5}", world, x, y, z, yaw, pitch);
    }
}


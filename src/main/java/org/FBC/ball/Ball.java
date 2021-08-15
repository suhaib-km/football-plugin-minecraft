/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.SkullMeta
 *  org.bukkit.util.Vector
 */
package org.FBC.ball;

import java.util.HashSet;
import java.util.UUID;
import org.FBC.ball.BallHandler;
import org.FBC.core.Main;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

public class Ball {
    protected Item item;
    protected UUID id;
    protected ItemStack BallItemStack = new ItemStack(Material.SKULL_ITEM, 1, 3);
    private Vector vectors;
    private Location lastKickedFrom;
    private Player lastKickedBy;
    private String particle = "instant_spell";

    public void doPhysics() {
        if (!this.getItem().isDead()) {
            Vector newVector;
            Vector velocity = this.item.getVelocity();
            if (this.vectors != null) {
                velocity = this.vectors;
            }
            if ((newVector = this.item.getVelocity()).getX() == 0.0) {
                newVector.setX((- velocity.getX()) * 0.9);
            } else if (Math.abs(velocity.getX() - newVector.getX()) < 0.15) {
                newVector.setX(velocity.getX() * 0.975);
            }
            if (newVector.getY() == 0.0 && velocity.getY() < -0.1) {
                newVector.setY((- velocity.getY()) * 0.9);
            }
            if (newVector.getZ() == 0.0) {
                newVector.setZ((- velocity.getZ()) * 0.9);
            } else if (Math.abs(velocity.getZ() - newVector.getZ()) < 0.15) {
                newVector.setZ(velocity.getZ() * 0.975);
            }
            this.vectors = newVector;
            this.item.setVelocity(newVector);
            this.showEffect((Entity)this.item);
        }
    }

    public void doKick(Player player) {
        Location location = player.getLocation();
        World world = player.getWorld();
        Vector kV = this.kickVector(player);
        this.item.setVelocity(kV);
        this.vectors = kV;
        this.lastKickedFrom = player.getLocation();
        world.playEffect(location, Effect.STEP_SOUND, 10);
        this.lastKickedBy = player;
    }

    public Ball spawnBall(Location loc) {
        World world = loc.getWorld();
        if (!this.BallItemStack.getType().equals((Object)Material.AIR)) {
            this.setHead();
            this.item = world.dropItem(loc, this.BallItemStack);
        } else {
            this.item = world.dropItem(loc, new ItemStack(Material.MAGMA_CREAM));
        }
        this.item.setVelocity(new Vector(0, 0, 0));
        this.id = this.item.getUniqueId();
        Main.plugin.ballhandler.balls.add(this);
        Main.plugin.ballhandler.addBall(this);
        Main.plugin.ballhandler.addBallItem(this.item);
        Main.plugin.ballhandler.addBallIds(this.id);
        Main.plugin.ballhandler.putBallID(this.id, this);
        return this;
    }

    public Vector kickVector(Player player) {
        double configPower = 1.55;
        float adjPitch = -15.0f;
        float maxPitch = -90.0f;
        Location loc = player.getEyeLocation();
        float pitch = loc.getPitch();
        if ((pitch += adjPitch) > 0.0f) {
            pitch = 0.0f;
        }
        if (pitch < maxPitch) {
            pitch = 0.0f + maxPitch;
        }
        loc.setPitch(pitch);
        Vector vector = loc.getDirection();
        vector = vector.multiply(configPower);
        return vector;
    }

    private void setHead() {
        SkullMeta head = (SkullMeta)this.BallItemStack.getItemMeta();
        head.setOwner("Porkzingis");
        this.BallItemStack.setItemMeta((ItemMeta)head);
    }

    public void showEffect(Entity entity) {
        Location location = entity.getLocation();
        World world = entity.getWorld();
        if (!entity.isDead()) {
            Effect effect = this.getEightEffect();
            if (effect != null) {
                world.playEffect(location, effect, 1);
            } else {
                world.playEffect(location, Effect.INSTANT_SPELL, 1);
            }
        }
    }

    public Effect getEightEffect() {
        Effect effect2;
        for (Effect effect2 : Effect.values()) {
            if (!effect2.toString().equals(this.particle)) continue;
            return effect2;
        }
        effect2 = Effect.INSTANT_SPELL;
        return effect2;
    }

    public Item getItem() {
        return this.item;
    }

    public ItemStack getItemStack() {
        return this.BallItemStack;
    }

    public void setItemStack(ItemStack item) {
        this.BallItemStack = item;
    }

    public String getParticle() {
        return this.particle;
    }

    public void setParticle(String particle) {
        this.particle = particle;
    }

    public Player getLastKickedBy() {
        return this.lastKickedBy;
    }

    public void setLastKickedBy(Player lastKickedBy) {
        this.lastKickedBy = lastKickedBy;
    }

    public Location getLastKickedFrom() {
        return this.lastKickedFrom;
    }

    public void setLastKickedFrom(Location lastKickedFrom) {
        this.lastKickedFrom = lastKickedFrom;
    }
}


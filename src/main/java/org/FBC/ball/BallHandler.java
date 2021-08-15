/*
 * Decompiled with CFR 0.137.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Item
 */
package org.FBC.ball;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import org.FBC.ball.Ball;
import org.bukkit.entity.Item;

public class BallHandler {
    HashSet<Ball> balls = new HashSet();
    HashSet<Item> ballItems = new HashSet();
    private HashSet<UUID> ballIDs = new HashSet();
    private HashMap<UUID, Ball> itemBall = new HashMap();

    public void tick() {
        if (this.balls != null && !this.balls.isEmpty()) {
            ArrayList<Ball> ballList = new ArrayList<Ball>();
            ballList.addAll(this.balls);
            for (Ball ball : ballList) {
                if (!ball.item.isDead()) {
                    ball.doPhysics();
                    continue;
                }
                this.balls.remove(ball);
                this.ballItems.remove((Object)ball.item);
            }
        }
    }

    public void addBallIds(UUID id) {
        this.ballIDs.add(id);
    }

    public void removeBallIds(UUID id) {
        this.ballIDs.remove(id);
    }

    public void despawnBall(UUID id) {
        Ball ball = this.itemBall.get(id);
        Item item = ball.item;
        this.balls.remove(ball);
        this.ballItems.remove((Object)item);
        item.remove();
    }

    public HashSet<Item> getItemBalls() {
        return this.ballItems;
    }

    public void removeBallItem(Item item) {
        this.ballItems.remove((Object)item);
    }

    public void addBallItem(Item item) {
        this.ballItems.add(item);
    }

    public HashSet<Ball> getBalls() {
        return this.balls;
    }

    public void removeBall(Ball ball) {
        this.balls.remove(ball);
    }

    public void addBall(Ball ball) {
        this.balls.add(ball);
    }

    public HashSet<UUID> getBallIDs() {
        return this.ballIDs;
    }

    public void setBallIDs(HashSet<UUID> ballIDs) {
        this.ballIDs = ballIDs;
    }

    public void putBallID(UUID id, Ball ball) {
        this.itemBall.put(id, ball);
    }

    public void takeBallID(UUID id) {
        this.itemBall.remove(id);
    }

    public HashMap<UUID, Ball> getItemBall() {
        return this.itemBall;
    }

    public void setItemBall(HashMap<UUID, Ball> itemBall) {
        this.itemBall = itemBall;
    }
}


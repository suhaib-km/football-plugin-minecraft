/*
 * Decompiled with CFR 0.137.
 */
package org.FBC.ball;

import org.FBC.ball.BallHandler;
import org.FBC.core.Main;

public class BallRunnable
implements Runnable {
    @Override
    public void run() {
        BallHandler ballhandler = Main.plugin.ballhandler;
        ballhandler.tick();
    }
}


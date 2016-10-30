package net.heyzeer0.magi.manager;

import org.bukkit.Bukkit;

/**
 * Created by HeyZeer0 on 30/10/2016.
 */
public class Lag implements Runnable {

    private static int TICK_COUNT= 0;
    private static long[] TICKS= new long[600];
    private static long LAST_TICK= 0L;

    public static double getTPS()
    {
        return getTPS(100);
    }

    public static double getRoundTPS() {
        return Math.round(getTPS(100) * 100.0D) / 100.0D;
    }

    private static double getTPS(int ticks)
    {
        if (TICK_COUNT< ticks) {
            return 20.0D;
        }
        int target = (TICK_COUNT- 1 - ticks) % TICKS.length;
        long elapsed = System.currentTimeMillis() - TICKS[target];

        return ticks / (elapsed / 1000.0D);
    }

    public void run()
    {
        TICKS[(TICK_COUNT% TICKS.length)] = System.currentTimeMillis();

        TICK_COUNT+= 1;
    }

}
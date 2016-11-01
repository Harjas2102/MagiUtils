package net.heyzeer0.magi.manager;

import org.bukkit.Bukkit;

/**
 * Created by HeyZeer0 on 30/10/2016.
 */
public class Lag implements Runnable {

    private static long mills;
    private static double[] tpsArr = new double[10];
    private static int index = 0;

    public static double getTPS()
    {
        double tpsSum = 0.0D;
        for (double d : tpsArr) {
            tpsSum += d;
        }
        return Math.round(tpsSum / 10.0D * 100.0D) / 100.0D;
    }

    public void run() {
        if (this.mills > 0L) {
            double diff = System.currentTimeMillis() - this.mills - 1000.0D;
            if (diff < 0.0D) {
                diff = Math.abs(diff);
            }
            double tps;
            if (diff == 0.0D) {
                tps = 20.0D;
            } else {
                tps = 20.0D - diff / 50.0D;
            }
            if (tps < 0.0D) {
                tps = 0.0D;
            }
            tpsArr[(index++)] = tps;
            if (index >= tpsArr.length) {

                    index = 0;
            }
        }
        mills = System.currentTimeMillis();
    }

}
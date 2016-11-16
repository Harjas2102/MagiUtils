package net.heyzeer0.magi.tasks;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 16/11/2016.
 * Copyright © HeyZeer0 - 2016
 */
public class AdvertisingTimer {

    public static void runAdvertising() {
        new BukkitRunnable() {
            public void run() {

                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(!p.hasPermission("magiutils.isento")) {
                        if (ConfigManager.advertising_action.contains("comando: ")) {
                            Bukkit.dispatchCommand(p, ConfigManager.advertising_action.replace("comando: ", ""));
                        } else if (ConfigManager.advertising_action.contains("mensagem: ")) {
                            p.sendMessage(ConfigManager.advertising_action.replace("mensagem: ", ""));
                        }
                    }
                }

                if(ConfigManager.advertising_boolean) {
                    runAdvertising();
                }

            }
        }.runTaskLaterAsynchronously(Main.main, ConfigManager.advertising_ticks);
    }


}

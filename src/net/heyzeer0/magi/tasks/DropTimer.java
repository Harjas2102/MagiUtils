package net.heyzeer0.magi.tasks;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 24/10/2016.
 * Copyright © HeyZeer0 - 2016
 */

public class DropTimer {

    public static void startCounting() {
        new BukkitRunnable() {
            Integer count = 61;

            public void run() {
                count--;

                if (count == 60) {
                    Bukkit.broadcastMessage(ConfigManager.clear_drops_message.replace("$s", count.toString()));
                }

                if (count == 30) {
                    Bukkit.broadcastMessage(ConfigManager.clear_drops_message.replace("$s", count.toString()));
                }

                if (count == 15) {
                    Bukkit.broadcastMessage(ConfigManager.clear_drops_message.replace("$s", count.toString()));
                }

                if (count == 5) {
                    Bukkit.broadcastMessage(ConfigManager.clear_drops_message.replace("$s", count.toString()));
                }

                if (count <= 0) {
                    this.cancel();
                    Bukkit.broadcastMessage(ConfigManager.clear_drops_message_2);
                    Bukkit.getLogger().info("[MagiUtils] Foram removidos " + RestartTimer.items.size() + " drops do chão.");
                    for (Item i : RestartTimer.items) {
                        i.remove();
                    }
                    RestartTimer.items.clear();
                }


            }
        }.runTaskTimer(Main.main, 0, 20);
    }

}

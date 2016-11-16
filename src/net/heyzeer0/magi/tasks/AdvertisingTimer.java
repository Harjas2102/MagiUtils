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

                        if(ConfigManager.advertising_action.size() > 1) {
                            for(String i : ConfigManager.advertising_action) {
                                if (i.contains("comando: ")) {
                                    Bukkit.dispatchCommand(p, i.replace("comando: ", "").replace("/", ""));
                                } else if (i.contains("mensagem: ")) {
                                    p.sendMessage(i.replace("mensagem: ", "").replace("&", "§"));
                                }
                            }
                        }else{
                            String i = ConfigManager.advertising_action.get(0);
                            if (i.contains("comando: ")) {
                                Bukkit.dispatchCommand(p, i.replace("comando: ", "").replace("/", ""));
                            } else if (i.contains("mensagem: ")) {
                                p.sendMessage(i.replace("mensagem: ", "").replace("&", "§"));
                            }
                        }




                    }
                }

                if(ConfigManager.advertising_boolean) {
                    runAdvertising();
                }

            }
        }.runTaskLater(Main.main, ConfigManager.advertising_ticks);
    }


}

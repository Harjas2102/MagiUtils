package net.heyzeer0.magi.tasks;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import net.heyzeer0.magi.manager.Lag;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

/**
 * Created by HeyZeer0 on 23/10/2016.
 * Copyright © HeyZeer0 - 2016
 */

public class RestartTimer {

    public static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<LivingEntity> entidades = new ArrayList<LivingEntity>();

    public static void startRestartCountdown() {
        new BukkitRunnable() {
            public void run() {

                if (Lag.getTPS() <= ConfigManager.min_tps) {

                    if (ConfigManager.clear_drops || ConfigManager.clear_entities) {

                        for (Entity i : Bukkit.getWorld(ConfigManager.world_name).getEntities()) {
                            if (i instanceof Item) {
                                items.add((Item) i);
                            }else if(i instanceof LivingEntity) {
                                if(((LivingEntity)i).getCustomName() == null) {
                                    entidades.add((LivingEntity)i);
                                }
                            }
                        }
                        if(ConfigManager.clear_entities) {
                            if(entidades != null && entidades.size() >= ConfigManager.clear_entities_amount) {
                                for(LivingEntity i : entidades) {
                                    i.remove();
                                }
                                Bukkit.getLogger().info("[MagiUtils] Foram removidas " + entidades.size() + " entidades.");
                                entidades.clear();
                            }
                        }
                        if(ConfigManager.clear_drops) {
                            if (items != null && items.size() >= ConfigManager.clear_drops_amount) {
                                DropTimer.startCounting();
                            }
                        }
                    }

                    startSecondCountdown();
                } else {
                    startRestartCountdown();
                }

            }
        }.runTaskLater(Main.main, ConfigManager.check_restart);
    }

    private static void startSecondCountdown() {
        new BukkitRunnable() {
            public void run() {

                if(Lag.getTPS() <= ConfigManager.min_tps) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.command);
                }else{
                    startRestartCountdown();
                }

            }
        }.runTaskLater(Main.main, 6000);
    }




}

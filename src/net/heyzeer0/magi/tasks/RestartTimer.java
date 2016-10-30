package net.heyzeer0.magi.tasks;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import net.heyzeer0.magi.manager.Lag;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by HeyZeer0 on 23/10/2016.
 */
public class RestartTimer {

    public static ArrayList<Item> items = new ArrayList<>();
    public static Integer item_amount = 0;

    public static void startRestartCountdown() {
        new BukkitRunnable() {
            public void run() {

                if(Math.round(Lag.getTPS() * 100.0D) / 100.0D <= ConfigManager.min_tps) {
                    if(ConfigManager.clear_drops) {
                        for(Entity i : Bukkit.getWorld(ConfigManager.world_name).getEntities()) {
                            if(i instanceof Item) {
                                items.add((Item)i);
                                item_amount++;
                            }
                        }
                        if(items != null && ConfigManager.clear_drops_amount >= item_amount) {
                            DropTimer.startCounting();
                        }
                    }

                    if(ConfigManager.clear_chunk_entities) {
                        for(Chunk k : Bukkit.getWorld(ConfigManager.world_name).getLoadedChunks()) {
                            if(k.getEntities().length >= ConfigManager.clear_chunk_entities_amount) {
                                for(Entity i : k.getEntities()) {
                                    if(!(i instanceof Player)) {
                                        if(i instanceof LivingEntity && !(i instanceof Minecart)) {
                                            if(((LivingEntity)i).getCustomName() == null || ((LivingEntity)i).getCustomName() != "") {
                                                i.remove();
                                            }
                                        }
                                    }
                                }
                                Bukkit.getLogger().info("[MagiUtils] Foram removidos " + k.getEntities().length + " entidades da chunk X: " + k.getX() + " Z: " + k.getZ());
                            }
                        }
                    }

                    startSecondCountdown();
                }else{
                    startRestartCountdown();
                }

            }
        }.runTaskLater(Main.main, ConfigManager.check_restart);
    }

    private static void startSecondCountdown() {
        new BukkitRunnable() {
            public void run() {

                if(Math.round(Lag.getTPS() * 100.0D) / 100.0D <= ConfigManager.min_tps) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.command);
                }else{
                    startRestartCountdown();
                }

            }
        }.runTaskLater(Main.main, 6000);
    }




}

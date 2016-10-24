package net.heyzeer0.magi.tasks;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Created by HeyZeer0 on 23/10/2016.
 */
public class RestartTimer {

    private static ArrayList<Item> items = new ArrayList<>();
    private static Integer item_amount = 0;

    public static void startRestartCountdown() {
        new BukkitRunnable() {
            public void run() {

                if(MinecraftServer.getServer().recentTps[0] <= 16.0) {
                    if(ConfigManager.clear_drops) {
                        for(Entity i : Bukkit.getWorld(ConfigManager.world_name).getEntities()) {
                            if(i instanceof Item) {
                                items.add((Item)i);
                                item_amount++;
                            }
                        }
                        if(items != null && ConfigManager.clear_drops_amount >= item_amount) {
                            for(Item i : items) {
                                i.remove();
                            }
                            Bukkit.getLogger().info("[MagiUtils] Foram removidos " + item_amount + " items do chão.");
                            items.clear();
                            item_amount = 0;
                        }
                    }

                    if(ConfigManager.clear_chunk_entities) {
                        for(Chunk k : Bukkit.getWorld(ConfigManager.world_name).getLoadedChunks()) {
                            if(k.getEntities().length >= ConfigManager.clear_chunk_entities_amount) {
                                for(Entity i : k.getEntities()) {
                                    if(!(i instanceof Player)) {
                                        if(i instanceof LivingEntity) {
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
                }

            }
        }.runTaskLater(Main.main, ConfigManager.check_restart);
    }

    private static void startSecondCountdown() {
        new BukkitRunnable() {
            public void run() {

                if(MinecraftServer.getServer().recentTps[0] <= 16.0) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "asw restart");
                }else{
                    startRestartCountdown();
                }

            }
        }.runTaskLater(Main.main, 1200);
    }




}

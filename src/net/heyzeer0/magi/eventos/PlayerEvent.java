package net.heyzeer0.magi.eventos;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by HeyZeer0 on 08/11/2016.
 * Copyright © HeyZeer0 - 2016
 */
public class PlayerEvent implements Listener {

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(ConfigManager.mundos.contains(e.getPlayer().getWorld().getName())) {
                if (ConfigManager.ids.contains(e.getPlayer().getItemInHand().getTypeId())) {
                    e.setCancelled(true);
                    ItemStack i = e.getPlayer().getItemInHand();
                    e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                    new BukkitRunnable() {
                        public void run() {
                            e.getPlayer().setItemInHand(i);
                        }
                    }.runTaskLaterAsynchronously(Main.main, 5);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getItem() == null) {
                return;
            }
            if (convertToCompleteID(e.getItem()).equals(ConfigManager.echest)) {
                Integer amount = 0;

                for(int i = 0; i < 8; i++) {
                    if(e.getPlayer().getInventory().getItem(i) != null && e.getPlayer().getInventory().getItem(i).getType() != Material.AIR) {
                        if(convertToCompleteID(e.getPlayer().getInventory().getItem(i)).equals(ConfigManager.echest)) {
                            amount++;
                        }
                    }
                }

                if(amount > 1) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "Desculpe, você não pode abrir este item com " + amount + " na hotbar.");

                    ItemStack i = e.getItem();
                    e.getPlayer().setItemInHand(new ItemStack(Material.AIR));

                    new BukkitRunnable() {
                        public void run() {
                            e.getPlayer().setItemInHand(i);
                        }
                    }.runTaskLaterAsynchronously(Main.main, 5);

                }
                return;
            }
        }
    }

    public String convertToCompleteID(ItemStack item) {
        return item.getTypeId() + ":" + item.getDurability();
    }


}

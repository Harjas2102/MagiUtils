package net.heyzeer0.magi.eventos;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

    //Thanks to Deathdroid65 =)
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null) {
            if (("" + e.getClickedBlock().getTypeId()).equals(ConfigManager.echest.split(":")[0])) {
                double y = e.getPlayer().getLocation().getY();
                double n = (y * 10) % 10; // 0.x

                if ((n == 0.1000213623046875) || e.getPlayer().isInsideVehicle() || !e.getPlayer().isOnGround()) {
                    e.setCancelled(true);
                }
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getItem() != null) {
            if (("" + e.getItem().getTypeId() + ":" + e.getItem().getData().getData()).equals(ConfigManager.echest)) {
                int echestCount = 0;

                ItemStack[] inventory = e.getPlayer().getInventory().getContents();
                for (int i = 0; i < 9; i++) {
                    if (inventory[i] != null) {
                        if ((inventory[i].getTypeId() + ":" + inventory[i].getData().getData()).equals(ConfigManager.echest)) {
                            echestCount++;
                        }
                    }
                }

                if (echestCount > 1) {
                    e.getPlayer().sendMessage(ChatColor.RED + "Esta ação esta bloqueada.");
                    e.setCancelled(true);
                }
            }
        }
    }


}

package net.heyzeer0.magi.eventos;

import net.heyzeer0.magi.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by HeyZeer0 on 14/11/2016.
 * Copyright © HeyZeer0 - 2016
 */
public class BlockEvent implements Listener {

    @EventHandler
    public void place(BlockPlaceEvent e) {
        if(e.getBlock().getLocation().clone().add(0, 1, 0).getBlock() != null) {
            if (e.getBlock().getLocation().clone().add(0, 1, 0).getBlock().getTypeId() == ConfigManager.armorstand) {
                if(e.getBlock().getTypeId() == ConfigManager.arcane) {
                    e.getBlock().setType(Material.AIR);
                    e.getPlayer().sendMessage(ChatColor.RED + "Desculpe, esta ação esta bloqueada.");
                }
            }
        }
    }

}

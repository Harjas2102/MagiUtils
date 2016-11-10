package net.heyzeer0.magi.comandos;

import net.heyzeer0.magi.manager.ConfigManager;
import net.heyzeer0.magi.manager.Lag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;

/**
 * Created by HeyZeer0 on 30/10/2016.
 * Copyright © HeyZeer0 - 2016
 */

public class ComandoTPSInfo implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String args2, String[] args) {
        if(cmd.getName().equalsIgnoreCase("tpsinfo")) {
            if(!sender.hasPermission("magiutils.tpsinfo")) {
                sender.sendMessage(ChatColor.RED + "Você não posssui permissão para realizar este comando.");
                return true;
            }
            Integer items = 0;
            Integer entidades = 0;

            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.GREEN + "TPS: " + ChatColor.GRAY + Lag.getTPS() + ".");
            sender.sendMessage(" ");
            sender.sendMessage(ChatColor.GREEN + "Chunks carregadas: " + ChatColor.GRAY + Bukkit.getWorld(ConfigManager.world_name).getLoadedChunks().length + ".");

            for(Entity i : Bukkit.getWorld(ConfigManager.world_name).getEntities()) {
                if(i instanceof Item) {
                    items++;
                }
                if(i instanceof LivingEntity) {
                    entidades++;
                }
            }

            sender.sendMessage(ChatColor.GREEN + "Items: " + ChatColor.GRAY + items + ".");
            sender.sendMessage(ChatColor.GREEN + "Entidades: " + ChatColor.GRAY + entidades + ".");
            sender.sendMessage(" ");

        }
        return false;
    }

}

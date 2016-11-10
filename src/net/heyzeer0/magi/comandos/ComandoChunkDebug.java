package net.heyzeer0.magi.comandos;

import net.heyzeer0.magi.manager.ChunkManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by HeyZeer0 on 23/10/2016.
 * Copyright © HeyZeer0 - 2016
 */

public class ComandoChunkDebug implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg1, String[] args) {
        if(cmd.getName().equalsIgnoreCase("chunkdebug") && sender.hasPermission("chunkdebug.op")) {
            if(sender instanceof Player) {
                sender.sendMessage(ChatColor.RED + "Comando apenas para console.");
                return true;
            }

            if(!ChunkManager.ativo) {
                ChunkManager.task();
                Bukkit.getLogger().info("  ");
                Bukkit.getLogger().info("  ");
                Bukkit.getLogger().info("  ");
                Bukkit.getLogger().info("Debug de chunks ativado, aguarde 10 segundos");
                Bukkit.getLogger().info("  ");
                Bukkit.getLogger().info("  ");
                Bukkit.getLogger().info("  ");
            }else{
                sender.sendMessage(ChatColor.RED + "Um debug já esta ocorrendo no momento");
            }
        }
        return false;
    }


}
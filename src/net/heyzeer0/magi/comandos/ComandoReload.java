package net.heyzeer0.magi.comandos;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by HeyZeer0 on 23/10/2016.
 * Copyright © HeyZeer0 - 2016
 */

public class ComandoReload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String args2, String[] args) {
        if(cmd.getName().equalsIgnoreCase("magireload")) {
            if(!sender.hasPermission("magiutils.reloadconfig")) {
                sender.sendMessage(ChatColor.RED + "Você não tem permissão para realizar este comando.");
                return true;
            }

            ConfigManager.loadConfig();
            Main.registerTasks();
            sender.sendMessage(ChatColor.GREEN + "Configs carregadas com sucesso, todas as tasks foram reiniciadas.");
        }
        return false;
    }

}

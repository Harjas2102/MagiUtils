package net.heyzeer0.magi.comandos;

import net.heyzeer0.magi.Main;
import net.heyzeer0.magi.manager.ConfigManager;
import net.heyzeer0.magi.tasks.RestartTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by HeyZeer0 on 23/10/2016.
 */
public class ComandoReload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String args2, String[] args) {
        if(cmd.getName().equalsIgnoreCase("magireload")) {
            if(!sender.hasPermission("magiutils.reloadconfig")) {
                sender.sendMessage(ChatColor.RED + "Você não tem permissão para realizar este comando.");
                return true;
            }

            Bukkit.getServer().getScheduler().cancelTasks(Main.main);
            try{
                ConfigManager.loadConfig();
                Main.registerTasks();
                sender.sendMessage(ChatColor.GREEN + "Configs carregadas com sucesso, todas as tasks foram reiniciadas.");
            }catch(Exception e) {
                RestartTimer.startRestartCountdown();
                sender.sendMessage(ChatColor.RED + "Erro ao carregar a config, desabilitando plugin.");
                Bukkit.getPluginManager().disablePlugin(Main.main);
            }



        }
        return false;
    }

}

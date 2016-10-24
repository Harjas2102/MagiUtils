package net.heyzeer0.magi;

import net.heyzeer0.magi.comandos.ComandoReload;
import net.heyzeer0.magi.manager.ConfigManager;
import net.heyzeer0.magi.tasks.RestartTimer;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by HeyZeer0 on 23/10/2016.
 */
public class Main extends JavaPlugin {

    public static Main main;


    public void onEnable() {
        main = this;
        saveDefaultConfig();

        ConfigManager.loadConfig();

        registerTasks();


        getCommand("magireload").setExecutor(new ComandoReload());
    }

    public void registerTasks() {
        RestartTimer.startRestartCountdown();
    }

    public void onDisable() {
        HandlerList.unregisterAll(this);
        Bukkit.getServer().getScheduler().cancelTasks(this);
    }
}

package net.heyzeer0.magi;

import net.heyzeer0.magi.comandos.ComandoChunkDebug;
import net.heyzeer0.magi.comandos.ComandoReload;
import net.heyzeer0.magi.comandos.ComandoTPSInfo;
import net.heyzeer0.magi.eventos.BlockEvent;
import net.heyzeer0.magi.eventos.ChunkEvent;
import net.heyzeer0.magi.eventos.PlayerEvent;
import net.heyzeer0.magi.manager.ChunkManager;
import net.heyzeer0.magi.manager.ConfigManager;
import net.heyzeer0.magi.manager.Lag;
import net.heyzeer0.magi.tasks.RestartTimer;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by HeyZeer0 on 23/10/2016.
 * Copyright © HeyZeer0 - 2016
 */

public class Main extends JavaPlugin {

    public static Main main;


    public void onEnable() {
        main = this;

        saveDefaultConfig();

        ConfigManager.loadConfig();

        registerTasks();

        getCommand("magireload").setExecutor(new ComandoReload());
        getCommand("chunkdebug").setExecutor(new ComandoChunkDebug());
        getCommand("tpsinfo").setExecutor(new ComandoTPSInfo());

        Bukkit.getPluginManager().registerEvents(new ChunkEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BlockEvent(), this);
    }

    public static void registerTasks() {
        RestartTimer.startRestartCountdown();

        if(ConfigManager.restart_enabled) {
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Lag(), 120L, 20L);
        }
    }

    public void onDisable() {
        HandlerList.unregisterAll(this);
        Bukkit.getServer().getScheduler().cancelTasks(this);
    }
}

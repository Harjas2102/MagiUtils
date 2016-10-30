package net.heyzeer0.magi.manager;

import net.heyzeer0.magi.Main;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/**
 * Created by HeyZeer0 on 24/10/2016.
 */
public class ChunkManager {

    public static boolean ativo = false;
    public static HashMap<Chunk, Integer> loaded = new HashMap<Chunk, Integer>();
    public static HashMap<Chunk, Integer> unloaded = new HashMap<Chunk, Integer>();

    public static void addChunkLoaded(Chunk k) {
        if(loaded.containsKey(k)) {
            loaded.replace(k, loaded.get(k) + 1);
        }else{
            loaded.put(k, 1);
        }
    }

    public static void addChunkUnloaded(Chunk k) {
        if(unloaded.containsKey(k)) {
            unloaded.replace(k, unloaded.get(k) + 1);
        }else{
            unloaded.put(k, 1);
        }
    }

    public static void broadcastList() {
        if(loaded != null) {
            LogManager.logToFile("CHUNK LOADED:");
            LogManager.logToFile("#Format = X: $x Z= $z [$loaded_amount] [$unloaded_amount]");
            LogManager.logToFile(" ");
            for(Chunk k : loaded.keySet()) {
                if(unloaded.containsKey(k)) {
                    LogManager.logToFile("Chunk loaded: X: " + k.getX() + " Z: " + k.getZ() + " [ " + loaded.get(k) + " ]" + " [ " + unloaded.get(k) + " ]");
                    unloaded.remove(k);
                }else{
                    LogManager.logToFile("Chunk loaded: X: " + k.getX() + " Z: " + k.getZ() + " [ " + loaded.get(k) + " ]" + " [ 0 ]");
                }

            }
        }
        if(unloaded != null) {
            LogManager.logToFile(" ");
            LogManager.logToFile("Chunk unloaded:");
            LogManager.logToFile("#Format = X: $x Z= $z [$unloaded]");
            for(Chunk k : unloaded.keySet()) {
                LogManager.logToFile("Chunk unloaded: X: " + k.getX() + " Z: " + k.getZ() + " [ " + unloaded.get(k) + " ]");
            }
        }

        Bukkit.getLogger().info("  ");
        Bukkit.getLogger().info("  ");
        Bukkit.getLogger().info("  ");
        Bukkit.getLogger().info("Debugg das chunks realizado com sucesso, veja-o na pasta do plugin.");
        Bukkit.getLogger().info("Para executar novamente digite /chunkdebug.");
        Bukkit.getLogger().info("  ");
        Bukkit.getLogger().info("  ");
        Bukkit.getLogger().info("  ");
        loaded.clear();
        unloaded.clear();
        ativo = false;
    }

    public static void task() {
        ativo = true;
        new BukkitRunnable() {
            public void run() {
                broadcastList();
            }
        }.runTaskLater(Main.main, 20*10);
    }

}

package net.heyzeer0.magi.manager;

import net.heyzeer0.magi.Main;

/**
 * Created by HeyZeer0 on 23/10/2016.
 */
public class ConfigManager {

    public static Integer check_restart = 12000;
    public static boolean clear_drops = true;
    public static Integer clear_drops_amount = 1000;
    public static String world_name = "world";
    public static boolean clear_chunk_entities = true;
    public static Integer clear_chunk_entities_amount = 200;

    public static void loadConfig() {
        check_restart = Main.main.getConfig().getInt("restart_tick");
        world_name = Main.main.getConfig().getString("world_name");
        clear_drops = Main.main.getConfig().getBoolean("clear_drops");
        clear_drops_amount = Main.main.getConfig().getInt("clear_drops_amount");
        clear_chunk_entities = Main.main.getConfig().getBoolean("clear_chunk_entities");
        clear_chunk_entities_amount = Main.main.getConfig().getInt("clear_chunk_entities_amount");
    }

}

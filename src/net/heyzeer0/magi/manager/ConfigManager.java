package net.heyzeer0.magi.manager;

import net.heyzeer0.magi.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by HeyZeer0 on 23/10/2016.
 * Copyright © HeyZeer0 - 2016
 */

public class ConfigManager {

    public static boolean restart_enabled = true;
    public static Integer check_restart = 12000;
    public static boolean clear_drops = true;
    public static Integer clear_drops_amount = 1000;
    public static String clear_drops_message = "§b[§c§l!§b] Limpando os drops em §c§l$s segundos§b.";
    public static String clear_drops_message_2 = "§b[§c§l!§b] Todos os drops foram limpos.";

    public static String world_name = "world";
    public static String command = "asw restart";
    public static boolean clear_chunk_entities = true;
    public static Integer clear_chunk_entities_amount = 200;

    public static double min_tps = 16.0;
    public static List<Integer> ids = new ArrayList<>();
    public static List<String> mundos = new ArrayList<>();

    public static String echest = "12";

    public static Integer advertising_ticks = 12000;
    public static List<String> advertising_action = new ArrayList<>();
    public static boolean advertising_boolean = true;

    public static void loadConfig() {
        Main.main.reloadConfig();

        //restart
        restart_enabled = Main.main.getConfig().getBoolean("restart_boolean");
        check_restart = Main.main.getConfig().getInt("restart_tick");
        world_name = Main.main.getConfig().getString("world_name");
        clear_drops = Main.main.getConfig().getBoolean("clear_drops");
        clear_drops_amount = Main.main.getConfig().getInt("clear_drops_amount");
        clear_drops_message = Main.main.getConfig().getString("clear_drops_message").replace("&", "§");
        clear_drops_message_2 = Main.main.getConfig().getString("clear_drops_message_2").replace("&", "§");
        clear_chunk_entities = Main.main.getConfig().getBoolean("clear_chunk_entities");
        clear_chunk_entities_amount = Main.main.getConfig().getInt("clear_chunk_entities_amount");
        min_tps = Main.main.getConfig().getDouble("min_tps");
        command = Main.main.getConfig().getString("command").replace("/", "");

        //advertising
        advertising_ticks = Main.main.getConfig().getInt("advertising_tick");

        if(Main.main.getConfig().getString("advertising_action").contains(",")) {
            Collections.addAll(advertising_action, Main.main.getConfig().getString("advertising_action").split(","));
        }else{
            advertising_action.add(Main.main.getConfig().getString("advertising_action"));
        }

        advertising_boolean = Main.main.getConfig().getBoolean("enable_advertising");


        //duplications
        echest = Main.main.getConfig().getString("eletrical_chest_id");

        //item gui blocker
        ids.clear();
        mundos.clear();

        String mundos_s = Main.main.getConfig().getString("blocked_worlds");
        if(mundos_s != null) {
            Collections.addAll(mundos, mundos_s.split(","));
        }

        String id = Main.main.getConfig().getString("item_ids");
        if(id != null) {
            for(String id2 : id.split(",")) {
                ids.add(Integer.valueOf(id2));
            }
        }

    }

}

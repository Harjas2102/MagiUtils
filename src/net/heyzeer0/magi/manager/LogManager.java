package net.heyzeer0.magi.manager;

import net.heyzeer0.magi.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by HeyZeer0 on 24/10/2016.
 * Copyright © HeyZeer0 - 2016
 */


public class LogManager {

    public static void logToFile(String message)
    {
        try
        {
            File dataFolder = Main.main.getDataFolder();
            if(!dataFolder.exists())
            {
                dataFolder.mkdir();
            }

            File saveTo = new File(Main.main.getDataFolder().toString(), "chunkdebug.txt");
            if (!saveTo.exists())
            {
                saveTo.createNewFile();
            }


            FileWriter fw = new FileWriter(saveTo, true);

            PrintWriter pw = new PrintWriter(fw);

            pw.println(message);

            pw.flush();

            pw.close();

        } catch (IOException e)
        {

            e.printStackTrace();

        }

    }

}


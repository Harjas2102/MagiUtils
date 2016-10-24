package net.heyzeer0.magi.eventos;

import net.heyzeer0.magi.manager.ChunkManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

/**
 * Created by HeyZeer0 on 24/10/2016.
 */
public class ChunkEvent implements Listener {

    @EventHandler
    public void debug2(ChunkLoadEvent e) {
        if(ChunkManager.ativo) {
            ChunkManager.addChunkLoaded(e.getChunk());
        }
    }

    @EventHandler
    public void debug1(ChunkUnloadEvent e) {
        if(ChunkManager.ativo) {
            ChunkManager.addChunkUnloaded(e.getChunk());
        }
    }

}

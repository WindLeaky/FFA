package de.windleak.ffa.Listener;

import de.windleak.ffa.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private Main main;

    public BlockBreakListener(Main main2) {
        main = main2;
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        event.setCancelled(true);

    }
}

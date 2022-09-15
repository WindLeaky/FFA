package de.windleak.ffa.Listener;

import de.windleak.ffa.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockPlaceListener implements Listener {
    private Main main;
    private Plugin instance;

    public BlockPlaceListener(Main main2) {
        main = main2;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlaceCopper(BlockPlaceEvent e) {
        Material material = e.getBlock().getType();


        if (material == Material.COPPER_BLOCK) {
            e.setCancelled(false);

            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.EXPOSED_COPPER);
                }
            }.runTaskLater(main, 30);

            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.WEATHERED_COPPER);
                }
            }.runTaskLater(main, 60);

            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.OXIDIZED_COPPER);
                }
            }.runTaskLater(main, 90);
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getBlock().setType(Material.AIR);
                }
            }.runTaskLater(main, 120);

        }
    }

}



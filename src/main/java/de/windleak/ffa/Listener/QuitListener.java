package de.windleak.ffa.Listener;

import de.windleak.ffa.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    private Main main;

    public QuitListener(Main main2) {
        main = main2;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        main.playerScoreboard.remove(player.getUniqueId());
    }
}

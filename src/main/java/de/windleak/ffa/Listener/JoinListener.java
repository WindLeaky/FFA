package de.windleak.ffa.Listener;


import de.windleak.ffa.Main;
import de.windleak.ffa.Sql;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.ResultSet;

import static de.windleak.ffa.Utilitys.setInventory;


public class JoinListener implements Listener {
    private Main main;

    public JoinListener(Main main2) {
        main = main2;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
        player.sendMessage(Main.getPrefix() + ChatColor.GRAY + "Play fair and have fun!");
        setInventory(main.Kits.get(main.currentKit), player);
        player.setHealth(20);
        player.setFoodLevel(20);
        Location location = new Location(Bukkit.getWorld("World"), -16, 109, -44);
        player.teleport(location);


        //create and set scoreboard
        main.playerScoreboard.put(player.getUniqueId(), main.getServer().getScoreboardManager().getNewScoreboard());
        player.setScoreboard(main.playerScoreboard.get(player.getUniqueId()));
        Scoreboard s = main.playerScoreboard.get(player.getUniqueId());
        Objective o = s.registerNewObjective("test", "dummy", "§eFFA");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.getScore("Current Killstreak: 0").setScore(8);
        o.getScore("§e").setScore(7);
        o.getScore("§b").setScore(5);
        o.getScore("Kit:").setScore(4);
        o.getScore("newkithere").setScore(3);
        o.getScore("§f").setScore(2);
        o.getScore("§ewww.cloudymc.net").setScore(1);

        //set the highest streak score to scoreboard
        int highestKillStreakScoreNumber = 6;
        try {
            Sql sql = new Sql("FFA");
            ResultSet r = sql.execute("select KillStreak from FFA.KillStreaks Where PlayerUUID='" + player.getName() + "';", true);
            int bestKillStreak = -1;
            if (r.next()) {
                bestKillStreak = r.getInt("KillStreak");
            }
            o.getScore("Highest Killstreak: " + bestKillStreak).setScore(highestKillStreakScoreNumber);
        } catch (Exception e) {
            o.getScore("Highest Killstreak: 0").setScore(highestKillStreakScoreNumber);
        }
    }
}


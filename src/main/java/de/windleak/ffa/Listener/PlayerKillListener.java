package de.windleak.ffa.Listener;


import de.windleak.ffa.Main;
import de.windleak.ffa.Sql;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scoreboard.Scoreboard;

import java.sql.ResultSet;
import java.sql.SQLException;

import static de.windleak.ffa.Utilitys.replaceScoreboardInfo;
import static de.windleak.ffa.Utilitys.setInventory;


public class PlayerKillListener implements Listener {
    private Main main;

    public PlayerKillListener(Main main2) {
        main = main2;
    }


    @EventHandler
    public void onDeath(EntityDamageEvent event) {
        Entity player = event.getEntity();
        if (player instanceof Player) {
            Player p = (Player) player;

            double damage = event.getDamage();
            double pHealth = p.getHealth();
            if (pHealth - damage <= 0) {
                event.setCancelled(true);
                Location loc = new Location(player.getWorld(), -16, 109, -44);
                p.teleport(loc);
                p.setHealth(20);
                p.setFoodLevel(20);
                p.getInventory().clear();
                setInventory(main.Kits.get(main.currentKit), (Player) player);
                Player killer = ((Player) player).getKiller();


                //Bukkit.broadcastMessage(((Player) player).getPlayer().getDisplayName() + " Killstreak: " + Main.getInstance().getKills(player.getUniqueId()));

                if (((Player) player).getKiller() == null) {
                    player.sendMessage(Main.getPrefix() + ChatColor.GRAY + "You died");
                } else {
                    Main.getInstance().resetKills(player.getUniqueId());
                    ((Player) player).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY + "You got killed by " + ChatColor.RED + ((Player) player).getKiller().getDisplayName()));
                    assert killer != null;
                    ((Player) killer).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GRAY + "You killed " + ChatColor.GREEN + ((Player) player).getPlayer().getDisplayName()));


                    //update scoreboard
                    Scoreboard s = main.playerScoreboard.get(player.getUniqueId());
                    replaceScoreboardInfo(s, s.getObjective("test"), "Current Killstreak", "Current Killstreak: 0");

                    Main.getInstance().addKill(((Player) player).getKiller().getUniqueId());
                    //Bukkit.broadcastMessage(((Player) player).getKiller().getDisplayName() + " Killstreak: " + Main.getInstance().getKills(((Player) player).getKiller().getUniqueId()));


                }
                try {
                    if (((Player) player).getKiller() != null) {
                        assert killer != null;

                        Sql sql = new Sql("FFA");

                        ResultSet r = sql.execute("select KillStreak from FFA.KillStreaks Where PlayerUUID='" + killer.getName() + "';", true);
                        int bestKillStreak = -1;
                        try {
                            if (r.next()) {
                                bestKillStreak = r.getInt("KillStreak");
                            }
                        } catch (SQLException ignored) {
                        }
                        if (bestKillStreak < Main.getInstance().getKills(killer.getUniqueId())) {

                            //update scoreboard
                            Scoreboard s = main.playerScoreboard.get(((Player) player).getKiller().getUniqueId());
                            replaceScoreboardInfo(s, s.getObjective("test"), "Highest Killstreak", "Highest Killstreak: " + main.getKills(((Player) player).getKiller().getUniqueId()));

                            sql.execute("REPLACE INTO `FFA`.`KillStreaks` (`PlayerUUID`, `KillStreak`) VALUES ('" + killer.getName() + "', '" + Main.getInstance().getKills(killer.getUniqueId()) + "');", false);
                        }
                        sql.close();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (killer != null) {


                    //update scoreboard
                    Scoreboard s = main.playerScoreboard.get(((Player) player).getKiller().getUniqueId());
                    replaceScoreboardInfo(s, s.getObjective("test"), "Current Killstreak", "Current Killstreak: " + main.getKills(((Player) player).getKiller().getUniqueId()));

                    if (Main.getInstance().getKills(killer.getUniqueId()) == 5) {
                        Bukkit.broadcastMessage(Main.getPrefix() + killer.getDisplayName() + " has a Killstreak of 5");
                    }
                    if (Main.getInstance().getKills(killer.getUniqueId()) == 10) {
                        Bukkit.broadcastMessage(Main.getPrefix() + killer.getDisplayName() + " has a Killstreak of 10");
                    }
                    if (Main.getInstance().getKills(killer.getUniqueId()) == 20) {
                        Bukkit.broadcastMessage(Main.getPrefix() + killer.getDisplayName() + " has a Killstreak of 20");
                    }
                    if (Main.getInstance().getKills(killer.getUniqueId()) == 50) {
                        Bukkit.broadcastMessage(Main.getPrefix() + killer.getDisplayName() + " has a Killstreak of 50");
                    }
                    if (Main.getInstance().getKills(killer.getUniqueId()) == 100) {
                        Bukkit.broadcastMessage(Main.getPrefix() + killer.getDisplayName() + " has a Killstreak of 100");
                    }
                    if (Main.getInstance().getKills(killer.getUniqueId()) == 150) {
                        Bukkit.broadcastMessage(Main.getPrefix() + killer.getDisplayName() + " has a Killstreak of 150");
                    }
                }
            }
        }
    }
}







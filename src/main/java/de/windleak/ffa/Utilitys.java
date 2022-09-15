package de.windleak.ffa;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Map;

public class Utilitys {

    public static void setInventory(Map<ItemStack, Integer> kit, Player player) {
        player.getInventory().clear();

        for (Map.Entry<ItemStack, Integer> k : kit.entrySet()) {
            if (k.getValue() == -1) {
                player.getInventory().addItem(k.getKey());
            } else {
                player.getInventory().setItem(k.getValue(), k.getKey());
            }
        }
    }

    public static void replaceScoreboardInfo(Scoreboard s, Objective o, String toReplaceContains, String toReplaceWith) {
        //update scoreboard
        for (String e : s.getEntries()) {
            if (e.contains(toReplaceContains)) {
                int ss = o.getScore(e).getScore();
                s.resetScores(e);
                o.getScore(toReplaceWith).setScore(ss);
                return;
            }
        }
        System.out.println("/!\\ nothing to replace found!");
    }
}

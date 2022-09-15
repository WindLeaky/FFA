package de.windleak.ffa;

import de.windleak.ffa.Listener.*;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Main extends JavaPlugin {

    public String currentKit = "Kit1";
    public Map<String, Map<ItemStack, Integer>> Kits;
    public Map<UUID, Scoreboard> playerScoreboard;

    public int ffaId;
    private int nextKit = 10 * 60;

    private static Main instance;
    private final Map<UUID, Integer> killCounterMap = new HashMap<>();

    @Override
    public void onEnable() {
        String motd = getServer().getMotd();
        ffaId = Integer.parseInt(motd.replace("ffaGameServer_", ""));
        instance = this;
        listenerRegistration();
        genKit();
        playerScoreboard = new HashMap<>();
    }

    public void listenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(this), this);
        pluginManager.registerEvents(new InvDropListener(this), this);
        pluginManager.registerEvents(new PlayerKillListener(this), this);
        pluginManager.registerEvents(new BlockPlaceListener(this), this);
        pluginManager.registerEvents(new BlockBreakListener(this), this);
        pluginManager.registerEvents(new QuitListener(this), this);
        pluginManager.registerEvents(new ChatListener(), this);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            nextKit = 60 * 10;

            int sel = ThreadLocalRandom.current().nextInt(0, Kits.keySet().size());
            currentKit = String.valueOf(Kits.keySet().toArray()[sel]);
            Location location = new Location(Bukkit.getWorld("World"), -16.9, 109, -45);
            for (Player p : Bukkit.getOnlinePlayers()) {
                Utilitys.setInventory(Kits.get(currentKit), p);
                p.sendTitle(ChatColor.RED + "NEW KIT!", ChatColor.GOLD + currentKit, 5, 40, 5);
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change: " + ChatColor.GOLD + currentKit);
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Next Kit Change in 10 minutes");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.7F, 1F);
                p.teleport(location);
            }
        }, 0L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                String tabListHeader = ChatColor.YELLOW + "www.CloudyMC.eu - FFA " + (ffaId + 1) + "\n  ";
                String tabListFooter = "\n  " + ChatColor.GRAY + "Play " + ChatColor.GREEN + "fair " + ChatColor.GRAY + "and have " + ChatColor.GREEN + "fun!" + "\n" + ChatColor.GRAY + "Use " + ChatColor.LIGHT_PURPLE + "/report " + ChatColor.GRAY + "to report a player\nNew Kit in " + ChatColor.GREEN + (Math.ceil(((double) nextKit * 100) / 60) / 100) + ChatColor.GRAY + " Minutes";
                p.setPlayerListHeaderFooter(tabListHeader, tabListFooter);
                nextKit--;
            }
        }, 1L, 20);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 5 minutes");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 6000L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 1 minute");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 10800L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 30 seconds");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 11400L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 10 seconds");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 11800L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 5 seconds");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 11900L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 4 seconds");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 11920L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 3 seconds");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 11940L, 20 * 60 * 10);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 2 seconds");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 11960L, 20 * 60 * 10);


        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(Main.getPrefix() + ChatColor.RED + "Kit Change in 1 second");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.7F, 2F);
            }
        }, 11980L, 20 * 60 * 10);

    }


    public void genKit() {

        Map<String, Map<ItemStack, Integer>> kit = new HashMap<>();

        //load config
        FileConfiguration config = this.getConfig();

        Map<ItemStack, Integer> temp;
        ItemStack tempKit;
        ItemMeta tempMeta;


        List<Map<String, Object>> kitConf = (List<Map<String, Object>>) config.get("Kits");
        assert kitConf != null;

        for (Map<String, Object> kitSel : kitConf) {
            String kitName = (String) kitSel.get("name");

            List<Map<String, Object>> a = (List<Map<String, Object>>) kitSel.get("items");

            temp = new HashMap<>();
            for (Map<String, Object> Item : a) {
                tempKit = new ItemStack(Objects.requireNonNull(Material.getMaterial((String) Item.get("type"))), 1);
                tempMeta = tempKit.getItemMeta();
                assert tempMeta != null;

                if (Item.get("name") != null) {
                    tempMeta.setDisplayName((String) Item.get("name"));
                }
                tempKit.setAmount((Integer) Item.get("count"));
                Integer s = -1;
                if (Item.get("slot") != null) {
                    s = (Integer) Item.get("slot");
                }
                if (Item.get("enchantments") != null) {
                    Map<String, Integer> enchantments = (Map<String, Integer>) Item.get("enchantments");
                    for (Map.Entry<String, Integer> enchant : enchantments.entrySet())
                        tempMeta.addEnchant(Objects.requireNonNull(Enchantment.getByKey(NamespacedKey.minecraft(enchant.getKey()))), enchant.getValue(), true);
                }
                temp.put(tempKit, s);
                tempKit.setItemMeta(tempMeta);

                //Item.get("slot");
            }
            kit.put((String) kitSel.get("name"), temp);
        }


        Kits = kit;
    }

    public static Main getInstance() {
        return instance;
    }

    public int getKills(final UUID uuid) {
        if (!killCounterMap.containsKey(uuid)) return 0;
        return killCounterMap.get(uuid);
    }

    public void addKill(final UUID uuid) {
        if (killCounterMap.containsKey(uuid)) {
            killCounterMap.put(uuid, getKills(uuid) + 1);
        } else {
            killCounterMap.put(uuid, 1);
        }
    }

    public void resetKills(final UUID uuid) {
        if (!killCounterMap.containsKey(uuid)) return;
        killCounterMap.remove(uuid);
    }

    @Override
    public void onDisable() {

    }


    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "FFA" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
    }
}


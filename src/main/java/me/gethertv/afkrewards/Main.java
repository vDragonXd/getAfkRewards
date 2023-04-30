package me.gethertv.afkrewards;

import me.gethertv.afkrewards.cmd.AfkZoneCmd;
import me.gethertv.afkrewards.data.AfkZone;
import me.gethertv.afkrewards.data.Cuboid;
import me.gethertv.afkrewards.event.ConnectPlayer;
import me.gethertv.afkrewards.listeners.InteractionClick;
import me.gethertv.afkrewards.runtask.CheckRegion;
import me.gethertv.afkrewards.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class Main extends JavaPlugin {

    private static Main instance;
    private List<CheckRegion> afkZoneList;

    private static ItemStack selector;

    private static Location first;
    private static Location second;


    private static CheckRegion checkRegion;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();

        afkZoneList = new ArrayList<>();

        selector = new ItemStack(Material.STICK);
        ItemMeta itemMeta = selector.getItemMeta();
        itemMeta.setDisplayName(ColorFixer.addColors("&4&k# &cSelector"));
        selector.setItemMeta(itemMeta);

        loadAfkZone();

        getServer().getPluginManager().registerEvents(new InteractionClick(), this);
        getCommand("afkzone").setExecutor(new AfkZoneCmd());

        getServer().getPluginManager().registerEvents(new ConnectPlayer(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(CheckRegion checkRegion : afkZoneList)
        {
            checkRegion.cancel();
        }
        Bukkit.getScheduler().cancelTasks(this);
        HandlerList.unregisterAll(this);
        instance = null;
    }

    public void reloadMainPlugin()
    {
        reloadConfig();
        for(CheckRegion checkRegion : afkZoneList)
        {
            checkRegion.cancel();
        }
        afkZoneList.clear();
        loadAfkZone();
    }

    private void loadAfkZone()
    {
        if(!getConfig().isSet("afk"))
            return;

        for(String name : getConfig().getConfigurationSection("afk").getKeys(false))
        {
            Location first = getConfig().getLocation("afk."+name+".first");
            Location second = getConfig().getLocation("afk."+name+".second");
            List<String> commands = new ArrayList<>();
            commands.addAll(getConfig().getStringList("afk."+name+".commands"));
            int time = getConfig().getInt("afk."+name+".time");
            Cuboid cuboid = new Cuboid(first, second);
            checkRegion = new CheckRegion(new AfkZone(cuboid, commands, time));
            checkRegion.runTaskTimer(this, 20L, 20L);
            afkZoneList.add(checkRegion);

        }
    }

    public static CheckRegion getCheckRegion() {
        return checkRegion;
    }

    public static Main getInstance() {
        return instance;
    }

    public static ItemStack getSelector() {
        return selector;
    }

    public static void setFirst(Location first) {
        Main.first = first;
    }

    public static void setSecond(Location second) {
        Main.second = second;
    }

    public static Location getFirst() {
        return first;
    }

    public static Location getSecond() {
        return second;
    }
}

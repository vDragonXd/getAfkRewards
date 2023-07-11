package me.gethertv.afkrewards.data;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

import java.util.List;

public class AfkZone {


    private Cuboid cuboid;
    private List<String> commands;
    private int second;

    private BarColor barColor;
    private BarStyle barStyle;
    private String bossName;
    public AfkZone(Cuboid cuboid, List<String> commands, int second, BarColor barColor, BarStyle barStyle, String bossName) {
        this.bossName = bossName;
        this.cuboid = cuboid;
        this.commands = commands;
        this.second = second;
        this.barColor = barColor;
        this.barStyle = barStyle;
    }

    public BarColor getBarColor() {
        return barColor;
    }

    public BarStyle getBarStyle() {
        return barStyle;
    }

    public String getBossName() {
        return bossName;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public List<String> getCommands() {
        return commands;
    }

    public int getSecond() {
        return second;
    }
}

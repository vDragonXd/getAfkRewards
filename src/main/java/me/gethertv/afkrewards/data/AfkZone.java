package me.gethertv.afkrewards.data;

import java.util.List;

public class AfkZone {

    private Cuboid cuboid;
    private List<String> commands;
    private int second;

    public AfkZone(Cuboid cuboid, List<String> commands, int second) {
        this.cuboid = cuboid;
        this.commands = commands;
        this.second = second;
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

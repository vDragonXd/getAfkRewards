package me.gethertv.afkrewards.event;

import me.gethertv.afkrewards.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectPlayer implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        if(Main.getCheckRegion().getUserdata().get(event.getPlayer().getUniqueId())!=null)
            Main.getCheckRegion().getUserdata().remove(event.getPlayer().getUniqueId());
    }
}

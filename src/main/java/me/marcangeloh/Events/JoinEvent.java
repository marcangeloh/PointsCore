package me.marcangeloh.Events;

import me.marcangeloh.PointsCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent joinEvent) {
        pointsCore.loadPlayerData(joinEvent.getPlayer());
    }
}

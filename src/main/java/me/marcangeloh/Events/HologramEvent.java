package me.marcangeloh.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class HologramEvent implements Listener {

    @EventHandler
    public void hologramEvent(PlayerArmorStandManipulateEvent event) {
        if(!event.getRightClicked().isVisible()) {
            event.setCancelled(true);
        }
    }
}

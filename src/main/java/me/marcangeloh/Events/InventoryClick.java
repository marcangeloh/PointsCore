package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerEquipArmorEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event) {
        if(event.getClickedInventory() == null)
            return;

        if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER))
            return;

        if(!(event.getWhoClicked() instanceof Player))
            return;

        if(event.getSlotType() != InventoryType.SlotType.ARMOR) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        PlayerEquipArmorEvent playerEquipArmorEvent = new PlayerEquipArmorEvent(player,event.getCurrentItem());
        Bukkit.getPluginManager().callEvent(playerEquipArmorEvent);
    }
}

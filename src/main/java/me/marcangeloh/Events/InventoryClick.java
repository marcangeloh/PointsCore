package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerEquipArmorEvent;
import me.marcangeloh.API.Util.ConfigurationUtil.ToolUtil;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void inventoryClickEvent(InventoryClickEvent event) {
        if(event.getClickedInventory() == null)
            return;

        if(!event.getClickedInventory().getType().equals(InventoryType.PLAYER))
            return;

        if(!(event.getWhoClicked() instanceof Player))
            return;

        ItemStack is = event.getCurrentItem();

        if(is == null)
            return;

        if(!ToolUtil.getToolTypeForMaterial(is.getType()).equals(Tools.ARMOR))
            return;

        Player player = (Player) event.getWhoClicked();

        if(!event.isShiftClick()) {
            if(event.getSlotType() == InventoryType.SlotType.ARMOR) {
                is = event.getCursor();
            }

        }

        if(is == null)
            return;

        PlayerEquipArmorEvent playerEquipArmorEvent = new PlayerEquipArmorEvent(player,is);
        Bukkit.getPluginManager().callEvent(playerEquipArmorEvent);
    }
}

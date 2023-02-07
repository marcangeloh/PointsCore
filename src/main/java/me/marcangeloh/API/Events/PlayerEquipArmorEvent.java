package me.marcangeloh.API.Events;

import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PlayerEquipArmorEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ItemStack armor;

    public PlayerEquipArmorEvent(Player player, ItemStack armor) {
        this.player = player;
        this.armor = armor;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getArmor() {
        return armor;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
}

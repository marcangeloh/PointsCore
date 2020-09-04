package me.marcangeloh.API.Events;

import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDamageEntityEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Entity entity;
    private final Tools tool;

    public PlayerDamageEntityEvent(Player player, Entity entity, Tools tool) {
        this.player = player;
        this.entity = entity;
        this.tool = tool;
    }

    public Player getPlayer() {
        return player;
    }

    public Entity getEntity() {
        return entity;
    }

    public Tools getTool() {
        return tool;
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

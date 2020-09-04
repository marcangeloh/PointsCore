package me.marcangeloh.API.Events;

import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerFishingEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Material fished;
    private final Tools tool;

    public PlayerFishingEvent(Player player, Material fished, Tools tool) {
        this.player = player;
        this.fished = fished;
        this.tool = tool;
    }

    public Player getPlayer() {
        return player;
    }

    public Material getFished() {
        return fished;
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

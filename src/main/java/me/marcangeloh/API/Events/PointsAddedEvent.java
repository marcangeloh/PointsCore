package me.marcangeloh.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class PointsAddedEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final UUID uuid;

    public PointsAddedEvent(UUID playerName, Double amount) {
        this.uuid = playerName;
        this.amount = amount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Double getAmount() {
        return amount;
    }

    private final Double amount;


    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}

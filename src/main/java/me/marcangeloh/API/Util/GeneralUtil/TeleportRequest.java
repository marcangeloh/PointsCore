package me.marcangeloh.API.Util.GeneralUtil;

import org.bukkit.entity.Player;

public class TeleportRequest {

    public Player player, player2;
    public int cooldown, moveCooldown;
    public boolean isInverted;
    public boolean isConfirmed;

    public double distance = 0;

    public TeleportRequest(Player player, Player player2, int cooldown, int moveCooldown, boolean isInverted) {
        this.player = player;
        this.player2 = player2;
        this.cooldown = cooldown;
        this.moveCooldown = moveCooldown;
        this.isInverted = isInverted;
        isConfirmed = false;
    }


}

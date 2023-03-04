package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.TeleportUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    private TeleportUtil teleportUtil;
    public MoveEvent(TeleportUtil teleportUtil) {
        this.teleportUtil = teleportUtil;
    }
    @EventHandler
    public void move(PlayerMoveEvent event) {
        handleTPA(event.getPlayer(), event.getFrom().distance(event.getTo()));
    }

    public void handleTPA(Player player, double distance) {
        boolean containsPlayer = false;

        for(Player temp: teleportUtil.teleportMap.keySet()) {

            if(teleportUtil.teleportMap.get(temp).isInverted) {
                if (teleportUtil.teleportMap.get(temp).player2 == player) {
                    containsPlayer = true;
                }
            } else {
                if (teleportUtil.teleportMap.get(temp).player == player) {
                    containsPlayer = true;
                }
            }
        }

        if(!containsPlayer)
            return;

        if(teleportUtil.teleportMap.get(player) == null)
            return;

        if(teleportUtil.teleportMap.get(player).moveCooldown == 0) {
            return;
        }

        teleportUtil.teleportMap.get(player).distance += distance;

        if(teleportUtil.teleportMap.get(player).distance < PointsCore.plugin.getConfig().getDouble("TPA.MaxMoveDistance",1.0)) {
            return;
        }

        teleportUtil.teleportMap.remove(player);

        Message.errorMessage("You've canceled your tpa request by moving.", player);


    }
}

package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.HashMapUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    private HashMapUtil hashMapUtil;
    public MoveEvent(HashMapUtil hashMapUtil) {
        this.hashMapUtil = hashMapUtil;
    }
    @EventHandler
    public void move(PlayerMoveEvent event) {
        handleTPA(event.getPlayer(), event.getFrom().distance(event.getTo()));
    }

    public void handleTPA(Player player, double distance) {
        boolean containsPlayer = false;

        for(Player temp: hashMapUtil.teleportMap.keySet()) {

            if(hashMapUtil.teleportMap.get(temp).isInverted) {
                if (hashMapUtil.teleportMap.get(temp).player2 == player) {
                    containsPlayer = true;
                }
            } else {
                if (hashMapUtil.teleportMap.get(temp).player == player) {
                    containsPlayer = true;
                }
            }
        }

        if(!containsPlayer)
            return;

        if(hashMapUtil.teleportMap.get(player) == null)
            return;

        if(hashMapUtil.teleportMap.get(player).moveCooldown == 0) {
            return;
        }

        hashMapUtil.teleportMap.get(player).distance += distance;

        if(hashMapUtil.teleportMap.get(player).distance < PointsCore.plugin.getConfig().getDouble("TPA.MaxMoveDistance",1.0)) {
            return;
        }

        hashMapUtil.teleportMap.remove(player);

        Message.errorMessage("You've canceled your tpa request by moving.", player);


    }
}

package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.TeleportUtil.HashMapUtil;
import me.marcangeloh.API.Util.TeleportUtil.TeleportUtil;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class MoveEvent implements Listener {
    private HashMapUtil hashMapUtil;
    private HashMap<Player, TeleportUtil> noMoveSpawn;
    private HashMap<Player, TeleportUtil> noMoveHome;
    private PointsCore pointsCore;
    public MoveEvent(PointsCore pointsCore, HashMap<Player, TeleportUtil> noMoveSpawn, HashMap<Player,TeleportUtil> noMoveHome) {
        this.pointsCore = pointsCore;
        this.hashMapUtil = pointsCore.hashMapUtil;
        this.noMoveSpawn = noMoveSpawn;
        this.noMoveHome = noMoveHome;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void move(PlayerMoveEvent event) {
        handleTPA(event.getPlayer(), event.getFrom().distance(event.getTo()));
        handleHome(event.getPlayer(), event.getFrom().distance(event.getTo()));
        handleSpawn(event.getPlayer(), event.getFrom().distance(event.getTo()));
    }

    private void handleSpawn(Player player, double distance) {
        handleTP(player, distance, "Spawn", noMoveSpawn);
    }

    private void handleHome(Player player, double distance) {
        handleTP(player, distance, "Home", noMoveHome);
    }

    private void handleTP(Player player, Double distance, String path,  HashMap<Player, TeleportUtil> noMove) {
        boolean isContained = false;

        for(Player temp: noMove.keySet()) {
            if(!temp.equals(player)) {
                continue;
            }

            isContained=true;
        }

        if(!isContained)
            return;

        if(noMove.get(player).countdownToTp == 0)
            return;

        noMove.get(player).distance += distance;

        if(noMove.get(player).distance < pointsCore.getConfig().getDouble(path+".MaxMoveDistance", 1.0)) {
            return;
        }

        noMove.remove(player);
        Message.errorMessage("You've canceled your "+path+" request by moving.", player);

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

        if(hashMapUtil.teleportMap.get(player).distance < pointsCore.getConfig().getDouble("TPA.MaxMoveDistance",1.0)) {
            return;
        }

        hashMapUtil.teleportMap.remove(player);

        Message.errorMessage("You've canceled your tpa request by moving.", player);


    }
}

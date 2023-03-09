package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerDamageByEntityEvent;
import me.marcangeloh.PointsCore;
import me.marcangeloh.API.Util.ConfigurationUtil.ValueUtil;
import me.marcangeloh.API.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.UUID;

public class ArmorEvent implements Listener {

    public ArmorEvent(PointsCore pointsCore) {
        this.pointsCore = pointsCore;
        this.valueUtil = new ValueUtil(pointsCore);
    }

    PointsCore pointsCore;

    ValueUtil valueUtil;

    @EventHandler(priority = EventPriority.LOW)
    public void playerDamagedEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            EntityType entityType;
            if(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
                entityType = EntityType.CREEPER;
            } else if(event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                entityType = EntityType.SKELETON;
            } else if(event.getCause().equals(EntityDamageEvent.DamageCause.WITHER)) {
                entityType = EntityType.WITHER;
            } else {
                return;
            }
            //Player got damaged by entity
            Player player = (Player) event.getEntity();
            Tools tool = Tools.ARMOR;
            double incrementValue = valueUtil.getDamageValues(tool, entityType);

            //Calls the custom event
            PlayerDamageByEntityEvent playerDamageByEntityEvent = new PlayerDamageByEntityEvent(player, null, tool);
            Bukkit.getPluginManager().callEvent(playerDamageByEntityEvent);

            if(playerDamageByEntityEvent.isCancelled()) {
                //If the event was cancelled stop adding points to the player
                return;
            }

            addPoints(player, incrementValue, tool);
        }
    }

    /**
     * Adds the corresponding points to the player
     * @param player The player to add points to
     * @param incrementValue The amount of points to add
     * @param toolType The type of tool to add the points to
     */
    private void addPoints(Player player, Double incrementValue, Tools toolType) {
        UUID uuid = player.getUniqueId();
        if(pointsCore.playerPoints.multiplierMap.containsKey(uuid)) {
            if(pointsCore.playerPoints.multiplierMap.get(uuid).isStillValid()) {
                incrementValue = incrementValue*pointsCore.playerPoints.multiplierMap.get(uuid).getMultiplierAmount();
            }
        }
        if (toolType.equals(Tools.ARMOR)) {
            Message.debugMessage("Added " + incrementValue + pointsCore.playerPoints.getPointNameFromToolType(toolType)+" to " + player.getName(), DebugIntensity.INTENSE);
            pointsCore.playerPoints.addPointsToToolType(Tools.ARMOR, player, incrementValue);
        }
    }

}

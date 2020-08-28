package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerDamageByEntityEvent;
import me.marcangeloh.API.Events.PlayerDamageEntityEvent;
import me.marcangeloh.PointsCore;
import me.marcangeloh.Util.ConfigurationUtil.ValueUtil;
import me.marcangeloh.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.Util.GeneralUtil.Message;
import me.marcangeloh.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ArmorEvent implements Listener {

    ValueUtil valueUtil = new ValueUtil();

    @EventHandler
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
        if (toolType.equals(Tools.ARMOR)) {
            Message.debugMessage("Added " + incrementValue + " armor points to " + player.getName(), DebugIntensity.INTENSE);
            PointsCore.playerPoints.armorPoints.addPointsToPlayer(player, incrementValue);
        }
    }

    /**
     * A method to handle the incrementation and the call of the
     * Custom event
     * @param player The player to increment the points of
     * @param incrementValue The amount to increment
     * @param tool The tool to increment the value of
     * @param entity The entity that received the damage
     */
    private void incrementHandler(Player player, double incrementValue, Tools tool, Entity entity) {
        //Calling the custom event
        PlayerDamageEntityEvent playerDamageEntityEvent = new PlayerDamageEntityEvent(player, entity, tool);
        Bukkit.getPluginManager().callEvent(playerDamageEntityEvent);

        if(playerDamageEntityEvent.isCancelled()) {
            //If the event was cancelled stop adding points to the player
            return;
        }

        addPoints(player, incrementValue, tool);
    }
}

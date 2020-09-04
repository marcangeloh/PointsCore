package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerDamageByEntityEvent;
import me.marcangeloh.API.Events.PlayerDamageEntityEvent;
import me.marcangeloh.PointsCore;
import me.marcangeloh.API.Util.ConfigurationUtil.ValueUtil;
import me.marcangeloh.API.Util.GeneralUtil.DebugIntensity;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.UUID;

public class WeaponEvent implements Listener {
    ValueUtil valueUtil = new ValueUtil();
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;

    @EventHandler
    public void entityDamageEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            //Player damaged entity
            Player player = (Player) event.getDamager();
            Tools tool = valueUtil.getToolType(player.getInventory().getItemInMainHand().getType());
            double incrementValue = valueUtil.getDamageValues(tool, event.getEntityType());

            incrementHandler(player, incrementValue, tool, event.getEntity());

        } else if(event.getDamager() instanceof Arrow ) {
            ProjectileSource projectileSource = ((Arrow)event.getDamager()).getShooter();
            //If it is a player (and not a skeleton)
            if(projectileSource instanceof  Player) {
                //Gets the player
                Player player = (Player) ((Arrow) event.getDamager()).getShooter();
                Tools tool = Tools.RANGED_WEAPON;
                double incrementValue = valueUtil.getDamageValues(tool, event.getEntityType());

                incrementHandler(player, incrementValue, tool, event.getEntity());
            }

        } else if(event.getDamager() instanceof Trident) {
            Player player = (Player) ((Trident)event.getDamager()).getShooter();
            Tools tool = Tools.RANGED_WEAPON;
            double incrementValue = valueUtil.getDamageValues(tool, event.getEntityType());

            incrementHandler(player, incrementValue, tool, event.getEntity());
        /**Armor Event**/
        } else if (event.getEntity() instanceof Player) {
            //Player got damaged by entity
            Player player = (Player) event.getEntity();
            Tools tool = Tools.ARMOR;
            double incrementValue = valueUtil.getDamageValues(tool, event.getDamager().getType());

            //Calls the custom event
            PlayerDamageByEntityEvent playerDamageByEntityEvent = new PlayerDamageByEntityEvent(player, event.getDamager(), tool);
            Bukkit.getPluginManager().callEvent(playerDamageByEntityEvent);

            if(playerDamageByEntityEvent.isCancelled()) {
                //If the event was cancelled stop adding points to the player
                return;
            }

            addPoints(player, incrementValue, tool);
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
        Message.debugMessage("Added " + incrementValue + pointsCore.playerPoints.getPointNameFromToolType(toolType)+" to " + player.getName(), DebugIntensity.INTENSE);
        pointsCore.playerPoints.addPointsToToolType(toolType, player, incrementValue);
    }
}

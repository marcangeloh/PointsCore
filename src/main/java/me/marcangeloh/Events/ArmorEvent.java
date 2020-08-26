package me.marcangeloh.Events;

import me.marcangeloh.API.PointsUtil.DetailedPoints.ArmorPoints;
import me.marcangeloh.PointsCore;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;

public class ArmorEvent implements Listener {

    private Plugin plugin =PointsCore.plugin;



    @EventHandler
    public void entityHitPlayerEvent(EntityDamageEvent event) {
        if(event.getEntityType().equals(EntityType.PLAYER)) {
            if(event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                PointsCore.playerPoints.armorPoints.addPointsToPlayer(player, plugin.getConfig().getDouble(PointsCore.pathArmorPointsIncrement));
            }
        }
    }

}

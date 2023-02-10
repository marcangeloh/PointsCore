package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerEquipArmorEvent;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinEvent implements Listener {
    PointsCore pointsCore = (PointsCore) PointsCore.plugin;

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent joinEvent) {
        pointsCore.loadPlayerData(joinEvent.getPlayer());
        if(!PointsCore.latest) {
            if(joinEvent.getPlayer().isOp()) {
                new Message().sendClickableLinkText(joinEvent.getPlayer(), "&a&lPoints&2&lCore &r&7has a new &a&nupdate&r &7available.",
                        "https://www.spigotmc.org/resources/pointscore.83263/",
                        "&7Click &a&nhere&r &7to download the latest version.");
            }
        }
        joinEvent.setJoinMessage(Message.format(PointsCore.plugin.getConfig().getString("CustomJoinMessage", joinEvent.getPlayer().getDisplayName() + " &#17fb04d&#21f90fr&#2af619o&#34f424p&#3ef22fp&#47ef39e&#51ed44d &#5aea4ei&#64e859n&#6ee664t&#77e36eo &#81e179t&#8bdf84h&#94dc8ee &#9eda99w&#a7d7a3o&#b1d5aer&#bbd3b9l&#c4d0c3d&#cecece.").replaceAll(":player:", joinEvent.getPlayer().getDisplayName())));

        Player player = joinEvent.getPlayer();

        for(ItemStack armor: player.getInventory().getArmorContents()) {
            if(armor == null)
                continue;

            PlayerEquipArmorEvent playerEquipArmorEvent = new PlayerEquipArmorEvent(player, armor);
            Bukkit.getPluginManager().callEvent(playerEquipArmorEvent);
        }
    }
}

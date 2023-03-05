package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        String playerName = event.getEntity().getDisplayName();
        String deathMessage = PointsCore.plugin.getConfig().getString("CustomDeathMessage."+ event.getEntity().getLastDamageCause().getCause(),
                playerName+ Message.format(event.getEntity()," &#17fb04h&#31f521a&#4bee3es &#65e85bd&#80e177i&#9adb94e&#b4d4b1d&#cecece."));

        if(PointsCore.plugin.getConfig().getBoolean("CustomMessages", true));
            event.setDeathMessage(Message.format(event.getEntity(), deathMessage.replaceAll(":player:" , playerName)));

    }
}

package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {
    @EventHandler
    public void leaveEvent(PlayerQuitEvent event) {
        event.setQuitMessage(Message.format(PointsCore.plugin.getConfig().getString("QuitMessage", ":player: &#17fb04p&#2bf61ae&#40f131a&#54ec47c&#68e75ee&#7de274d &#91dd8bo&#a5d8a1u&#bad3b8t&#cecece.").replaceAll(":player:", event.getPlayer().getDisplayName())));
    }
}

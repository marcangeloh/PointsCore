package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormatter implements Listener {

    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        if(event.getMessage().indexOf("/") == 0)
            return;

        if(!event.getPlayer().hasPermission("pointscore.chatcolor") && !event.getPlayer().hasPermission("pointscore.*") && !event.getPlayer().hasPermission("*")) {
            return;
        }

        if(event.getMessage().contains("&") || event.getMessage().contains("§") || event.getMessage().contains("#")) {
            event.setMessage(Message.format(event.getMessage()));
        }

    }
}

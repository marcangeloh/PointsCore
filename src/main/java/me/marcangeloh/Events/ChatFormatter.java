package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.DiscordWebhook;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;

public class ChatFormatter implements Listener {


    JDA discord;
    PointsCore pointsCore;
    public ChatFormatter(PointsCore pointsCore, JDA discordWebhook) {
        this.pointsCore = pointsCore;
        this.discord = discordWebhook;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        if(event.getMessage().indexOf("/") == 0)
            return;

        if(pointsCore.getConfig().getBoolean("Discord.Enabled", true)) {
            long channelID = pointsCore.getConfig().getLong("Discord.ChannelID", 0);
            TextChannel textChannel = discord.getTextChannelById(channelID);
            textChannel.sendMessage("**"+ event.getPlayer().getDisplayName()+"** >> " + event.getMessage()).queue();
        }

        if(!event.getPlayer().hasPermission("pointscore.chatcolor") && !event.getPlayer().hasPermission("pointscore.*") && !event.getPlayer().hasPermission("*")) {
            return;
        }

        if(event.getMessage().contains("&") || event.getMessage().contains("§") || event.getMessage().contains("#")) {
            event.setMessage(Message.format(pointsCore,event.getPlayer(), event.getMessage()));
        }

    }


}

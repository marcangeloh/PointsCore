package me.marcangeloh.Events;

import me.marcangeloh.API.Util.ConfigurationUtil.ValueUtil;
import me.marcangeloh.API.Util.GeneralUtil.DiscordWebhook;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.API.Util.GeneralUtil.Tools;
import me.marcangeloh.PointsCore;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.util.HashMap;

public class ChatFormatter implements Listener {


    JDA discord;
    PointsCore pointsCore;
    HashMap<Player, Integer> chatPointsCooldown;
    int joinCooldown;
    public ChatFormatter(PointsCore pointsCore, JDA discordWebhook, HashMap<Player, Integer> chatPointsCooldown, int joinCooldown) {
        this.pointsCore = pointsCore;
        this.discord = discordWebhook;
        this.chatPointsCooldown = chatPointsCooldown;
        this.joinCooldown = joinCooldown;

    }

    @EventHandler(priority = EventPriority.LOW)
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        if(event.getMessage().indexOf("/") == 0)
            return;

        Player player = event.getPlayer();

        handleWelcome(player, event.getMessage());
        handleChatPoints(player, event.getMessage());
        handleDiscord(player,event.getMessage());

        if(!player.hasPermission("pointscore.chatcolor") && !player.hasPermission("pointscore.*") && !player.hasPermission("*")) {
            return;
        }

        if(event.getMessage().contains("&") || event.getMessage().contains("§") || event.getMessage().contains("#")) {
            event.setMessage(Message.format(pointsCore,player, event.getMessage()));
        }


    }

    private void handleWelcome(Player player, String message) {
        if(!message.contains("welcome") && !message.contains("wlc")) {
            return;
        }

        if(joinCooldown <= 0) {
            return;
        }

        double points = pointsCore.getConfig().getDouble("Increment.ChatValues.welcome-bonus", 2.0);
        pointsCore.addPoints(Tools.CHAT, player, points);
        Message.notifyMessage("Successfully gained "+ points+" points.", player);
    }

    private void handleDiscord(Player player, String message) {
        if(discord != null && pointsCore.getConfig().getBoolean("Discord.Enabled", true)) {
            long channelID = pointsCore.getConfig().getLong("Discord.ChannelID", 0);
            TextChannel textChannel = discord.getTextChannelById(channelID);
            assert textChannel != null;
            textChannel.sendMessage("**"+ player.getDisplayName()+"** >> " + message).queue();
        }
    }

    private void handleChatPoints(Player player, String message) {
        if(chatPointsCooldown.containsKey(player)) {
            return;
        }

        pointsCore.addPoints(Tools.CHAT, player, new ValueUtil(pointsCore).getMessageValue(message));
        chatPointsCooldown.put(player,pointsCore.getConfig().getInt("Increment.ChatValues.delay", 60));

    }


}

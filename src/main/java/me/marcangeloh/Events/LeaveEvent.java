package me.marcangeloh.Events;

import me.marcangeloh.API.Util.GeneralUtil.DiscordWebhook;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.io.IOException;

public class LeaveEvent implements Listener {

    private JDA discord;

    private PointsCore pointsCore;

    public LeaveEvent(PointsCore pointsCore, JDA discord) {
        this.discord = discord;
        this.pointsCore = pointsCore;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void leaveEvent(PlayerQuitEvent event) {

        if(pointsCore.getConfig().getBoolean("CustomMessages", true))
            event.setQuitMessage(Message.format(pointsCore,event.getPlayer(), pointsCore.getConfig().getString("QuitMessage", ":player: &#17fb04p&#2bf61ae&#40f131a&#54ec47c&#68e75ee&#7de274d &#91dd8bo&#a5d8a1u&#bad3b8t&#cecece.").replaceAll(":player:", event.getPlayer().getDisplayName())));

        if(pointsCore.getConfig().getBoolean("Discord.Enabled", true)) {
            int online = Bukkit.getOnlinePlayers().size()-1;
            int total = Bukkit.getServer().getMaxPlayers();
            if(pointsCore.getConfig().getBoolean("CustomMessages", true)) {
                createEmbed("Player disconnected.",
                        ChatColor.stripColor(Message.format(pointsCore,event.getPlayer(), pointsCore.getConfig().getString("QuitMessage", ":player: &#17fb04p&#2bf61ae&#40f131a&#54ec47c&#68e75ee&#7de274d &#91dd8bo&#a5d8a1u&#bad3b8t&#cecece.").replaceAll(":player:", event.getPlayer().getDisplayName()))),
                        "{"+online +" / "+total+"}",
                        new Color(220,40,40));
            } else {
                createEmbed("Player disconnected.",
                        ChatColor.stripColor(event.getPlayer().getDisplayName() + " has left the server."),
                        "{"+online +" / "+total+"}",
                        new Color(220, 40, 40));
            }
        }
    }


    private void createEmbed(String title, String message, String fieldText, Color color) {
        long channelID = pointsCore.getConfig().getLong("Discord.ChannelID", 0);
        MessageEmbed embed = new EmbedBuilder().setTitle(title).setDescription(message).addField("Remaining players: ", fieldText, true).setColor(color).build();
        TextChannel textChannel = discord.getTextChannelById(channelID);
        textChannel.sendMessageEmbeds(embed).queue();
    }
}

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
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;
import java.io.IOException;

public class PlayerDeath implements Listener {

    JDA discord;
    PointsCore pointsCore;
    public PlayerDeath(PointsCore pointsCore, JDA discordWebhook) {
        this.pointsCore = pointsCore;
        this.discord = discordWebhook;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void playerDeath(PlayerDeathEvent event) {
        String playerName = event.getEntity().getDisplayName();
        String deathMessage = pointsCore.getConfig().getString("CustomDeathMessage."+ event.getEntity().getLastDamageCause().getCause(),
                playerName+ Message.format(pointsCore,event.getEntity()," &#17fb04h&#31f521a&#4bee3es &#65e85bd&#80e177i&#9adb94e&#b4d4b1d&#cecece."));

        if(pointsCore.getConfig().getBoolean("CustomMessages", true));
            event.setDeathMessage(Message.format(pointsCore,event.getEntity(), deathMessage.replaceAll(":player:" , playerName)));


        if(pointsCore.getConfig().getBoolean("Discord.Enabled", true)) {
            if(pointsCore.getConfig().getBoolean("CustomMessages", true)) {
                createEmbed(playerName+" died.", playerName+" met their maker",
                        ChatColor.stripColor(Message.format(pointsCore,event.getEntity(), deathMessage.replaceAll(":player:" , playerName))),
                        new Color(219, 70, 39));
                return;
            }
            createEmbed(playerName+" died.", playerName+" met their maker",ChatColor.stripColor(event.getEntity().getDisplayName()) + " has died of "+event.getDeathMessage(),
                    new Color(219, 70, 39));

        }
    }


    private void createEmbed(String title, String message, String fieldText, Color color) {
        long channelID = pointsCore.getConfig().getLong("Discord.ChannelID", 0);
        MessageEmbed embed = new EmbedBuilder().setTitle(title).setDescription(message).addField("Died Of: ", fieldText, true).setColor(color).build();
        TextChannel textChannel = discord.getTextChannelById(channelID);
        textChannel.sendMessageEmbeds(embed).queue();
    }
}

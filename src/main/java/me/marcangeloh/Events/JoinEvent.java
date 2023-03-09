package me.marcangeloh.Events;

import me.marcangeloh.API.Events.PlayerEquipArmorEvent;
import me.marcangeloh.API.Util.GeneralUtil.DiscordWebhook;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.io.IOException;

public class JoinEvent implements Listener {
    PointsCore pointsCore;
    JDA discord;
    private final boolean latest;
    public JoinEvent(PointsCore pointsCore, JDA discordWebhook, boolean latest) {
        this.pointsCore = pointsCore;
        this.discord = discordWebhook;
        this.latest = latest;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void playerJoinEvent(PlayerJoinEvent joinEvent) {
        pointsCore.loadPlayerData(joinEvent.getPlayer());
        if(!latest) {
            if(joinEvent.getPlayer().isOp()) {
                new Message().sendClickableLinkText(joinEvent.getPlayer(), "&a&lPoints&2&lCore &r&7has a new &a&nupdate&r &7available.",
                        "https://www.spigotmc.org/resources/pointscore.83263/",
                        "&7Click &a&nhere&r &7to download the latest version.");
            }
        }

        Player player = joinEvent.getPlayer();
        if(pointsCore.getConfig().getBoolean("CustomMessages", true))
            joinEvent.setJoinMessage(Message.format(pointsCore,player, pointsCore.getConfig().getString("CustomJoinMessage", player.getDisplayName() + " &#17fb04d&#21f90fr&#2af619o&#34f424p&#3ef22fp&#47ef39e&#51ed44d &#5aea4ei&#64e859n&#6ee664t&#77e36eo &#81e179t&#8bdf84h&#94dc8ee &#9eda99w&#a7d7a3o&#b1d5aer&#bbd3b9l&#c4d0c3d&#cecece.").replaceAll(":player:", player.getDisplayName())));

        if(pointsCore.getConfig().getBoolean("Discord.Enabled", true)) {
            int online = Bukkit.getOnlinePlayers().size();
            int total = Bukkit.getServer().getMaxPlayers();

            if(pointsCore.getConfig().getBoolean("CustomMessages", true)) {
                createEmbed("Player joined the Server!",ChatColor.stripColor(Message.format(pointsCore,joinEvent.getPlayer(), player.getDisplayName() + " &#17fb04d&#21f90fr&#2af619o&#34f424p&#3ef22fp&#47ef39e&#51ed44d &#5aea4ei&#64e859n&#6ee664t&#77e36eo &#81e179t&#8bdf84h&#94dc8ee &#9eda99w&#a7d7a3o&#b1d5aer&#bbd3b9l&#c4d0c3d&#cecece.").replaceAll(":player:", player.getDisplayName())),
                        "{"+online +" / "+total+"}",
                        new Color(3, 255, 0));
            } else {
                createEmbed("Player joined the server!", ChatColor.stripColor(player.getDisplayName()) + " has joined the server.","{"+online +" / "+total+"}",
                        new Color(3, 255, 0));
            }
        }

        for(ItemStack armor: player.getInventory().getArmorContents()) {
            if(armor == null)
                continue;

            PlayerEquipArmorEvent playerEquipArmorEvent = new PlayerEquipArmorEvent(player, armor);
            Bukkit.getPluginManager().callEvent(playerEquipArmorEvent);
        }
    }

    private void createEmbed(String title, String message, String fieldText, Color color) {
        long channelID = pointsCore.getConfig().getLong("Discord.ChannelID", 0);
        MessageEmbed embed = new EmbedBuilder().setTitle(title).setDescription(message).addField("Online players: ", fieldText, true).setColor(color).build();
        TextChannel textChannel = discord.getTextChannelById(channelID);
        textChannel.sendMessageEmbeds(embed).queue();
    }
}

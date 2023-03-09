package me.marcangeloh.Events.DiscordEvents;

import me.marcangeloh.PointsCore;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordChatHandler extends ListenerAdapter {
    PointsCore pointsCore;
    JDA jda;

    public DiscordChatHandler(PointsCore pointsCore, JDA jda) {
        this.pointsCore = pointsCore;
        this.jda = jda;
        this.jda.addEventListener(this);
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot() || event.isWebhookMessage())return;

        String message = event.getMessage().getContentRaw();
        User user = event.getAuthor();
        Bukkit.broadcastMessage("§a["+user.getName()+"#"+user.getDiscriminator()+"]: §e"+message);
    }
}

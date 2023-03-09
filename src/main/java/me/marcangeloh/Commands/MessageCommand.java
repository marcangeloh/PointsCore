package me.marcangeloh.Commands;

import me.clip.placeholderapi.PlaceholderAPI;
import me.marcangeloh.API.Util.GeneralUtil.GeneralUtil;
import me.marcangeloh.API.Util.TeleportUtil.HashMapUtil;
import me.marcangeloh.API.Util.GeneralUtil.Message;
import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MessageCommand implements CommandExecutor {

    HashMapUtil hashMapUtil;
    private PointsCore pointsCore;


    public MessageCommand(PointsCore pointsCore, HashMapUtil hashMapUtil) {
        this.pointsCore = pointsCore;
        this.hashMapUtil = hashMapUtil;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!label.equalsIgnoreCase("msg") && !label.equalsIgnoreCase("message") && !label.equalsIgnoreCase("r")
            && !label.equalsIgnoreCase("reply")) {
            return false;
        }

        if(!GeneralUtil.hasPermission(sender, "msg")) {
            return true;
        }
        if(!label.equalsIgnoreCase("msg") && !label.equalsIgnoreCase("message") && args.length < 2) {
            Message.errorMessage("Incorrect command usage, command is: \n/msg <player> <message>",sender);
            return true;
        }

        if(!(sender instanceof Player)) {
            Message.errorMessage("You must be a player to use this comand.", sender);
            return true;
        }

        Player player = (Player) sender;
        Player msgPlayer;

        if(!hashMapUtil.lastMessaged.containsKey(player) && label.equalsIgnoreCase("r")) {
            Message.errorMessage("There is no one to reply to.", sender);
            return true;
        }

        if(label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("message")) {
            msgPlayer = Bukkit.getPlayer(args[0]);
            hashMapUtil.lastMessaged.putIfAbsent(player, msgPlayer);
            hashMapUtil.lastMessaged.putIfAbsent(msgPlayer, player);

        } else if(label.equalsIgnoreCase("r") || label.equalsIgnoreCase("reply")) {
            msgPlayer = hashMapUtil.lastMessaged.get(player);
        } else {
            return false;
        }
        hashMapUtil.lastMessaged.replace(player, msgPlayer);
        hashMapUtil.lastMessaged.replace(msgPlayer, player);

        String builder = "";

        int counter = 0;

        for (String arg: args
             ) {
            if(counter == 0 && !label.equalsIgnoreCase("r") && !label.equalsIgnoreCase("reply")) {
                counter++;
                continue;
            }
            builder += " " + arg;
        }

        builder = builder.replaceFirst(" ", "");
        String messageSent = pointsCore.getConfig().getString("MSG.PrefixSent","&e[You -> :player:]&r ") + builder;
        String messageReceived = pointsCore.getConfig().getString("MSG.PrefixReceived", "&e[:player: -> You]&r ") + builder;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            messageSent = PlaceholderAPI.setPlaceholders(player, messageSent);
            messageReceived = PlaceholderAPI.setPlaceholders(msgPlayer, messageReceived);
        }
        messageSent = messageSent.replaceAll(":player:", msgPlayer.getDisplayName());
        messageReceived = messageReceived.replaceAll(":player:", player.getDisplayName());


        player.sendMessage(Message.format(pointsCore,player,messageSent));
        msgPlayer.sendMessage(Message.format(pointsCore,player,messageReceived));
        return true;
    }
}

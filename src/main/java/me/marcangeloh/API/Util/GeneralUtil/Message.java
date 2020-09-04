package me.marcangeloh.API.Util.GeneralUtil;

import me.marcangeloh.PointsCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {
    /**
     * Sends an error message to the command sender
     * @param message the message to send
     * @param sender the person to send a message to
     */
    public static void errorMessage(String message, CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + message);
    }

    /**
     * Sends an error message to the server
     * @param message the message to send
     */
    public static void debugMessage(String message, DebugIntensity intensity) {
        if(isDebugIntenseEnough(intensity)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "PointsCoreDebug: " + ChatColor.RED + message);
        }
    }

    /**
     * Checks if the intensity is enough to send a message
     * @param intensity The intensity of the message
     * @return wether or not to send a message
     */
    private static boolean isDebugIntenseEnough(DebugIntensity intensity) {
        if(intensity.equals(DebugIntensity.INTENSE) && (PointsCore.serverDebugIntensity.equals(DebugIntensity.INTENSE))) {
            return true;
        } else if(intensity.equals(DebugIntensity.LIGHT) && (PointsCore.serverDebugIntensity.equals(DebugIntensity.INTENSE)||PointsCore.serverDebugIntensity.equals(DebugIntensity.LIGHT))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sends an error message to the player
     * @param message the message to send
     * @param sender the player to send it to
     */
    public static void errorMessage(String message, Player sender) {
        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "PointsCore: " + ChatColor.RED + message);
    }

    /**
     * Notifies a player with the message
     * @param notification The notification to send
     * @param player The player to send it to
     */
    public static void notifyMessage(String notification, Player player) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD +"PointsCore: " + ChatColor.GOLD + notification);
    }

    /**
     * Notifies the command sender with a message
     * @param notification the message to send
     * @param player the player to notify
     */
    public static void notifyMessage(String notification, CommandSender player) {
        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD +"PointsCore: " + ChatColor.GOLD + notification);
    }
}

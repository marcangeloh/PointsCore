package me.marcangeloh.API.Util.GeneralUtil;

import me.clip.placeholderapi.PlaceholderAPI;
import me.marcangeloh.PointsCore;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {

    private static String branding = "&#17fb04P&#2bf61ao&#40f131i&#54ec47n&#68e75et&#7de274s&#91dd8bC&#a5d8a1o&#bad3b8r&#cececee: ";

    /**
     * Sends an error message to the command sender
     * @param message the message to send
     * @param sender the person to send a message to
     */
    public static void errorMessage(String message, CommandSender sender) {
        sender.sendMessage( formatNoPAPI(branding) + ChatColor.RED + message);
    }

    /**
     * Sends an error message to the server
     * @param message the message to send
     */
    public static void debugMessage(String message, DebugIntensity intensity) {
        if(isDebugIntenseEnough(intensity)) {
            Bukkit.getConsoleSender().sendMessage(formatNoPAPI(branding)+ ChatColor.RED + message);
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
        sender.sendMessage(format(sender, branding) + ChatColor.RED + message);
    }

    /**
     * Notifies a player with the message
     * @param notification The notification to send
     * @param player The player to send it to
     */
    public static void notifyMessage(String notification, Player player) {
        player.sendMessage(format(player,branding) + ChatColor.GOLD + notification);
    }

    /**
     * Notifies the command sender with a message
     * @param notification the message to send
     * @param player the player to notify
     */
    public static void notifyMessage(String notification, CommandSender player) {
        player.sendMessage(formatNoPAPI(branding) + ChatColor.GOLD + notification);
    }

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String format(Player player, String s) {
        if(PointsCore.plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            return formatNoPAPI(PlaceholderAPI.setPlaceholders(player, s));
        } else {
            return formatNoPAPI(s);
        }
    }

    public static String formatNoPAPI(String s) {
        if(!s.contains("#"))
            return ChatColor.translateAlternateColorCodes('§',ChatColor.translateAlternateColorCodes('&', s));


        String cleaned = s;
        Matcher matcher = pattern.matcher(cleaned);
        while (matcher.find()) {
            String color = cleaned.substring(matcher.start(), matcher.end());
            cleaned = cleaned.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            matcher = pattern.matcher(cleaned);
        }

        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('§', net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', cleaned)).replaceAll("&", "");
    }

    public static String formatLinGradient(String str, Color from, Color to) {
        final double[] red = interpolateLin(from.getRed(), to.getRed(), str.length());
        final double[] green = interpolateLin(from.getGreen(), to.getGreen(), str.length());
        final double[] blue = interpolateLin(from.getBlue(), to.getBlue(), str.length());

        final StringBuilder builder = new StringBuilder();

        // create a string that matches the input-string but has
        // the different color applied to each char
        for (int i = 0; i < str.length(); i++) {
            builder.append(net.md_5.bungee.api.ChatColor.of(new Color(
                            (int) Math.round(red[i]),
                            (int) Math.round(green[i]),
                            (int) Math.round(blue[i]))))
                    .append(str.charAt(i));
        }

        return builder.toString();
    }

    private static double[] interpolateLin(double from, double to, int max) {
        final double[] res = new double[max];
        for (int i = 0; i < max; i++) {
            res[i] = from + i * ((to - from) / (max - 1));
        }
        return res;
    }

    public void hologramAtLocation(org.bukkit.Location location, String text) {
        org.bukkit.entity.ArmorStand as = (org.bukkit.entity.ArmorStand) location.getWorld().spawnEntity(location, org.bukkit.entity.EntityType.ARMOR_STAND); //Spawn the ArmorStand

        as.setGravity(false); //Make sure it doesn't fall
        as.setCanPickupItems(false); //I'm not sure what happens if you leave this as it is, but you might as well disable it
        as.setCustomName(text); //Set this to the text you want
        as.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
        as.setVisible(false); //Makes the ArmorStand invisible
    }

    public void sendHoverableText(Player player, String text, String hover) {
        TextComponent msg = new TextComponent(format(player,text));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format(player, hover)).create()));
        player.spigot().sendMessage(msg);
    }

    public void sendClickableCommandText(Player player, String text, String command, String hover) {
        TextComponent msg = new TextComponent(format(player,text));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format(player, hover)).create()));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        player.spigot().sendMessage(msg);
    }

    public void sendClickableLinkText(Player player, String text, String url, String hover) {
        TextComponent msg = new TextComponent();
        msg.setText(format(player,text));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(format(player, hover)).create()));
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        player.spigot().sendMessage(msg);
    }

}

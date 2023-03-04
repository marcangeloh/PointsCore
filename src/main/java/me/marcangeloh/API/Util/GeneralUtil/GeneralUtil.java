package me.marcangeloh.API.Util.GeneralUtil;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;

public class GeneralUtil {

    public static void spawnRedstoneParticle(Location location, int amount, int r, int g, int b, float size) {
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(r, g, b), size);
        location.getWorld().spawnParticle(Particle.REDSTONE, location, amount, dustOptions);
    }

    public static boolean hasPermission(CommandSender sender, String permission) {
        if(sender.hasPermission("pointscore."+permission) ||
            sender.hasPermission("pointscore.*") ||
            sender.hasPermission("*")||
            sender.isOp()) {
            return true;
        }
        Message.errorMessage("You do not have permission to use this command.", sender);
        return false;
    }



}